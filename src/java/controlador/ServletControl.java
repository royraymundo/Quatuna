package controlador;

import beans.Carrito;
import beans.Cliente;
import beans.ClienteDAO;
import beans.Compra;
import beans.ComprasDAO;
import beans.DetalleCompra;
import beans.Pago;
import beans.Producto;
import beans.ProductoDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.Fecha;

public class ServletControl extends HttpServlet {

    Pago pago = new Pago();
    Cliente cl = new Cliente();
    ClienteDAO cldao = new ClienteDAO();
    ComprasDAO cdao = new ComprasDAO();
    ProductoDAO pdao=new ProductoDAO();
    Producto p=new Producto();
   
     int item =0;           //ok
     double totalPagar=0.0; //ok
     double subtotal = 0.0; //ok
     int cantidad=1;        //ok
     
    
    List<Carrito> listaCarrito= new ArrayList<>();
    List productos=new ArrayList();
    
    String logueo = "Iniciar Sesion";
    String correo = "Iniciar Sesion";
     
    //nuevas variables para pago
    int idcompra;
    int idpago;
    double montopagar;
    int idProducto = 0;

    Carrito car = new Carrito();
     
    Fecha fechaSistem = new Fecha(); //para usar fecha en formato string
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.setAttribute("logueo", logueo);
        session.setAttribute("correo", correo);
        
        productos = pdao.listar();
        String accion=request.getParameter("accion");
        
        productos = pdao.listar();
               
