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

  <!-- Body -->
  <%DecimalFormat df2 = new DecimalFormat("0.00");
    if (session.getAttribute("Usuario") != null) {
      Usuarios usrActual = (Usuarios) session.getAttribute("Usuario");
      if ((usrActual.isAdm()) || (!usrActual.isAdm() && !usrActual.isMecanico())) {
        ArrayList<Modelos> modelos_dispo = null;
        int tipo = 0, modelo = 0;
        boolean bndCompleto = true;
        SimpleDateFormat format;
        String hrDsd = "09";
        String hrHst = "21";
        int dif = 0;

        if (request.getParameter("tipos") != null) {
          try {
            tipo = Integer.valueOf(request.getParameter("tipos").toString());
            if (tipo < 0) {
              response.sendRedirect("error.jsp");
              return;
            } else {%>
  <input type="hidden" name="idt" id="idt" value="<%=request.getParameter("tipos")%>">
  <% }
      } catch (Exception exception1) {
        response.sendRedirect("error.jsp");
        return;
      }
    }

    if (request.getParameter("modelos") != null && tipo > 0) {
      try {
        String[] codModelo = request.getParameter("modelos").toString().split("-");
        if (codModelo.length > 1) {
          modelo = Integer.valueOf(codModelo[1]);
          if (modelo < 0) {
            response.sendRedirect("error.jsp");
                   return;
                 } else {%>
  <input type="hidden" name="idb" id="idb" value="<%=request.getParameter("modelos")%>">
  <% }
        }
      } catch (Exception exception1) {
        response.sendRedirect("error.jsp");
        return;
      }
    }

    if (request.getParameter("fecha") != null) {
      try {
        String formatString = "yyyy-MM-dd";
        format = new SimpleDateFormat(formatString);
        format.setLenient(false);
        format.parse(request.getParameter("fecha"));%>
  <input type="hidden" name="fechaR" id="fechaR" value="<%=request.getParameter("fecha")%>">
  <%} catch (Exception e) {
        response.sendRedirect("error.jsp");
        return;
      }
    }

    if (request.getParameter("hrdesde") != null || request.getParameter("hrhasta") != null) {
      try {
        hrDsd = request.getParameter("hrdesde");
        hrHst = request.getParameter("hrhasta");
        if (Integer.valueOf(hrDsd) < Integer.valueOf(hrHst)) {
          if (Integer.valueOf(hrDsd) == 9 && Integer.valueOf(hrHst) == 21) {
            bndCompleto = true;
          } else {
            bndCompleto = false;
            dif = Integer.valueOf(hrHst) - Integer.valueOf(hrDsd);
          }
        } else {
          response.sendRedirect("error.jsp");
          return;
        }
      } catch (Exception e) {
        response.sendRedirect("error.jsp");
        return;
      }
    }

    if (request.getAttribute("bicicletas") != null) {
      modelos_dispo = (ArrayList<Modelos>) request.getAttribute("bicicletas");
    }
  %>

  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3">Nueva reserva</span>
  </h1>

  <div class="col-lg-10 col-xs-10 col-md-10 col-centered well form-text">
    <div class="container" align="left"> 
      <form method="GET" action="reservar">
        <div class="row">        
          <div class="col-lg-6 col-xs-6 margin1top">
            <label>&nbsp;Seleccione el tipo de bicicleta</label>
            <select class="form-control" name="tipos" id="tipos" placeholder="Tipos de Bicicleta" title="Tipo de Bicicleta" required="true" >
              <option value="0000">TODOS LOS TIPOS DE BICICLETAS</option>
              <% for (TiposBicicleta t : new ControladorBicicletas().getTiposBicicleta()) {%>                    
              <option value="<%=t.getId()%>"
                      <% if (tipo == t.getId()) { %>
                      selected="true"
                      <% }%>
                      ><%=t.getNombre().toUpperCase()%></option>
              <%}%>
            </select>
          </div>
          <div class="col-lg-6 col-xs-6 margin1top">
            <label>&nbsp;Seleccione el modelo de bicicleta</label>
            <select class="form-control" name="modelos" id="modelos" placeholder="Modelos de Bicicleta" title="Modelo de Bicicleta" required="true" >
              <option value="" selected>TODOS LOS MODELOS</option>
              <option value="0">TODOS LOS MODELOS</option>
              <% for (Modelos m : new ControladorBicicletas().getModelos()) {%>                    
              <option value="<%= String.format("%4s", m.getTipo().getId()).replace(' ', '0') + "-" + m.getId()%>"
                      <% if (modelo == m.getId() && tipo == m.getTipo().getId()) { %>
                      selected="true"
                      <% }%>
                      ><%=m.getNombre().toUpperCase()%></option>
              <% } %>
            </select>
          </div>
        </div>
        <div class="row">          
          <div class="col-lg-3 col-xs-3 margin1top">
            <label>&nbsp;Fecha de Reserva</label>
            <input type="date" class="form-control" id="fecha" name="fecha" required="true">
          </div>
          <div class="col-lg-3 col-xs-3 chkReserva">
            <li class="lireserva">
              <label><input type="checkbox" class="margin1right" id="completo" name="completo" onclick="turno_completo()"
                            <%if (bndCompleto) {%>
                            checked="true" value="true"
                            <% } else { %>
                            value="false"
                            <%}%>>Día Completo</label>
            </li>
            <span class="lblPreferencial"><i><b>Precio diferencial.</b> De 9:00 a 21:00 hs.</i></span>
          </div>
          <div class="col-lg-2 col-xs-2 margin1top">
            <label>&nbsp;Hora Inicio</label>
            <input type="number" class="form-control" min="09" max="21" value="<%=hrDsd%>" id="hrdesde" name="hrdesde" required="true"
                   <%if (bndCompleto) { %>
                   disabled="true"  
                   <% }%>>
          </div>
          <div class="col-lg-2 col-xs-2 margin1top">
            <label>&nbsp;Hora Fin</label>
            <input type="number" class="form-control" min="09" max="21" value="<%=hrHst%>" id="hrhasta" name="hrhasta" required="true"
                   <%if (bndCompleto) { %>
                   disabled="true"
                   <% }%>>
          </div>
          <div class="col-lg-2 margin16top">
            <button type="submit" id="buscar" class="col-sm-12 col-xs-12 btn btn-lg btn-buscar" title="Buscar bicicletas"><i class="fa fa-search"></i></button>
          </div>
        </div>
      </form>
    </div>
    <hr />
    <div>
      <h2 class="text-center">Bicicletas disponibles</h2>
      <br>
      <%if (modelos_dispo != null && modelos_dispo.size() > 0) { %>
      <div class="row text-center text-lg-left">
        <% for (Modelos m : modelos_dispo) {%>
        <div class="col-lg-5 col-md-12 img-contenedor">
          <a class="">
            <img class="img<%=m.getId()%> img-fluid2 img-thumbnail2 imgid imgReserva " onclick="ampliar(this.src)" src="<%=m.getUrl1()%>" alt="<%= m.getNombre()%>">                
          </a>
          <% if (m.getUrl2() != null && !m.getUrl2().trim().isEmpty()) {%>
          <img class="img<%=m.getId()%> img-fluid2 img-thumbnail2 imgid imgReserva" onclick="ampliar(this.src)" style="display: none" src="<%=m.getUrl2()%>" alt="<%= m.getNombre()%>">                
          <% } %>            
          <% if (m.getUrl3() != null && !m.getUrl3().trim().isEmpty()) {%>
          <img class="img<%=m.getId()%> img-fluid2 img-thumbnail2 imgid imgReserva" onclick="ampliar(this.src)" style="display: none" src="<%=m.getUrl3()%>" alt="<%= m.getNombre()%>">                
          <% }%>
          <div>
            <p style="text-align: center;">
              <label class='label flechaImagen' onclick="plusDivs('img<%=m.getId()%>', -1)"><a class="col-sm-6 col-xs-6"><<</a></label>
              <label class='label flechaImagen' onclick="plusDivs('img<%=m.getId()%>', 1)"><a class="col-sm-6 col-xs-6">>></a></label>
            </p>
          </div>
        </div>
        <div class="col-lg-7 col-md-12">
          <form method="POST" action="PreReserva">
            <input type="hidden" id="idModeloReserva" name="idModeloReserva" value="<%=m.getId()%>">
            <input type="hidden" class="fechaReserva" name="fechaReserva">
            <input type="hidden" class="completoReserva" name="completoReserva" value="<%=bndCompleto%>">
            <%if (!bndCompleto) {%>
            <input type="hidden" id="hrDesdeReserva" name="hrDesdeReserva" value="<%=hrDsd%>">
            <input type="hidden" id="hrHastaReserva" name="hrHastaReserva" value="<%=hrHst%>">
            <% }%>
            <p class="tituloBiciReserva"><b><%=m.getTipo().getNombre() + " - " + m.getNombre()%></b></p>          
            <p class="descripBiciReserva"><%=m.getCaracteristicas_gral()%></i></p>            
            <p class="kit"><i>Incluye kit de seguridad: casco, cadena y candado.</i></p>            
            <p class="preciosReserva"><i><b><%="Precio por hora: $ " + df2.format(m.getPrecioXHr()) + " - Precio Día Completo: $ " + df2.format(m.getPrecioXDia())%></b></i></p>            
            <div class="">
              <p class="col-sm-6 col-xs-12 precioFinalReserva"><b><u>Precio Final:</u></b> 
                    <% if (bndCompleto) {%>
                $ <%=df2.format(m.getPrecioXDia())%>
                <input type="hidden" class="importeReserva" name="importeReserva" value="<%=m.getPrecioXDia()%>">
                <% } else {%>
                $ <%=df2.format(m.getPrecioXHr() * dif)%>
                <input type="hidden" class="importeReserva" name="importeReserva" value="<%=m.getPrecioXHr() * dif%>">
                <% } %>              
              </p>
              <p>
                <button type="submit" class="btn btn-reserva col-sm-6 col-xs-12 margin1bottom">¡Reservala!</button>
              </p>
            </div>
          </form>
        </div>
        <% } %>
      </div>
      <% } else { %>
      <div class="errorReserva importante">No hay bicicletas disponibles.</div>
      <div class="errorReserva"><i>Recomendados cambiar los filtros para obtener otros resultados.</i></div>
      <% } %>
    </div>
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
  <script>

                var today = new Date();
                var dia = today.getDate();
                var mes = today.getMonth() + 1;
                var anio = today.getFullYear();
                document.getElementById("fecha").setAttribute("min", anio + '-' + String(mes).padStart(2, "0") + '-' + String(dia).padStart(2, "0"));

                if (document.getElementById("fechaR"))
                {
                  document.getElementById("fecha").setAttribute("value", document.getElementById("fechaR").value);
                  $('.fechaReserva').val(document.getElementById("fechaR").value);
                } else
                {
                  document.getElementById("fecha").setAttribute("value", anio + '-' + String(mes).padStart(2, "0") + '-' + String(dia).padStart(2, "0"));
                  $('.fechaReserva').val(anio + '-' + String(mes).padStart(2, "0") + '-' + String(dia).padStart(2, "0"));
                }

                var tipos = $('#tipos');
                var modelos = $('#modelos');
                var options = modelos.find('option');
                tipos.on('change', function () {
                  if (this.value)
                  {
                    if (this.value !== "0000")
                    {
                      modelos.html(options.filter('[value=0],[value*="' + String(this.value).padStart(4, "0") + '"]'));
                      modelos.prop('disabled', false);
                      modelos.prop('required', true);
                      $("#modelos option:first").text("TODOS LOS MODELOS DE " + $(this).find('option:selected').text());

                      if (this.value !== $('#idt').val())
                      {
                        modelos.val($("#modelos option:first").val());
                      } else
                      {
                        if (!$('#idb').val())
                        {
                          modelos.val($("#modelos option:first").val());
                        } else
                        {
                          modelos.val($('#idb').val());
                        }
                      }
                    } else
                    {
                      modelos.html(options.filter('[value=""]'));
                      modelos.prop('disabled', true);
                      modelos.prop('required', false);
                    }
                  } else
                  {
                    modelos.html(options.filter('[value=""]'));
                    modelos.prop('disabled', true);
                    modelos.prop('required', false);
                  }
                }).trigger('change');

                function turno_completo() {
                  if (document.getElementById("completo").checked)
                  {
                    document.getElementById("hrdesde").value = "09";
                    document.getElementById("hrdesde").disabled = true;
                    document.getElementById("hrhasta").value = "21";
                    document.getElementById("hrhasta").disabled = true;
                  } else
                  {
                    document.getElementById("hrdesde").disabled = false;
                    document.getElementById("hrhasta").disabled = false;
                  }
                }

                // Get the modal
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

                var slideIndex = 1;

                function plusDivs(imgid, n) {
                  showDivs(imgid, slideIndex += n);
                }

                function showDivs(imgid, n) {
                  var i;
                  var x = document.getElementsByClassName(imgid);
                  if (n > x.length) {
                    slideIndex = 1;
                  }
                  if (n < 1) {
                    slideIndex = x.length;
                  }
                  for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                  }
                  x[slideIndex - 1].style.display = "block";
                }

  </script>
</body>
