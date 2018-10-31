<%@page import="java.text.DecimalFormat,java.text.DateFormat,Entidades.*,Negocio.*,java.util.ArrayList"%>
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
      if (usrActual.isAdm()) {%>
  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3">administrador de reservas</span>
  </h1>

  <div class="col-lg-10 col-centered well" id="tablareservas">

    <div style="overflow-x:auto;">
      <button class="tablink" onclick="openPage('pendientes', this)" id="defaultOpen">Pendientes</button>
      <button class="tablink" onclick="openPage('encurso', this)">En Curso</button>
      <button class="tablink" onclick="openPage('finalizadas', this)">Finalizadas</button>
    </div>

    <!-- RESERVAS PENDIENTES -->
    <div id="pendientes" class="tabcontent">
      <div align="right">
        <form method="POST" action="Reservar">
          <button type="submit" class="btn btn-nuevo" style="margin-bottom:0.5rem" title="Nueva Reserva"><span class="fa fa-plus-square"></span></button>
        </form>
      </div>
      <div style="overflow-x:auto;">
        <table class="table display" id="rpendientes">
          <thead style="color: #fff;background-color: #373a3c;">
            <tr align="center">
              <th>ID</th>
              <th>BICICLETA</th>
              <th>USUARIO</th>
              <th>D.N.I.</th>
              <th>INICIO</th>
              <th>FIN</th>
              <th></th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>            
            <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
              DecimalFormat df2 = new DecimalFormat("0.00");
              ArrayList<Reservas> reservas_activas = new ControladorReservas().getReservasPendientes();
              if (reservas_activas.size() > 0) {
                for (Reservas r : reservas_activas) {%>
            <tr align="center" >
              <%String bicicleta = r.getBici().getModelo().getTipo().getNombre() + " - " + r.getBici().getModelo().getNombre() + " - " + r.getBici().getPatente();
                String reserva = "R" + String.format("%5s", r.getId()).replace(' ', '0');%>
              <td style="vertical-align:middle;font-size: 1rem;"><%=reserva%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%=bicicleta%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= r.getCliente().getUsuario()%></td>   
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= r.getCliente().getDocumento()%></td>   
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= df.format(r.getFechaInicioP())%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= df.format(r.getFechaFinP())%></td>
              <td style="vertical-align:middle;">
                <button class="btn btn-reset" title="Ver Reserva" 
                        onclick="verReserva('<%=r.getEstado()%>', '<%=r.getEstado().getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioP())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">
                  <span class="fa fa-eye"></span>
                </button>
              </td>
              <td style="vertical-align:middle;">
                <button class="btn btn-nuevo" title="Iniciar Reserva" 
                        onclick="iniciarReserva('<%=r.getEstado()%>', '<%=r.getEstado().getId()%>', '<%=r.getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioP())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">
                  <span class="fa fa-play"></span>
                </button>
              </td>
              <td style="vertical-align:middle">
                <button class="btn btn-eliminar" title="Eliminar Reserva"
                        onclick="eliminarReserva('<%=r.getId()%>', '<%=r.getEstado()%>', '<%=r.getEstado().getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioP())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">
                  <span class="fa fa-trash-o"></span>
                </button>
              </td>
            </tr>        
            <% }
              }%>
          </tbody>
        </table>
      </div>
    </div>

    <!-- RESERVAS EN CURSO -->
    <div id="encurso" class="tabcontent">
      <div style="overflow-x:auto;">
        <table class="table display" id="rencurso">
          <thead style="color: #fff;background-color: #373a3c;">
            <tr align="center">
              <th>ID</th>
              <th>BICICLETA</th>
              <th>USUARIO</th>
              <th>INICIO</th>
              <th>FIN</th>
              <th></th>
              <th></th>
              <th></th>
            </tr>
          </thead>
          <tbody>            
            <% ArrayList<Reservas> reservas_curso = new ControladorReservas().getReservasEnCurso();
              if (reservas_curso.size() > 0) {
                for (Reservas r : reservas_curso) {%>
            <tr align="center" >
              <%String bicicleta = r.getBici().getModelo().getTipo().getNombre() + " - " + r.getBici().getModelo().getNombre() + " - " + r.getBici().getPatente();
                String reserva = "R" + String.format("%5s", r.getId()).replace(' ', '0');%>
              <td style="vertical-align:middle;font-size: 1rem;"><%= bicicleta%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= reserva%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= r.getCliente().getUsuario()%></td>          
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= df.format(r.getFechaInicioR())%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= df.format(r.getFechaFinP())%></td>
              <td style="vertical-align:middle;">
                <button class="btn btn-reset" title="Ver Reserva" 
                        onclick="verReserva('<%=r.getEstado()%>', '<%=r.getEstado().getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioR())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">
                  <span class="fa fa-eye"></span>
                </button>
              </td>
              <td style="vertical-align:middle;">
                <button class="btn btn-mecanico" title="Finalizar por fallas"
                        onclick="fallasReserva('<%=df2.format(r.getBici().getKmDsdMantenimiento())%>', '<%=r.getEstado()%>', '<%=r.getEstado().getId()%>', '<%=r.getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioR())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">
                  <span class="fa fa-exclamation-triangle"></span>
                </button>
              </td>
              <td style="vertical-align:middle;">
                <button class="btn btn-detener" title="Finalizar correctamente"
                        onclick="finalizarReserva('<%=df2.format(r.getBici().getKmDsdMantenimiento())%>', '<%=r.getEstado()%>', '<%=r.getEstado().getId()%>', '<%=r.getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioR())%>', '<%= df.format(r.getFechaFinP())%>', '<%= df2.format(r.getImporte())%>')">
                  <span class="fa fa-stop"></span>
                </button>
              </td>
            </tr>        
            <% }
              }%>
          </tbody>
        </table>
      </div>
    </div>      

    <!-- RESERVAS FINALIZADAS -->
    <div id="finalizadas" class="tabcontent">
      <div class="form-group">
        <button type="button" onclick="vaciarFiltro()" class="label label-cliente" title="Todos">TODOS</button>
        <button type="button" onclick="buscarFinalizadas()" class="label label-success" title="Reservas finalizadas sin fallas">FINALIZADAS</button>
        <button type="button" onclick="buscarEliminadas()" class="label label-danger" title="Reservas eliminadas por los administradores">ELIMINADAS</button>
        <button type="button" onclick="buscarCanceladas()" class="label label-danger" title="Reservas canceladas por los usuarios">CANCELADAS</button>
        <button type="button" onclick="buscarFallas()" class="label label-mecanico" title="Reservas finalizadas con fallas">FALLAS</button>
        <button type="button" onclick="buscarDesconocidos()" class="label label-desconocido" title="No identificados">DESCONOCIDOS</button>        
      </div>
      <hr/>
      <div style="overflow-x:auto;">
        <table class="table display" id="rfinalizadas">
          <thead style="color: #fff;background-color: #373a3c;">
            <tr align="center">
              <th>ID</th>
              <th>BICICLETA</th>
              <th>USUARIO</th>
              <th>INICIO</th>
              <th>FIN</th>
              <th>KM TOTALES</th>
              <th>ESTADO</th>
              <th></th>
            </tr>
          </thead>
          <tbody>            
            <% ArrayList<Reservas> reservas_fin = new ControladorReservas().getReservasFinalizados();
              if (reservas_fin.size() > 0) {
                for (Reservas r : reservas_fin) {
                  String bicicleta = r.getBici().getModelo().getTipo().getNombre() + " - " + r.getBici().getModelo().getNombre() + " - " + r.getBici().getPatente();
                  String reserva = "R" + String.format("%5s", r.getId()).replace(' ', '0');%>
            <tr align="center" >
              <td style="vertical-align:middle;font-size: 1rem;"><%= reserva%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= bicicleta%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= r.getCliente().getUsuario()%></td>          
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= df.format(r.getFechaInicioR())%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= df.format(r.getFechaFinR())%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;"><%= df2.format(r.getKmRecorridos())%></td>
              <td style="vertical-align:middle;font-size: 0.8rem;">
                <span
                  <% if (r.getEstado() == EstadosReserva.FINALIZADA) { %>
                  class="label label-success"
                  <% } else if (r.getEstado() == EstadosReserva.CANCELADA) { %>
                  class="label label-danger"
                  <% } else if (r.getEstado() == EstadosReserva.ELIMINADA) { %>
                  class="label label-danger"
                  <% } else if (r.getEstado() == EstadosReserva.FALLAS) { %>
                  class="label label-mecanico"
                  <% } else { %>
                  class="label label-desconocido"
                  <% }%>><%=r.getEstado()%>
                </span>
              </td>
              <td style="vertical-align:middle;font-size: 0.8rem;">
                <button class="btn btn-reset" title="Ver Reserva" 
                        onclick="verReserva('<%=r.getEstado()%>', '<%=r.getEstado().getId()%>', '<%=r.getCliente().getUsuario() + " (" + r.getCliente().getApynom().trim() + ")"%>', '<%=reserva%>', '<%=bicicleta%>', '<%=r.getBici().getModelo().getUrl1()%>', '<%= df.format(r.getFechaInicioR())%>', '<%= df.format(r.getFechaFinR())%>', '<%= df2.format(r.getImporte())%>')">
                  <span class="fa fa-eye"></span>
                </button>
              </td>
            </tr>        
            <% }
              }%>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <!-- VER RESERVA -->
  <div class="col-lg-8 col-md-8 col-xs-8 col-centered well form-text container" id="verReserva" style="display: none">
    <form class="form-text" method="POST">
      <input type="hidden" name="id" id="id">
      <div class="form-group float-sm-right">
        <span id="cruzMR" class="fa fa-close" style="right: 50px"></span>      
      </div>
      <div>
        <p style="margin-bottom: 0.1rem;font-size: 18px; text-align: center"><label id="estadoR"></label></p>
        <p style="margin-bottom: 0.2rem;font-size: 20px; text-align: center"><b><u>Código de reserva:</u> <label id="idR" ></label></b></p>        
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
          <p style="margin-top: 0.4rem; margin-bottom: 0.2rem;font-size: 16px"><b><u>Para:</u></b> <label id="usrR"></label></p>
          <p style="margin-bottom: 0.2rem;font-size: 16px"><b><u>Bicicleta reservada:</u></b> <label id="biciR"></label> </p>          
          <p style="margin-bottom: 0.2rem;font-size: 16px"><b><u>Día:</u></b> <label id="diaR"></label></p>
          <p style="margin-bottom: 0.2rem;font-size: 16px"><b><u>Turno:</u></b> <label id="turnoR"></label></p>
          <p style="margin-bottom: 0.2rem;font-size: 16px"><b><u>Precio final:</u> $ <label id="precioR"></label></b></p>          
        </div>
      </div>
      <hr />
      <div id="fin" style="display: none;">
        <div class="row">
          <div class="col-sm-4 form-group">
            <input type="text" name="fec_fin" id="fec_fin" placeholder="Fecha Fin" title="Fecha Fin" class="form-control" readonly="true">
          </div>
          <div class="col-sm-4 form-group">
            <input type="text" name="hr_fin" id="hr_fin" placeholder="Hora Fin" title="Hora Fin" class="form-control" readonly="true">
          </div>
          <div class="col-sm-4 form-group">
            <input type="number" step="any" name="km_fin" id="km_fin" placeholder="KM Fin" title="KM Fin" class="form-control" autofocus="true">
          </div> 
        </div>
        <div class="col-form form-group">
          <textarea name="obs" id="obs" maxlength="100" placeholder="Observacion" title="Observacion"  class="form-control" autofocus="true" style="min-height: 70px;"></textarea>
        </div>
        <hr />  
      </div>
      <div class="row">
        <div class="col-lg-12 col-md-12 col-xs-12">
          <label style="display: none; text-align: center" class="error" id="msj"></label>
          <label style="display: none; text-align: center" class="error" id="msjFallas"></label>
          <button type="button" onclick="closeMR()" id="verR" class="btn btn-primary col-lg-12 col-md-6 col-xs-6" style="margin-top:0.5rem;">Volver</button>
          <input type="submit" id="iniciarR" class="btn btn-lg btn-nuevo col-lg-12 col-md-6 col-xs-6" style="display: none" value="Iniciar Reserva" onclick="javascript:form.action = 'IniciarReserva';">
          <input type="submit" id="eliminarR" class="btn btn-lg btn-eliminar col-lg-12 col-md-6 col-xs-6" style="display: none" value="Eliminar Reserva" onclick="javascript:form.action = 'EliminarReserva';">
          <input type="submit" id="fallasR" class="btn btn-lg btn-mecanico col-lg-12 col-md-6 col-xs-6" style="display: none" value="Finalar Reserva por fallas" onclick="javascript:form.action = 'FallasReserva';">
          <input type="submit" id="finalizarR" class="btn btn-lg btn-detener col-lg-12 col-md-6 col-xs-6" style="display: none" value="Finalizar Reserva correctamente" onclick="javascript:form.action = 'FinalizarReserva';">
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
  <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>    
  <script src="js/abmres.js"></script>
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
              var table = $('#rfinalizadas').DataTable();
              table.search("").draw();
            }

            function buscarFinalizadas() {
              var table = $('#rfinalizadas').DataTable();
              table.search("FINALIZADA").draw();
            }

            function buscarEliminadas() {
              var table = $('#rfinalizadas').DataTable();
              table.search("ELIMINADA").draw();
            }

            function buscarCanceladas() {
              var table = $('#rfinalizadas').DataTable();
              table.search("CANCELADA").draw();
            }

            function buscarFallas() {
              var table = $('#rfinalizadas').DataTable();
              table.search("FALLAS").draw();
            }

            function buscarDesconocidos() {
              var table = $('#rfinalizadas').DataTable();
              table.search("DESCONOCIDO").draw();
            }
            
            
            
  </script>
</body>