        switch (accion){
            
            case "Catalogo":
                request.setAttribute("contador", listaCarrito.size());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("catalogo.jsp").forward(request, response);
                break;
                           
            case "AgregarCarrito": //btn que agrega al carrito
                agregarCarrito(request);
                request.setAttribute("contador", listaCarrito.size());
                request.setAttribute("msgOK", "Producto Añadido al Carrito");
                request.getRequestDispatcher("ServletControl?accion=Catalogo").forward(request, response);
                break;
                 
            case "Carrito":
                totalPagar = 0.0;
                item = 0;
                request.setAttribute("carrito", listaCarrito);
                for (int i = 0; i < listaCarrito.size(); i++) {
                    totalPagar = totalPagar + listaCarrito.get(i).getSubTotal(); //suma columna subtotal
                    listaCarrito.get(i).setItem(item + i + 1);
                }
                request.setAttribute("totalpagar", totalPagar);
                request.getRequestDispatcher("carrito.jsp").forward(request, response);
                break;
                
            case "Comprar": //ok
                agregarCarrito(request);
                request.getRequestDispatcher("ServletControl?accion=Carrito").forward(request, response);
                break;
                
            case "RealizarPago":
                montopagar = totalPagar;
                String mensaje="";
                if (cl.getIdCliente() != 0 && montopagar > 0) {
                    cdao.Pagar(montopagar);
                    request.setAttribute("msgOK", "Pago Realizado Correctamente");
                } else {
                    montopagar = 0;
                    request.setAttribute("msgNO", "Inicie Sesión para Realizar Pago");
                }
                request.getRequestDispatcher("ServletControl?accion=Carrito").forward(request, response);
                break;
                
            case "GenerarCompra":
                idpago = cdao.IdPago();
                if (cl.getIdCliente() != 0 && listaCarrito.size() != 0 && montopagar > 0) {
                    if (totalPagar > 0.0) {
                        Compra co = new Compra();
                        co.setIdCliente(cl.getIdCliente());
                       co.setFecha(fechaSistem.FechaBD());
                        co.setMonto(totalPagar);
                        co.setIdPago(idpago);
                        co.setEstado("Pagado - Envio en proceso");
                        cdao.guardarCompra(co);
                        montopagar = 0;

                        idcompra = cdao.IdCompra();
                        for (int i = 0; i < listaCarrito.size(); i++) {
                            DetalleCompra dc = new DetalleCompra();
                            dc.setIdcompra(idcompra);
                            dc.setIdproducto(listaCarrito.get(i).getIdProducto());
                            dc.setCantidad(listaCarrito.get(i).getCantidad());
                            dc.setPrecioCompra(listaCarrito.get(i).getPrecioCompra());
                            cdao.guardarDetalleCompra(dc);
                        }
                        listaCarrito = new ArrayList<>();
                        List compra = cdao.misCompras(cl.getIdCliente());
                        request.setAttribute("myCompras", compra);
                        request.setAttribute("msgOK", "Compra Realizada Correctamente");
                        request.getRequestDispatcher("compras.jsp").forward(request, response);
                    } else {
                        request.setAttribute("msgNO", "Su Carrito está Vacío");
                        request.getRequestDispatcher("ServletControl?accion=listar").forward(request, response);
                    }
                } else {
                    if(listaCarrito.size() == 0){
                        request.setAttribute("msgNO", "Su Carrito esta Vacío");
                    }else{
                        request.setAttribute("msgNO", "Realice Primero el Pago");
                    }
                    request.getRequestDispatcher("ServletControl?accion=Carrito").forward(request, response);
                }
                break;
                
            case "MisCompras":
                if (cl.getIdCliente()!= 0) {
                    List compra = cdao.misCompras(cl.getIdCliente());
                    request.setAttribute("myCompras", compra);
                    request.getRequestDispatcher("compras.jsp").forward(request, response);
                } else if (listaCarrito.size() > 0) {
                    request.setAttribute("msgNO", "Inicie Sesión para Procesar Compra");
                    request.getRequestDispatcher("ServletControl?accion=Carrito").forward(request, response);
                } else {
                    request.setAttribute("msgNO", "Inicie Sesión para ver Historial de Compras");
                    request.getRequestDispatcher("ServletControl?accion=Catalogo").forward(request, response);
                }
                break;
            
            case "verDetalle":
                totalPagar = 0.0;
                int idcompras = Integer.parseInt(request.getParameter("idcompra"));
                List<DetalleCompra> detalle = cdao.Detalle(idcompras);
                request.setAttribute("myDetalle", detalle);
                for (int i = 0; i < detalle.size(); i++) {
                    totalPagar = totalPagar + (detalle.get(i).getPrecioCompra() * detalle.get(i).getCantidad());
                }
                request.setAttribute("montoPagar", totalPagar);
                request.getRequestDispatcher("detalleCompra.jsp").forward(request, response);
                break;
                
            case "Validar":
                String email = request.getParameter("txtEmail");
                String pass = request.getParameter("txtPass");
                cl = cldao.Validar(email, pass);
                if (cl.getIdCliente() != 0) {
                    logueo = cl.getNombres();
                    correo = cl.getEmail();
                }else{
                    request.setAttribute("msgNO", "Usuario y/o Contraseña Incorrectos");
                    request.getRequestDispatcher("ServletControl?accion=listar").forward(request, response);
                    break;
                }
                request.setAttribute("msgOK", "Bienvenido " + logueo);
                request.getRequestDispatcher("ServletControl?accion=listar").forward(request, response);
                break;
                
            case "Registrar":
                String nom = request.getParameter("txtNombre");
                String dni = request.getParameter("txtDni");
                String em = request.getParameter("txtEmail");
                String pas = request.getParameter("txtPassword");
                String dir = request.getParameter("txtDireccion");
                cl.setNombres(nom);
                cl.setDni(dni);
                cl.setEmail(em);
                cl.setPass(pas);
                cl.setDireccion(dir);
                cldao.AgregarCliente(cl);
                request.setAttribute("msgOK", "Nuevo Usuario Creado Correctamente");
                request.getRequestDispatcher("ServletControl?accion=listar").forward(request, response);
                break;
                
            case "Salir":
                listaCarrito = new ArrayList();
                cl = new Cliente();
                session.invalidate();
                logueo = "Iniciar Sesion";
                correo = "Iniciar Sesion";
                request.setAttribute("msgNO", "Ha cerrado su Sesión");
                request.getRequestDispatcher("ServletControl?accion=listar").forward(request, response);
                break;
                
            default:
                request.setAttribute("contador", listaCarrito.size());
                request.setAttribute("productos", productos);
                request.getRequestDispatcher("home.jsp").forward(request, response);
        }
        
            
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    public void agregarCarrito(HttpServletRequest request) {
        cantidad = 1;
        int pos = 0;
        int idpp = Integer.parseInt(request.getParameter("id"));
        if (listaCarrito.size() > 0) {
            for (int i = 0; i < listaCarrito.size(); i++) {
                if (listaCarrito.get(i).getIdProducto() == idpp) {
                    pos = i;
                }
            }
            if (idpp == listaCarrito.get(pos).getIdProducto()) {
                cantidad = listaCarrito.get(pos).getCantidad() + cantidad;
                subtotal = listaCarrito.get(pos).getPrecioCompra() * cantidad;
                listaCarrito.get(pos).setCantidad(cantidad);
                listaCarrito.get(pos).setSubTotal(subtotal);
            } else {
                car = new Carrito();
                p = pdao.listarId(idpp);
                car.setIdProducto(p.getIdProducto());
                car.setNombre(p.getNombre());
                car.setFoto(p.getFoto());
                car.setDescripcion(p.getDescripcion());
                car.setPrecioCompra(p.getPrecio());
                car.setCantidad(cantidad);
                subtotal = cantidad * p.getPrecio();
                car.setSubTotal(subtotal);
                listaCarrito.add(car);
            }
        } else {
            car = new Carrito();
            p = pdao.listarId(idpp);
            car.setIdProducto(p.getIdProducto());
            car.setNombre(p.getNombre());
            car.setFoto(p.getFoto());
            car.setDescripcion(p.getDescripcion());
            car.setPrecioCompra(p.getPrecio());
            car.setCantidad(cantidad);
            subtotal = cantidad * p.getPrecio();
            car.setSubTotal(subtotal);
            listaCarrito.add(car);
        }
    }
}
