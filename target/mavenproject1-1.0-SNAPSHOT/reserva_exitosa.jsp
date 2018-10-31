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
  %>
  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3">¡Bicicleta reservada con éxito!</span>
  </h1>  
  <div class="col-lg-6 col-md-6 col-xs-6 col-centered well form-text">
    <div class="container">
      <p align="center" style="font-size: 1.5rem;"><b><u>CÓDIGO DE RESERVA:</u> <%=session.getAttribute("numeroReserva").toString()%></b></p>
      <hr />
      <p align="justify">¡Muy bien! Ahora solo te queda acercarte a nuestro local el día <b><%=session.getAttribute("fechaReserva").toString()%></b> a partir 
        de las <b><%=session.getAttribute("desdeReserva").toString()%>:00</b> hs. con tu <b>D.N.I.</b>, <b>CARNET DE CONDUCIR</b> o <b>PASAPORTE</b> 
        y con el <b>Código de Reserva</b> para retirar tu bicicleta. ¡Te esperamos!</p>      
      <hr />
      <div class="row">
        <div class="col-lg-12 col-md-12 col-xs-12">
          <button type="button" onclick="misreservas();" class="btn btn-nuevo col-lg-12 col-md-6 col-xs-6" style="margin-top:0.5rem;">¡Entendido!</button>
        </div>
      </div>
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
    function misreservas() {
        window.open("index.jsp","_self");
      }
  </script>
</body>
