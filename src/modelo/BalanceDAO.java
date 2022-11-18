package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BalanceDAO {
    
    public List listarIngreso(String a, String b) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Ingresos> datos = new ArrayList<>();
        String aa = "'"+a+"'";
        String bb = "'"+b+"'";
        String sql = "	select fecha,tipo,total from ingresos where estado='ALTA' and fecha between "+aa+" and " + bb;
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Ingresos p = new Ingresos();
                p.setFecha(r.getDate(1));
                p.setTipo(r.getString(2));
                p.setTotal(r.getDouble(3));
                datos.add(p);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
        return datos;
    }

    public List listarEgreso(String a, String b) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Egresos> datos = new ArrayList<>();
        String aa = "'" + a + "'";
        String bb = "'" + b + "'";
        String sql = "	select fecha,tipo, total from egresos where estado = 'ALTA' and fecha between " + aa + " and " + bb;
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Egresos p = new Egresos();
                p.setFecha(r.getDate(1));
                p.setTipo(r.getString(2));
                p.setTotal(r.getDouble(3));
                datos.add(p);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
        return datos;
    }
}
