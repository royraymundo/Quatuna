<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="beans.Producto"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>QHATUNA Catalogo</title>
        <!--fontawesome cdn-->
        <link href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" rel="stylesheet" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
         <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
         <!-- Boxicons CSS -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <nav class="navbar">
            <a class="navbar-logo" href="#" >
                <img src="imagenes/logo.jpg" width="200" height="45">
            </a>
        </nav>
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">QHATUNA</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="ServletControl?accion=listar">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" href="ServletControl?accion=Catalogo">Catalogo</a>
                        </li>
                         <li class="nav-item">
                            <a class="nav-link" href="ServletControl?accion=Catalogo">Promociones</a>
                        </li>
                    </ul>
                    
                    
                    <ul class="navbar-nav">
                        <li class="nav-item">                           
                             <a class="btn btn-outline-success" href="ServletControl?accion=Carrito">
                                 <i class='bx bx-cart'>(<label style="color:orange">${contador}</label>)</i>
                             </a>
                        </li>
                    </ul>
                        
                    <ul class="navbar-nav btn-group my-2 my-lg-0" role="group">
                        <a style="color: white; cursor: pointer" class="btn btn-outline-success" data-bs-toggle="dropdown">
                            <i class='bx bxs-user'></i>${logueo}</a>
                        <div class="dropdown-menu text-center dropdown-menu-right">
                            <a class="dropdown-item" href="#"><img src="imagenes/user.png" alt="60" height="60"/></a>                        
                            <a class="dropdown-item" href="#">${user}</a>
                            <a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#myModal">${correo}</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="ServletControl?accion=MisCompras">Mis Compras</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="ServletControl?accion=Salir"> <i class="fas fa-arrow-right"> Salir</i></a>                        
                        </div>
                    </ul> 
                </div>
            </div>
        </nav>
        
        <!-- ALERTAS GENERALES -->
        <!-- ALERTA NEGATIVA -->
        <%
            if (request.getAttribute("msgNO") != null) {
                String msg = request.getAttribute("msgNO").toString();
        %>
        <div class="alert alert-danger" role="alert">
            <strong><h4>Aviso: <%=msg%></h4></strong>
        </div>

        <%
            }
        %>
        <!-- ALERTA POSITIVA -->
        <%
            if (request.getAttribute("msgOK") != null) {
                String msg = request.getAttribute("msgOK").toString();
        %>
        <div class="alert alert-success" role="alert">
            <strong><h4>Aviso: <%=msg%></h4></strong>
        </div>

        <%
            }
        %>                    
                            
        <div class="container mt-4">
            <div class="row g-4">
                <c:forEach var="p" items="${productos}">
                <div class="col-sm-3 form-group">
                    <div class="card">
                        
                        <div class="card-header"><label>${p.getNombre()}</label></div>
                        
                        <div class="card-body">
                            <img src="imagenes/${p.getFoto()}" width="250" height="200">
                        </div> 
                        
                        <div class="card-footer text-center">
                            
                            <h6>${p.getDescripcion()}</h6>
                            <h6>Precio S/${p.getPrecio()}</h6>
                            
                            <div class="py-2">
                                <a href="ServletControl?accion=AgregarCarrito&id=${p.getIdProducto()}" class="btn btn-success d-block w-100">Agregar a Carrito</a>
                            </div>
                            
                            <div>
                               <a href="ServletControl?accion=Comprar&id=${p.getIdProducto()}" class="btn btn-warning d-block w-100">Comprar</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                 </c:forEach>
            </div>
        </div>
                            
        <!-- ////////////////////////////Modal Iniciar Session o Registrarse //////////////////////////////////////-->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="container col-lg-9">                   
                    <div class="modal-content">
                        
                        <div class="modal-header text-center"> 
                            <label></label>
                            <button type="button" class="close" data-bs-dismiss="modal"><span aria-hidden="true">&times;</span></button>  
                        </div>
                                                
                        <div class="text-center">                         
                            <img src="imagenes/user.png" width="50" height="50">                         
                        </div>
                        <div class="modal-header text-center">                      
                            <ul class="nav nav-tabs">                           
                                <li class="nav-item">
                                    <a class="nav-link active" data-bs-toggle="pill" aria-current="page" href="#pills-iniciar">Iniciar Sesion</a>
                                </li>
                                
                                <li class="nav-item">
                                    <a class="nav-link" data-bs-toggle="pill" href="#pills-registrar">Registrarse</a>
                                </li>                          
                            </ul>  
                        </div>
                        <div class="modal-body"> 
                            <div class="tab-content" id="pills-tabContent">
                                
                                <!-- ////////////////////////////////////// INICIAR SESION ///////////////////////////////////////// -->
                                <div class="tab-pane fade show active" id="pills-iniciar" role="tabpanel">
                                   <form action="ServletControl" method="post">

                                            <div class="form-group">
                                                <label>Correo Electrónico <span class="text-danger">*</span></label>
                                                
                                                <input type="email" name="txtEmail" class="form-control" placeholder="Ingrese su correo electronico" required>
                                            </div>
                                            <p></p>

                                            <div class="form-group">
                                                <label>Contraseña <span class="text-danger">*</span></label>
                                                <input type="password" name="txtPass" class="form-control" placeholder="Ingrese su contraseña" required>
                                            </div>

                                            <p></p>

                                            <div class="d-grid gap-2">
                                                <button type="submit" name="accion" value="Validar" class="btn btn-success" class="d-block w-100" >Iniciar Sesion</button>
                                            </div> 

                                            <p></p>
                                            <div class="social-auth-links text-center mb-3">
                                                <p>- Tambien puedes acceder con -</p>
                                            </div> 

                                            <div class="d-grid gap-2">
                                                <button type="submit" class="btn btn-danger d-block w-100 btn-sm">Continuar con Google+</button>
                                            </div> 

                                            <p></p>

                                            <div class="d-grid gap-2">
                                                <button type="submit" class="btn btn-primary d-block w-100 btn-sm">Continuar con Facebook</button>
                                            </div> 
                                        </form>
                                </div>
                                
                                <!-- /////////////////////////////////////// REGISTRO CLIENTE ///////////////////////////////////// -->
                                <div class="tab-pane fade" id="pills-registrar" role="tabpanel">
                                    <form action="ServletControl">                               
                                        <div class="form-group">
                                            <label>Nombres</label>
                                            <input type="text" name="txtNombre" class="form-control" placeholder="Nombres y Apellidos" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Dni</label>
                                            <input type="number" name="txtDni" class="form-control" placeholder="D.N.I" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Direccion</label>
                                            <input type="text" name="txtDireccion" class="form-control" placeholder="Direccion" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Email address</label>
                                            <input type="email" name="txtEmail" class="form-control" placeholder="email@example.com" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Password</label>
                                            <input type="password" name="txtPassword" class="form-control" placeholder="Password" required>
                                        </div>     
                                        <br>
                                        <button type="submit" name="accion" value="Registrar" class="btn btn-danger d-block w-100 btn-sm">Crear Cuenta</button>
                                    </form>
                                </div>      
                                
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
       
       <script>
            //Autoclose
            window.setTimeout(function(){
                $(".alert").fadeTo(1500, 0).slideDown(1000, function(){
                    $(this).remove();
                });
            }, 2000);//2 segundos y desaparece
        </script> 
       <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
            
    </body>
</html>
