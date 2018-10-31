<!DOCTYPE html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
    
    <h1 class="site-heading text-center text-white d-none d-lg-block">
      <span class="site-heading-upper text-primary mb-3">Recorre Rosario de la manera más cómoda</span>
      <a class="site-heading-lower" href="index.jsp" style="text-decoration: none;color: #FFFFFF;" >BiciAmiga</a>
    </h1>
    
    <%@page import="Entidades.Usuarios"%>
    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="mainNav">
      <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="index.jsp">BiciAmiga</a>
        <button class="navbar-toggler" type="button" id="btn1">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav mx-auto">
            <li class="nav-item px-lg-4" id="index">
              <a class="nav-link text-uppercase text-expanded" href="index.jsp">Inicio
                <span class="sr-only">(current)</span>
              </a>
            </li>
            <li class="nav-item px-lg-4" id="comofun">
              <a class="nav-link text-uppercase text-expanded" href="index.jsp#como">¿Cómo funciona?</a>
            </li>
            <li class="nav-item px-lg-4" id="nuestras_bicicletas">
              <a class="nav-link text-uppercase text-expanded" href="nuestras_bicicletas.jsp">Nuestras Bicis</a>
            </li>
            <li class="nav-item px-lg-4" id="reservar">
              <form method="POST" action="Reservar">
                <button type="submit" class="nav-link text-uppercase text-expanded" style="color: #ffc107;border:none;background-color: #321811;">Reservar</button>
              </form>
            </li>                        
            <li class="nav-item px-lg-4" id="contacto">
              <a class="nav-link text-uppercase text-expanded" href="contacto.jsp">Contacto</a>
            </li>
            <% if (session.getAttribute("Usuario") == null) {%>
            <li class="nav-item px-lg-4" id="login">
              <a class="nav-link text-uppercase text-expanded" href="login.jsp">Ingresa / Registrate</a>
            </li>
            <%}%>
          </ul>
        </div>
      </div>
    </nav>

    <!-- Sesson Navigation -->
    <% if (session.getAttribute("Usuario") != null) {
      Usuarios usrActual = (Usuarios)session.getAttribute("Usuario");%>
    <nav class="navbar navbar-expand-lg navbar-dark py-lg-4" id="sessionNav">
      <div class="container">
        <a class="navbar-brand text-uppercase text-expanded font-weight-bold d-lg-none" href="mi_cuenta.jsp">
          <span class="fa fa-user-circle-o"></span> <%= usrActual.getUsuario()%>
        </a>
        <button class="navbar-toggler" type="button" id="btn2">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="sesbarResponsive">
          <ul class="navbar-nav mx-auto">
            <% if(usrActual.isAdm()){%>
            <li class="nav-item px-lg-4" id="admbici">
              <a class="nav-link text-uppercase text-expanded" href="admbici.jsp">Bicicletas</a>
            </li>    
            <li class="nav-item px-lg-4" id="admusr">
              <a class="nav-link text-uppercase text-expanded" href="admusr.jsp">Usuarios</a>
            </li>
            <li class="nav-item px-lg-4" id="admres">
              <a class="nav-link text-uppercase text-expanded" href="admres.jsp">Reservas</a>
            </li>
            <%}if(usrActual.isMecanico()){%>
            <li class="nav-item px-lg-4" id="admmant">
              <a class="nav-link text-uppercase text-expanded" href="admmant.jsp">Mantenimientos</a>
            </li>
            <%}if(!usrActual.isAdm() && !usrActual.isMecanico()){%>
            <li class="nav-item px-lg-4" id="mis_reservas">
              <a class="nav-link text-uppercase text-expanded" href="mis_reservas.jsp">Mis reservas</a>
            </li>
            <%}%>
            <li class="nav-item px-lg-4" id="mi_cuenta">
              <a class="nav-link text-uppercase text-expanded" href="mi_cuenta.jsp">Mi Cuenta</a>
            </li>
            <li class="nav-item px-lg-4">
              <a class="nav-link text-uppercase text-expanded" href="Logout">Cerrar Sesión</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>  
    <%}%>
    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script>
      $("#btn1").click(function ()
      {
        $("#navbarResponsive").toggleClass("show");
      });
      
      $("#btn2").click(function ()
      {
        $("#sesbarResponsive").toggleClass("show");
      });
      
      $(function() {        
        $('#' + location.pathname.split("/")[2].replace('.jsp','')).toggleClass('active');
        
        var url = location.pathname.split("/")[2].replace('.jsp','');
                
        if (url === "registrar") {
          $("#login").toggleClass('active');           
        };
        
        if (url === "mantxbici" || url === "nuevoMantenimiento" || url === "verMantenimiento" || url === "modificarMantenimiento" || url === "eliminarMantenimiento") {
          $("#admmant").toggleClass('active');           
        };
        
        if (url === "Reservar" || url === "PreReserva" || url==="AltaReserva" || url==="reservar" ) {
          $("#admres").toggleClass('active');
          $("#mis_reservas").toggleClass('active');
        };
        
      });
    </script>
  </body>
