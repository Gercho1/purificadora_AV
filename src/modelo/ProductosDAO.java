package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    public int guardar(Productos prod) {
        int n = 0;
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "insert into productos (nombre,descripcion,precioventa,estado) values (?,?,?,?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, prod.getNombre());
            ps.setString(2, prod.getDescripcion());
            ps.setDouble(3, prod.getPrecioventa());
            ps.setString(4, prod.getEstado());
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

    public int modificar(Productos prod) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "update productos set nombre=?, descripcion=?, precioventa=?, estado=? where id=?";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, prod.getNombre());
            ps.setString(2, prod.getDescripcion());
            ps.setDouble(3, prod.getPrecioventa());
            ps.setString(4, prod.getEstado());
            ps.setInt(5, prod.getId());
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
        List<Productos> datos = new ArrayList<>();
        String c = "'ALTA'";
        String sql = "select * from productos where estado = " + c + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Productos p = new Productos();
                p.setId(r.getInt(1));
                p.setNombre(r.getString(2));
                p.setDescripcion(r.getString(3));
                p.setPrecioventa(r.getDouble(4));
                p.setEstado(r.getString(5));
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
        List<Productos> datos = new ArrayList<>();
        String c = "'BAJA'";
        String sql = "select * from productos where estado = " + c + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Productos p = new Productos();
                p.setId(r.getInt(1));
                p.setNombre(r.getString(2));
                p.setDescripcion(r.getString(3));
                p.setPrecioventa(r.getDouble(4));
                p.setEstado(r.getString(5));
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
        List<Productos> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String d = "'" + b + "'";
        String sql = "select * from productos where nombre like" + c + " and estado = " + d + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Productos p = new Productos();
                p.setId(r.getInt(1));
                p.setNombre(r.getString(2));
                p.setDescripcion(r.getString(3));
                p.setPrecioventa(r.getDouble(4));
                p.setEstado(r.getString(5));
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
