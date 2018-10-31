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
      int id = 0;
      if (usrActual.isMecanico()) {
        ArrayList<Bicicletas> bicicletas = new ControladorBicicletas().getBicicletas();
        ArrayList<Mantenimientos> mantenimientos = null;
        if (request.getParameter("idBici") != null) {
          try {
            id = Integer.valueOf(request.getParameter("idBici"));
            Bicicletas b = new ControladorBicicletas().getBicicleta(id);
            if (b != null) {
              mantenimientos = new ControladorMantenimientos().getMantenimientosXBici(id);
            } else {
              response.sendRedirect("error.jsp");
              return;
            }
          } catch (NumberFormatException ex) {
            response.sendRedirect("error.jsp");
            return;
          } catch (Exception ex) {
            response.sendRedirect("error.jsp");
            return;
          }
        } else {
          id = 0;
        }
  %>
  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3">MANTENIMIENTOS DE BICICLETAS</span>
  </h1>
  <div class="container text-center">
    <div class="col-lg-11 col-sm-11 col-centered well">
      <div class="row" align="left">
        <div class="col-lg-9 col-sm-9">
          <label>&nbsp;Seleccione una Bicicleta</label>
          <select class="form-control" onchange="cargarMantenimientos()" name="bici" id="bici" placeholder="Seleccione Bicicleta" title="Seleccione Bicicleta" required="true" autofocus="true">
            <option value="" disabled selected>Seleccione Bicicleta</option>
            <% for (Bicicletas b : bicicletas) {%>                    
            <option value="<%=b.getId()%>"
                    <%if (b.getId() == id) {%>
                    selected
                    <%}%>
                    ><%= b.getModelo().getTipo().getNombre() + " - " + b.getModelo().getNombre() + " - " + b.getPatente()%></option>
            <%}%>
          </select>
        </div>
        <div class="col-lg-3 col-sm-3" align="center">                        
          <div class="form-check form-check-inline" style="margin-top: 1rem;">            
              <label class="form-check-label label-consulta label-success">
                <input class="form-check-input" id="chkCompletados" name="chkCompletados" type="checkbox" onclick="checkCompleto()">
                SOLO COMPLETADOS
              </label>
          </div>
        </div>
      </div>
      <hr>
      <%if (id > 0) {
          Bicicletas bici = new ControladorBicicletas().getBicicleta(id);
          DecimalFormat df2 = new DecimalFormat("0.00");%>
      <div class="row">      
        <div class="col-lg-6 col-md-12 img-contenedor margin1bottom margin05top">         
          <a>
            <img class="img-fluid3 img-thumbnail2 imgid" id="imgR" src="<%=bici.getModelo().getUrl1()%>" onclick="ampliar(this.src)">                
          </a>            
        </div>
        <div class="col-lg-6 col-md-12" align="left">            
          <%if (bici.isDisponible()) { %>
          <label class="label label-success">DISPONIBLE</label>
            <% } else { %>
            <label class="label label-danger">NO DISPONIBLE</label>
              <% }%>            
            <p style="margin-top:0.5rem;margin-bottom: 0.1rem;font-size: 16px"><b><u>Tipo:</u></b> <label><%=bici.getModelo().getTipo().getNombre()%></label></p>
            <p style="margin-bottom: 0.1rem;font-size: 16px"><b><u>Modelo:</u></b> <label><%=bici.getModelo().getNombre()%></label></p>
            <p style="margin-bottom: 0.1rem;font-size: 16px"><b><u>Patente:</u></b> <label><%=bici.getPatente()%></label></p>        
            <p style="margin-bottom: 0.1rem;font-size: 16px"><b><u>Km acumulados:</u></b> <label><%=df2.format(bici.getKmEnViaje())%></label></p>                    
            <p style="margin-bottom: 0.1rem;font-size: 16px"><b><u>Km desde último mantenimiento:</u></b> <label><%= df2.format(bici.getKmDsdMantenimiento())%></label></p>        
        </div>
      </div>
      <hr>
      <% if (mantenimientos == null || mantenimientos.size() == 0) { %>
      <div class="error" style="display: flex;justify-content: center;align-items: center;">La bicicleta seleccionada no posee ningún mantenimiento realizado.</div>      
      <hr>
      <%} else {%>
      <div style="overflow-x:auto;">
        <table class="table display" id="mantenimientos">
          <thead class='encabezado'>
            <tr align="center">
              <th>FECHA/HORA INGRESO</th>
              <th>FECHA/HORA EGRESO</th>
              <th>ESTADO</th>
              <th></th>
            </tr>
          </thead>
          <tbody>            
            <%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm");
              for (Mantenimientos m : mantenimientos) {%>
            <tr align="center" >
              <td class="col-secundario" style="vertical-align: middle"><%= df.format(m.getFechaIngreso())%></td>
              <% if (m.getFechaEgreso() != null) {%>
              <td class="col-secundario" style="vertical-align: middle"><%= df.format(m.getFechaEgreso())%></td>
              <td class="col-secundario" style="vertical-align: middle"><label class="label label-success">COMPLETADO</label></td>
              <td class="vertical">
                <button class="btn btn-reset" title="Ver Mantenimiento" 
                        onclick='window.open("verMantenimiento.jsp?idMant=" +<%=m.getId()%>, "_self")'>
                  <span class="fa fa-eye"></span>
                </button>
              </td>
              <% } else {%>
              <td class="col-secundario" style="vertical-align: middle">---</td>
              <td class="col-secundario" style="vertical-align: middle"><label class="label label-danger">INCOMPLETO</label></td>
              <td class="vertical">
                <button class="btn btn-editar" title="Completar/Editar" 
                        onclick='window.open("modificarMantenimiento.jsp?idMant=" +<%=m.getId()%>, "_self")'>
                  <span class="fa fa-check-square-o"></span>
                </button>
              </td>
              <% } %>
            </tr>        
            <% } %>
          </tbody>
        </table>
      </div>
      <hr />
      <%}
        } %>
      <input type="button" class="btn btn-lg btn-principal btn-block margin1top" value="Volver a Mantenimentos" onclick="window.open('admmant.jsp', '_self')"> 
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
  <script src="js/mantxbici.js"></script>
</body>
