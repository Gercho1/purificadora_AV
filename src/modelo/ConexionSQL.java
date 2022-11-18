
package modelo;

import java.sql.*;
import javax.swing.JOptionPane;

public class ConexionSQL {
public Connection con=null;
public static Connection conectar(){
        String host;
        String port;
        String database;
        String usuario;
        String clave;
        String cadenaconexion;
        Connection cn = null;
        
        try{
            host = "127.0.0.1";
            port = "1433";
            database = "PurificadoraChiac";
            usuario ="sa";
            clave = "1234";
            
            
            cadenaconexion = "jdbc:sqlserver://"+host+":"+port+";"+"databasename="+database;
            
            
            try{
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            }catch(ClassNotFoundException ef){
                System.out.println("problemas con el driver" + ef.getMessage());
            }
            cn = DriverManager.getConnection(cadenaconexion, usuario, clave);
            
            if(cn != null){
                System.out.println("conectado");
            }
            return cn;
            
        }catch(SQLException ex){
            System.out.println("Problemas en la conexion, sqlserver " + ex);
            
            JOptionPane.showMessageDialog(null,"Error: \n" + ex);
            
            return null;
        }
    }
}
