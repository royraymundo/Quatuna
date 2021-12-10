package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.ConexionDB;


public class ClienteDAO {
    
    PreparedStatement ps;
    ResultSet rs;

    public Cliente Validar(String email, String pass) {
        String sql="select * from cliente where Email=? and Password=?";
        Cliente c=new Cliente();
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setIdCliente(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombres(rs.getString(3));
                c.setDireccion(rs.getString(4));
                c.setEmail(rs.getString(5));
                c.setPass(rs.getString(6));
            }
        } catch (Exception e) {
        }
        return c;        
    }
    public int AgregarCliente(Cliente c) {
        String sql="INSERT INTO cliente (Dni, Nombres, Direccion, Email, Password)values(?,?,?,?,?)";        
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            ps.setString(1,c.getDni());
            ps.setString(2, c.getNombres());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getPass());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;        
    }
    
}
