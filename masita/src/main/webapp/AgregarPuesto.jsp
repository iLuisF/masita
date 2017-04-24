<%-- 
    Document   : AgregarPuesto
    Created on : 18/03/2017, 06:53:02 PM
    Author     : Ernesto Palacios
--%>
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
        <h1>
            <p class="text-primary">Agregar Puesto</p>
        </h1>
         <div class="jumbotron">
            <p class="text-danger">Selecciona la sección que quieras actualizar y posteriormente, agrega la información actualizada:</p>
            <br />
            <!--Nombre-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Nombre del Puesto</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="Nombre" class="form-control" placeholder="Rico y Saludable">
                    </div>
                </div>
            </form>
            <!--Tipo de comida-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Tipo comida</p>
                    </h3>
                </div>
                <select class="form-control">
                    <option>Comida Corrida</option>
                    <option>Tacos</option>
                    <option>Quesadillas</option>
                    <option>Ensaladas</option>
                    <option>Otros</option>
                </select>
            </form>
            <!--horario-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Horario</p>
                    </h3>
                </div>
                <div class="form-group">
                    <label class="col-lg-1 control-label"> </label>
                    <div class="col-lg-10">
                        <input type="Horario" class="form-control" placeholder="8:00 - 20:00">
                    </div>
                </div>
            </form>
             <!--Servicios-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Servicios</p>
                    </h3>
                </div>
                <div class="checkbox">
                    <label>
                         <input type="checkbox" value="">
                         <p class="lead">Baños</p>
                    </label>
                </div>
                <div class="checkbox">
                    <label>
                         <input type="checkbox" value="">
                         <p class="lead">Mesas</p>
                    </label>
                </div>
            </form>
            <!--Ubicacion-->
            <form class="form-horizontal" role= "form">
                <div class="form form-group ">
                    <h3>
                        <p class="bg-primary col-lg-12">Ubicacion</p>
                    </h3>
                </div>
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
                            </div>
                        </div>
                    </div>
                </div>
                <div class ="pull-right" >
                <button type="button" class="btn btn-danger">Cancelar</button>
                <button type="button" class="btn btn-primary">Enviar</button>
                </div>
            </form>    
        </div>
    </body>
</html>
