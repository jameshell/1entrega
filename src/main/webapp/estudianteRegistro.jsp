<%-- 
    Document   : estudianteRegistro
    Created on : Mar 13, 2017, 2:54:25 AM
    Author     : james
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

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
                    <li class="active">
                         <a href="estudiante.jsp"><i class="fa fa-fw fa-user" ></i> Estudiante</a>
                    </li>
                     <li>
                        <a href="formularios.jsp"><i class="fa fa-fw fa-table"></i> Formularios</a>
                    </li>
                     <li>
                        <a href="herramienta.jsp"><i class="fa fa-fw fa-edit"></i> Herramienta</a>
                    </li>
                           <li>
                        <a href="inventario.jsp"><i class="fa fa-fw fa-file"></i> Inventario</a>
                    </li>
                           <li>
                        <a href="mantenimiento.jsp"><i class="fa fa-fw fa-dashboard"></i> Mantenimiento</a>
                    </li>
                  
                    <li>
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="fa fa-fw fa-arrows-v"></i> Listas <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            <li>
                                <a href="crudEstudiantes.jsp">Listas de Estudiantes</a>
                            </li>
                             <li>
                                <a href="crudInscripciones.jsp">Lista de Formularios</a>
                            </li>
                        
                             <li>
                                <a href="crudProfesores.jsp">Lista de Herramientas</a>
                            </li>
                            <li>
                                <a href="crudCursos.jsp">Lista de Inventarios</a>
                            </li>
                            <li>
                                <a href="crudCursos.jsp">Lista de Mantenimientos</a>
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
                          Estudiante
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> Registro de Estudiante
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <div class="page-header">
                    <h1>Sistema de Inventario </h1>
                </div>
                <p>
                    Introduzca los datos del estudiante que desea registrar a la mmemoria.
                </p>

                <div class="page-header">
                    
                   <form action="estudianteControlador" method="POST"> <!--ATENCION: la palabra action indica el tipo de acción que va a realizar el formulario-->
            
                                
                            <div class="form-group">
                                <label><h3>Cédula del Estudiante</h3>
                                </label>
                                <input type="text" class="form-control" name="cedulaEstudiante">
                                <p class="help-block">Ej: 1056373.</p>
                            </div>
                            <div class="form-group">
                                <label><h3> Nombres del Estudiante</h3>
                                </label>
                                <input type="text" class="form-control" name="nombreEstudiante">
                                <p class="help-block">Ej: Nicolas</p>
                            </div>
                               <div class="form-group">
                                <label><h3>Apellidos del Estudiante</h3>
                                </label>
                                <input type="text" class="form-control" name="apellidoEstudiante">
                                <p class="help-block">Ej: Infante</p>
                            </div>
                            <div class="form-group">
                                <label><h3>Semestre en el cual se encuentra el Estudiante</h3>
                                </label>
                                <input type="text" class="form-control" name="semestreEstudiante">
                                <p class="help-block">Ej: 3</p>
                            </div>
                              <div class="form-group">
                                <label><h3>Carrera</h3>
                                </label>
                                <input type="text" class="form-control" name="carreraEstudiante">
                                <p class="help-block">Ej: Ingeniería de Sistemas</p>
                            </div>
                       
                            
                      <input type="submit" name="Registrar" value="Registrar" class="btn btn-primary btn-lg" role="button"  >
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