<%-- 
    Document   : estudianteRegistro
    Created on : Mar 13, 2017, 2:54:25 AM
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

    <title>Sistema de Gestión</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home.jsp">Home</a>
            </div>
            
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li >
                         <a href="persona.jsp"><i class="fa fa-fw fa-user" ></i> Personas </a>
                    </li>
                     <li class="active">
                        <a href="prestamo.jsp"><i class="fa fa-fw fa-table"></i> Prestamo </a>
                    </li>
                     <li>
                        <a href="herramienta.jsp"><i class="fa fa-fw fa-edit"></i> Herramienta</a>
                    </li>
                           <li>
                        <a href="salon.jsp"><i class="fa fa-fw fa-file"></i> Salon</a>
                    </li>
                           <li>
                        <a href="mantenimiento.jsp"><i class="fa fa-fw fa-dashboard"></i> Mantenimiento</a>
                    </li>
                  
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Listas <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="listaPersona.jsp">Listas de Personas</a>
                            </li>
                             <li>
                                <a href="listaPrestamo.jsp">Lista de Prestamos</a>
                            </li>
                        
                             <li>
                                <a href="listaHerramienta">Lista de Herramientas</a>
                            </li>
                            <li>
                                <a href="listaSalon">Lista de Salones</a>
                            </li>
                            <li>
                                <a href="listaMantenimiento">Lista de Mantenimientos</a>
                            </li>
                        
                        
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>

        <div id="page-wrapper">

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                          Prestamo
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="home.jsp">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> Actualizacion de Prestamo
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <div class="page-header">
                    <h1>Sistema de Inventario </h1>
                </div>
                <p>
                    Introduzca los datos del prestamo que desea actualizar.
                </p>

                <div class="page-header">
                    
                   <form action="prestamoControlador"> <!--ATENCION: la palabra action indica el tipo de acción que va a realizar el formulario-->
            
                                
                            <div class="form-group">
                                <label><h3>Cédula de la persona va a realizar el prestamo</h3>
                                </label>
                                <input type="text" class="form-control" name="idPersona">
                                <p class="help-block">Ej: 1056373.</p>
                            </div>
                            <div class="form-group">
                                <label><h3> ID de la herramienta que se va a entregar</h3>
                                </label>
                                <input type="text" class="form-control" name="idHerramienta">
                                <p class="help-block">Ej: 76842684</p>
                            </div>
                               <div class="form-group">
                                <label><h3>ID del Salon si es una practica guiada</h3>
                                </label>
                                <input type="text" class="form-control" name="idSalon">
                                <p class="help-block">Ej: 64546</p>
                            </div>
                            <div class="form-group">
                                <label><h3>Código de Prestamo</h3>
                                </label>
                                <input type="text" class="form-control" name="codPrestamo">
                                <p class="help-block">Ej: 356464</p>
                            </div>
                              <div class="form-group">
                                <label><h3> Tipo de práctica </h3>
                                </label>
                                <input type="text" class="form-control" name="tipoPractica">
                                <p class="help-block">Ej: Guíada  o Libre</p>
                            </div>
                              <div class="form-group">
                                <label><h3>Fecha de salida</h3>
                                </label>
                                <input type="date" class="form-control" name="fechaSalida">
                                <p class="help-block"> Introduzca la fecha en la que se realiza el prestamo</p>
                            </div>  
                             <div class="form-group">
                                <label><h3>Fecha de Entrada</h3>
                                </label>
                                <input type="date" class="form-control" name="fechaSalida">
                                <p class="help-block"> Introduzca la fecha en la que se devuelve el objeto de prestamo</p>
                            </div>
                            <div class="form-group">
                                <label><h3>Estado del objeto a prestar</h3>
                                </label>
                                <input type="text" class="form-control" name="estado">
                                <p class="help-block">Ej: Bueno </p>
                            </div>
                           <div class="form-group">
                                <label><h3>Justificación</h3>
                                </label>
                                <input type="text" class="form-control" name="justificacion">
                                <p class="help-block">Ej: Khé </p>
                            </div>
                         <div class="form-group">
                                <label><h3>Observaciones</h3>
                                </label>
                                <input type="text" class="form-control" name="observaciones">
                                <p class="help-block">Ej: Observaciones del prestamo </p>
                            </div>
                       <div class="form-group">
                                <label><h3>Tipo de Prestamo</h3>
                                </label>
                           <br>
                                <input type="radio" name="tipoPrestamo"  value="externo" checked> Externo<br>
                                <input type="radio" name="tipoPrestamo"  value="interno"> Interno 
                            
                                <p class="help-block">Ej: Elija si el prestamo es de caracter interno o externo </p>
                            </div>
                 
                       
   
                            
                      <input type="submit" name="Registrar" value="submit" class="btn btn-primary btn-lg" role="button"  >
                         <input type="reset" name="reset" value="reset" class="btn btn-primary btn-lg" role="button"  >
                       </form>
                      </div>
                           
                             
                        </form>
                        

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>