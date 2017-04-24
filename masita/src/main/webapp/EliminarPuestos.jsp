<%-- 
    Document   : EliminarPuestos
    Created on : 22/03/2017, 01:50:32 AM
    Author     : Ernesto Palacios
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">        
        <title>Masita</title>
        <!-- Bootstrap core CSS -->
        <link href="./css/bootstrap.min.css" rel="stylesheet">
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <link href="./assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
        <!-- Custom styles for this template -->
        <link href="jumbotron.css" rel="stylesheet">      
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">        
                    <button type="button" class="navbar-toggle collapsed" 
                            data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Navegación</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#"><font color = "white">Masita</font></a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <form class="navbar-form navbar-right">            
                        <button type="submit" class="btn btn-primary">Juanito Perez</button>
                        <button type="submit" class="btn btn-success">Cerrar sesión</button>
                    </form>          
                </div>
            </div>
        </nav>
        <br />
        <br />
        <br />
        <form class="navbar-form navbar-left" role="search">
            <div class="form-group">
                 <input type="text" class="form-control" placeholder="Buscar">
            </div>
                 <button type="submit" class="btn btn-default">Enviar</button>
        </form>
        <br />
        <br />
        <br />
        <div class="panel panel-default">
            <div class="panel-body">
                 <img src="./imagenes/eliminar.png" height="30" width="30" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/stars3.png" height="70" width="420" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/edificio.png" height="100" width="60" alt="Eliminar comentario" align="left">
                 <h3>Cafetería Ciencias</h3>
                 <h3>Comida Corrida</h3>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                 <img src="./imagenes/eliminar.png" height="30" width="30" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/stars2.png" height="70" width="420" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/edificio.png" height="100" width="60" alt="Eliminar comentario" align="left">
                 <h3>Taco Ciencias</h3>
                 <h3>Tacos</h3>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                 <img src="./imagenes/eliminar.png" height="30" width="30" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/stars4.png" height="70" width="420" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/edificio.png" height="100" width="60" alt="Eliminar comentario" align="left">
                 <h3>Ensaladas Ciencias</h3>
                 <h3>Ensaladas y Comida Corrida</h3>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-body">
                 <img src="./imagenes/eliminar.png" height="30" width="30" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/stars3.png" height="70" width="420" alt="Eliminar comentario" align="right">
                 <img src="./imagenes/edificio.png" height="100" width="60" alt="Eliminar comentario" align="left">
                 <h3>Rico y Saludable</h3>
                 <h3>Comida Corrida</h3>
            </div>
        </div>
    </body>
</html>