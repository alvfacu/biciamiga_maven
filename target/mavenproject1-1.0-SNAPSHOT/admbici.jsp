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
        if (usrActual.isAdm()) {%>
    <h1 class="site-heading text-center text-white d d-lg-block">
      <span class="site-heading-upper text-primary mb-3">administrador de bicicletas</span>
    </h1>

    <div class="col-lg-10 col-centered well" id="tablabicicletas">
      
      <div style="overflow-x:auto;">
        <button class="tablink" onclick="openPage('bicis', this)" id="defaultOpen">Bicicletas</button>
        <button class="tablink" onclick="openPage('modelosbici', this)">Modelos</button>
        <button class="tablink" onclick="openPage('tipos', this)">Tipos</button>
      </div>
      
      <!-- BICICLETA -->
      <div id="bicis" class="tabcontent">
        <div>      
          <div align="right">
            <a class="btn btn-nuevo margin05bottom" title="Nueva Bicicleta" onclick="nuevabici()"><span class="fa fa-plus-square"></span></a>
          </div>
        </div>
        <div style="overflow-x:auto;">
          <table class="table display" id="bicicletas">
            <thead class="encabezado">
              <tr align="center">
                <th>TIPO</th>
                <th>MODELO</th>
                <th>PATENTE</th>            
                <th title="Kms realizados desde el último mantenimiento">Kms MANTEN.</th>
                <th title="Kms acumulados">Kms TOT.</th>
                <th>ESTADO</th>
                <th></th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>            
              <%DecimalFormat df2 = new DecimalFormat("0.00");
                ArrayList<Bicicletas> bicicletas = new ControladorBicicletas().getBicicletas();
                if (bicicletas.size() > 0) {
                  for (Bicicletas b : bicicletas) {%>
              <tr align="center" >
                <td class="col-secundario" style="vertical-align: middle"><%= b.getModelo().getTipo().getNombre()%></td>
                <td class="col-secundario" style="vertical-align: middle"><%= b.getModelo().getNombre()%></td>
                <td class="col-principal" style="vertical-align: middle"><%= b.getPatente()%></td>          
                <td class="col-secundario" style="vertical-align: middle"><%= df2.format(b.getKmDsdMantenimiento())%></td>
                <td class="col-secundario" style="vertical-align: middle"><%= df2.format(b.getKmEnViaje())%></td>
                <td class="col-secundario" style="vertical-align: middle">
                  <% if (b.isDisponible()) {%>
                  <span class="label label-success">DISPONIBLE</span>
                  <% } else { %>
                  <span class="label label-danger">NO DISPONIBLE</span>
                  <% }%>
                </td> 
                <td class="vertical">
                  <% if (b.isDisponible() && !(new ControladorBicicletas().tieneReservasEnCurso(b))) {%>
                  <button class="btn btn-detener" title="Enviar a mantenimiento"
                          onclick='window.open("nuevoMantenimiento.jsp?idBici="+<%= b.getId()%>,"_self")'>
                    <span class="fa fa-wrench"></span>
                  </button>
                  <% }%>
                </td>
                <td class="vertical">
                  <button class="btn btn-reset" title="Consultar mantenimientos realizados"
                          onclick='window.open("mantxbici.jsp?idBici="+<%= b.getId()%>,"_self")'>
                    <span class="fa fa-info-circle"></span>
                  </button>
                </td>
                <td class="vertical">
                  <button class="btn btn-eliminar" title="Eliminar" 
                          onclick="eliminarbici('<%= b.getId()%>', '<%= b.getPatente()%>', '<%= b.getModelo().getId()%>', '<%= b.getModelo().getTipo().getNombre()%>', '<%= b.isDisponible()%>', '<%= df2.format(b.getKmDsdMantenimiento())%>', '<%= df2.format(b.getKmEnViaje())%>', '<%= b.getModelo().getUrl1()%>')">
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

      <!-- MODELOS BICICLETA -->
      <div id="modelosbici" class="tabcontent">
        <div align="right">
          <a class="btn btn-nuevo margin05bottom" title="Nuevo Modelo" onclick="nuevomodelo()"><span class="fa fa-plus-square"></span></a>
        </div>
        <div style="overflow-x:auto;">
          <table class="table display" id="modelos">
            <thead class="encabezado">
              <tr align="center">
                <th>TIPO</th>
                <th>NOMBRE</th>
                <th>PRECIO HORA</th>
                <th>PRECIO DÍA</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>            
              <% ArrayList<Modelos> modelos = new ControladorBicicletas().getModelos();
                if (modelos.size() > 0) {
                  for (Modelos modelo : modelos) {%>
              <tr align="center" >
                <td class="col-secundario" style="vertical-align: middle"><%= modelo.getTipo().getNombre()%></td>
                <td class="col-principal" style="vertical-align: middle"><%= modelo.getNombre()%></td>
                <td class="col-secundario" style="vertical-align: middle">$ <%= df2.format(modelo.getPrecioXHr())%></td>
                <td class="col-secundario" style="vertical-align: middle">$ <%= df2.format(modelo.getPrecioXDia())%></td>
                <td class="vertical">
                  <button class="btn btn-editar" title="Editar" 
                          onclick="editarmodelo('<%= modelo.getId()%>', '<%=modelo.getTipo().getId()%>', '<%=modelo.getNombre()%>', '<%=modelo.getCaracteristicas_gral()%>', '<%= df2.format(modelo.getPrecioXHr())%>', '<%= df2.format(modelo.getPrecioXDia())%>', '<%= modelo.getUrl1()%>', '<%= modelo.getUrl2()%>', '<%= modelo.getUrl3()%>')">
                    <span class="fa fa-edit"></span>
                  </button>
                </td>
                <td class="vertical">
                  <button class="btn btn-eliminar" title="Eliminar" 
                          onclick="eliminarmodelo('<%= modelo.getId()%>', '<%=modelo.getTipo().getId()%>', '<%=modelo.getNombre()%>', '<%=modelo.getCaracteristicas_gral()%>', '<%= df2.format(modelo.getPrecioXHr())%>', '<%= df2.format(modelo.getPrecioXDia())%>', '<%= modelo.getUrl1()%>', '<%= modelo.getUrl2()%>', '<%= modelo.getUrl3()%>')">
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

      <!-- TIPO BICICLETA -->
      <div id="tipos" class="tabcontent">
        <div align="right">
          <a class="btn btn-nuevo margin05bottom" title="Nuevo Tipo Bicicleta" onclick="nuevotipo()"><span class="fa fa-plus-square"></span></a>
        </div>
        <div style="overflow-x:auto;">
          <table class="table display" id="tiposbici">
            <thead class="encabezado">
              <tr align="center">
                <th>NOMBRE</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>            
              <% ArrayList<TiposBicicleta> tipos = new ControladorBicicletas().getTiposBicicleta();
                if (tipos.size() > 0) {
                  for (TiposBicicleta tipo : tipos) {%>
              <tr align="center" >
                <td class="col-principal" style="vertical-align: middle"><%= tipo.getNombre()%></td>
                <td class="vertical">
                  <button class="btn btn-editar" title="Editar" 
                          onclick="editartipo('<%= tipo.getId()%>', '<%=tipo.getNombre()%>', '<%=tipo.getDescripcion()%>', '<%=tipo.getUrl()%>')">
                    <span class="fa fa-edit"></span>
                  </button>
                </td>
                <td class="vertical">
                  <button class="btn btn-eliminar" title="Eliminar" 
                          onclick="eliminartipo('<%= tipo.getId()%>', '<%=tipo.getNombre()%>', '<%=tipo.getDescripcion()%>', '<%=tipo.getUrl()%>')">
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
            
    </div>

    <!-- TIPO BICICLETA -->
    <div class="container text-center" id="nuevotipo" style="display: none" enctype = "multipart/form-data">
      <form class="form-text" method="POST">
        <div class="col-lg-7 col-centered well" align="left">
          <div class="row">
            <div class="col-sm-12">
              <div class="form-group float-sm-right">
                <span id="cruzNuevoTB" class="fa fa-close" style="right: 50px"></span>
              </div>
              <div class="form-group">
                <label class="error" id="msj1" style="display: none"></label>
                <input type="hidden" name="idtb" id="idtb">
                <label>&nbsp;Tipo de Bicicleta / Título a mostrar</label>
                <input name="nombretb" id="nombretb" maxlength="50" placeholder="Nombre del Tipo de Bicicleta" title="Nombre del Tipo de Bicicleta"  class="form-control" autofocus="true" required="true">
              </div>
              <div class="form-group">
                <label>&nbsp;Descripción</label>
                <textarea name="descriptb" id="descriptb" maxlength="250" placeholder="Descripcion del Tipo de Bicicleta" title="Descripcion del Tipo de Bicicleta"  class="form-control" autofocus="true" required="true"></textarea>
              </div>
              <div class="form-group margin05top" align="center">
                <label style="font-size: 12px;">Imagen que se utilizará como portada de este Tipo en la sección <b>"Nuestras bicis"</b>. 
                       <br><i>Se recomienda seleccionar imagen de tamaño cercano a <b>700x400px</b>.</i></label>
                <figure class="figure">
                  <img id="portada" name="portada" src="img/imagen-vacia.jpg" width="250" height="250" title="Portada" style="border-style: solid; border-width: 1px;">
                  <figcaption class="figure-caption text-right"><input type="url" maxlength="250" class="form-control" name="urlPortada" id="urlPortada" placeholder="Link imagen portada" title="Link imagen portada"  class="form-control" autofocus="true" style="width: 100%" /></figcaption>
                </figure>
              </div>
              <input type="submit" id="guardartb" class="btn btn-lg btn-nuevo btn-block" value="Guardar" onclick="javascript:form.action = 'AltaTipoBici';">
              <input type="submit" id="editartb" class="btn btn-lg btn-editar btn-block" style="display: none" value="Modificar" onclick="javascript:form.action = 'ModificarTipoBici';">
              <input type="submit" id="eliminartb" class="btn btn-lg btn-eliminar btn-block" style="display: none" value="Eliminar" onclick="javascript:form.action = 'EliminarTipoBici';">
            </div>              
          </div>
        </div>         
      </form>
    </div>

    <!-- MODELO BICICLETA -->
    <div class="container text-center" id="nuevomodelo" style="display: none" enctype = "multipart/form-data">
      <form class="form-text" method="POST">
        <div class="col-lg-8 col-centered well" align="left">
          <div class="row">
            <div class="col-sm-12">
              <div class="form-group float-sm-right">
                <span id="cruzNuevoM" class="fa fa-close" style="right: 50px"></span>
              </div>
              <div class="form-group">
                <label class="error" id="msj2" style="display: none"></label>
              </div>
              <div class="row">
                <div class="col-sm-6 form-group">                  
                  <input type="hidden" name="idm" id="idm">
                  <label>&nbsp;Tipo de Bicicleta</label>
                  <select class="form-control" name="clr" id="clr" placeholder="Tipo de Bicicleta" title="Tipo de Bicicleta" required="true">
                    <option value="" disabled selected>Tipo de Bicicleta</option>
                    <% for (TiposBicicleta t : tipos) {%>                    
                    <option value="<%=t.getId()%>"><%=t.getNombre()%></option>
                    <%}%>
                  </select>
                </div>	
                <div class="col-sm-6 form-group">
                  <label>&nbsp;Modelo/Marca</label>
                  <input name="nombrem" id="nombrem" maxlength="50" placeholder="Nombre del Modelo/Marca" title="Nombre del Modelo/Marca"  class="form-control" autofocus="true" required="true">
                </div>
              </div>
              <div class="form-group">
                <label>&nbsp;Descripción del Modelo</label>
                <textarea name="descripm" id="descripm" maxlength="250" placeholder="Caracteristicas generales del Modelo" title="Caracteristicas generales del Modelo"  class="form-control" autofocus="true" required="true"></textarea>
              </div>
              <div class="row">
                <div class="col-sm-6 form-group">
                  <label>&nbsp;Precio por Hora ($)</label>
                  <input type="number" step="any" name="precioHr" id="precioHr" placeholder="Precio x Hora" title="Precio x Hora" class="form-control" autofocus="true" required="true">
                </div>	
                <div class="col-sm-6 form-group">
                  <label>&nbsp;Precio por día completo ($)</label>
                  <input type="number" step="any" name="precioDia" id="precioDia" placeholder="Precio x Día" title="Precio x Día" class="form-control" autofocus="true" required="true">
                </div>
              </div>
              <div align="center">
                <label>&nbsp;Imágenes de referencia</label>
              </div>
              <div class="row" align="center">
                <div class="col-sm-4 form-group">
                  <figure class="figure">
                    <img id="imgbici1" name="imgbici1" src="img/imagen-vacia.jpg" width="150" height="150" title="Imagen principal" style="border-style: solid; border-width: 1px;">
                    <figcaption class="figure-caption text-right"><input type="url" maxlength="250" class="form-control" name="url1" id="url1" placeholder="Link imagen principal" title="Link imagen principal"  class="form-control" autofocus="true" required="true" style="width: 100%; font-size: 10px" /></figcaption>
                  </figure>
                </div>	
                <div class="col-sm-4 form-group">
                  <figure class="figure">
                    <img id="imgbici2" name="imgbici2" src="img/imagen-vacia.jpg" width="150" height="150" title="Imagen secundaria" style="border-style: solid; border-width: 1px;">
                    <figcaption class="figure-caption text-right"><input type="url" maxlength="250" class="form-control" name="url2" id="url2" placeholder="Link imagen secundaria" title="Link imagen secundaria"  class="form-control" autofocus="true" style="width: 100%; font-size: 10px" /></figcaption>
                  </figure>
                </div>
                <div class="col-sm-4 form-group">
                  <figure class="figure">
                    <img id="imgbici3" name="imgbici3" src="img/imagen-vacia.jpg" width="150" height="150" title="Imagen secundaria" style="border-style: solid; border-width: 1px;">
                    <figcaption class="figure-caption text-right"><input type="url" maxlength="250" class="form-control" name="url3" id="url3" placeholder="Link imagen secundaria" title="Link imagen secundaria"  class="form-control" autofocus="true" style="width: 100%; font-size: 10px" /></figcaption>
                  </figure>
                </div>
              </div>
              <input type="submit" id="guardarm" class="btn btn-lg btn-nuevo btn-block" value="Guardar" onclick="javascript:form.action = 'AltaModelo';">
              <input type="submit" id="editarm" class="btn btn-lg btn-editar btn-block" style="display: none" value="Modificar" onclick="javascript:form.action = 'ModificarModelo';">
              <input type="submit" id="eliminarm" class="btn btn-lg btn-eliminar btn-block" style="display: none" value="Eliminar" onclick="javascript:form.action = 'EliminarModelo';">
            </div>              
          </div>
        </div>         
      </form>
    </div>

    <!-- BICICLETA -->
    <div class="container text-center" id="nuevabici" style="display: none" enctype = "multipart/form-data">
      <form class="form-text" method="POST">
        <div class="col-lg-7 col-centered well" align="left">
          <div class="row">
            <div class="col-sm-12">
              <div class="form-group float-sm-right">
                <span id="cruzNuevoB" class="fa fa-close" style="right: 50px"></span>
              </div>              
              <div class="form-group" align="center">                
                <img id="imgbici" name="imgbici" src="img/imagen-vacia.jpg" width="200" height="200" style="border-style: solid; border-width: 1px;">
              </div>
              <div class="row" style="margin-top:1px;">
                <div class="col-sm-4 form-group">
                  <label class="error" id="msj3" style="display: none"></label>
                  <input type="hidden" name="idb" id="idb">
                  <label>&nbsp;Tipo de Bicicleta</label>
                  <input type="text" class="form-control" disabled="true" name="tipob" id="tipob" placeholder="Tipo de Bicicleta" title="Tipo de Bicicleta" required="false">
                </div>	
                <div class="col-sm-5 form-group">
                  <label>&nbsp;Modelo / Marca</label>
                  <select class="form-control" name="modelobici" id="modelobici" placeholder="Modelo de Bicicleta" title="Modelo de Bicicleta" required="true" onchange="dameImagen()" autofocus="true">
                    <option value="" disabled selected>Modelo Bicicleta</option>
                    <% for (Modelos m : new ControladorBicicletas().getModelos()) {%>                    
                    <option value="<%= m.getId()%>"><%= m.getNombre()%></option>
                    <%}%>
                  </select>
                </div>
                <div class="col-sm-3 form-group">
                  <label>&nbsp;Estado</label>
                  <input type="hidden" name="disponible" id="disponible" value="true">
                  <span id="disponibleS" name="disponibleS" class="form-control label label-disponibilidad label-success" >DISPONIBLE</span>
                  <span id="disponibleN" name="disponibleN" class="form-control label label-disponibilidad label-danger" style="display:none">NO DISPONIBLE</span>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-4 form-group">
                  <label>&nbsp;Patente / ID </label>
                  <input type="text" name="patente" maxlength="10" id="patente" style="text-transform:uppercase" placeholder="Patente Bicicleta" title="Patente Bicicleta" class="form-control" autofocus="true" required="true">                  
                </div>
                <div class="col-sm-4 form-group">
                  <label>&nbsp;Km últ mantenimiento</label>
                  <input type="number" step="any" name="kmMantenimiento" id="kmMantenimiento" placeholder="Kms desde el últ mantenimiento" title="Kms desde el último mantenimiento" class="form-control" autofocus="true" required="true">
                </div>	
                <div class="col-sm-4 form-group">
                  <label>&nbsp;Km totales viajados</label>
                  <input type="number" step="any" name="kmViajados" id="kmViajados" placeholder="Kms totales" title="Kms totales recorridos" class="form-control" autofocus="true" required="true">
                </div>
              </div>             
              <input type="submit" id="guardarb" class="btn btn-lg btn-nuevo btn-block margin1top" value="Guardar" onclick="javascript:form.action = 'AltaBicicleta';">
              <input type="submit" id="eliminarb" class="btn btn-lg btn-eliminar btn-block margin1top" style="display: none" value="Eliminar" onclick="javascript:form.action = 'EliminarBicicleta';">
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
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
    <script src="js/existePatente.js"></script>
    <script src="js/abmbici.js"></script>
    
   
  </body>
