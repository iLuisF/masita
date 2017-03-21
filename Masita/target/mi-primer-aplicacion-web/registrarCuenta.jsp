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
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">        
                    <a class="navbar-brand" href="#"><font color = "white">Masita</font></a>
                </div>
                
            </div>
        </nav>
        <br />
        <br />
        <h1>
            <p class="text-primary">Registrar Cuenta</p>
        </h1>
         <div class="jumbotron">
            <p class="text-danger">Por favor proporciona los datos que se te solicitan a continuación:</p>
            <br />
            <!--Nombre-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Nombre</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="Nombre" class="form-control" placeholder="Nombre">
                    </div>
                </div>
            </form>
            <!--Apellido paterno-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Apellido paterno</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="ApellidoP" class="form-control" placeholder="Apellido Paterno">
                    </div>
                </div>
            </form>
            <!--Apellido materno-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Apellido materno</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="ApellidoM" class="form-control" placeholder="Apellido Materno">
                    </div>
                </div>
            </form>
            <!--Nombre de usuario-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Nombre de usuario</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="NombreUsuario" class="form-control" placeholder="Nombre de usuario">
                    </div>
                </div>
            </form>
            <!--Correo ElectrÃ³nico-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Correo Electrónico</p>
                    </h3>
                     <p class="text-danger col-lg-12">*Solo con terminación @ciencias.unam.mx</p>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="Correo" class="form-control" placeholder="Correo Electrónico">
                    </div>
                </div>
            </form>
            <!--ContraseÃ±a-->
            <form class="form-horizontal" role= "form">
                <div class="form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Contraseña</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id ="inputPassword" placeholder="Contraseña">
                    </div>
                </div>
            </form>
            
            <!--Repetir Contraseña-->
            <form class="form-horizontal" role= "form">
                <div class="form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Repetir contraseña</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id ="inputPassword" placeholder="Contraseña">
                    </div>
                </div>
            </form>
            <div class ="pull-right" >
                <button type="button" class="btn btn-danger">Cancelar</button>
                <button type="button" class="btn btn-primary">Enviar</button>
            </div>
            
            
        </div>
    </body>
</html>
