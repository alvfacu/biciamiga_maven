
$(document).ready(function () {  
  $('#rpendientes').DataTable({
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
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todos"]],
    "pagingType": "simple_numbers",
    "order": [[ 4, "asc" ]],
    "columns": [
      null,
      null,
      null,
      null,
      null,
      null,
      {"orderable": false},
      {"orderable": false},
      {"orderable": false}
    ]
  });
  
  $('#rencurso').DataTable({
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
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todos"]],
    "pagingType": "simple_numbers",
    "order": [[ 3, "asc" ]],
    "columns": [
      null,
      null,
      null,
      null,
      null,
      {"orderable": false},
      {"orderable": false},
      {"orderable": false}
    ]
  });

  $('#rfinalizadas').DataTable({
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
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todos"]],
    "pagingType": "simple_numbers",
    "order": [[ 3, "asc" ]],
    "columns": [
      null,
      null,
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
  tablinks = document.getElementsByClassName("tablink");
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

function verReserva(estado, idEstado, usr, idReserva, bicicleta, url, inicio, fin, total){
  document.getElementById('eliminarR').style.display = "none";
  document.getElementById('fallasR').style.display = "none";
  document.getElementById('fin').style.display = "none";
  document.getElementById('finalizarR').style.display = "none";
  document.getElementById('iniciarR').style.display = "none";
  document.getElementById('verR').style.display = "block";
  document.getElementById("tablareservas").style.display = "none";
  document.getElementById("verReserva").style.display = "block";
  document.getElementById("idR").innerHTML  = idReserva;
  document.getElementById("biciR").innerHTML  = bicicleta;
  document.getElementById("usrR").innerHTML  = usr;
  document.getElementById("diaR").innerHTML = inicio.substring(0, 10);
  document.getElementById("turnoR").innerHTML  = inicio.split(" ")[1].substring(0, 5)+" hasta "+fin.split(" ")[1].substring(0, 5);
  document.getElementById("precioR").innerHTML  = total;
  document.getElementById("estadoR").innerHTML  = estado;
  
  document.getElementById('msjFallas').style.display = "none";
  document.getElementById("msjFallas").innerHTML = "";
  
  if(idEstado === "1")
    document.getElementById("estadoR").className = "label label-cliente";
  else if(idEstado === "2")
    document.getElementById("estadoR").className = "label label-success";
  else if(idEstado === "3")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "4")
    document.getElementById("estadoR").className = "label label-success";
  else if(idEstado === "5")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "6")
    document.getElementById("estadoR").className = "label label-mecanico";
  else
    document.getElementById("estadoR").className = "label label-desconocido";
  
  document.getElementById("imgR").src  = url;
  document.getElementById("id").value  = '';
  document.getElementById('msj').style.display = "none";
}

function eliminarReserva(idR, estado, idEstado, usr, idReserva, bicicleta, url, inicio, fin, total){
  document.getElementById('eliminarR').style.display = "block";
  document.getElementById('fallasR').style.display = "none";
  document.getElementById('fin').style.display = "none";
  document.getElementById('finalizarR').style.display = "none";
  document.getElementById('iniciarR').style.display = "none";
  document.getElementById('verR').style.display = "none";
  document.getElementById("tablareservas").style.display = "none";
  document.getElementById("verReserva").style.display = "block";
  document.getElementById("idR").innerHTML  = idReserva;
  document.getElementById("biciR").innerHTML  = bicicleta;
  document.getElementById("usrR").innerHTML  = usr;
  document.getElementById("diaR").innerHTML = inicio.substring(0, 10);
  document.getElementById("turnoR").innerHTML  = inicio.split(" ")[1].substring(0, 5)+" hasta "+fin.split(" ")[1].substring(0, 5);
  document.getElementById("precioR").innerHTML  = total;
  document.getElementById("estadoR").innerHTML  = estado;
  document.getElementById('msjFallas').style.display = "none";
  document.getElementById("msjFallas").innerHTML = "";
  
  if(idEstado === "1")
    document.getElementById("estadoR").className = "label label-cliente";
  else if(idEstado === "2")
    document.getElementById("estadoR").className = "label label-success";
  else if(idEstado === "3")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "4")
    document.getElementById("estadoR").className = "label label-success";
  else if(idEstado === "5")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "6")
    document.getElementById("estadoR").className = "label label-mecanico";
  else
    document.getElementById("estadoR").className = "label label-desconocido";
  
  document.getElementById("imgR").src  = url;
  document.getElementById("id").value  = idR;
  document.getElementById('msj').style.display = "none";
}

function iniciarReserva(estado, idEstado, id, usr, idReserva, bicicleta, url, inicio, fin, total){
  document.getElementById('iniciarR').style.display = "none";
  document.getElementById('fallasR').style.display = "none";
  document.getElementById('fin').style.display = "none";
  document.getElementById('finalizarR').style.display = "none";
  document.getElementById('msj').style.display = "none";
  document.getElementById('eliminarR').style.display = "none";
  document.getElementById("tablareservas").style.display = "none";
  document.getElementById('verR').style.display = "none";
  document.getElementById('msjFallas').style.display = "none";
  document.getElementById("msjFallas").innerHTML = "";
  
  $.post('PuedeIniciarReserva', 
          {
            idReserva : id
          }, 
          function(responseText) 
          {
            //VALIDO
            if(responseText==="1")
            {
              document.getElementById('iniciarR').style.display = "none";
              document.getElementById("msj").innerHTML ="<b>\u00a1Atenci\u00f3n!</b> No se puede iniciar una reserva que no se encuentre entre la fecha/hora pactada.";
              document.getElementById('msj').style.display = "block";
            }
            else if(responseText==="2")
            {
              document.getElementById('iniciarR').style.display = "none";
              document.getElementById("msj").innerHTML ="<b>\u00a1Atenci\u00f3n!</b> No se puede iniciar la reserva porque la bicicleta se encuentra en Mantenimiento.";
              document.getElementById('msj').style.display = "block";
            }
            //INVALIDO
            else
            {
              document.getElementById('iniciarR').style.display = "block";
              document.getElementById('msj').style.display = "none";
            }
          });
  
  document.getElementById("estadoR").innerHTML  = estado;
  
  if(idEstado === "1")
    document.getElementById("estadoR").className = "label label-cliente";
  else if(idEstado === "2")
    document.getElementById("estadoR").className = "label label-success";
  else if(idEstado === "3")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "4")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "5")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "6")
    document.getElementById("estadoR").className = "label label-mecanico";
  else
    document.getElementById("estadoR").className = "label label-desconocido";
  
  document.getElementById('verReserva').style.display = "block"; 
  document.getElementById("idR").innerHTML  = idReserva;
  document.getElementById("biciR").innerHTML  = bicicleta;
  document.getElementById("usrR").innerHTML  = usr;
  document.getElementById("diaR").innerHTML = inicio.substring(0, 10);
  document.getElementById("turnoR").innerHTML  = inicio.split(" ")[1].substring(0, 5)+" hasta "+fin.split(" ")[1].substring(0, 5);
  document.getElementById("precioR").innerHTML  = total;
  document.getElementById("imgR").src  = url;
  document.getElementById("id").value  = id;
}

