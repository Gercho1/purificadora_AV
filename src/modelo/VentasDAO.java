package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class VentasDAO {

    public int agregar(Ventas ven) {
        PreparedStatement ps;
        Connection cn = ConexionSQL.conectar();
        String sql = "insert into ventas (idvendedor,fecha,total,estado) values (?,?,?,?)";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, ven.getIdvendedor());
            ps.setDate(2, ven.getFecha());
            ps.setDouble(3, ven.getTotal());
            ps.setString(4, ven.getEstado());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
        return n;
    }

    public int agregardetalle(VentasDetalle vendetalle) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "insert into detalleventa (idventa,idproducto,cantidad,subtotal) values (?,?,?,?)";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, vendetalle.getIdventa());
            ps.setInt(2, vendetalle.getIdproducto());
            ps.setInt(3, vendetalle.getCantidad());
            ps.setDouble(4, vendetalle.getSubtotal());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
        return n;
    }

    public int modificar(Ventas user) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement stmt;
        String qry = "update ventas set estado=? where id=?";
        int n = 0;
        try {
            stmt = cn.prepareStatement(qry);
            stmt.setString(1, user.getEstado());
            stmt.setInt(2, user.getId());
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

    public int Numeroventa() {
        Connection cn = ConexionSQL.conectar();
        ResultSet r;
        Statement stmt;
        String qry = null;

        int n = 0;
        try {
            stmt = cn.createStatement();
            qry = "select MAX(id) id from ventas";
            r = stmt.executeQuery(qry);

            if (r.next()) {
                n = Integer.parseInt(r.getString(1));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return n;

    }

    public Productos BuscarPro(int cod) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        Productos producto = new Productos();
        String sql = "select * from productos where id = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cod);
            r = ps.executeQuery();
            if (r.next()) {
                producto.setNombre(r.getString("nombre"));
                producto.setDescripcion(r.getString("descripcion"));
                producto.setPrecioventa(r.getInt("precioventa"));
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return producto;
    }

    public Usuario Buscaruser(int id) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        Usuario user = new Usuario();
        String sql = "select * from usuarios where id = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            r = ps.executeQuery();
            if (r.next()) {
                user.setNombres(r.getString("nombres"));
                user.setApellidos(r.getString("apellidos"));
                user.setUsuario(r.getString("usuario"));
                user.setClave(r.getString("clave"));
                user.setTipo_Usuario(r.getString("tipo"));
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(VentasDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public List listarAltaVenta() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Ventas> datos = new ArrayList<>();
        String c = "'ALTA'";
        String sql = "SELECT top 50 v.id,v.idvendedor,u.nombres,u.apellidos,v.fecha,v.total,v.estado FROM ventas v\n"
                + "INNER JOIN usuarios u\n"
                + "	ON v.idvendedor = u.id where v.estado = " + c + " order by v.id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Ventas p = new Ventas();
                p.setId(r.getInt(1));
                p.setIdvendedor(r.getInt(2));
                p.setNombre(r.getString(3));
                p.setApellidos(r.getString(4));
                p.setFecha(r.getDate(5));
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

    public List listarAltaVentaDetalle(int a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<VentasDetalle> datos = new ArrayList<>();
        String sql = "	SELECT d.idventa,p.nombre,d.cantidad,d.subtotal FROM detalleventa d\n"
                + "INNER JOIN ventas v\n"
                + "	ON d.idventa = v.id\n"
                + "INNER JOIN productos p\n"
                + "	ON d.idproducto= p.id where d.idventa = " + a + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                VentasDetalle p = new VentasDetalle();
                p.setIdventa(r.getInt(1));
                p.setProducto(r.getString(2));
                p.setCantidad(r.getInt(3));
                p.setSubtotal(r.getDouble(4));
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

    public List buscarvendedor(String a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Usuario> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String sql = "	select id, nombres, apellidos, tipo from usuarios where nombres like " + c + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Usuario p = new Usuario();
                p.setID(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setTipo_Usuario(r.getString(4));
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

    public List listarvendedor() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Usuario> datos = new ArrayList<>();
        String sql = "	select id, nombres, apellidos, tipo from usuarios";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Usuario p = new Usuario();
                p.setID(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setTipo_Usuario(r.getString(4));
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


    public List listarproducto() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Productos> datos = new ArrayList<>();
        String sql = "	select id, nombre, descripcion, precioventa from productos";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Productos p = new Productos();
                p.setId(r.getInt(1));
                p.setNombre(r.getString(2));
                p.setDescripcion(r.getString(3));
                p.setPrecioventa(r.getDouble(4));
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
