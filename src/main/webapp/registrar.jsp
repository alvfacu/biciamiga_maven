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
      <span class="site-heading-upper text-primary mb-3">Formulario de registro</span>
    </h1> 
    <div class="container text-center">
      <form class="form-text" method="POST" action="AltaUsuario" id="frmAltaUsr">
        <div class="col-lg-7 col-centered well">
          <div class="row">
            <div class="col-sm-12" align="left">
              <input type="hidden" name="admin" value="false">
              <input type="hidden" name="meca" value="false">
              <input type="hidden" name="cliente" value="true">
              <input type="hidden" name="habilitado" value="true">              
              <div class="form-group">
                <label>&nbsp;Nombre de usuario</label>
                <input type="text" name="usuario" id="usuario" placeholder="Usuario" title="Ingrese nombre de usuario" class="form-control" autofocus="true" required="true">
              </div>
              <div class="row">
                <div class="password col-sm-6 form-group">
                  <label>&nbsp;Contraseña</label>
                  <input type="password" name="pass" id="pass" placeholder="Contraseña" title="Ingrese una contraseña" class="form-control" autofocus="true" required="true">
                  <span id="ojopas" class="fa fa-eye" style="right: 25px;top: 42px;"></span>
                </div>	
                <div class="password col-sm-6 form-group">
                  <label>&nbsp;Repita contraseña</label>
                  <input type="password" name="repass" id="repass" placeholder="Repita Contraseña" title="Repita contraseña" class="form-control" autofocus="true" required="true">
                  <span id="ojore" class="fa fa-eye" style="right: 25px;top: 42px;"></span>
                </div>
              </div>
              <div class="form-group">
                <label>&nbsp;Apellido y Nombre</label>
                <input type="text" name="apenom" id="apenom" placeholder="Apellido y Nombre" title="Ingrese su apellido y nombre" class="form-control" autofocus="true" required="true">
              </div>			
              <div class="form-group">
                <label>&nbsp;Domicilio</label>
                <input type="text" name="domicilio" id="domicilio" placeholder="Domicilio" title="Ingrese su Domicilio" class="form-control" autofocus="true" required="true">
              </div>
              <div class="form-group">
                <label>&nbsp;Documento de Identidad</label>
                <input type="text" name="documento" id="documento" placeholder="Documento de Identidad" title="Ingrese su documento de identidad" class="form-control" autofocus="true" required="true">
              </div>
              <div class="form-group">
                <label>&nbsp;Teléfono de contacto</label>
                <input type="text" name="telefono" id="telefono" placeholder="Teléfono de contacto" title="Ingrese un número de contacto" class="form-control" autofocus="true" required="true">
              </div>		
              <div class="form-group">
                <label>&nbsp;Email</label>
                <input type="email" name="email" id="email" placeholder="Email de contacto" title="Ingrese su email" class="form-control" autofocus="true" required="true">
              </div>
              <!--button type="submit" id="enviar" class="btn btn-lg btn-primary btn-block margin1top" >Registrarse en BiciAmiga</button-->
              <input type="button" id="enviar" value="Registrarse en BiciAmiga" onclick="validar()" class="btn btn-lg btn-primary btn-block margin1top" />
            </div>              
          </div>
        </div>
      </form>
    </div>
    <br>
    <br>

    <!-- Footer -->
    <%@include file="footer.jsp"%>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="js/existeUsuario.js"></script>
    <script src="js/registroNuevoUsr.js"></script>
    
    <script language="javascript" type="text/javascript">
    function validar(){
      //alert("entered function");
      
      var pass1 = document.getElementById("pass").value;
      var pass2 = document.getElementById("repass").value;
      
      if(pass1 !== pass2)
      {
        //alert("contraseñas distintas");
        document.getElementById("enviar").disabled = true;
        document.getElementById("repass").style.backgroundColor = "#ff6666";
        document.getElementById("repass").focus();
      }
      else
      {
        //#66cc66
        //alert(document.getElementById("usuario").style.backgroundColor);  
        var rgb = "rgb("+hexToRgb("#66cc66").r+", "+hexToRgb("#66cc66").g+", "+hexToRgb("#66cc66").b+")";
        //alert(rgb);
        if(document.getElementById("usuario").style.backgroundColor !== rgb)
        {
          document.getElementById("enviar").disabled = true;
          document.getElementById("usuario").style.backgroundColor = "#ff6666";
          document.getElementById("usuario").focus();
        }
        else
          document.getElementById("frmAltaUsr").submit();
      }
    }
    
    function hexToRgb(hex) {
        var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
        return result ? {
            r: parseInt(result[1], 16),
            g: parseInt(result[2], 16),
            b: parseInt(result[3], 16)
        } : null;
    }
  
    </script>
    
  </body>
