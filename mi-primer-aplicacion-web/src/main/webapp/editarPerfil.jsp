<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Masita</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
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
            <p class="text-primary">Editar Perfil</p>
        </h1>
         <div class="jumbotron">
            <p class="text-danger">Selecciona la sección que quieras actualizar y posteriormente, agrega la información actualizada:</p>
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
                        <input type="Nombre" class="form-control" placeholder="Nicolas">
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
                        <input type="ApellidoP" class="form-control" placeholder="Súper">
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
                        <input type="ApellidoM" class="form-control" placeholder="Cage">
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
                        <input type="NombreUsuario" class="form-control" placeholder="Hugh Jackman">
                    </div>
                </div>
            </form>
            <!--ContraseÃ±a-->
            <form class="form-horizontal" role= "form">
                <div class="form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Contraseña(Nueva)</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id ="inputPassword" placeholder="**********">
                    </div>
                </div>
            </form>
            
            <!--Repetir ContraseÃ±a-->
            <form class="form-horizontal" role= "form">
                <div class="form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Repetir contraseña</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="password" class="form-control" id ="inputPassword" placeholder="**********">
                    </div>
                </div>
            </form>
            <div class ="pull-right" >
                <button type="button" class="btn btn-danger">Cancelar</button>
                <button type="button" class="btn btn-primary">Enviar</button>
            </div>
            
            
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
    </body>
</html>
