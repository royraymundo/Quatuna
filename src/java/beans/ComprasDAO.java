package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.ConexionDB;


public class ComprasDAO {

    PreparedStatement ps;
    ResultSet rs;

    public int IdCompra() {
        int idc = 0;
        String sql = "select max(idCompras) from compras";
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idc = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idc;
    }

    public int guardarCompra(Compra co) {
        String sql = "insert into Compras(idCliente,idPago, FechaCompras,Monto,Estado)values(?,?,?,?,?)";
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            ps.setInt(1, co.getIdCliente());
            ps.setInt(2, co.getIdPago());
            ps.setString(3, co.getFecha());
            ps.setDouble(4, co.getMonto());
            ps.setString(5, co.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return 1;
    }

    public int guardarDetalleCompra(DetalleCompra dc) {
        String sql = "insert into Detalle_Compras(idProducto,idCompras, Cantidad, PrecioCompra)values(?,?,?,?)";
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            ps.setInt(1, dc.getIdproducto());
            ps.setInt(2, dc.getIdcompra());
            ps.setInt(3, dc.getCantidad());
            ps.setDouble(4, dc.getPrecioCompra());
            ps.executeUpdate();
            
        } catch (Exception e) {
        }
        return 1;
    }

    public List misCompras(int id) {
        List lista = new ArrayList();
        String sql = "select * from compras where idCliente=" + id;
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Compra com = new Compra();
                com.setIdCompra(rs.getInt(1));
                com.setIdCliente(rs.getInt(2));
                com.setIdPago(rs.getInt(3));
                com.setFecha(rs.getString(4));
                com.setMonto(rs.getDouble(5));
                com.setEstado(rs.getString(6));
                lista.add(com);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List Detalle(int id) {
        List lista = new ArrayList();
        String sql = "select dc.idDetalle, p.Foto,p.Descripcion,dc.idCompras, dc.Cantidad, dc.PrecioCompra "
                    + "FROM detalle_compras dc "
                    + "INNER JOIN producto p ON p.idProducto = dc.idProducto "
                    + "WHERE idCompras=" + id;
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleCompra dc = new DetalleCompra();
                dc.setIddetalle(rs.getInt(1));
                dc.setProducto(new Producto());
                dc.getProducto().setFoto(rs.getString(2));
                dc.getProducto().setDescripcion(rs.getString(3));
                dc.setIdcompra(rs.getInt(4));
                dc.setCantidad(rs.getInt(5));
                dc.setPrecioCompra(rs.getDouble(6));
                lista.add(dc);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public void Pagar(double monto) {
        String sql = "insert into pago(Monto)values(?)";
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            ps.setDouble(1, monto);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        //return 1;
    }

    public int IdPago() {
        int idpago = 0;
        String sql = "select max(idPago) from pago";
        try {
            ps=ConexionDB.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idpago = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return idpago;
    }
}
