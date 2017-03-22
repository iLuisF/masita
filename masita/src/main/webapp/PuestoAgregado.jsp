<%-- 
    Document   : PuestoAgregado
    Created on : 22/03/2017, 01:33:07 AM
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
        <br><br>
        <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">El puesto fue agregado correctamente</p>
                    </h3>
                </div>
        </form>
        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">                
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8">
                            <!-- Primer columna correspondiente a google maps-->
                            <div class="Flexible-container">
                                <iframe width="425" height="350" frameborder="0"
                                        scrolling="no" marginheight="0" marginwidth="0"
                                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3765.054246151732!2d-99.18183038478072!3d19.32345224916825!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x85ce000fdd96288f%3A0x1096af9b5b03d38d!2sFacultad+de+Ciencias%2C+UNAM!5e0!3m2!1ses!2smx!4v1489916016734">                                            
                                </iframe>    
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <!--Segunda columa correspondiente a los datos del puesto-->
                            <center>
                                <h2><img src="./imagenes/edificio.png" width="40px" alt="Cafeteria Ciencias">
                                    Cafeteria Ciencias</h2>
                                <h5>Comida Corrida</h5>
                                <p><img src="./imagenes/stars0.png" width="70%"></p>
                            </center>
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title">
                                        <strong>Información Puesto</strong></h3>
                                </div>
                                <div class="panel-body">
                                    <p><strong>Horario:</strong> 9:00 - 18:00</p>
                                    <p><strong>Mesas:</strong> Si</p>
                                    <p><strong>Baños:</strong> No</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>      
                            <th>Usuario</th>
                            <th>Comentario</th>
                            <th>Calificación</th>
                            <th>Fecha</th>
                        </tr>
                    </thead>
                </table>
            </div>


            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">¿Qué piensas sobre este puesto?</h3>
                </div>
                <textarea class="form-control" rows="3" placeholder="Aquí va tu comentario."></textarea>
                <div class="panel-footer">
                    <p class="text-right">
                        <img src="./imagenes/stars0.png" width="150px"> 
                        <button type="button" class="btn btn-primary">Comentar</button>
                    </p>
                </div>
            </div>            
            <hr>
            <footer>
                <p>&copy; 2017 KAAB, Inc.</p>
            </footer>
        </div> <!-- /container -->

    </body>
</html>
