<!DOCTYPE html>

<head>

  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>BiciAmiga - Rosario</title>

  <!-- Bootstrap core CSS -->
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

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
  <br>
  <h1 class="site-heading text-center text-white d d-lg-block">
    <span class="site-heading-upper text-primary mb-3">CONTACTO</span>    
  </h1>
  <h5 class="text-center mb-3">
    <span class="site-heading-lower subtitulo">¿Querés hacernos algún comentario?¿Necesitas asesoramiento?<br></span>    
    <span class="site-heading-lower subtitulo">Envianos un mensaje y estaremos en contacto contigo</span>
  </h5>
  <div class="container text-center">
      <form class="form-text" method="POST" action="Contacto">
        <div class="col-lg-7 col-centered well" align='left' >
          <div class="row">
            <div class="col-sm-12">
              <div class="form-group">
                <label>&nbsp;Apellido y Nombre</label>
                <input type="text" name="apenom" id="apenom" placeholder="Apellido y Nombre" title="Ingrese su apellido y nombre" class="form-control" autofocus="true" required="true"
                  <% if (session.getAttribute("Usuario") != null) { %> value="<%=((Usuarios)session.getAttribute("Usuario")).getApynom()%>" <% } %>>
              </div>	
              <div class="form-group">
                <label>&nbsp;Email</label>
                <input type="email" name="email" id="email" placeholder="Email de contacto" title="Ingrese email de contacto" class="form-control" autofocus="true" required="true"
                       <% if (session.getAttribute("Usuario") != null) { %> value="<%=((Usuarios)session.getAttribute("Usuario")).getEmail()%>" <% } %>>
              </div>
              <div class="form-group">
                <label>&nbsp;Telefono</label>
                <input type="text" name="telefono" id="telefono" placeholder="Teléfono de contacto" title="Ingrese un número de contacto" class="form-control" autofocus="true" required="true"
                       <% if (session.getAttribute("Usuario") != null) { %> value="<%=((Usuarios)session.getAttribute("Usuario")).getTelefono()%>" <% } %>>
              </div>
              <div class="form-group">
                <label>&nbsp;Asunto / Motivo</label>
                <input type="text" name="asunto" id="asunto" placeholder="Asunto" title="Ingrese asunto del mensaje" class="form-control" autofocus="true" required="true">
              </div>
              <div class="form-group">
                <label>&nbsp;Mensaje / Comentario / Queja</label>
                <textarea name="mensaje" id="mensaje" maxlength="1600" placeholder="Mensaje" title="Detalle el mensaje"  class="form-control" autofocus="true" required="true" style="min-height: 300px;"></textarea>
              </div>
              <button type="submit" id="enviar" class="btn btn-lg btn-principal btn-block margin1top">Enviar mensaje</button>
            </div>              
          </div>
        </div>
      </form>
    </div>  

  <!-- Footer -->
  <%@include file="footer.jsp"%>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
