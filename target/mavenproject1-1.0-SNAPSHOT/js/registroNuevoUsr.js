$("#repass").keyup(function (event) {
  //Store the password field objects into variables ...
  var pass1 = $('#pass').val();
  var pass2 = $('#repass');
  //Set the colors we will be using ...
  var goodColor = "#66cc66";
  var badColor = "#ff6666";
  //Compare the values in the password field 
  //and the confirmation field
  if (pass1 === pass2.val()) {
    $("#enviar").removeAttr('disabled');
    pass2.css("backgroundColor", goodColor);
  } else {
    $("#enviar").attr('disabled', 'disabled');
    pass2.css("backgroundColor", badColor);
  }
});

$("#pass").on("keyup", function () {
  if ($(this).val())
    $("#ojopas").show();
  else
    $("#ojopas").hide();
});

$("#ojopas").mousedown(function () {
  $("#pass").attr('type', 'text');
}).mouseup(function () {
  $("#pass").attr('type', 'password');
}).mouseout(function () {
  $("#pass").attr('type', 'password');
});


$("#repass").on("keyup", function () {
  if ($(this).val())
    $("#ojore").show();
  else
    $("#ojore").hide();
});

$("#ojore").mousedown(function () {
  $("#repass").attr('type', 'text');
}).mouseup(function () {
  $("#repass").attr('type', 'password');
}).mouseout(function () {
  $("#repass").attr('type', 'password');
});

$('#ojopas').
        on('touchstart click', function () {
          $("#pass").attr('type', 'text');
        }).
        on('touchend click', function () {
          $("#pass").attr('type', 'password');
        });

$('#ojore').
        on('touchstart click', function () {
          $("#repass").attr('type', 'text');
        }).
        on('touchend click', function () {
          $("#repass").attr('type', 'password');
        });
            