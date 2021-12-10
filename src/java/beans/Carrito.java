package beans;

public class Carrito {
   private int item;
   private int idProducto;
   private String nombre;
   private String descripcion;
   private String foto;
   private double precioCompra;
   private int cantidad;
   private double subTotal;

    public Carrito() {
    }

    public Carrito(int item, int idProducto, String nombre, String descripcion, String foto, double precioCompra, int cantidad, double subTotal) {
        this.item = item;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
}
