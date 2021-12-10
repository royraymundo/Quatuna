package beans;


public class DetalleCompra {

    private int iddetalle;
    private int idcompra;
    private int idproducto;
    private int cantidad;
    private double precioCompra;
    private Producto producto;

    public DetalleCompra() {
    }

    public DetalleCompra(int iddetalle, int idcompra, int idproducto, int cantidad, double precioCompra, Producto producto) {
        this.iddetalle = iddetalle;
        this.idcompra = idcompra;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.producto = producto;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIdcompra() {
        return idcompra;
    }

    public void setIdcompra(int idcompra) {
        this.idcompra = idcompra;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    
}
