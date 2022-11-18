package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimientoDAO {

    public List listar() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Movimiento> datos = new ArrayList<>();
        String sql = "select fecha,tipo,entrada,salida,estado,saldo from (select top 50 * from movimientos order by id desc) movimientos order by id asc ";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Movimiento p = new Movimiento();
                
                p.setFecha(r.getDate(1));
                p.setTipo(r.getString(2));
                p.setEntrada(r.getDouble(3));
                p.setSalida(r.getDouble(4));
                p.setEstado(r.getString(5));
                p.setSaldo(r.getDouble(6));
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

    public double buscar() {
        double saldo = 0.00;
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Movimiento> datos = new ArrayList<>();
        String sql = "SELECT TOP 1 saldo FROM movimientos ORDER BY id DESC";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                saldo = r.getDouble(1);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }return saldo;
    } 
}
