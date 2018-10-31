var abmtb = '';
var abmm = '';
var abmb = '';

$(document).ready(function () {
  $('#tiposbici').DataTable({
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
      {"orderable": false},
      {"orderable": false}
    ]
  });

  $('#bicicletas').DataTable({
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
      null,
      null,
      null,
      null,
      {"orderable": false},
      {"orderable": false},
      {"orderable": false}
    ]
  });

  $('#modelos').DataTable({
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
      {"orderable": false},
      {"orderable": false}
    ]
  });
});

function openPage(pageName, elmnt) {
  var i;
  var badColor = "#373a3c";
  abmtb = '';
  abmm = '';
  abmb = '';

  document.getElementById("nuevomodelo").style.display = "none";
  document.getElementById("nuevotipo").style.display = "none";
  document.getElementById("nuevabici").style.display = "none";

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
  document.getElementById("nuevomodelo").style.display = "none";
  document.getElementById("nuevabici").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";

  document.getElementById('editartb').style.display = "none";
  document.getElementById('eliminartb').style.display = "none";
  document.getElementById('guardartb').style.display = "block";
  document.getElementById('nombretb').disabled = false;
  document.getElementById('descriptb').disabled = false;
  document.getElementById('urlPortada').disabled = false;
  document.getElementById('nombretb').value = '';
  document.getElementById('descriptb').value = '';
  document.getElementById('idtb').value = '';
  document.getElementById('urlPortada').value = '';
  document.getElementById('portada').src = "img/imagen-vacia.jpg";
  document.getElementById('msj1').style.display = "none";

  if (abmtb !== 'A') {
    document.getElementById("nuevotipo").style.display = "block";
    abmtb = 'A';
    document.getElementById('nombretb').focus();
  } else {
    document.getElementById("nuevotipo").style.display = "none";
    abmtb = '';
  }
}

function editartipo(id, nombre, descrip, url) {
  document.getElementById("nuevomodelo").style.display = "none";
  document.getElementById("nuevabici").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";
  document.getElementById('msj1').style.display = "none";
  document.getElementById('nombretb').disabled = false;
  document.getElementById('descriptb').disabled = false;
  document.getElementById('urlPortada').disabled = false;
  document.getElementById('guardartb').style.display = "none";
  document.getElementById('editartb').style.display = "block";
  document.getElementById('eliminartb').style.display = "none";

  if (abmtb !== 'M' || document.getElementById('idtb').value !== id) {
    //abre form
    abmtb = 'M';
    document.getElementById("nuevotipo").style.display = "block";
    document.getElementById('idtb').value = id;
    document.getElementById('nombretb').value = nombre;
    document.getElementById('descriptb').value = descrip;
    document.getElementById('urlPortada').value = url;

    if (url)
      document.getElementById('portada').src = url;
    else
      document.getElementById('portada').src = "img/imagen-vacia.jpg";

    document.getElementById('nombretb').focus();
  } else {
    //cerrar form
    abmtb = '';
    document.getElementById("nuevotipo").style.display = "none";
    document.getElementById('idtb').value = '';
    document.getElementById('nombretb').value = '';
    document.getElementById('descriptb').value = '';
    document.getElementById('urlPortada').value = '';
    document.getElementById('portada').src = "img/imagen-vacia.jpg";
  }
}

function eliminartipo(id, nombre, descrip, url) {
  document.getElementById("nuevomodelo").style.display = "none";
  document.getElementById("nuevabici").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";
  document.getElementById('guardartb').style.display = "none";
  document.getElementById('editartb').style.display = "none";
  document.getElementById('eliminartb').style.display = "block";

  if (abmtb !== 'B' || document.getElementById('idtb').value !== id) {
    //abre form
    document.getElementById("nuevotipo").style.display = "block";
    abmtb = 'B';
    document.getElementById('idtb').value = id;
    document.getElementById('nombretb').value = nombre;
    document.getElementById('descriptb').value = descrip;
    document.getElementById('urlPortada').value = url;

    if (url)
      document.getElementById('portada').src = url;
    else
      document.getElementById('portada').src = "img/imagen-vacia.jpg";

    document.getElementById('nombretb').disabled = true;
    document.getElementById('descriptb').disabled = true;
    document.getElementById('urlPortada').disabled = true;
    document.getElementById('eliminartb').focus();
  } else {
    //cerrar form
    document.getElementById("nuevotipo").style.display = "none";
    abmtb = '';
    document.getElementById('idtb').value = '';
    document.getElementById('nombretb').value = '';
    document.getElementById('descriptb').value = '';
    document.getElementById('urlPortada').value = '';
    document.getElementById('portada').src = "img/imagen-vacia.jpg";
    document.getElementById('nombretb').disabled = false;
    document.getElementById('descriptb').disabled = false;
    document.getElementById('urlPortada').disabled = false;
  }

  $.post('ExistenModelosXTipo',
          {
            id: $('#idtb').val()
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
              document.getElementById('msj1').innerHTML = "<b>\u00a1ATENCI\u00d3N!</b> Existen modelos de bicicletas registradas para este tipo.<br>Al eliminarlo, se eliminar\u00e1n automaticamente todos los modelos de este tipo.";
            }
          });
}

