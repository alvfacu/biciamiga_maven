<%@page import="Entidades.*" %>
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
    <h1 class="site-heading text-center text-white d d-lg-block">
      <span class="site-heading-upper text-primary mb-3">Iniciar Sesión</span>
    </h1> 
    
    <%session.setAttribute("Usuario",null); %>
    
    <!-- Body -->
    <div class="container text-center col-lg-4 col-centered well">
      <form class="form-text" action="Login" method="POST">
        <%if(session.getAttribute("Msj") != null){ %>
          <div class="errorReserva margin04bottom"><b>Usuario y/o contraseña incorrectos</b></div>
        <% } session.setAttribute("Msj",null); %>
        <div class="col-sm-12" align="left">
          <label>&nbsp;Nombre de usuario</label>
          <input type="text" name="user" id="inputEmail" class="form-control margin04bottom" placeholder="Nombre de usuario" required autofocus>
        </div>
        <div class="col-sm-12" align="left">
          <label>&nbsp;Contraseña</label>
          <div class="password">
            <input type="password" name="pass" id="inputPassword" class="form-control" placeholder="Contraseña" required>
            <span class="fa fa-eye" ></span>
          </div>            
        </div>
        <div class="col-sm-12" align="center">          
          <button class="btn btn-lg btn-principal btn-block margin16top" type="submit">Ingresar</button>
          <a class="vertical"href="registrar.jsp">Aún no estoy registrado</a>
        </div>
      </form>
    </div>

    <!-- Footer -->
    <%@include file="footer.jsp"%>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script>
      $("#inputPassword").on("keyup",function(){
        if($(this).val())
          $(".fa-eye").show();
        else
          $(".fa-eye").hide();
      });
      
      $(".fa-eye").mousedown(function(){
        $("#inputPassword").attr('type','text');
      }).mouseup(function(){
        $("#inputPassword").attr('type','password');
      }).mouseout(function(){
        $("#inputPassword").attr('type','password');
            });
                        
       $('.fa-eye').
               on('touchstart click', function(){ $("#inputPassword").attr('type','text'); }).
               on('touchend click', function(){ $("#inputPassword").attr('type','password'); });
                        
    </script>
  </body>


