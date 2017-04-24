
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
                        <button type="submit" class="btn btn-warning">Regístrate</button>
                        <button type="submit" class="btn btn-success">Iniciar Sesión</button>
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
                <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                <input type="text" class="form-control" placeholder="Estoy buscando...">
                </div>
                <button type="submit" class="btn btn-default">Ir</button>
                </form>
                <div align="right">
                <button type="button" class="btn btn-default btn-lg">
                Buscar en el mapa <span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>
                </button>
                </div>
                </div>
                <div class="dropdown">
                  <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                    Ordenar por
                    <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                    <li><a href="#">Nombre</a></li>
                    <li><a href="#">Tipo de comida</a></li>
                    <li><a href="#">Calificación</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Separated link</a></li>
                  </ul>
                </div>
                <br><br>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Información Puesto</h3>
                            </div>
                            <div class="panel-body">
                            <div class="row">
                            <div class="col-sm-1">
                                <img src="images/company-building-icon.png" height="50">
                            </div>
                            <div class="col-sm-6">
                                <h3>Cafetería Ciencias</h3>
                                <p>Comida Corrida</p>
                            </div>
                            <div class="col-sm-2">
                                <img src="images/3star-rating.gif" height="50">
                            </div>
                            </div>
                        </div>
                    </div>
                </div>    
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Información Puesto</h3>
                            </div>
                            <div class="panel-body">
                            <div class="row">
                            <div class="col-sm-1">
                                <img src="images/company-building-icon.png" height="50">
                            </div>
                            <div class="col-sm-6">
                                <h3>Rico y Saludable</h3>
                                <p>Comida Corrida</p>
                            </div>
                            <div class="col-sm-2">
                                <img src="images/3star-rating.gif" height="50">
                            </div>
                            </div>
                        </div>
                    </div>
                </div>    
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="panel panel-info">
                            <div class="panel-heading">
                                <h3 class="panel-title">Información Puesto</h3>
                            </div>
                            <div class="panel-body">
                            <div class="row">
                            <div class="col-sm-1">
                                <img src="images/company-building-icon.png" height="50">
                            </div>
                            <div class="col-sm-6">
                                <h3>Eco Taco</h3>
                                <p>Comida Corrida</p>
                            </div>
                            <div class="col-sm-2">
                                <img src="images/3star-rating.gif" height="50">
                            </div>
                            </div>
                        </div>
                    </div>
                </div>    
                </div>
            </div>
        </div>
        </div>
        <center>
        <div class="container">
        <nav aria-label="...">
          <ul class="pagination">
            <li class="disabled">
              <span>
                <span aria-hidden="true">&laquo;</span>
              </span>
            </li>
            <li class="active">
              <span>1 <span class="sr-only">(current)</span></span>
            </li>
            <li class="disabled">
              <span>2 <span class="sr-only">(current)</span></span>
            </li>
            <li class="disabled">
              <span>3 <span class="sr-only">(current)</span></span>
            </li>
            <li class="disabled">
              <span>4 <span class="sr-only">(current)</span></span>
            </li>
            <li class="disabled">
              <span>5 <span class="sr-only">(current)</span></span>
            </li>
          </ul>
        </nav>
        </div>
        </center>

        <div class="container">          
            <hr>
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
