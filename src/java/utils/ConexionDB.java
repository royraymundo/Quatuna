package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConexionDB {
    
     public static Connection getConexion(){
        Connection cn=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/carritov2","root","");
            System.out.println("Conexion Satisfactoria");
        }catch(Exception e){
            System.out.println("Eror de conexión");
        }
        return cn;
    }
    
    public static void main(String[] args) {
        
        getConexion();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
       
    }
    
}