function finalizarReserva(km, estado, idEstado, id, usr, idReserva, bicicleta, url, inicio, fin, total){
  document.getElementById('iniciarR').style.display = "none";
  document.getElementById('fallasR').style.display = "none";
  document.getElementById('msj').style.display = "none";
  document.getElementById('msjFallas').style.display = "none";
  document.getElementById("msjFallas").innerHTML = "";
  document.getElementById('eliminarR').style.display = "none";
  document.getElementById("tablareservas").style.display = "none";
  document.getElementById('verR').style.display = "none";
  document.getElementById('finalizarR').style.display = "block";
  document.getElementById('fin').style.display = "block";
  document.getElementById('verReserva').style.display = "block"; 
  document.getElementById("estadoR").innerHTML  = estado;
    
  if(idEstado === "1")
    document.getElementById("estadoR").className = "label label-cliente";
  else if(idEstado === "2")
    document.getElementById("estadoR").className = "label label-success";
  else if(idEstado === "3")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "4")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "5")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "6")
    document.getElementById("estadoR").className = "label label-mecanico";
  else
    document.getElementById("estadoR").className = "label label-desconocido";
    
  document.getElementById("idR").innerHTML  = idReserva;
  document.getElementById("biciR").innerHTML  = bicicleta;
  document.getElementById("usrR").innerHTML  = usr;
  document.getElementById("diaR").innerHTML = inicio.substring(0, 10);
  document.getElementById("turnoR").innerHTML  = inicio.split(" ")[1].substring(0, 5)+" hasta "+fin.split(" ")[1].substring(0, 5);
  document.getElementById("precioR").innerHTML  = total;
  document.getElementById("imgR").src  = url;
  document.getElementById("id").value  = id;
  
  var d = new Date(), h = d.getHours(), m = d.getMinutes();
  document.getElementById('hr_fin').value = String(h).padStart(2, "0") + ':' + String(m).padStart(2, "0");
  
  var d = new Date(), dia = d.getDate(), mes = d.getMonth()+1, anio = d.getFullYear();
  document.getElementById('fec_fin').value = anio +'/'+ String(mes).padStart(2, "0") + '/' + String(dia).padStart(2, "0");
  
  document.getElementById('km_fin').value = parseFloat(km.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
  document.getElementById('km_fin').disabled = false;
  document.getElementById('km_fin').min = parseFloat(km.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
}

function fallasReserva(km, estado, idEstado, id, usr, idReserva, bicicleta, url, inicio, fin, total){
  document.getElementById('iniciarR').style.display = "none";
  document.getElementById('finalizarR').style.display = "none";
  document.getElementById('msj').style.display = "none";
  document.getElementById('eliminarR').style.display = "none";
  document.getElementById("tablareservas").style.display = "none";
  document.getElementById('verR').style.display = "none";
  
  document.getElementById('fin').style.display = "block";
  document.getElementById('verReserva').style.display = "block"; 
  document.getElementById('fallasR').style.display = "block";
  
  document.getElementById("estadoR").innerHTML  = estado;
    
  if(idEstado === "1")
    document.getElementById("estadoR").className = "label label-cliente";
  else if(idEstado === "2")
    document.getElementById("estadoR").className = "label label-success";
  else if(idEstado === "3")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "4")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "5")
    document.getElementById("estadoR").className = "label label-danger";
  else if(idEstado === "6")
    document.getElementById("estadoR").className = "label label-mecanico";
  else
    document.getElementById("estadoR").className = "label label-desconocido";
    
  document.getElementById("idR").innerHTML  = idReserva;
  document.getElementById("biciR").innerHTML  = bicicleta;
  document.getElementById("usrR").innerHTML  = usr;
  document.getElementById("diaR").innerHTML = inicio.substring(0, 10);
  document.getElementById("turnoR").innerHTML  = inicio.split(" ")[1].substring(0, 5)+" hasta "+fin.split(" ")[1].substring(0, 5);
  document.getElementById("precioR").innerHTML  = total;
  document.getElementById("imgR").src  = url;
  document.getElementById("id").value  = id;
  
  document.getElementById("msjFallas").innerHTML ="<b>Bicicleta ser\u00e1 enviada a Mantenimiento</b>";
  document.getElementById('msjFallas').style.display = "block";
  
  var d = new Date(), h = d.getHours(), m = d.getMinutes();
  document.getElementById('hr_fin').value = String(h).padStart(2, "0") + ':' + String(m).padStart(2, "0");
  
  var d = new Date(), dia = d.getDate(), mes = d.getMonth()+1, anio = d.getFullYear();
  document.getElementById('fec_fin').value = anio +'/'+ String(mes).padStart(2, "0") + '/' + String(dia).padStart(2, "0");
  
  document.getElementById('km_fin').value = parseFloat(km.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
  document.getElementById('km_fin').disabled = false;
  document.getElementById('km_fin').min = parseFloat(km.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
}

$("#cruzMR").mousedown(function () {
  document.getElementById("verReserva").style.display = "none";
  document.getElementById("idR").innerHTML  = '';
  document.getElementById("biciR").innerHTML  = '';
  document.getElementById("usrR").innerHTML  = '';
  document.getElementById("turnoR").innerHTML  = '';
  document.getElementById("diaR").innerHTML  = '';
  document.getElementById("precioR").innerHTML  = '';
  document.getElementById("imgR").src  = 'img/imagen-vacia.jpg';
  document.getElementById("tablareservas").style.display = "block";
  document.getElementById('eliminarR').style.display = "none";
  document.getElementById('verR').style.display = "none";
  document.getElementById("id").value  = '';
  document.getElementById('fallasR').style.display = "none";
  document.getElementById('fin').style.display = "none";
  document.getElementById('finalizarR').style.display = "none";
  document.getElementById('msjFallas').style.display = "none";
  document.getElementById("msjFallas").innerHTML = "";
});

function closeMR(){
  document.getElementById("verReserva").style.display = "none";
  document.getElementById("idR").value = '';
  document.getElementById("biciR").value = '';
  document.getElementById("usrR").value = '';
  document.getElementById("turnoR").innerHTML  = '';
  document.getElementById("diaR").innerHTML  = '';
  document.getElementById("precioR").value = '';
  document.getElementById("imgR").value = 'img/imagen-vacia.jpg';
  document.getElementById("tablareservas").style.display = "block";
  document.getElementById('eliminarR').style.display = "none";
  document.getElementById('verR').style.display = "none";
  document.getElementById("id").value  = '';
  document.getElementById('fallasR').style.display = "none";
  document.getElementById('fin').style.display = "none";
  document.getElementById('finalizarR').style.display = "none";
  document.getElementById('msjFallas').style.display = "none";
  document.getElementById("msjFallas").innerHTML = "";
}


