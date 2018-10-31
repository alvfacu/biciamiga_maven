
$(document).ready(function () {
  $('#usuarios').DataTable({
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
    "order": [[4, "asc"]],
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
});

function nuevousr() {
  document.getElementById("tablausuarios").style.display = "none";
  document.getElementById("usuario").style.display = "block";
  document.getElementById("enviar").style.display = "block";
  document.getElementById("editar").style.display = "none";
  document.getElementById("eliminar").style.display = "none";
  document.getElementById("usrE").style.display = "none";
  document.getElementById('nuevoPass').style.display = "block";
  document.getElementById('nuevoRPass').style.display = "block";
  document.getElementById('pass').required = true;
  document.getElementById('usuario').required = true;
  document.getElementById('repass').required = true;
  
  if (document.getElementById("nuevousr").style.display === "none") {
    document.getElementById("nuevousr").style.display = "block";
    document.getElementById('habilitado').value = true;
  }
}

function editarusr(id, user, apenom, docu, email, admin, meca, habi, domi, tel) {
  document.getElementById("tablausuarios").style.display = "none";
  document.getElementById('enviar').style.display = "none";
  document.getElementById('editar').style.display = "block";
  document.getElementById('eliminar').style.display = "none";
  document.getElementById('nuevoPass').style.display = "none";
  document.getElementById('nuevoRPass').style.display = "none";
  document.getElementById('pass').required = false;
  document.getElementById('usuario').required = false;
  document.getElementById('repass').required = false;
  
  
  if (document.getElementById("nuevousr").style.display === "none" || document.getElementById('idusr').value !== id) {
    document.getElementById("usuario").style.display = "none";
    document.getElementById("usrE").style.display = "block";
    document.getElementById("nuevousr").style.display = "block";
    
    document.getElementById('idusr').value = id;
    document.getElementById('usrE').value = user;
    document.getElementById('habilitado').value = habi;
    document.getElementById('apenom').value = apenom;
    document.getElementById('domicilio').value = domi;
    document.getElementById('documento').value = docu;
    document.getElementById('telefono').value = tel;
    document.getElementById('email').value = email;

    if (admin === "true")
    {
      document.getElementById('admin').checked = true;
      document.getElementById('admin').value = true;
      document.getElementById('meca').disabled = true;
      document.getElementById('cliente').disabled = true;
    } else
    {
      document.getElementById('admin').checked = false;
      document.getElementById('admin').value = false;
    }

    if (meca === "true")
    {
      document.getElementById('meca').checked = true;
      document.getElementById('meca').value = true;
      document.getElementById('admin').disabled = true;
      document.getElementById('cliente').disabled = true;
    } else
    {
      document.getElementById('meca').checked = false;
      document.getElementById('meca').value = false;
    }

    if (meca === "false" && admin === "false")
    {
      document.getElementById('cliente').checked = true;
      document.getElementById('cliente').value = true;
      document.getElementById('meca').disabled = true;
      document.getElementById('admin').disabled = true;      
    } else
    {
      document.getElementById('cliente').checked = false;
      document.getElementById('cliente').value = false;
    }
  }
}

function eliminarusr(id, user, apenom, docu, email, admin, meca, habi, domi, tel) {
  document.getElementById("tablausuarios").style.display = "none";
  document.getElementById('enviar').style.display = "none";
  document.getElementById('eliminar').style.display = "block";
  document.getElementById('editar').style.display = "none";
  document.getElementById('nuevoPass').style.display = "none";
  document.getElementById('nuevoRPass').style.display = "none";
  document.getElementById('pass').required = false;
  document.getElementById('usuario').required = false;
  document.getElementById('repass').required = false;
  
  if (document.getElementById("nuevousr").style.display === "none" || document.getElementById('idusr').value !== id) {
    document.getElementById("nuevousr").style.display = "block";    
    document.getElementById("usuario").style.display = "none";
    document.getElementById("usrE").style.display = "block";
    
    document.getElementById('idusr').value = id;
    document.getElementById('usuario').value = user;
    document.getElementById('habilitado').value = habi;
    document.getElementById('apenom').value = apenom;
    document.getElementById('domicilio').value = domi;
    document.getElementById('documento').value = docu;
    document.getElementById('telefono').value = tel;
    document.getElementById('email').value = email;

    if (admin === "true")
    {
      document.getElementById('admin').checked = true;
    } else
    {
      document.getElementById('admin').checked = false;
    }

    if (meca === "true")
    {
      document.getElementById('meca').checked = true;
    } else
    {
      document.getElementById('meca').checked = false;
    }

    if (meca === "false" && admin === "false")
    {
      document.getElementById('cliente').checked = true;    
    } else
    {
      document.getElementById('cliente').checked = false;
    }    
    document.getElementById('usuario').disabled = true;
    document.getElementById('habilitado').disabled = true;
    document.getElementById('apenom').disabled = true;
    document.getElementById('domicilio').disabled = true;
    document.getElementById('documento').disabled = true;
    document.getElementById('telefono').disabled = true;
    document.getElementById('email').disabled = true;
    document.getElementById('meca').disabled = true;
    document.getElementById('admin').disabled = true; 
    document.getElementById('cliente').disabled = true;     
  }
}

function reset(id) {

  $.post('ResetContrasenia',
          {
            id: id
          },
          function (responseText)
          {
            //VALIDO
            if (responseText === "0")
            {
              alert("Contrase\u00f1a reestablecida. Nueva contrase\u00f1a: 12345");
              location.reload();
            }
            //INVALIDO
            else
            {
              alert("¡No ha sido posible reestrablecer la cotnraseña!");
              location.reload();
            }
          });
}

function resetear() {
  $.post('ResetContrasenia',
          {
            id: document.getElementById('idusr').value
          },
          function (responseText)
          {
            //VALIDO
            if (responseText === "0")
            {
              alert("Contrase\u00f1a reestablecida. Nueva contrase\u00f1a: 12345");
              location.reload();
            }
            //INVALIDO
            else
            {
              alert("¡No ha sido posible reestrablecer la cotnrase\u00f1a!");
              location.reload();
            }
          });
}

function checkNuevoAdmin(form) {
  if (form.elements["admin"].checked)
  {
    form.elements["admin"].value = true;
  }    
  else
    form.elements["admin"].value = false;

  if (form.elements["admin"].checked) {
    form.elements["cliente"].disabled = true;
    form.elements["meca"].disabled = true;
  } else {
    form.elements["cliente"].disabled = false;
    form.elements["meca"].disabled = false;
  }
}

function checkNuevoMeca(form) {
  if (form.elements["meca"].checked)
    form.elements["meca"].value = true;
  else
    form.elements["meca"].value = false;

  if (form.elements["meca"].checked) {
    form.elements["cliente"].disabled = true;
    form.elements["admin"].disabled = true;
  } else {
    form.elements["cliente"].disabled = false;
    form.elements["admin"].disabled = false;
  }
}

function checkNuevoCliente(form) {
  if (form.elements["cliente"].checked)
    form.elements["cliente"].value = true;
  else
    form.elements["cliente"].value = false;

  if (form.elements["cliente"].checked) {
    form.elements["admin"].disabled = true;
    form.elements["meca"].disabled = true;
  } else {
    form.elements["admin"].disabled = false;
    form.elements["meca"].disabled = false;
  }
}

function checkEditar(form) {
  if (form.elements["ediadmin"].checked)
    form.elements["ediadmin"].value = true;
  else
    form.elements["ediadmin"].value = false;

  if (form.elements["edimeca"].checked)
    form.elements["edimeca"].value = true;
  else
    form.elements["edimeca"].value = false;

  if (form.elements["ediadmin"].checked || form.elements["edimeca"].checked) {
    form.elements["edicliente"].disabled = true;
    form.elements["edicliente"].checked = false;
  } else {
    form.elements["edicliente"].disabled = false;
  }
}

function checkEditar2(form) {
  if (form.elements["edicliente"].checked)
    form.elements["edicliente"].value = true;
  else
    form.elements["edicliente"].value = false;

  if (form.elements["edicliente"].checked) {
    form.elements["ediadmin"].disabled = true;
    form.elements["edimeca"].disabled = true;
    form.elements["ediadmin"].checked = false;
    form.elements["edimeca"].checked = false;
  } else {
    form.elements["ediadmin"].disabled = false;
    form.elements["edimeca"].disabled = false;
  }
}

$("#cruzNuevo").mousedown(function () {
  document.getElementById("nuevousr").style.display = "none";
  document.getElementById("tablausuarios").style.display = "block";
  document.getElementById('usuario').disabled = false;
  document.getElementById('habilitado').disabled = false;
  document.getElementById('apenom').disabled = false;
  document.getElementById('domicilio').disabled = false;
  document.getElementById('documento').disabled = false;
  document.getElementById('telefono').disabled = false;
  document.getElementById('email').disabled = false;
  document.getElementById('meca').disabled = false;
  document.getElementById('admin').disabled = false; 
  document.getElementById('cliente').disabled = false; 
  document.getElementById('meca').checked = false;
  document.getElementById('admin').checked = false; 
  document.getElementById('cliente').checked = false; 
  document.getElementById('meca').value = false;
  document.getElementById('admin').value = false; 
  document.getElementById('cliente').value = false;  
  
  
  document.getElementById("nuevousr").style.display = "none";
  document.getElementById('idusr').value = '';
  document.getElementById('usuario').value = '';
  document.getElementById('habilitado').value = '';
  document.getElementById('apenom').value = '';
  document.getElementById('domicilio').value = '';
  document.getElementById('documento').value = '';
  document.getElementById('telefono').value = '';
  document.getElementById('email').value = '';
  document.getElementById('pass').value = '';
  document.getElementById('pass').style.backgroundColor = "white";
  document.getElementById('repass').value = '';
  document.getElementById('repass').style.backgroundColor = "white";
  document.getElementById('usuario').style.backgroundColor = "white";
});


function vaciarFiltro() {
  var table = $('#usuarios').DataTable();
  table.search("").draw();
}

function buscarAdmin() {
  var table = $('#usuarios').DataTable();
  table.search("ADMIN").draw();
}

function buscarMecanicos() {
  var table = $('#usuarios').DataTable();
  table.search("MECANICO").draw();
}

function buscarClientes() {
  var table = $('#usuarios').DataTable();
  table.search("CLIENTE").draw();
}