function nuevomodelo() {
  document.getElementById("nuevotipo").style.display = "none";
  document.getElementById("nuevabici").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";

  document.getElementById('msj2').style.display = "none";
  document.getElementById('editarm').style.display = "none";
  document.getElementById('eliminarm').style.display = "none";
  document.getElementById('guardarm').style.display = "block";
  document.getElementById('clr').disabled = false;
  document.getElementById('nombrem').disabled = false;
  document.getElementById('descripm').disabled = false;
  document.getElementById('precioHr').disabled = false;
  document.getElementById('precioDia').disabled = false;
  document.getElementById('url1').disabled = false;
  document.getElementById('url2').disabled = false;
  document.getElementById('url3').disabled = false;
  document.getElementById('imgbici1').src = "img/imagen-vacia.jpg";
  document.getElementById('imgbici2').src = "img/imagen-vacia.jpg";
  document.getElementById('imgbici3').src = "img/imagen-vacia.jpg";

  document.getElementById('clr').value = '';
  document.getElementById('nombrem').value = '';
  document.getElementById('descripm').value = '';
  document.getElementById('precioHr').value = '';
  document.getElementById('precioDia').value = '';
  document.getElementById('url1').value = '';
  document.getElementById('url2').value = '';
  document.getElementById('url3').value = '';

  if (abmm !== 'A') {
    document.getElementById("nuevomodelo").style.display = "block";
    abmm = 'A';
    document.getElementById('clr').focus();
  } else {
    document.getElementById("nuevomodelo").style.display = "none";
    abmm = '';
  }
}

