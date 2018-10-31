<%@page import="Negocio.ControladorUsuarios"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="Entidades.Modelos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Entidades.Bicicletas"%>
<%@page import="Negocio.ControladorBicicletas"%>
<%@page import="Entidades.TiposBicicleta"%>
<!DOCTYPE html>

<head>

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>BiciAmiga - Rosario</title>

  <!-- Bootstrap core CSS -->
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <!-- Custom fonts for this template -->
  <link href="https://fonts.googleapis.com/css?family=Raleway:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Lora:400,400i,700,700i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="css/business-casual.min.css" rel="stylesheet">
  <link rel="icon" href="img/favicon.ico" type="image/x-icon">
  <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon"> 


</head>

<body>
  <!-- Navigation -->
  <%@include file="nav_bar.jsp"%>

  <%DecimalFormat df2 = new DecimalFormat("0.00");
    if (session.getAttribute("Usuario") != null) {
      Usuarios usrActual = (Usuarios) session.getAttribute("Usuario");
      if ((usrActual.isAdm()) || (!usrActual.isAdm() && !usrActual.isMecanico())) {
        Modelos modeloActual = (Modelos)session.getAttribute("modeloReserva");
  %>
  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3">Confirmar Reserva</span>
  </h1>  
  <div class="col-lg-8 col-md-8 col-xs-8 col-centered well form-text">
    <div class="container">
      <form method="POST" action="AltaReserva">
          <div class="row">
            <div class="col-lg-4 col-md-12 img-contenedor">         
            <a class="">
              <img class="img-fluid3 img-thumbnail2 imgid" style="" onclick="ampliar(this.src)" src="<%=modeloActual.getUrl1()%>" alt="<%= modeloActual.getNombre()%>">                
            </a>
          </div>
          <div class="span6"></div>
          <div class="col-lg-8 col-md-12">
            <p style="margin-top: 0.8rem; margin-bottom: 0.2rem;font-size: 18px"><b><u>Bicicleta a reservar:</u> <%=modeloActual.getTipo().getNombre() + " - " + modeloActual.getNombre()%></b></p>          
            <p style="margin-bottom: 0.2rem;font-size: 18px"><b><u>Para usuario:</u></b>  
              <%if(usrActual.isAdm()){%>
              <select class="form-control" style="display: inline; width: auto;" name="usuarios" id="usuarios" placeholder="Usuarios" title="Usuarios" required="true" >
                <% for (Usuarios u : new ControladorUsuarios().getUsuarios()) {%>
                <% if (u.isHabilitado() && !u.isAdm() && !u.isMecanico()) { %>
                  <option value="<%=u.getId()%>"><%=u.getUsuario().trim()+" ("+u.getApynom().trim()+")"%></option>
                <% } } %>
              </select>
              <% } else { %>
              <%=usrActual.getApynom().trim()+" ("+usrActual.getUsuario().trim()+")"%>
              <% } %>
            </p>
            <p style="margin-bottom: 0.2rem;font-size: 18px"><b><u>Fecha de Reserva:</u> <%=session.getAttribute("fechaReserva")%></b></p>          
            <% if(Boolean.valueOf(session.getAttribute("completoReserva").toString())){%>
              <p style="margin-bottom: 0.2rem;font-size: 18px"><b><u>Día completo:</u> De 9 a 21 hs.</b></p>
            <% } else { %>
              <p style="margin-bottom: 0.2rem;font-size: 18px"><b><u>Franja Horario:</u> <%=session.getAttribute("desdeReserva")%> a <%=session.getAttribute("hastaReserva")%> hs.</b></p>
            <% } %>
              <p style="margin-bottom: 0.2rem;font-size: 18px"><b><u>Precio final:</u> $ <%=df2.format(Double.valueOf(session.getAttribute("importeReserva").toString()))%></b></p>                    
          </div>
        </div>
        <hr />
        <div class="row">
          <div class="col-lg-6 col-md-6 col-xs-6">
            <button type="button" onclick="goBack()" class="btn btn-eliminar col-lg-12 col-md-6 col-xs-6" style="margin-top:0.5rem;">Cancelar</button>
          </div>
          <div class="col-lg-6 col-md-6 col-xs-6">
            <button type="submit" class="btn btn-nuevo col-lg-12 col-md-6 col-xs-6" style="margin-top:0.5rem;">Confirmar Reserva</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <% } else {
        response.sendRedirect("error.jsp");
      }
    } else {
      response.sendRedirect("login.jsp");
  }%>
  
  <div id="myModal" class="modal">
    <!-- The Close Button -->
    <span class="close">&times;</span>

    <!-- Modal Content (The Image) -->
    <img class="modal-content" id="img01">

    <!-- Modal Caption (Image Text) -->
    <div id="caption"></div>
  </div>
  
  <!-- Footer -->
  <%@include file="footer.jsp"%>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>    
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
  <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
  <script>
    var modal = document.getElementById('myModal');
                
    function ampliar(imagen){
      modal.style.display = "block";
      document.getElementById("img01").src = imagen;
    }

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() { 
      modal.style.display = "none";
    };
    
    function goBack() {
        window.history.back();
      }
  </script>
</body>
