<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>Detalle de Compra</title>
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
                            <a class="nav-link active" aria-current="page" href="ServletControl?accion=listar">Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ServletControl?accion=Catalogo">Catalogo</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ServletControl?accion=Catalogo">Promociones</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="ServletControl?accion=Catalogo">Seguir Comprando</a>
                        </li>
                    </ul>
                    
                    <ul class="navbar-nav btn-group my-2 my-lg-0" role="group">
                        <a style="color: white; cursor: pointer" class="btn btn-outline-success dropdown-toggle" data-bs-toggle="dropdown">
                            <i class="fas fa-user-tie"></i>${logueo}</a>
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
            <div class="card"> 
                <div class="card-header d-flex">
                    <h2>Detalle</h2>
                    <a href="ServletControl?accion=MisCompras" class="pt-2 pl-4">Regresar</a>
                </div>   
                <div class="card-body">
                    <table class="table tab-pane">
                        <thead class="thead-light">
                            <tr class="text-center">
                                <th>CODIGO COMPRA</th>                               
                                <th>Articulo</th>
                                <th>Cantidad</th>
                                <th>Precio Compra</th>                                                   
                                <th></th>                                                   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="p" items="${myDetalle}">
                                <tr class="text-center">
                                    <td>C00${p.getIdcompra()}</td> 
                                    <td>                                        
                                        <label><i>${p.producto.descripcion}</i></label><br>                                      
                                        <img src="imagenes/${p.producto.foto}" width="50" height="40">
                                    </td>                                                                
                                    <td>${p.getCantidad()}</td>
                                    <td>${p.getPrecioCompra()}</td>                                                                                                      

                                </tr>
                            </c:forEach>
                        </tbody>
                    </table> 
                </div>
                <div class="card-footer d-flex">
                    <label class="col-sm-9 text-right mt-1">Monto Total Pagado</label>
                    <input type="text" class=" text-center form-control col-sm-3" readonly="" value="S/.${montoPagar}0" style="font-size: 20px; font-family: monospace">
                </div>
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
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    </body>
</html>
