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
  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3">Mi cuenta</span>
  </h1> 

  <% if (session.getAttribute("Usuario") != null) {
      Usuarios usrActual = (Usuarios) session.getAttribute("Usuario"); %>
  <div class="container text-center">
    <div class="col-lg-7 col-centered well">
      <form class="form-text" method="POST" action="ModificarDatos">
        <% if(!usrActual.isHabilitado()) { %>
        <div align="center">
          <div class="errorReserva importante"><b>Usuario inhabilitado</b></div>
          <div class="errorReserva"><i>Cualquier comentario, comuníquese con nosotros.</i></div>
          <br>
        </div>
        <% } %>
        <% if(session.getAttribute("msj")!=null){ %>
        <div align="center">
          <div class="correcto"><b>¡Datos modificados con exito!</b></div>
          <hr>
        </div>
        <% } session.setAttribute("msj",null); %>
        <div class="row" align="left">
          <div class="col-sm-12">
            <div class="row">
              <div class="col-sm-6 form-group">
                <label>&nbsp;Usuario</label>
                <input type="text" name="usuario" id="usuario" placeholder="Usuario" class="form-control" disabled="true" value="<%=usrActual.getUsuario()%>">
              </div>	
              <div class="col-sm-6 form-group">
                <label>&nbsp;E-mail</label>
                <input type="email" name="email" id="email" placeholder="Email de contacto" title="Ingrese su email" class="form-control" autofocus="true" required="true" value="<%=usrActual.getEmail()%>">
              </div>
            </div>
            <div class="form-group" >
              <label>&nbsp;Apellido y Nombre</label>
              <input type="text" name="apenom" id="apenom" placeholder="Apellido y Nombre" title="Ingrese su apellido y nombre" class="form-control" autofocus="true" required="true" value="<%=usrActual.getApynom()%>">
            </div>			
            <div class="form-group">
              <label>&nbsp;Domicilio</label>
              <input type="text" name="domicilio" id="domicilio" placeholder="Domicilio" title="Ingrese su Domicilio" class="form-control" autofocus="true" required="true" value="<%=usrActual.getDomicilio()%>">
            </div>
            <div class="form-group">
              <label>&nbsp;Documento de Identidad</label>
              <input type="text" name="documento" id="documento" placeholder="Documento de Identidad" title="Ingrese su documento de identidad" class="form-control" autofocus="true" required="true" value="<%=usrActual.getDocumento()%>">
            </div>
            <div class="form-group">
              <label>&nbsp;Teléfono de contacto</label>
              <input type="text" name="telefono" id="telefono" placeholder="Teléfono de contacto" title="Ingrese un número de contacto" class="form-control" autofocus="true" required="true" value="<%=usrActual.getTelefono()%>">
            </div>
            <div class="form-group">
              <fieldset class="groupboxPass">
                <legend class="legendPass">&nbsp;<input type="checkbox" id="chk" name="chk" onclick="enableDisable(this.checked)">&nbsp;¿Cambia contraseña?&nbsp;&nbsp;</legend>
                <div class="password col-sm-12 form-group">
                  <input type="password" name="pass" id="pass" placeholder="Nueva Contraseña" disabled="true" title="Ingrese nueva contraseña" class="form-control" autofocus="true" onchange="prueba()">
                 <span id="ojopas" class="fa fa-eye" style="right: 30px"></span>
                </div>
                <div class="password col-sm-12 form-group">
                  <input type="password" id="repass" placeholder="Repita Nueva Contraseña" disabled="true" title="Repita nueva contraseña" class="form-control" autofocus="true">
                 <span id="ojore" class="fa fa-eye" style="right: 30px"></span>
                </div>
               </fieldset>
            </div>
            <button type="submit" id="enviar" class="btn btn-lg btn-principal btn-block margin1top">Modificar datos</button>
          </div>              
        </div>
      </form>
    </div>
  </div>
  <br>
  <br>
  <% } else {
      response.sendRedirect("login.jsp");
    }%>
  <!-- Footer -->
  <%@include file="footer.jsp"%>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script>
    function enableDisable(bEnable){
      $('#pass').prop( "disabled", !bEnable );
      $('#repass').prop( "disabled", !bEnable );
      $('#pass').css("backgroundColor", "white");
      $('#repass').css("backgroundColor", "white");
      
      if(!bEnable){
        $('#pass').val("");
        $('#repass').val("");
        $('#pass').prop('required',false);
        $('#repass').prop('required',false);
        $('#pass').css("backgroundColor", "#C3C3C3");
        $('#repass').css("backgroundColor", "#C3C3C3");
        $('#enviar').prop( "disabled", false );
      }else{
      		$('#pass').prop('required',true);
        $('#repass').prop('required',true);
      } 
    }    
    
    $('#pass').css("backgroundColor", "#C3C3C3");
    $('#repass').css("backgroundColor", "#C3C3C3");
    
    $("#repass").keyup(function(event){
    var pass1 = $('#pass').val();
    var pass2 = $('#repass');

    var goodColor = "#66cc66";
    var badColor = "#ff6666";

    if (pass1 === pass2.val()){
     $('#enviar').prop( "disabled", false );
     pass2.css("backgroundColor", goodColor);
    } else {
     $('#enviar').prop( "disabled", true );
     pass2.css("backgroundColor", badColor);
    }
  });
  
  $('#ojopas').on('touchstart click', function () {
    $("#pass").attr('type', 'text');
  }).on('touchend click', function () {
    $("#pass").attr('type', 'password');
  });
  
  $('#ojore').on('touchstart click', function () {
    $("#repass").attr('type', 'text');
  }).on('touchend click', function () {
    $("#repass").attr('type', 'password');
  });
  
  $("#pass").on("keyup", function () {
  if ($(this).val())
    $("#ojopas").show();
  else
    $("#ojopas").hide();
  });

  $("#ojopas").mousedown(function () {
    $("#pass").attr('type', 'text');
  }).mouseup(function () {
    $("#pass").attr('type', 'password');
  }).mouseout(function () {
    $("#pass").attr('type', 'password');
  });

  $("#repass").on("keyup", function () {
    if ($(this).val())
      $("#ojore").show();
    else
      $("#ojore").hide();
  });

  $("#ojore").mousedown(function () {
    $("#repass").attr('type', 'text');
  }).mouseup(function () {
    $("#repass").attr('type', 'password');
  }).mouseout(function () {
    $("#repass").attr('type', 'password');
  });
  </script>
</body>
