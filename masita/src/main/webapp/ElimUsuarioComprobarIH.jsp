
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
                        <button type="submit" class="btn btn-warning">Bob Bobbins</button>
                        <button type="submit" class="btn btn-success">Cerrar Sesi&oacuten</button>
                    </form>
                </div>
            </div>
        </nav>

        <div class="jumbotron">
        <div class="container">
        	<form class="navbar-form navbar-left" role="search">
        	<div class="form-group">
        	<input type="text" class="form-control" placeholder="Buscar Usuario">
        	</div>
        	<button type="submit" class="btn btn-default">Ir</button>
        	</form>
        </div>
        <div class="container">
          <h2><small>Usuarios:</small></h2>
        </div>
        </div>

        <div class="container" style="position: relative; left: 30%; top: 70%;">
            <div class="panel panel-info" style="width: 30%;">
                <div class="panel-heading">
                    <h3 class="panel-title">Eliminar Usuario</h3>
                </div>
                <div class="panel-body">
                    <p>¿Está seguro de que quiere eliminar a este usuario?</p>
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
                            <th>Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            Justin Bieber</th>
                            <td>
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            Justina Bobbins</th>
                            <td>
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            Justino Pinsker</th>
                            <td>
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            Justina Zera</th>
                            <td>
                            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>            
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