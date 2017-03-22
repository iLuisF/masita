<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Iniciar Sesión</title>
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
            <button type="button" class="btn btn-warning" style="position:absolute;left:75%;top:1%;">Registrarse</button>
            <button type="button" class="btn btn-success" style="position:absolute;left:85%;top:1%;">Iniciar Sesión</button>
    
        </div>
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Estoy buscando...">
            </div>
            <button type="submit" class="btn btn-default">Ir</button>
        </form>
        <button type="button" class="btn btn-primary" style="position:absolute;left:75%;top:10%;">Buscar en el mapa</button>
        <div class="btn-group" style="position:absolute;left:5%;top:20%;">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">Ordenar por <span class="caret"></span></button>
        </div>
        <div class="panel panel-info" style="position:absolute;left:5%;top:30%;width:90%;">
            <div class="panel-heading"> </div>
            <div class="panel-body" style="font-size:150%">Cafetería Ciencias</div>
            <div class="panel-body">Comida corrida</div>
        </div>
            <div class="ec-stars-wrapper" style="position:absolute;left:75%;top:40%;width:90%;font-size:32px;">
            <a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
            <a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
            <a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
            <a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
            <a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
        </div>
        
        <div class="panel panel-info" style="position:absolute;left:5%;top:55%;width:90%;">
            <div class="panel-heading"> </div>
            <div class="panel-body" style="font-size:150%">Rico y Saludable</div>
            <div class="panel-body">Comida corrida</div>
        </div>
            <div class="ec-stars-wrapper" style="position:absolute;left:75%;top:65%;width:90%;font-size:32px;">
            <a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
            <a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
            <a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
            <a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
            <a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
        </div>
        
        <div class="panel panel-info" style="position:absolute;left:5%;top:80%;width:90%;">
            <div class="panel-heading"> </div>
            <div class="panel-body" style="font-size:150%">EcoTaco</div>
            <div class="panel-body">Comida corrida</div>
        </div>
            <div class="ec-stars-wrapper" style="position:absolute;left:75%;top:90%;width:90%;font-size:32px;">
            <a href="#" data-value="1" title="Votar con 1 estrellas">&#9733;</a>
            <a href="#" data-value="2" title="Votar con 2 estrellas">&#9733;</a>
            <a href="#" data-value="3" title="Votar con 3 estrellas">&#9733;</a>
            <a href="#" data-value="4" title="Votar con 4 estrellas">&#9733;</a>
            <a href="#" data-value="5" title="Votar con 5 estrellas">&#9733;</a>
        </div>
        
        <div class="container" style="position:absolute;left:80%;top:6%;width:20%;background:#93E7A5;">
            <form class="form-signin">
                <h1 class="form-heading">Iniciar Sesión</h1>
                <label for="inputEmail" class="sr-only">Correo electrónico</label>
                <input type="correo" id="inputEmail" class="form-control" placeholder="Correo elextrónico" required autofocus>
                <label for="inputPassword" class="sr-only">Contraseña</label>
                <input type="contrasena" id="inputPassword" class="form-control" placeholder="Contraseña" required>
                <div class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Recordarme
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar Sesión</button>
            </form>
        </div
        
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>
