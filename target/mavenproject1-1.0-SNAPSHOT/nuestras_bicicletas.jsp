<%@page import="java.io.IOException"%>
<%@page import="Entidades.Modelos"%>
<%@page import="Entidades.TiposBicicleta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Negocio.ControladorBicicletas"%>
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
    <span class="site-heading-upper text-primary mb-3">Nuestras bicis</span>    
  </h1>
  <h3 class="text-center">
    <span class="site-heading-lower subtitulo">¿Conoces los tipos de bicicletas que ofrecemos?</span>    
  </h3>

  <%try {
      ArrayList<TiposBicicleta> tipos = new ControladorBicicletas().getTiposBicicleta();
      if (tipos.size() > 0) {
        for (TiposBicicleta t : tipos) {%>
  <section class="page-section">
    <div class="container">
      <h1 class="site-heading text-center text-white d d-lg-block">
        <span class="site-heading-upper mb-3 titulobicicleta"><%=t.getNombre()%></span>
      </h1>
      <div class="product-item-img rounded border image col-centered margin1bottom">
        <img src="<%=t.getUrl()%>">
      </div>
      <div class="product-item-description d-flex border">
        <div class="bg-bicis p-4 rounded">
          <p class="mb-0 descripBici"><b><%=t.getDescripcion()%></b></p>          
          <br>
          <div>
            <form method="POST" action="Reservar">
              <button type="submit" class="btn btn-reserva btn-xl bg-faded botonReservaNB descripBici" title="¡Reservala!">¡Reservala!</button>
            </form>
          </div>              
        </div>
      </div>       
    </div>
  </section>
  <%}
      } else {
        response.sendRedirect("error.jsp");
      }

    } catch (IOException ex) {
      response.sendRedirect("error.jsp");
    } catch (Exception ex) {
      response.sendRedirect("error.jsp");
    }%>

  <!-- Footer -->
  <%@include file="footer.jsp"%>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
