<%-- 
    Document   : estudianteRegistro
    Created on : Mar 13, 2017, 2:54:25 AM
    Author     : james
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="edu.co.sergio.mundo.vo.*"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
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
                    <li class="active">
                         <a href="persona.jsp"><i class="fa fa-fw fa-user" ></i> Personas </a>
                    </li>
                     <li>
                        <a href="formularios.jsp"><i class="fa fa-fw fa-table"></i> Prestamo </a>
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
                                <a href="listaPrestamo.jsp">Lista de Prestamo</a>
                            </li>
                             <li>
                                <a href="listaSalon.jsp">Lista de Salones</a>
                            </li>
                        
                             <li>
                                <a href="listaHerramienta.jsp">Lista de Herramientas</a>
                            </li>
                            
                            <li>
                                <a href="listaMantenimiento.jsp">Lista de Mantenimientos</a>
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
                          Persona
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="home.jsp">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> Registro de Persona
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <div class="page-header">
                    <h1>Sistema de Inventario </h1>
                </div>
           
                 <div align="center" style="margin-top: 50px;">
                               <div class="table-responsive">
                             <table class="table table-bordered table-hover">
                    <tr><th>Nombre</th> <th>Cargo</th> <th> ID</th></tr>  
                    
                   
            <%
              int contEstudiante=0;
              int contProfesor=0;
            if( request.getAttribute("personas")!=null){
            
          List<Persona> personas  = (List<Persona>)request.getAttribute("personas");
           for (Persona persona : personas) {
          if(persona.getCargopersona()=="Estudiante"){
                  contEstudiante=contEstudiante+1;
                    } 
          if(persona.getCargopersona()=="Profesor"){
                   contProfesor=contProfesor+1;
                         }
         %>      
          <tr><td><%=persona.getNombrepersona()%> </td> <td><%=persona.getCargopersona()%> </td> <td><%=persona.getIdpersona()%> </td></tr>
         <%    
         
          }
       }
    %>
    
    <p><%=contEstudiante%></p>
    <p><%=contProfesor%></p>
                        
                      
     <div id="piechart" style="width: 900px; height: 500px;"></div>
                  </table>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
                               </div>
                     
                     
        <form action="graficaPersonasControlador">
                          <button type="submit" class="btn btn-default" role="button" > Mostrar Lista</button>
        </form>
             
      
    
               
                     
     </div>
          
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
    <!-- Graph source for Dev charts in JavaScript from Google-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    
     <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
    
      function drawChart() {
          var estudiante = '${contEstudiante}';
           var profesor = '${contProfesor}';
          
        var data = google.visualization.arrayToDataTable([
             
          ['Cargo', 'Cantidad'],
          ['Estudiantes', estudiante],
          ['Profesores', profesor],
          ['Admins',  2],
          ['Funcionarios', 2],
          ['Normies',    7]
          
        ]);
        
        var options = {
          title: 'Porcentajes de cada Cargo'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>

</body>

</html>