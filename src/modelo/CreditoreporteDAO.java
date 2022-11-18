/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gchoj
 */
public class CreditoreporteDAO {

    public List buscarvendedor(String a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Usuario> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String sql = "	select id, nombres, apellidos, tipo from usuarios where nombres like " + c + " and estado = 'ALTA'";
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
        } catch (SQLException e) {
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

    public List listarUsuario() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Usuario> datos = new ArrayList<>();
        String sql = "select id,nombres,apellidos,tipo from usuarios where estado = 'ALTA'";
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
        } catch (SQLException e) {
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

    public List listarCredito(int a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditos> datos = new ArrayList<>();
        String sql = "select c.fecha,cl.nombres,cl.apellidos,cl.direccion,c.total,c.pendiente from creditos c\n"
                + "inner join clientes cl\n"
                + "on cl.id = c.idcliente\n"
                + "where c.idvendedor = " + a + " and c.detalle = 'DEBE'";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditos p = new Creditos();
                p.setFecha(r.getDate(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setDireccion(r.getString(4));
                p.setTotal(r.getDouble(5));
                p.setPendiente(r.getDouble(6));
                datos.add(p);
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
        return datos;
    }

    public List listarCreditoGeneral() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditos> datos = new ArrayList<>();
        String sql = "select concat(cl.nombres,' ',cl.apellidos)cliente,concat(u.nombres,' ',u.apellidos)vendedor,cl.direccion,cl.telefono,c.fecha,c.total,c.pendiente from creditos c\n"
                + "inner join clientes cl\n"
                + "on c.idcliente = cl.id\n"
                + "inner join usuarios u\n"
                + "on c.idvendedor = u.id where c.detalle = 'DEBE'";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditos p = new Creditos();
                p.setNombres(r.getString(1));
                p.setNombrevendedor(r.getString(2));
                p.setDireccion(r.getString(3));
                p.setTelefono(r.getInt(4));
                p.setFecha(r.getDate(5));
                p.setTotal(r.getDouble(6));
                p.setPendiente(r.getDouble(7));
                datos.add(p);
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
        return datos;
    }

    public List listarCreditoMorosos() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Creditos> datos = new ArrayList<>();
        String sql = "select concat(cl.nombres,' ',cl.apellidos)cliente,concat(u.nombres,' ',u.apellidos)vendedor,cl.direccion,cl.telefono,c.fecha,c.total,c.pendiente from creditos c\n"
                + "inner join clientes cl\n"
                + "on c.idcliente = cl.id\n"
                + "inner join usuarios u\n"
                + "on c.idvendedor = u.id where c.fecha <= DATEADD(MM, -2, GETDATE()) and c.detalle = 'DEBE'";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Creditos p = new Creditos();
                p.setNombres(r.getString(1));
                p.setNombrevendedor(r.getString(2));
                p.setDireccion(r.getString(3));
                p.setTelefono(r.getInt(4));
                p.setFecha(r.getDate(5));
                p.setTotal(r.getDouble(6));
                p.setPendiente(r.getDouble(7));
                datos.add(p);
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
        return datos;
    }
}
