package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GastosvariosDAO {

    public int guardar(Gastosvarios gastosv) {
        int n = 0;
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "insert into gastosvarios (fecha,tipopago,documento,descripcion,total,estado) values (?,?,?,?,?,?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setDate(1, gastosv.getFecha());
            ps.setString(2, gastosv.getTipopago());
            ps.setString(3, gastosv.getDocumento());
            ps.setString(4, gastosv.getDescripcion());
            ps.setDouble(5, gastosv.getTotal());
            ps.setString(6, gastosv.getEstado());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.toString());

        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e.toString());
            }
        }
        return n;
    }

    public int modificar(Gastosvarios gastosv) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "update gastosvarios set fecha=?, tipopago=?, documento=?, descripcion=?, total=?, estado=? where id=?";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setDate(1, gastosv.getFecha());
            ps.setString(2, gastosv.getTipopago());
            ps.setString(3, gastosv.getDocumento());
            ps.setString(4, gastosv.getDescripcion());
            ps.setDouble(5, gastosv.getTotal());
            ps.setString(6, gastosv.getEstado());
            ps.setInt(7, gastosv.getId());
            n = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.toString());

        } finally {
            try {
                cn.close();
            } catch (SQLException e) {
                System.err.println(e.toString());
            }
        }
        return n;
    }

    public List listarAlta() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Gastosvarios> datos = new ArrayList<>();
        String c = "'ALTA'";
        String sql = "select * from gastosvarios where estado = " + c + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Gastosvarios p = new Gastosvarios();
                p.setId(r.getInt(1));
                p.setFecha(r.getDate(2));
                p.setTipopago(r.getString(3));
                p.setDocumento(r.getString(4));
                p.setDescripcion(r.getString(5));
                p.setTotal(r.getDouble(6));
                p.setEstado(r.getString(7));
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

    public List listarBaja() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Gastosvarios> datos = new ArrayList<>();
        String c = "'BAJA'";
        String sql = "select * from gastosvarios where estado = " + c + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Gastosvarios p = new Gastosvarios();
                p.setId(r.getInt(1));
                p.setFecha(r.getDate(2));
                p.setTipopago(r.getString(3));
                p.setDocumento(r.getString(4));
                p.setDescripcion(r.getString(5));
                p.setTotal(r.getDouble(6));
                p.setEstado(r.getString(7));
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

    public List buscar(String a, String b) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Gastosvarios> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String d = "'" + b + "'";
        String sql = "select * from gastosvarios where fecha like" + c + " and estado = " + d + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Gastosvarios p = new Gastosvarios();
                p.setId(r.getInt(1));
                p.setFecha(r.getDate(2));
                p.setTipopago(r.getString(3));
                p.setDocumento(r.getString(4));
                p.setDescripcion(r.getString(5));
                p.setTotal(r.getDouble(6));
                p.setEstado(r.getString(7));
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
