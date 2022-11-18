package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresDAO {

    public int guardar(Proveedores prod) {
        int n = 0;
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "insert into proveedores (nombres,apellidos,razon,telefono,correo,descripcion,estado) values (?,?,?,?,?,?,?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, prod.getNombres());
            ps.setString(2, prod.getApellidos());
            ps.setString(3, prod.getRazon());
            ps.setInt(4, prod.getTelefono());
            ps.setString(5, prod.getCorreo());
            ps.setString(6, prod.getDescripcion());
            ps.setString(7, prod.getEstado());
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

    public int modificar(Proveedores prod) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "update proveedores set nombres=?, apellidos=?, razon=?, telefono=?, correo=?, descripcion=?, estado=? where id=?";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, prod.getNombres());
            ps.setString(2, prod.getApellidos());
            ps.setString(3, prod.getRazon());
            ps.setInt(4, prod.getTelefono());
            ps.setString(5, prod.getCorreo());
            ps.setString(6, prod.getDescripcion());
            ps.setString(7, prod.getEstado());
            ps.setInt(8, prod.getId());
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
        List<Proveedores> datos = new ArrayList<>();
        String c = "'ALTA'";
        String sql = "select * from proveedores where estado = " + c + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Proveedores p = new Proveedores();
                p.setId(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setRazon(r.getString(4));
                p.setTelefono(r.getInt(5));
                p.setCorreo(r.getString(6));
                p.setDescripcion(r.getString(7));
                p.setEstado(r.getString(8));
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
        List<Proveedores> datos = new ArrayList<>();
        String c = "'BAJA'";
        String sql = "select * from proveedores where estado = " + c + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Proveedores p = new Proveedores();
                p.setId(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setRazon(r.getString(4));
                p.setTelefono(r.getInt(5));
                p.setCorreo(r.getString(6));
                p.setDescripcion(r.getString(7));
                p.setEstado(r.getString(8));
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
        List<Proveedores> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String d = "'" + b + "'";
        String sql = "select * from proveedores where nombres like" + c + " and estado = " + d + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Proveedores p = new Proveedores();
                p.setId(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setRazon(r.getString(4));
                p.setTelefono(r.getInt(5));
                p.setCorreo(r.getString(6));
                p.setDescripcion(r.getString(7));
                p.setEstado(r.getString(8));
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
