package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditosabonoDAO {

    public int guardar(Creditosabono cred) {
        PreparedStatement ps;
        Connection cn = ConexionSQL.conectar();
        String sql = "insert into abonocredito (idcredito,fecha,abono,estado) values (?,?,?,?)";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cred.getIdcredito());
            ps.setDate(2, cred.getFecha());
            ps.setDouble(3, cred.getAbono());
            ps.setString(4, cred.getEstado());
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

    public int modificar(Creditosabono prod) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "update abonocredito set estado=? where id=?";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, prod.getEstado());
            ps.setInt(2, prod.getId());
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

    public List listarAltaC() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditos> datos = new ArrayList<>();
        String sql = "select c.id,idcliente,cl.nombres,cl.apellidos,cl.direccion,c.fecha,c.total,c.pendiente from creditos c\n"
                + "inner join clientes cl\n"
                + "on c.idcliente = cl.id where c.estado = 'ALTA' and c.detalle='DEBE' order by c.id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditos p = new Creditos();
                p.setId(r.getInt(1));
                p.setIdcliente(r.getInt(2));
                p.setNombres(r.getString(3));
                p.setApellidos(r.getString(4));
                p.setDireccion(r.getString(5));
                p.setFecha(r.getDate(6));
                p.setTotal(r.getDouble(7));
                p.setPendiente(r.getDouble(8));
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

    public List listarAltaAC(int a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditosabono> datos = new ArrayList<>();
        String c = "'ALTA'";
        String sql = "select * from abonocredito where estado = " + c + " and idcredito = " + a + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditosabono p = new Creditosabono();
                p.setId(r.getInt(1));
                p.setIdcredito(r.getInt(2));
                p.setFecha(r.getDate(3));
                p.setAbono(r.getDouble(4));
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

    public Clientes BuscarPro(int cod) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        Clientes clientes = new Clientes();
        String sql = "select * from clientes where id = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cod);
            r = ps.executeQuery();
            if (r.next()) {
                clientes.setId(r.getInt("id"));
                clientes.setNombres(r.getString("nombres"));
                clientes.setApellidos(r.getString("apellidos"));
                clientes.setDireccion(r.getString("direccion"));
                clientes.setTelefono(r.getInt("telefono"));
                clientes.setEstado(r.getString("estado"));
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
        return clientes;
    }

    public List buscar(String a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditos> datos = new ArrayList<>();
        String d = "'" + a + "%" + "'";
        String sql = "select c.id,idcliente,cl.nombres,cl.apellidos,cl.direccion,c.fecha,c.total,c.pendiente from creditos c\n"
                + "inner join clientes cl\n"
                + "on c.idcliente = cl.id where c.estado = 'ALTA' and cl.nombres like " + d + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditos p = new Creditos();
                p.setId(r.getInt(1));
                p.setIdcliente(r.getInt(2));
                p.setNombres(r.getString(3));
                p.setApellidos(r.getString(4));
                p.setDireccion(r.getString(5));
                p.setFecha(r.getDate(6));
                p.setTotal(r.getDouble(7));
                p.setPendiente(r.getDouble(8));
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
