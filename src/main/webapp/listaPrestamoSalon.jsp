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


    <title>Sistema de Gesti�n</title>

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
                          Prestamos
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="home.jsp">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-desktop"></i> Registro de Prestamos
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
                    <tr><th>Cod Prestamo</th> 
                        <th> ID Persona</th> 
                        <th> ID Herramienta </th> 
                        <th> Salida </th> 
                        <th> Entrada </th></tr>  
                    
            <%
            if( request.getAttribute("prestamos")!=null){
          List<Prestamo> prestamos  = (List<Prestamo>)request.getAttribute("prestamos");
           for (Prestamo prestamo : prestamos) {
         %>      
          <tr><td><%=prestamo.getCodPrestamo()%> </td> 
              <td><%=prestamo.getIdPersona()%> </td> 
              <td><%=prestamo.getIdHerramienta()%> </td>
              <td><%=prestamo.getFechaSalida()%> </td>
              <td><%=prestamo.getFechaEntrada()%> </td></tr>
         <%      
          }
       }
    %>
                        
    
                  </table>
                               </div>
                     
                     
        <form action="listaControladorPrestamo">
                          <button type="submit" class="btn btn-default" role="button" > Mostrar Lista de Prestamos</button>
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

</body>

</html>