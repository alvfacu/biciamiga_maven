<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat,Entidades.*,Negocio.*"%>
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
  <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
  <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon">
</head>

<body>
  <!-- Navigation -->
  <%@include file="nav_bar.jsp"%>

  <!-- Body -->

  <%if (session.getAttribute("Usuario") != null) {
      Usuarios usrActual = (Usuarios) session.getAttribute("Usuario");
      if (!usrActual.isAdm() && !usrActual.isMecanico()) {%>
  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3" id="titulo">historial de reservas</span>
  </h1>

  <div class="col-lg-10 col-centered well" id="tablamisreservas">

    <div style="overflow-x:auto;">
      <button class="tablink2" onclick="openPage('pendientes', this)" id="defaultOpen">Pendientes</button>
      <button class="tablink2" onclick="openPage('finalizadas', this)">Finalizadas</button>
    </div>

    <!-- PENDIENTES -->
    <div id="pendientes" class="tabcontent">
      <% if (usrActual.isHabilitado()) { %>
      <div align="right">
        <form method="POST" action="Reservar">
          <button type="submit" class="btn btn-nuevo margin05bottom" title="Nueva Reserva"><span class="fa fa-plus-square"></span></button>
        </form>
      </div>
      <% } else { %>
      <div align="center">
        <div class="errorReserva importante"><b>Usuario inhabilitado</b></div>
        <div class="errorReserva"><i>No tiene permitido realizar reservas. Cualquier comentario, comuníquese con nosotros.</i></div>
        <br>
      </div>
      <% } %>
      <div style="overflow-x:auto;">
        <table class="table display" id="rpendientesxusr">
          <thead class="encabezado">
            <tr align="center">
              <th>ID</th>
              <th>BICICLETA</th>                
              <th>INICIO</th>
              <th>FIN</th>
              <th>IMPORTE</th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>            
            <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
              DecimalFormat df2 = new DecimalFormat("0.00");
              ArrayList<Reservas> reservas_activas = new ControladorReservas().getReservasPendientesXUsr(usrActual);
              if (reservas_activas.size() > 0) {
                for (Reservas r : reservas_activas) {%>
            <tr align="center" >
              <%String bicicleta = r.getBici().getModelo().getTipo().getNombre() + " - " + r.getBici().getModelo().getNombre();
                String reserva = "R" + String.format("%5s", r.getId()).replace(' ', '0');%>
              <td class="col-principal"><%=reserva%></td>
              <td class="col-secundario"><%=bicicleta%></td>  
              <td class="col-secundario"><%= df.format(r.getFechaInicioP())%></td>
              <td class="col-secundario"><%= df.format(r.getFechaFinP())%></td>
              <td class="col-secundario"><%= df2.format(r.getImporte())%></td>
              <td class="vertical">
                <button class="btn btn-reset" title="Ver Reserva" 
                        onclick="verReserva('<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioP())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">                          
                  <span class="fa fa-eye"></span>
                </button>
              </td>
              <td class="vertical">
                <button class="btn btn-eliminar" title="Eliminar Reserva"
                        onclick="eliminarReserva('<%=r.getId()%>','<%=r.getCliente().getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioP())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">                          
                  <span class="fa fa-remove"></span>
                </button>
              </td>
            </tr>        
            <% }
              }%>
          </tbody>
        </table>
      </div>
    </div>

    <!-- FINALIZADAS -->
    <div id="finalizadas" class="tabcontent">
      <div class="form-group">
        <button type="button" onclick="vaciarFiltro()" class="label label-cliente" title="Todos">TODOS</button>
        <button type="button" onclick="buscarFinalizadas()" class="label label-success" title="Reservas finalizadas">FINALIZADAS</button>
        <button type="button" onclick="buscarEliminadas()" class="label label-danger" title="Reservas canceladas">CANCELADAS</button>
      </div>
      <div style="overflow-x:auto;">
        <table class="table display" id="rfinalizadasxusr">
          <thead class="encabezado">
            <tr align="center">
              <th>ID</th>
              <th>BICICLETA</th>                
              <th>INICIO</th>
              <th>FIN</th>
              <th>IMPORTE</th>
              <th>ESTADO</th>
            </tr>
          </thead>
          <tbody>            
            <%ArrayList<Reservas> reservas_finalizadas = new ControladorReservas().getReservasFinalizadasXUsr(usrActual);
              if (reservas_finalizadas.size() > 0) {
                for (Reservas r : reservas_finalizadas) {%>
            <tr align="center" >
              <%String bicicleta = r.getBici().getModelo().getTipo().getNombre() + " - " + r.getBici().getModelo().getNombre();
                String reserva = "R" + String.format("%5s", r.getId()).replace(' ', '0');%>
              <td class="col-principal"><%=reserva%></td>
              <td class="col-secundario"><%=bicicleta%></td>  
              <td class="col-secundario"><%= df.format(r.getFechaInicioR())%></td>
              <td class="col-secundario"><%= df.format(r.getFechaFinR())%></td>
              <td class="col-secundario"><%= df2.format(r.getImporte())%></td>
              <td class="col-secundario">
                <span
                  <% if (r.getEstado() == EstadosReserva.FINALIZADA) { %>
                  class="label label-success"
                  <% } else if (r.getEstado() == EstadosReserva.CANCELADA) { %>
                  class="label label-danger"
                  <% } else { %>
                  class="label label-desconocido"
                  <% }%>><%=r.getEstado()%>
                </span>
              </td>
            </tr>        
            <% }
              }%>
          </tbody>
        </table>
      </div>
    </div>            
  </div>

  <div class="col-lg-8 col-md-8 col-xs-8 col-centered well form-text container" id="verReserva" style="display: none">
    <form class="form-text" method="POST">
      <input type="hidden" name="id" id="id">
      <input type="hidden" name="idUsr" id="idUsr">
      <div class="form-group float-sm-right">
        <span id="cruzMR" class="fa fa-close cruz"></span>      
      </div>
      <div>
        <p class="codReserva"><b><u>Código de reserva:</u> <label id="idR" ></label> </b></p>
      </div>
      <hr/>
      <div class="row">      
        <div class="col-lg-4 col-md-12 img-contenedor">         
          <a class="">
            <img class="img-fluid3 img-thumbnail2 imgid" id="imgR" onclick="ampliar(this.src)">                
          </a>
        </div>
        <div class="span6"></div>
        <div class="col-lg-8 col-md-12">        
          <p class="lblPara" ><b><u>Para:</u></b> <label id="usrR"></label></p>
          <p class="lblVisualizacion"><b><u>Bicicleta reservada:</u> <label id="biciR"></label> </b></p>          
          <p class="lblVisualizacion"><b><u>Turno:</u> <label id="turnoR"></label></b></p>
          <p class="lblVisualizacion"><b><u>Precio final:</u> $ <label id="precioR"></label></b></p>                    
        </div>
      </div>
      <hr />
      <div class="row margin1top">
        <div class="col-lg-12 col-md-12 col-xs-12">
          <button type="button" onclick="closeMR()" id="verMR" class="btn btn-primary col-lg-12 col-md-6 col-xs-6 margin05top">Volver</button>
          <input type="submit" id="eliminarMR" class="btn btn-lg btn-eliminar col-lg-12 col-md-6 col-xs-6" style="display: none" value="Eliminar Reserva" onclick="javascript:form.action = 'CancelarReserva';">
        </div>
      </div>
    </form>
  </div>

  <div id="myModal" class="modal">
    <!-- The Close Button -->
    <span class="close">&times;</span>

    <!-- Modal Content (The Image) -->
    <img class="modal-content" id="img01">

    <!-- Modal Caption (Image Text) -->
    <div id="caption"></div>
  </div>

  <% } else {
        response.sendRedirect("error.jsp");
      }
    } else {
      response.sendRedirect("login.jsp");
    }%>

  <!-- Footer -->
  <%@include file="footer.jsp"%>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>    
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
  <script src="js/misreservas.js"></script>
  <script>
          var modal = document.getElementById('myModal');

          function ampliar(imagen) {
            modal.style.display = "block";
            document.getElementById("img01").src = imagen;
          }

          // Get the <span> element that closes the modal
          var span = document.getElementsByClassName("close")[0];

          // When the user clicks on <span> (x), close the modal
          span.onclick = function () {
            modal.style.display = "none";
          };

          function goBack() {
            window.history.back();
          }
          
          function vaciarFiltro() {
              var table = $('#rfinalizadasxusr').DataTable();
              table.search("").draw();
            }

            function buscarFinalizadas() {
              var table = $('#rfinalizadasxusr').DataTable();
              table.search("FINALIZADA").draw();
            }

            function buscarEliminadas() {
              var table = $('#rfinalizadasxusr').DataTable();
              table.search("CANCELADA").draw();
            }
  </script>
</body>
