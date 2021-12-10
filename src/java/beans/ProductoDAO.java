package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.ConexionDB;

public class ProductoDAO {

    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Producto>productos=new ArrayList();
        String sql="SELECT * FROM producto";
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto p=new Producto();
                p.setIdProducto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
        } catch (Exception e) {
             System.out.println("error: "+e);
        }
        return productos;
    }
    
    public Producto listarId(int id){
        String sql="SELECT * FROM producto where idProducto="+id;
        Producto p=new Producto();
        
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                p.setIdProducto(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setFoto(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
            }
        } catch (Exception e) {
        }
        return p;
    }
//     public static void main(String[] args) {
//          ProductoDAO pdao=new ProductoDAO();
//    List<Producto> productos= new ArrayList<>();
//    
//      
//      productos = pdao.listar();
//         System.out.println(productos);
//    }
}
