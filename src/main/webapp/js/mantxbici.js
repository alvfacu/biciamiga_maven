var modal = document.getElementById('myModal');

function ampliar(imagen) {
  modal.style.display = "block";
  document.getElementById("img01").src = imagen;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
  modal.style.display = "none";
};

function cargarMantenimientos() {
  window.open("mantxbici.jsp?idBici=" + document.getElementById("bici").value, "_self");
}

$(document).ready(function () { 
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
    "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "Todos"]],
    "pagingType": "simple_numbers",
     "order": [[ 0, "asc" ]],
    "columns": [
      null,
      null,
      null,
      {"orderable": false}
    ]
  });  
});

function checkCompleto(form) {
  if (document.getElementById("chkCompletados").checked)
    document.getElementById("chkCompletados").value = true;
  else
    document.getElementById("chkCompletados").value = false;
  
  var table = $('#mantenimientos').DataTable();

  if (document.getElementById("chkCompletados").checked) {
    table.search("COMPLETADO").draw();
  } else {
    table.search("").draw();
  }
}