function editarmodelo(id, idTipo, nombre, caract, preciohr, preciodia, url1, url2, url3) {
  document.getElementById("nuevotipo").style.display = "none";
  document.getElementById("nuevabici").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";

  document.getElementById('msj2').style.display = "none";
  document.getElementById('clr').disabled = false;
  document.getElementById('nombrem').disabled = false;
  document.getElementById('descripm').disabled = false;
  document.getElementById('precioHr').disabled = false;
  document.getElementById('precioDia').disabled = false;
  document.getElementById('url1').disabled = false;
  document.getElementById('url2').disabled = false;
  document.getElementById('url3').disabled = false;
  document.getElementById('guardarm').style.display = "none";
  document.getElementById('editarm').style.display = "block";
  document.getElementById('eliminarm').style.display = "none";

  if (abmm !== 'M' || document.getElementById('idm').value !== id) {
    //abre form
    abmm = 'M';
    document.getElementById("nuevomodelo").style.display = "block";
    document.getElementById('idm').value = id;
    document.getElementById('clr').value = idTipo;
    document.getElementById('nombrem').value = nombre;
    document.getElementById('descripm').value = caract;
    document.getElementById('precioHr').value = parseFloat(preciohr.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    document.getElementById('precioDia').value = parseFloat(preciodia.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);

    if (url1)
    {
      document.getElementById('url1').value = url1;
      document.getElementById('imgbici1').src = url1;
    } else
    {
      document.getElementById('url1').value = url1;
      document.getElementById('imgbici1').src = "img/imagen-vacia.jpg";
    }
    if (url2)
    {
      document.getElementById('url2').value = url2;
      document.getElementById('imgbici2').src = url2;
    } else
    {
      document.getElementById('url2').value = url2;
      document.getElementById('imgbici2').src = "img/imagen-vacia.jpg";
    }
    if (url3)
    {
      document.getElementById('url3').value = url3;
      document.getElementById('imgbici3').src = url3;
    } else
    {
      document.getElementById('url3').value = url3;
      document.getElementById('imgbici3').src = "img/imagen-vacia.jpg";
    }
    document.getElementById('clr').focus();
  } else {
    //cerrar form
    abmm = '';
    document.getElementById("nuevomodelo").style.display = "none";
    document.getElementById('idm').value = '';
    document.getElementById('clr').value = '';
    document.getElementById('nombrem').value = '';
    document.getElementById('descripm').value = '';
    document.getElementById('precioHr').value = '';
    document.getElementById('precioDia').value = '';
    document.getElementById('url1').value = '';
    document.getElementById('url2').value = '';
    document.getElementById('url3').value = '';
    document.getElementById('imgbici1').src = "img/imagen-vacia.jpg";
    document.getElementById('imgbici2').src = "img/imagen-vacia.jpg";
    document.getElementById('imgbici3').src = "img/imagen-vacia.jpg";
  }
}

function eliminarmodelo(id, idTipo, nombre, caract, preciohr, preciodia, url1, url2, url3) {
  document.getElementById("nuevotipo").style.display = "none";
  document.getElementById("nuevabici").style.display = "none";
  
  document.getElementById("tablabicicletas").style.display = "none";
  
  document.getElementById('guardarm').style.display = "none";
  document.getElementById('eliminarm').style.display = "block";
  document.getElementById('editarm').style.display = "none";

  if (abmm !== 'B' || document.getElementById('idm').value !== id) {
    //abre form
    abmm = 'B';
    document.getElementById("nuevomodelo").style.display = "block";
    document.getElementById('idm').value = id;
    document.getElementById('clr').value = idTipo;
    document.getElementById('nombrem').value = nombre;
    document.getElementById('descripm').value = caract;
    document.getElementById('precioHr').value = parseFloat(preciohr.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    document.getElementById('precioDia').value = parseFloat(preciodia.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    document.getElementById('clr').disabled = true;
    document.getElementById('nombrem').disabled = true;
    document.getElementById('descripm').disabled = true;
    document.getElementById('precioHr').disabled = true;
    document.getElementById('precioDia').disabled = true;
    document.getElementById('url1').disabled = true;
    document.getElementById('url2').disabled = true;
    document.getElementById('url3').disabled = true;

    if (url1)
    {
      document.getElementById('url1').value = url1;
      document.getElementById('imgbici1').src = url1;
    } else
    {
      document.getElementById('url1').value = url1;
      document.getElementById('imgbici1').src = "img/imagen-vacia.jpg";
    }
    if (url2)
    {
      document.getElementById('url2').value = url2;
      document.getElementById('imgbici2').src = url2;
    } else
    {
      document.getElementById('url2').value = url2;
      document.getElementById('imgbici2').src = "img/imagen-vacia.jpg";
    }
    if (url3)
    {
      document.getElementById('url3').value = url3;
      document.getElementById('imgbici3').src = url3;
    } else
    {
      document.getElementById('url3').value = url3;
      document.getElementById('imgbici3').src = "img/imagen-vacia.jpg";
    }
    document.getElementById('eliminarm').focus();
  } else {
    //cerrar form
    abmm = '';
    document.getElementById("nuevomodelo").style.display = "none";
    document.getElementById('idm').value = '';
    document.getElementById('clr').value = '';
    document.getElementById('nombrem').value = '';
    document.getElementById('descripm').value = '';
    document.getElementById('precioHr').value = '';
    document.getElementById('precioDia').value = '';
    document.getElementById('url1').value = '';
    document.getElementById('url2').value = '';
    document.getElementById('url3').value = '';
    document.getElementById('clr').disabled = false;
    document.getElementById('nombrem').disabled = false;
    document.getElementById('descripm').disabled = false;
    document.getElementById('precioHr').disabled = false;
    document.getElementById('precioDia').disabled = false;
    document.getElementById('url1').disabled = false;
    document.getElementById('url2').disabled = false;
    document.getElementById('url3').disabled = false;
    document.getElementById('imgbici1').src = "img/imagen-vacia.jpg";
    document.getElementById('imgbici2').src = "img/imagen-vacia.jpg";
    document.getElementById('imgbici3').src = "img/imagen-vacia.jpg";
  }

  $.post('ExistenBicicletasXModelo',
          {
            id: $('#idm').val()
          },
          function (responseText)
          {
            //VALIDO
            if (responseText === "0")
            {
              document.getElementById('msj2').style.display = "none";
            }
            //INVALIDO
            else
            {
              document.getElementById('msj2').style.display = "block";
              document.getElementById('msj2').innerHTML = "<b>\u00a1ATENCI\u00d3N!</b> Existen bicicletas registradas para este modelo.<br>Al eliminarlo, se eliminar\u00e1n automaticamente todas las bicicletas de este modelo.";
            }
          });
}

function nuevabici() {
  document.getElementById("nuevotipo").style.display = "none";
  document.getElementById("nuevomodelo").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";
  
  document.getElementById('eliminarb').style.display = "none";
  document.getElementById('guardarb').style.display = "block";

  document.getElementById('guardarb').disabled = false;

  if (abmb !== 'A') {
    document.getElementById("nuevabici").style.display = "block";
    abmb = 'A';
    document.getElementById('modelobici').focus();
  } else {
    document.getElementById("nuevabici").style.display = "none";
    abmb = '';
  }
}

function dameImagen() {
  $.post('DameImagenBici',
          {
            idModelo: $('#modelobici').val()
          },
          function (responseText)
          {
            if (Boolean(responseText)) {
              var res = responseText.split("//////");
              document.getElementById("tipob").value = res[1];
              document.getElementById('imgbici').src = res[0];
            } else {
              document.getElementById("tipob").value = '';
              document.getElementById('imgbici').src = "img/imagen-vacia.jpg";
            }
          });
}

function verbici(id, patente, idModelo, tipo, disponible, kmMant, kmTot, url) {
  document.getElementById("nuevotipo").style.display = "none";
  document.getElementById("nuevomodelo").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";
  
  document.getElementById('modelobici').disabled  = true;
  document.getElementById('kmMantenimiento').disabled  = true;
  document.getElementById('kmViajados').disabled  = true;
  document.getElementById('patente').disabled  = true;

  document.getElementById('guardarb').style.display = "none";
  document.getElementById('eliminarb').style.display = "none";
  document.getElementById('patente').style.backgroundColor = "#e9ecef";

  if (abmb !== 'M' || document.getElementById('idb').value !== id) {
    //abre form
    abmb = 'M';
    document.getElementById("nuevabici").style.display = "block";
    document.getElementById('idb').value = id;
    document.getElementById('modelobici').value = idModelo;
    document.getElementById('patente').value = patente;
    document.getElementById('tipob').value = tipo;

    if (disponible === "true")
    {
      document.getElementById('disponibleS').style.display = "block";
      document.getElementById('disponibleN').style.display = "none";
      document.getElementById('disponible').value = "true";
    } else
    {
      document.getElementById('disponibleS').style.display = "none";
      document.getElementById('disponibleN').style.display = "block";
      document.getElementById('disponible').value = "false";
    }

    document.getElementById('disponible').value = disponible;
    document.getElementById('kmMantenimiento').value = parseFloat(kmMant.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    document.getElementById('kmViajados').value = parseFloat(kmTot.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    document.getElementById('imgbici').src = url;
    document.getElementById('modelobici').focus();
  }
}

function eliminarbici(id, patente, idModelo, tipo, disponible, kmMant, kmTot, url) {
  document.getElementById("nuevotipo").style.display = "none";
  document.getElementById("nuevomodelo").style.display = "none";
  document.getElementById("tablabicicletas").style.display = "none";
  
  document.getElementById('modelobici').disabled = true;
  document.getElementById('kmMantenimiento').disabled = true;
  document.getElementById('kmViajados').disabled = true;
  document.getElementById('disponible').disabled = true;
  document.getElementById('patente').disabled = true;

  document.getElementById('guardarb').style.display = "none";
  document.getElementById('eliminarb').style.display = "block";
  
  document.getElementById('patente').style.backgroundColor = "#e9ecef";
  
  if (abmb !== 'B' || document.getElementById('idb').value !== id) {
    //abre form
    abmb = 'B';
    document.getElementById("nuevabici").style.display = "block";
    document.getElementById('idb').value = id;
    document.getElementById('modelobici').value = idModelo;
    document.getElementById('patente').value = patente;
    document.getElementById('tipob').value = tipo;
    document.getElementById('kmMantenimiento').value = parseFloat(kmMant.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    document.getElementById('kmViajados').value = parseFloat(kmTot.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
    document.getElementById('imgbici').src = url;

    if (disponible === "true")
    {
      document.getElementById('disponibleS').style.display = "block";
      document.getElementById('disponibleN').style.display = "none";
      document.getElementById('disponible').value = "true";
    } else
    {
      document.getElementById('disponibleS').style.display = "none";
      document.getElementById('disponibleN').style.display = "block";
      document.getElementById('disponible').value = "false";
    }
  }
}

$("#cruzNuevoTB").mousedown(function () {
  document.getElementById("nuevotipo").style.display = "none";
  abmtb = '';
  document.getElementById("tablabicicletas").style.display = "block";
});

$("#cruzNuevoM").mousedown(function () {
  document.getElementById("nuevomodelo").style.display = "none";
  abmm = '';
  document.getElementById("tablabicicletas").style.display = "block";
});

$("#cruzNuevoB").mousedown(function () {
  document.getElementById("nuevabici").style.display = "none";
  abmb = '';
  document.getElementById("tablabicicletas").style.display = "block";
  document.getElementById('modelobici').disabled = false;
  document.getElementById('patente').disabled = false;
  document.getElementById('kmMantenimiento').disabled = false;
  document.getElementById('kmViajados').disabled = false;

  document.getElementById('tipob').value = '';
  document.getElementById('modelobici').value = '';
  document.getElementById('imgbici').src = "img/imagen-vacia.jpg";
  document.getElementById('patente').value = '';
  document.getElementById('kmMantenimiento').value = parseFloat('0'.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
  document.getElementById('kmViajados').value = parseFloat('0'.replace(' ', '').replace('.', '').replace(',', '.')).toFixed(2);
  
  document.getElementById('disponibleS').style.display = "block";
  document.getElementById('disponibleN').style.display = "none";
  document.getElementById('disponible').value = "true";
  
  document.getElementById('modelobici').disabled  = false;
  document.getElementById('kmMantenimiento').disabled  = false;
  document.getElementById('kmViajados').disabled  = false;
  document.getElementById('patente').disabled  = false;
  document.getElementById('patente').style.backgroundColor = "white";
});

$("#url1").focusout(function () {
  var url = document.getElementById('url1').value;
  if (url)
  {
    if (url.match(/\.(jpeg|jpg|gif|png)$/) !== null)
    {
      document.getElementById('imgbici1').src = url;
    } else
    {
      document.getElementById('url1').value = '';
      document.getElementById('imgbici1').src = "img/imagen-vacia.jpg";
    }
  } else
  {
    document.getElementById('imgbici1').src = "img/imagen-vacia.jpg";
  }
});

$("#url2").focusout(function () {
  var url = document.getElementById('url2').value;
  if (url)
  {
    if (url.match(/\.(jpeg|jpg|gif|png)$/) !== null)
    {
      document.getElementById('imgbici2').src = url;
    } else
    {
      document.getElementById('url2').value = '';
      document.getElementById('imgbici2').src = "img/imagen-vacia.jpg";
    }
  } else
  {
    document.getElementById('imgbici2').src = "img/imagen-vacia.jpg";
  }
});

$("#url3").focusout(function () {
  var url = document.getElementById('url3').value;
  if (url)
  {
    if (url.match(/\.(jpeg|jpg|gif|png)$/) !== null)
    {
      document.getElementById('imgbici3').src = url;
    } else
    {
      document.getElementById('url3').value = '';
      document.getElementById('imgbici3').src = "img/imagen-vacia.jpg";
    }
  } else
  {
    document.getElementById('imgbici3').src = "img/imagen-vacia.jpg";
  }
});

$("#urlPortada").focusout(function () {
  var url = document.getElementById('urlPortada').value;
  if (url)
  {
    if (url.match(/\.(jpeg|jpg|gif|png)$/) !== null)
    {
      document.getElementById('portada').src = url;
    } else
    {
      document.getElementById('urlPortada').value = '';
      document.getElementById('portada').src = "img/imagen-vacia.jpg";
    }
  } else
  {
    document.getElementById('portada').src = "img/imagen-vacia.jpg";
  }
});

function mantexbici() {
  window.open("mantxbici.jsp","_self");
}