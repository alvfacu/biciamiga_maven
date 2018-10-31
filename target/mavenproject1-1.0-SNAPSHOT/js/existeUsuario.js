// Recomiendo leer este archivo de abajo hacia arriba para comprenderlo mejor.

$(function () {


  function siError(e) {
    alert('Ocurri\u00f3 un error al realizar la petici\u00f3n: ' + e.statusText);
  }

  function peticion(e) {
    // Realizar la petición
    // $.ajax({type: "POST",
    //  url: 'ValidaUsuario',
    //  data: {"usuario": $('#usuario').val()}          
    //    });
    $.post('ValidaUsuario', 
          {
            usuario : $('#usuario').val()
          }, 
          function(responseText) 
          {
            //VALIDO
            if(responseText==="0")
            {
              $("#enviar").removeAttr('disabled');                 
              $('#usuario').css("backgroundColor", "#66cc66"); 
            }
            //INVALIDO
            else
            {
              $("#enviar").attr('disabled', 'disabled');
              $('#usuario').css("backgroundColor", "#ff6666"); 
            }
              
          });
  }

  $('#usuario').focusout(peticion); // Registrar evento al boton "Calcular" con la funcion "peticion"
});


