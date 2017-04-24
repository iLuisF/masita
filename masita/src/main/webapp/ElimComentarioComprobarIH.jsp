
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
        <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
        <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
        <script src="./assets/js/ie-emulation-modes-warning.js"></script>
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    
    <body style="width: 70%;">
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
                        <button type="submit" class="btn btn-warning">Bob Bobbins</button>
                        <button type="submit" class="btn btn-success">Cerrar Sesi&oacuten</button>
                    </form>
                </div>
            </div>
        </nav>

        <br><br>
        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">                
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8">
                            <!-- Responsive iFrame google maps-->
                            <div class="Flexible-container">
                                <iframe width="425" height="350" frameborder="0"
                                        scrolling="no" marginheight="0" marginwidth="0"
                                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3765.054246151732!2d-99.18183038478072!3d19.32345224916825!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x85ce000fdd96288f%3A0x1096af9b5b03d38d!2sFacultad+de+Ciencias%2C+UNAM!5e0!3m2!1ses!2smx!4v1489916016734">                                            
                                </iframe>    
                            </div>
                        </div>
                        <div class="col-sm-4">
                            <center>
                                <h2>Cafeteria Ciencias</h2>
                                <h5>Comida Corrida</h5>
                                <img src="images/3star-rating.gif" height="50">
                            </center>
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Informaci&oacuten Puesto</h3>
                                </div>
                                <div class="panel-body">
                                    <p>Horario: 9:00 - 18:00</p>
                                    <p>Mesas: Si</p>
                                    <p>Ba&ntildeos: No</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        

        <div class="page-header">
          <h1><small>Comentarios:</small></h1>
        </div>

        <div class="container" style="position: relative; left: 30%; top: 70%;">
            <div class="panel panel-info" style="width: 30%;">
                <div class="panel-heading">
                    <h3 class="panel-title">Eliminar comentario</h3>
                </div>
                <div class="panel-body">
                    <p>¿Está seguro de que quiere eliminar el comentario?</p>
                    <form class="navbar-form navbar-center">
                        <button type="submit" class="btn btn-success">Aceptar</button>
                        <button type="submit" class="btn btn-danger">Cancelar</button>
                    </form>
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
                            <th>Calificaci&oacuten</th>
                            <th>Fecha</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">Jennifer Pinsker</th>
                            <td>¡Deliciosas enchiladas!
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </td>
                            <td><img src="images/3star-rating.gif" height="40"> </td>
                            <td>15 Sep, 8:56 AM</td>
                        </tr>
                        <tr>
                            <th scope="row">Bob bobbins</th>
                            <td>Est&aacuten sucias las mesas
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </td>
                            <td><img src="images/3star-rating.gif" height="40"> </td>
                            <td>15 Sep, 8:56 AM</td>
                        </tr>
                    </tbody>
                </table>
            </div>            
            <hr>

            <div class="panel panel-default" style="width: 70%">
                <div class="panel-heading">
                    <h3 class="panel-title">¿Qué piensas sobre este puesto?</h3>
                </div>
                <textarea class="form-control" rows="3" placeholder="Aquí va tu comentario."></textarea>
                <div class="panel-footer">
                    <p class="text-right">
                        <img src="images/stars0.png" width="150px"> 
                        <button type="button" class="btn btn-primary">Comentar</button>
                    </p>
                </div>
            </div> 

            <footer>
                <p>&copy; 2017 KAAB, Inc.</p>
            </footer>
        </div> <!-- /container -->

        


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
        <script src="../../dist/js/bootstrap.min.js"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
        <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
    </body>
</html>
