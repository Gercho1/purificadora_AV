package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreditosDAO {

    public int agregar(Creditos cred) {
        PreparedStatement ps;
        Connection cn = ConexionSQL.conectar();
        String sql = "insert into creditos (idvendedor,idcliente,fecha,total,pendiente,estado,detalle) values (?,?,?,?,?,?,?)";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cred.getIdvendedor());
            ps.setInt(2, cred.getIdcliente());
            ps.setDate(3, cred.getFecha());
            ps.setDouble(4, cred.getTotal());
            ps.setDouble(5, cred.getPendiente());
            ps.setString(6, cred.getEstado());
            ps.setString(7, cred.getDetalle());
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

    public int modificar(Creditos user) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement stmt;
        String qry = "update creditos set estado=? where id=?";
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

    public int agregardetalle(Creditodetalle credetalle) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "insert into detallecredito (idcredito,idproducto,cantidad,subtotal) values (?,?,?,?)";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, credetalle.getIdcredito());
            ps.setInt(2, credetalle.getIdproducto());
            ps.setInt(3, credetalle.getCantidad());
            ps.setDouble(4, credetalle.getSubtotal());
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

    public int Numerocredito() {
        Connection cn = ConexionSQL.conectar();
        ResultSet r;
        Statement stmt;
        String qry = null;

        int n = 0;
        try {
            stmt = cn.createStatement();
            qry = "select MAX(id) id from creditos";
            r = stmt.executeQuery(qry);

            if (r.next()) {
                n = Integer.parseInt(r.getString(1));
            }

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
                System.err.println(ex.toString());
            }
        }
        return producto;
    }

    public List buscarcliente(String a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Clientes> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String sql = "	select id, nombres, apellidos, direccion, telefono from clientes where nombres like " + c + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Clientes p = new Clientes();
                p.setId(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setDireccion(r.getString(4));
                p.setTelefono(r.getInt(5));
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

    public List listarAltaCrédito() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditos> datos = new ArrayList<>();
        String sql = "select c.id,u.nombres,u.apellidos,cl.nombres,cl.apellidos,c.fecha,c.total,c.pendiente,c.estado from creditos c\n"
                + "inner join clientes cl\n"
                + "on c.idcliente=cl.id\n"
                + "inner join usuarios u\n"
                + "on c.idvendedor = u.id where c.estado = 'ALTA' and c.detalle ='DEBE' order by c.id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditos p = new Creditos();
                p.setId(r.getInt(1));
                p.setNombrevendedor(r.getString(2));
                p.setApellidovendedor(r.getString(3));
                p.setNombres(r.getString(4));
                p.setApellidos(r.getString(5));
                p.setFecha(r.getDate(6));
                p.setTotal(r.getDouble(7));
                p.setPendiente(r.getDouble(8));
                p.setEstado(r.getString(9));
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

    public List listarAltaAbonoDetalle(int a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditosabono> datos = new ArrayList<>();
        String sql = "SELECT a.id, a.idcredito, a.fecha, a.abono FROM abonocredito a\n"
                + "INNER JOIN creditos c\n"
                + "	ON a.idcredito = c.id where a.estado = 'ALTA' and a.idcredito =" + a;
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditosabono p = new Creditosabono();
                p.setId(r.getInt(1));
                p.setIdcredito(r.getInt(2));
                p.setFecha(r.getDate(3));
                p.setAbono(r.getDouble(4));
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

//    public List buscarcliente(String a) {
//        Connection cn = ConexionSQL.conectar();
//        PreparedStatement ps;
//        ResultSet r;
//        List<Clientes> datos = new ArrayList<>();
//        String c = "'" + a + "%" + "'";
//        String sql = "	select id, nombres, apellidos, direccion, telefono from clientes where nombres like " + c + "";
//        try {
//            ps = cn.prepareStatement(sql);
//            r = ps.executeQuery();
//            while (r.next()) {
//                Clientes p = new Clientes();
//                p.setId(r.getInt(1));
//                p.setNombres(r.getString(2));
//                p.setApellidos(r.getString(3));
//                p.setDireccion(r.getString(4));
//                p.setTelefono(r.getInt(5));
//                datos.add(p);
//            }
//        } catch (Exception e) {
//            System.err.println(e.toString());
//        } finally {
//            try {
//                cn.close();
//            } catch (SQLException ex) {
//                System.err.println(ex.toString());
//            }
//        }
//        return datos;
//    }
    public List listarcliente() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Clientes> datos = new ArrayList<>();
        String sql = "	select id, nombres, apellidos, direccion, telefono tipo from clientes";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Clientes p = new Clientes();
                p.setId(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setDireccion(r.getString(4));
                p.setTelefono(r.getInt(5));
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

}
