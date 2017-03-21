<%-- 
    Document   : validarCuenta
    Created on : 20/03/2017, 09:40:04 PM
    Author     : Rocío Huerta
--%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Validar Cuenta</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  </head>
  <body>
      <style type="text/css"> 
        .barrasup{ 
        width:100%; 
        height:50px; 
        background:#76D6C4; 
        color:#FFFFFF; 
        font-size: 200%;
        } 
        </style>
        <div class="barrasup"> Masita
            <button type="button" class="btn btn-warning" style="position:absolute;left:75%;top:1%;">Hugh Jackman</button>
            <button type="button" class="btn btn-success" style="position:absolute;left:85%;top:1%;">Cerrar Sesión</button>
        </div>
        
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>¡Se creó la cuenta exitosamente!</strong>
        </div>
        
        <p class="lead" style="font-size:120%;position:absolute;left:5%;">Se ha enviado un correo electrónico a la dirección: </p>
        <strong style="font-size:120%;position:absolute;left:15%;top:25%;">alumno@ciencias.unam.mx</strong>
        <p class="lead" style="font-size:120%;position:absolute;left:5%;top:32%;">
            Para confirmar que es la dirección de tu correo electrónico, haz click en el hipervínculo de confirmación que se mostrará
            en el mismo.
        </p>
        <p class="lead" style="font-size:120%;position:absolute;left:5%;top:50%;">
            ¿No es tu dirección de correo electrónico?
        </p>
        <u style="font-size:120%;position:absolute;left:30%;top:50%;color:#040BE5;">
            Editar correo
        </u>
        <p class="lead" style="font-size:120%;position:absolute;left:5%;top:56%;">
            ¿No has recibido el correo?
        </p>
        <u style="font-size:120%;position:absolute;left:22%;top:56%;color:#040BE5;">
            Reenviar mi validación
        </u>
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
