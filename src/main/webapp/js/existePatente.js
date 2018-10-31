// Recomiendo leer este archivo de abajo hacia arriba para comprenderlo mejor.

$(function () {

  $(document).on("keypress", "form", function (event) {
    return event.keyCode !== 13;
  });

  function siError(e) {
    alert('Ocurri\u00f3 un error al realizar la petici\u00f3n: ' + e.statusText);
  }

  function peticion(e) {
    $.post('ValidaPatente', 
          {
            patente : $('#patente').val()
          }, 
          function(responseText) 
          {
            //VALIDO
            if(responseText==="0")
            {
              $("#guardarb").removeAttr('disabled');                 
              $('#patente').css("backgroundColor", "#66cc66"); 
            }
            //INVALIDO
            else
            {
              $("#guardarb").attr('disabled', 'disabled');
              $('#patente').css("backgroundColor", "#ff6666");
            }
              
          });
  }
  
  $('#patente').focusout(peticion);
});
