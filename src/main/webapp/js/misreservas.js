$(document).ready(function () {    
  $('#rpendientesxusr').DataTable({
    "language": {
      "decimal": ",",
      "search": "Buscar ",
      "emptyTable": "No se encontraron registros",
      "lengthMenu": "Registros por p\u00e1gina _MENU_",
      "zeroRecords": "No se encontraron registros",
      "info": " _PAGE_ de _PAGES_ ",
      "infoEmpty": "",
      "infoFiltered": " (Filtrados de un total de _MAX_ registros)",
      "paginate": {
        "first": "Primero",
        "last": "\u00daltimo",
        "next": "Siguiente",
        "previous": "Anterior"}
    },
    "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "Todos"]],
    "pagingType": "simple_numbers",
    "columns": [
      null,
      null,
      null,
      null,
      null,
      {"orderable": false},
      {"orderable": false}
    ]
  });
  
  $('#rfinalizadasxusr').DataTable({
    "language": {
      "decimal": ",",
      "search": "Buscar ",
      "emptyTable": "No se encontraron registros",
      "lengthMenu": "Registros por p\u00e1gina _MENU_",
      "zeroRecords": "No se encontraron registros",
      "info": " _PAGE_ de _PAGES_ ",
      "infoEmpty": "",
      "infoFiltered": " (Filtrados de un total de _MAX_ registros)",
      "paginate": {
        "first": "Primero",
        "last": "\u00daltimo",
        "next": "Siguiente",
        "previous": "Anterior"}
    },
    "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "Todos"]],
    "pagingType": "simple_numbers",
    "order": [[ 2, "asc" ]],
    "columns": [
      null,
      null,
      null,
      null,
      null,
      {"orderable": false}
    ]
  });  
});

function openPage(pageName, elmnt) {
  var i;
  var badColor = "#373a3c";
  abmtm = '';
  abmm = '';
    
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablink2");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }
  document.getElementById(pageName).style.display = "block";
  elmnt.style.backgroundColor = badColor;
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();

function nuevareserva() {
  window.open("reservar.jsp","_self");
}

function verReserva(usr, idReserva, bicicleta, url, inicio, fin, total){
  document.getElementById("titulo").innerHTML  = 'Reserva';
  document.getElementById('eliminarMR').style.display = "none";
  document.getElementById('verMR').style.display = "block";
  document.getElementById("tablamisreservas").style.display = "none";
  document.getElementById("verReserva").style.display = "block";
  document.getElementById("idR").innerHTML  = idReserva;
  document.getElementById("biciR").innerHTML  = bicicleta;
  document.getElementById("usrR").innerHTML  = usr;
  document.getElementById("turnoR").innerHTML  = inicio+" a "+fin;
  document.getElementById("precioR").innerHTML  = total;
  document.getElementById("imgR").src  = url;
  document.getElementById("id").value  = '';
}

function eliminarReserva(id, idusr, usr, idReserva, bicicleta, url, inicio, fin, total){
  document.getElementById("titulo").innerHTML  = 'Reserva';
  document.getElementById('eliminarMR').style.display = "block";
  document.getElementById('verMR').style.display = "none";
  document.getElementById("tablamisreservas").style.display = "none";
  document.getElementById("verReserva").style.display = "block";  
  document.getElementById("idR").innerHTML  = idReserva;
  document.getElementById("biciR").innerHTML  = bicicleta;
  document.getElementById("usrR").innerHTML  = usr;
  document.getElementById("turnoR").innerHTML  = inicio+" a "+fin;
  document.getElementById("precioR").innerHTML  = total;
  document.getElementById("imgR").src  = url;
  document.getElementById("id").value  = id;
  document.getElementById("idUsr").value  = idusr;
}

$("#cruzMR").mousedown(function () {
  document.getElementById("titulo").innerHTML  = 'historial de reserva';
  document.getElementById("verReserva").style.display = "none";
  document.getElementById("idR").innerHTML  = '';
  document.getElementById("biciR").innerHTML  = '';
  document.getElementById("usrR").innerHTML  = '';
  document.getElementById("turnoR").innerHTML  = '';
  document.getElementById("precioR").innerHTML  = '';
  document.getElementById("imgR").src  = 'img/imagen-vacia.jpg';
  document.getElementById("tablamisreservas").style.display = "block";
  document.getElementById('eliminarMR').style.display = "none";
  document.getElementById('verMR').style.display = "none";
  document.getElementById("id").value  = '';
  document.getElementById("idUsr").value  = '';
});

function closeMR(){
  document.getElementById("titulo").innerHTML  = 'historial de reserva';
  document.getElementById("verReserva").style.display = "none";
  document.getElementById("idR").value = '';
  document.getElementById("biciR").value = '';
  document.getElementById("usrR").value = '';
  document.getElementById("turnoR").value = '';
  document.getElementById("precioR").value = '';
  document.getElementById("imgR").value = 'img/imagen-vacia.jpg';
  document.getElementById("tablamisreservas").style.display = "block";
  document.getElementById('eliminarMR').style.display = "none";
  document.getElementById('verMR').style.display = "none";
  document.getElementById("id").value  = '';
  document.getElementById("idUsr").value  = '';
}