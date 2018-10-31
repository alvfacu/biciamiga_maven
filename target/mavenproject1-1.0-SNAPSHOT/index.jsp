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

  <style>
    #map {
      height: 350px;
      width: 100%;
    }
  </style>

</head>

<body>
  <!-- Navigation -->
  <%@include file="nav_bar.jsp"%>

  <!-- Body -->
  <section class="page-section clearfix">
    <div class="container">
      <div class="intro">
        <img class="intro-img img-fluid mb-3 mb-lg-0 rounded" src="img/intro.jpg" alt="">
        <div class="intro-text left-0 text-center bg-faded p-5 rounded">
          <img src="img/favicon.ico" alt="BiciAmiga" width="200">
          <h2 class="section-heading mb-4">              
            <span class="section-heading-lower">BiciAmiga</span>
            <span class="section-heading-upper">Nosotros</span>
          </h2>
          <p class="mb-3"><b>BiciAmiga</b> es una empresa de turismo y de alquiler de bicicletas, emplazada en el microcentro de la ciudad de Rosario, provincia de Santa Fe.<br><br>Somos un centro de información turístico, en el que extranjeros y locales pueden acercarse y obtener información para facilitar su paso por la ciudad.</p>
        </div>
      </div>
    </div>
  </section>
  <br>  
  <div id="como"></div>
  <br>
  <section class="page-section cta">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="section-heading text-uppercase">¿Cómo funciona?</h2>
          <p class="mb-3">Reserva en BiciAmiga en tres simples pasos</p>
        </div>
      </div>
      <div class="row text-center">
        <div class="col-md-4">
          <span class="fa-stack fa-4x">
            <i class="fa fa-circle fa-stack-2x text-icons"></i>
            <i class="fa fa-mouse-pointer fa-stack-1x fa-inverse"></i>
          </span> 
          <h4 class="service-heading">1er PASO</h4>
          <form method="POST" action="Reservar">
            <p class="mb-3">Haz click en <button type="submit" class="label label-reserva" style="text-decoration: none;color: #FFFFFF;">Reservar</button></p>
          </form>
        </div>
        <div class="col-md-4">
          <span class="fa-stack fa-4x">
            <i class="fa fa-circle fa-stack-2x text-icons"></i>
            <i class="fa fa-pencil-square-o fa-stack-1x fa-inverse"></i>
          </span>
          <h4 class="service-heading">2do PASO</h4>
          <p class="mb-3">Selecciona la bicicleta, la fecha y <br> la cantidad de horas que queres usarla</p>            
        </div>
        <div class="col-md-4">
          <span class="fa-stack fa-4x">
            <i class="fa fa-circle fa-stack-2x text-icons"></i>
            <i class="fa fa-bicycle fa-stack-1x fa-inverse"></i>
          </span>
          <h4 class="service-heading">3er PASO</h4>
          <p class="mb-3">Confirma tu reserva. El día elegido acercate con tu comprobante y tu identificación</p>                        
        </div>
      </div>
    </div>
  </section>
  
  <section class="page-section clearfix">
    <div class="container">
      <h1 class="site-heading text-center text-white d d-lg-block">
        <span class="site-heading-upper text-primary mb-3">Acercate a nuestro local</span>
      </h1>
      <div id="map"></div>
    </div>
  </section>

  <!-- Footer -->
  <%@include file="footer.jsp"%>

  <!-- Bootstrap core JavaScript -->
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script>
    function initMap() {
      var uluru = {lat: -32.954392, lng: -60.643802};
      var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: uluru
      });

      var contentString = '<div id="content">' +
              '<div id="siteNotice">' +
              '</div>' +
              '<strong>BiciAmiga</strong><br>' +
              '<div id="bodyContent">' +
              'Zeballos 1341<br>' +
              '2000 Rosario, Santa Fe' +
              '</div>' +
              '</div>';

      var infowindow = new google.maps.InfoWindow({
        content: contentString
      });

      var marker = new google.maps.Marker({
        position: uluru,
        map: map,
        title: 'BiciAmiga'
      });

      marker.addListener('click', function () {
        infowindow.open(map, marker);
      });

      infowindow.open(map, marker);
    }
  </script>
  <script async defer
          src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCBy52O248NVTAreNPSQnH_Khbt7pYI-go&callback=initMap">
  </script>  
</body>