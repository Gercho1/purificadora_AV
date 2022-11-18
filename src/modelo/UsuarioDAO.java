package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario autenticacion(String user, String clave) {
        Connection cn = ConexionSQL.conectar();
        ResultSet r;
        Usuario usuario = null;
        try {

            String qry = "select * from usuarios where usuario =? and clave= ?";
            PreparedStatement past = cn.prepareStatement(qry);
            past.setString(1, user);
            past.setString(2, clave);
            r = past.executeQuery();
            if (r.next()) {
                usuario = new Usuario();
                usuario.setID(r.getInt(1));
                usuario.setNombres(r.getString(2));
                usuario.setApellidos(r.getString(3));
                usuario.setUsuario(r.getString(4));
                usuario.setClave(r.getString(5));
                usuario.setTipo_Usuario(r.getString(6));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
        return usuario;
    }

    public int agregar(Usuario user) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement stmt;
        int n = 0;
        try {
            String qry = "insert into usuarios (nombres,apellidos,usuario,clave,tipo,estado) values (?,?,?,?,?,?)";
            stmt = cn.prepareStatement(qry);
            stmt.setString(1, user.getNombres());
            stmt.setString(2, user.getApellidos());
            stmt.setString(3, user.getUsuario());
            stmt.setString(4, user.getClave());
            stmt.setString(5, user.getTipo_Usuario());
            stmt.setString(6, user.getEstado());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
        return n;
    }

    public int modificar(Usuario user) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement stmt;
        String qry = "update usuarios set nombres=?, apellidos=?, usuario=?, clave=?, tipo=?, estado=? where id=?";
        int n = 0;
        try {
            stmt = cn.prepareStatement(qry);
            stmt.setString(1, user.getNombres());
            stmt.setString(2, user.getApellidos());
            stmt.setString(3, user.getUsuario());
            stmt.setString(4, user.getClave());
            stmt.setString(5, user.getTipo_Usuario());
            stmt.setString(6, user.getEstado());
            stmt.setInt(7, user.getID());
            n= stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
        return n;
    }




    public List buscar(String a,String b) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Usuario> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String d = "'" + b + "'";
        String sql = "select * from usuarios where nombres like" + c + " and estado = "+ d + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Usuario p = new Usuario();
                p.setID(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setUsuario(r.getString(4));
                p.setClave(r.getString(5));
                p.setTipo_Usuario(r.getString(6));
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
    public List listarAlta() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Usuario> datos = new ArrayList<>();
        String c = "'ALTA'";
        String sql = "select * from usuarios where estado = " + c + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Usuario p = new Usuario();
                p.setID(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setUsuario(r.getString(4));
                p.setClave(r.getString(5));
                p.setTipo_Usuario(r.getString(6));
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
        List<Usuario> datos = new ArrayList<>();
        String c = "'BAJA'";
        String sql = "select * from usuarios where estado =" + c + " order by id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Usuario p = new Usuario();
                p.setID(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setUsuario(r.getString(4));
                p.setClave(r.getString(5));
                p.setTipo_Usuario(r.getString(6));
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
