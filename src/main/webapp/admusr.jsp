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
  <link href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
  <link rel="icon" href="img/favicon.ico" type="image/x-icon">
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
    <span class="site-heading-upper text-primary mb-3">administrador de usuarios</span>
  </h1>

  <!-- USUARIOS -->
  <div class="col-lg-10 col-centered well" id="tablausuarios" >      
    <div style="overflow-x:auto;margin:0.5rem">
      <div class="form-group">
        <button type="button" onclick="vaciarFiltro()" class="label-consulta label-desconocido" title="Todos">TODOS</button>
        <button type="button" onclick="buscarAdmin()" class="label-consulta label-adm" title="Administradores">ADMINISTRADORES</button>
        <button type="button" onclick="buscarMecanicos()" class="label-consulta label-mecanico" title="Mecanicos">MECANICOS</button>
        <button type="button" onclick="buscarClientes()" class="label-consulta label-cliente" title="Clientes">CLIENTES</button>
      </div>
      <div align="right">
        <a class="btn btn-nuevo margin05bottom" title="Nuevo Usuario" onclick="nuevousr()"><span class="fa fa-plus-square"></span></a>
      </div
      <br>
      <table class="display table" id="usuarios">
        <thead class="encabezado">
          <tr align="center">
            <th>USUARIO</th>
            <th>NOMBRE</th>
            <th>DOCUMENTO</th>
            <th>EMAIL</th>
            <th>ROL</th>
            <th>¿HABILITADO?</th>
            <th></th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>            
          <%ArrayList<Usuarios> usuarios = new ControladorUsuarios().getUsuarios();
              if (usuarios.size() > 0) {
                for (Usuarios u : usuarios) {%>
          <tr align="center" >
            <td class="col-principal" style="vertical-align: middle;"><%= u.getUsuario().trim()%></td>
            <td class="col-secundario" style="vertical-align: middle;"><%= u.getApynom().trim()%></td>
            <td class="col-secundario" style="vertical-align: middle;"><%= u.getDocumento().trim()%></td>
            <td class="col-secundario" style="vertical-align: middle;"><%= u.getEmail().trim()%></td>
            <td class="col-secundario" style="vertical-align: middle;">
              <% if (u.isAdm()) {%>
              <span class="label label-adm">ADMIN</span>
              <% }
                  if (u.isMecanico() && !u.isAdm()) { %>
              <span class="label label-mecanico">MECANICO</span>
              <% }
                  if (!u.isAdm() && !u.isMecanico()) { %>
              <span class="label label-cliente">CLIENTE</span>
              <% } %>
            </td>
            <td style="vertical-align:middle">
              <% if (u.isHabilitado()) {%>
              <span class="label label-success">SI</span>
              <% } else { %>
              <span class="label label-danger">NO</span>
              <% }%>
            </td>   
            <td style="vertical-align:middle">
              <button class="btn btn-editar" title="Ver/Editar" 
                      onclick="editarusr('<%= u.getId()%>', '<%=u.getUsuario().trim()%>', '<%= u.getApynom().trim()%>', '<%= u.getDocumento()%>',
                                    '<%= u.getEmail()%>', '<%= u.isAdm()%>', '<%= u.isMecanico()%>', '<%= u.isHabilitado()%>', '<%= u.getDomicilio().trim()%>', '<%= u.getTelefono()%>')">
                <span class="fa fa-edit"></span>
              </button>
            </td>
            <td style="vertical-align:middle">
              <button class="btn btn-eliminar" title="Eliminar" 
                      onclick="eliminarusr('<%= u.getId()%>', '<%=u.getUsuario().trim()%>', '<%= u.getApynom().trim()%>', '<%= u.getDocumento()%>',
                                    '<%= u.getEmail()%>', '<%= u.isAdm()%>', '<%= u.isMecanico()%>', '<%= u.isHabilitado()%>', '<%= u.getDomicilio().trim()%>', '<%= u.getTelefono()%>')">
                <span class="fa fa-trash-o"></span>
              </button>
            </td>
            <td style="vertical-align:middle">
              <button class="btn btn-reset" title="Reestablecer Contraseña" onclick="reset('<%= u.getId()%>')">
                <span class="fa fa-refresh"></span>
              </button>
            </td>
          </tr>                
          <% }
              }%>
        </tbody>
      </table>
    </div>
  </div>

  <!-- NUEVO USUARIO -->
  <div class="container text-center" id="nuevousr" style="display: none">
    <form class="form-text" method="POST">
      <div class="col-lg-8 col-centered well" align="left">
        <div class="row">
          <div class="col-sm-12">
            <div class="form-group float-sm-right">
              <span id="cruzNuevo" class="fa fa-close" style="right: 50px"></span>
            </div>
            <div class="row">                
              <div class="col-sm-9 form-group">
                <input hidden="true" id='idusr' name='idusr'>
                <label>&nbsp;Nombre del Usuario</label>
                <input type="text" title="Nombre de Usuario" name="usuario" id="usuario" placeholder="Nombre de Usuario" class="form-control" autofocus="true" required="true">
                <input type="text" title="Nombre de Usuario" name="usrE" id="usrE" placeholder="Nombre de Usuario" class="form-control" readonly display='none'>
              </div>
              <div class="col-sm-3 form-group">
                <label>&nbsp;¿Habilitado?</label>
                <select class="form-control" name="habilitado" id="habilitado" title="¿Usuario Habilitado?">
                  <option value="" disabled selected>¿Habilitado?</option>
                  <option value="true" selected="true">SI</option>
                  <option value="false">NO</option> 
                </select>
              </div>                  
            </div>
            <div class="row">
              <div class="password col-sm-6 form-group" id='nuevoPass' display='block'>
                <label>&nbsp;Contraseña</label>
                <input type="password" name="pass" id="pass" placeholder="Contraseña" title="Contraseña" class="form-control" autofocus="true" required="true">
                <span id="ojopas" class="fa fa-eye" style="right: 23px;top:42px"></span>
              </div>	
              <div class="password col-sm-6 form-group" id='nuevoRPass' display='block'>
                <label>&nbsp;Repita Contraseña</label>
                <input type="password" id="repass" name="repass" placeholder="Repita Contraseña" title="Repita Contraseña" class="form-control" autofocus="true" required="true">
                <span id="ojore" class="fa fa-eye" style="right: 23px;top:42px"></span>
              </div>
            </div>
            <div class="form-group">
              <label>&nbsp;Apellido y Nombre</label>
              <input type="text" name="apenom" id="apenom" placeholder="Apellido y Nombre" title="Apellido y Nombre" class="form-control" autofocus="true" required="true">
            </div>			
            <div class="form-group">
              <label>&nbsp;Domicilio</label>
              <input type="text" name="domicilio" id="domicilio" placeholder="Domicilio" title="Domicilio" class="form-control" autofocus="true" required="true">
            </div>
            <div class="form-group">
              <label>&nbsp;Documento de Identidad</label>
              <input type="text" name="documento" id="documento" placeholder="Documento de Identidad" title="Documento de Identidad" class="form-control" autofocus="true" required="true">
            </div>
            <div class="form-group">
              <label>&nbsp;Teléfono de contacto</label>
              <input type="text" name="telefono" id="telefono" placeholder="Teléfono de contacto" title="Teléfono de contacto" class="form-control" autofocus="true" required="true">
            </div>		
            <div class="form-group">
              <label>&nbsp;Email</label>
              <input type="email" name="email" id="email" placeholder="Email de contacto" title="Email de contacto" class="form-control" autofocus="true" required="true">
            </div>
            <div class="form-group" align="center">
              <fieldset class="groupboxPass">
                <legend class="legendPass">&nbsp;Tipo de Usuario&nbsp;</legend>
                  <div class="form-check form-check-inline">
                    <label class="btn btn-admin">                        
                      <label class="form-check-label label label-adm"><input class="form-check-input" type="checkbox" id="admin" name="admin" value="false"
                                                                             onclick="javascript:checkNuevoAdmin(this.form)">ADMINISTRADOR</label>
                    </label>
                  </div>
                  <div class="form-check form-check-inline">
                    <label class="btn btn-mecanico">
                      <label class="form-check-label label label-mecanico"><input class="form-check-input" type="checkbox" id="meca" name="meca" value="false"
                                                                                  onclick="javascript:checkNuevoMeca(this.form)">MECANICO</label>
                    </label>
                  </div>
                  <div class="form-check form-check-inline">                      
                    <label class="btn label-clienteusr">
                      <label class="form-check-label label label-clienteusr"><input class="form-check-input" type="checkbox" id="cliente" name="cliente" value="false"
                                                                                 onclick="javascript:checkNuevoCliente(this.form)">CLIENTE</label>
                    </label>
                  </div>
               </fieldset>
            </div>
            <div align="center">
              <label><b><i>El rol <span class="adm">ADMINISTRADOR</span> incluye las funcionalidades del rol <span class="mecanico">MECÁNICO</span></i></b></label>
            </div>            
            <input type="submit" id="enviar" class="btn btn-lg btn-nuevo btn-block margin1top" value="Guardar" onclick="javascript:form.action = 'AltaUsuario';">
            <input type="submit" id="editar" class="btn btn-lg btn-editar btn-block margin1top" style="display: none" value="Modificar" onclick="javascript:form.action = 'ModificarUsuario';">
            <input type="submit" id="eliminar" class="btn btn-lg btn-eliminar btn-block margin1top" style="display: none" value="Eliminar" onclick="javascript:form.action = 'EliminarUsuario';">
          </div>              
        </div>
      </div>
    </form>
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
  <script src="js/existeUsuario.js"></script>
  <script src="js/registroNuevoUsr.js"></script>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
  <script src="js/abmusr.js"></script>

</body>
