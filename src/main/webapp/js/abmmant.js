var abmtm = '';
var abmm = '';

$(document).ready(function () {  
  $('#tipos_mant').DataTable({
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
    "columns": [
      null,
      null,
      {"orderable": false},
      {"orderable": false},
      {"orderable": false}
    ]
  });

  $('#mantenimientos').DataTable({
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
     "order": [[ 1, "asc" ]],
    "columns": [
      null,
      null,
      null,
      {"orderable": false},
      {"orderable": false}
    ]
  });
  
  $('#mantenimientosfin').DataTable({
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
     "order": [[ 1, "asc" ]],
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
  tablinks = document.getElementsByClassName("tablink");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].style.backgroundColor = "";
  }
  document.getElementById(pageName).style.display = "block";
  elmnt.style.backgroundColor = badColor;
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();

function nuevotipo() { 
  document.getElementById("tablamantenimientos").style.display = "none";

  document.getElementById('editartm').style.display = "none";
  document.getElementById('eliminartm').style.display = "none";
  document.getElementById('guardartm').style.display = "block";
  document.getElementById('vertm').style.display = "none";
  
  document.getElementById('nombretm').disabled = false;
  document.getElementById('descriptm').disabled = false;
  document.getElementById('obligatorio').disabled = false;
  document.getElementById('km').disabled = true;
  
  document.getElementById('nombretm').value = '';
  document.getElementById('descriptm').value = '';
  document.getElementById('obligatorio').value = '';
  document.getElementById('km').value = '';
  document.getElementById('msj1').style.display = "none";

  if (abmtm !== 'A') {
    document.getElementById("nuevotipo").style.display = "block";
    abmtm = 'A';
    document.getElementById('nombretm').focus();
  } else {
    document.getElementById("nuevotipo").style.display = "none";
    abmtm = '';
  }
}

function editartipo(id, nombre, descrip, obligatorio, km) {
  document.getElementById("tablamantenimientos").style.display = "none";

  document.getElementById('msj1').style.display = "none";
  
  document.getElementById('nombretm').disabled = false;
  document.getElementById('descriptm').disabled = false;
  document.getElementById('obligatorio').disabled = false;
  
  document.getElementById('vertm').style.display = "none";
  document.getElementById('guardartm').style.display = "none";
  document.getElementById('editartm').style.display = "block";
  document.getElementById('eliminartm').style.display = "none";

  if (abmtm !== 'M' || document.getElementById('idtm').value !== id) {
    //abre form
    abmtm = 'M';
    document.getElementById("nuevotipo").style.display = "block";
    document.getElementById('idtm').value = id;
    document.getElementById('nombretm').value = nombre;
    document.getElementById('descriptm').value = descrip;
    document.getElementById('obligatorio').value = obligatorio;
    if(obligatorio === "true")
    {
      document.getElementById('km').disabled = false;
      document.getElementById('km').value = parseFloat(km.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    }
    else
    {
      document.getElementById('km').disabled = true;
      document.getElementById('km').value = '';
    } 
    document.getElementById('nombretm').focus();
  } 
}

function eliminartipo(id, nombre, descrip, obligatorio, km) {
  document.getElementById("tablamantenimientos").style.display = "none";

  document.getElementById('guardartm').style.display = "none";
  document.getElementById('editartm').style.display = "none";
  document.getElementById('eliminartm').style.display = "block";
  document.getElementById('vertm').style.display = "none";

  if (abmtm !== 'B' || document.getElementById('idtm').value !== id) {
    //abre form
    document.getElementById("nuevotipo").style.display = "block";
    abmtm = 'B';
    document.getElementById('idtm').value = id;
    document.getElementById('nombretm').value = nombre;
    document.getElementById('descriptm').value = descrip;
    document.getElementById('obligatorio').value = obligatorio;
    document.getElementById('km').value = parseFloat(km.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);

    document.getElementById('nombretm').disabled = true;
    document.getElementById('descriptm').disabled = true;
    document.getElementById('obligatorio').disabled = true;
    document.getElementById('km').disabled = true;
  } 

  $.post('ExistenMantenimientosXTipo',
          {
            id: $('#idtm').val()
          },
          function (responseText)
          {
            //VALIDO
            if (responseText === "0")
            {
              document.getElementById('msj1').style.display = "none";
            }
            //INVALIDO
            else
            {
              document.getElementById('msj1').style.display = "block";
              document.getElementById('msj1').innerHTML = "<b>\u00a1ATENCI\u00d3N! </b> Existen mantenimientos en vigencia.<br>Al eliminarlo, se eliminar\u00e1n todos los mantenimientos de este tipo.";
            }
          });
}

function vertipo(id, nombre, descrip, obligatorio, km) {
  document.getElementById("tablamantenimientos").style.display = "none";

  document.getElementById('guardartm').style.display = "none";
  document.getElementById('editartm').style.display = "none";
  document.getElementById('eliminartm').style.display = "none";
  document.getElementById('vertm').style.display = "block";

  if (abmtm !== 'V' || document.getElementById('idtm').value !== id) {
    //abre form
    document.getElementById("nuevotipo").style.display = "block";
    abmtm = 'V';
    document.getElementById('idtm').value = id;
    document.getElementById('nombretm').value = nombre;
    document.getElementById('descriptm').value = descrip;
    document.getElementById('obligatorio').value = obligatorio;
    document.getElementById('km').value = parseFloat(km.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);

    document.getElementById('nombretm').disabled = true;
    document.getElementById('descriptm').disabled = true;
    document.getElementById('obligatorio').disabled = true;
    document.getElementById('km').disabled = true;
  }
}

function nuevomantenimiento() {
  window.open("nuevoMantenimiento.jsp","_self");
}

function mantexbici() {
  window.open("mantxbici.jsp","_self");
}

$("#cruzNuevoTM").mousedown(function () {
  document.getElementById("nuevotipo").style.display = "none";
  abmtm = '';
  document.getElementById("tablamantenimientos").style.display = "block";
});


function habilitarKM() {
    if(document.getElementById("obligatorio").value === "true")
    {
      document.getElementById("km").disabled = false;
      document.getElementById("km").value = "0,00";
      document.getElementById("km").required = true;
    }
    else
    {
      document.getElementById("km").disabled = true;
      document.getElementById("km").value = "0,00";
      document.getElementById("km").required = false;
    }
}