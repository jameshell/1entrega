<%-- 
    Document   : home
    Created on : Feb 10, 2017, 10:30:11 AM
    Author     : james
--%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<% import edu.co.sergio.mundo.vo.*;  %>
<%import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; %>

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
                    <li>
                         <a href="persona.jsp"><i class="fa fa-fw fa-user"></i> Persona </a>
                    </li>
                     <li>
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
                                <a href="">Lista de Herramientas</a>
                            </li>
                            <li>
                                <a href="">Lista de Inventarios</a>
                            </li>
                            <li>
                                <a href="">Lista de Mantenimientos</a>
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
                            Interfaz Principal
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="home.jsp">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> Modo Privilegiado
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

                <!-- Main jumbotron for a primary marketing message or call to action -->
                <div class="jumbotron">
                   <img class="img-responsive" src="usergioarboleda.png">
                    <p>Sistema de registro para el laboratorio de Electrónica</p>
                    <p><a href="#" class="btn btn-primary btn-lg" role="button">Learn more &raquo;</a>
                    </p>
                </div>

                <div class="page-header">
                    <h1>Sistema de Inventario </h1>
                </div>
             
                <p>
                    Prototipo para sistema de registro usando arboles para bases de datos.
                </p>
                
                <a href='prestamoSalon.jsp'> Lista Prestamo Salon <a/>

                <div class="page-header">
                   

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
      <% HttpSession session = request.getSession();
        Administrativo admin= (Administrativo) session.getAttribute("administrativo");
        
        if(admin==null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        %>
    
    
     <%
             session.setMaxInactiveInterval(2);
          %>

                      <script type="text/javascript">
                      var Msg ='<%=session.getAttribute("administrador")%>';
                      if (Msg != "null") {
                      function alertName(){
                      alert("Bienvenido! Sesion HTTP iniciada! ");
                             } 
                             }
 </script> 
    
    
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>
        <script type="text/javascript"> window.onload = alertName; </script>

</body>

</html>