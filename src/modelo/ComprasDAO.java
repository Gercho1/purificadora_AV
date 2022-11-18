package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComprasDAO {

    public int guardar(Compras com) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "insert into compras (idproveedor,fecha,factura,descripcion,total,estado) values (?,?,?,?,?,?)";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, com.getIdproveedor());
            ps.setDate(2, com.getFecha());
            ps.setString(3, com.getFactura());
            ps.setString(4, com.getDescripcion());
            ps.setDouble(5, com.getTotal());
            ps.setString(6, com.getEstado());
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

    public int modificar(Compras com) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        String sql = "update compras set idproveedor=?,fecha=?,factura=?,descripcion=?,total=?,estado=? where id=?";
        int n = 0;
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, com.getIdproveedor());
            ps.setDate(2, com.getFecha());
            ps.setString(3, com.getFactura());
            ps.setString(4, com.getDescripcion());
            ps.setDouble(5, com.getTotal());
            ps.setString(6, com.getEstado());
            ps.setInt(7, com.getId());
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
        List<Compras> datos = new ArrayList<>();
        String sql = "select c.id,c.idproveedor,p.nombres,p.apellidos,p.razon,c.fecha,c.factura,c.descripcion,c.total,c.estado from compras c\n"
                + "inner join proveedores p\n"
                + "on c.idproveedor = p.id\n"
                + "where c.estado = 'ALTA' order by c.id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Compras p = new Compras();
                p.setId(r.getInt(1));
                p.setIdproveedor(r.getInt(2));
                p.setNombres(r.getString(3));
                p.setApellidos(r.getString(4));
                p.setRazon(r.getString(5));
                p.setFecha(r.getDate(6));
                p.setFactura(r.getString(7));
                p.setDescripcion(r.getString(8));
                p.setTotal(r.getDouble(9));
                p.setEstado(r.getString(10));
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
        List<Compras> datos = new ArrayList<>();
        String sql = "select c.id,c.idproveedor,p.nombres,p.apellidos,p.razon,c.fecha,c.factura,c.descripcion,c.total,c.estado from compras c\n"
                + "inner join proveedores p\n"
                + "on c.idproveedor = p.id\n"
                + "where c.estado = 'BAJA' order by c.id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Compras p = new Compras();
                p.setId(r.getInt(1));
                p.setIdproveedor(r.getInt(2));
                p.setNombres(r.getString(3));
                p.setApellidos(r.getString(4));
                p.setRazon(r.getString(5));
                p.setFecha(r.getDate(6));
                p.setFactura(r.getString(7));
                p.setDescripcion(r.getString(8));
                p.setTotal(r.getDouble(9));
                p.setEstado(r.getString(10));
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
        List<Compras> datos = new ArrayList<>();
        String c = "'" + a + "%" + "'";
        String d = "'" + b + "'";
        String sql = "select c.id,c.idproveedor,p.nombres,p.apellidos,p.razon,c.fecha,c.factura,c.descripcion,c.total,c.estado from compras c\n"
                + "inner join proveedores p\n"
                + "on c.idproveedor = p.id\n"
                + "where c.fecha like "+a+" and c.estado = "+b+"  order by c.id desc";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Compras p = new Compras();
                p.setId(r.getInt(1));
                p.setIdproveedor(r.getInt(2));
                p.setNombres(r.getString(3));
                p.setApellidos(r.getString(4));
                p.setRazon(r.getString(5));
                p.setFecha(r.getDate(6));
                p.setFactura(r.getString(7));
                p.setDescripcion(r.getString(8));
                p.setTotal(r.getDouble(9));
                p.setEstado(r.getString(10));
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

    public Proveedores BuscarPro(int cod) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        Proveedores prov = new Proveedores();
        String sql = "select * from proveedores where id = ?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, cod);
            r = ps.executeQuery();
            if (r.next()) {
                prov.setNombres(r.getString("nombres"));
                prov.setApellidos(r.getString("apellidos"));
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
        return prov;
    }

    public List listarAltaProv() {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Proveedores> datos = new ArrayList<>();
        String c = "'ALTA'";
        String sql = "select id,nombres,apellidos,razon,descripcion from proveedores where estado = " + c + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Proveedores p = new Proveedores();
                p.setId(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setRazon(r.getString(4));
                p.setDescripcion(r.getString(5));
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

    public List buscarProv(String a) {
        Connection cn = ConexionSQL.conectar();
        PreparedStatement ps;
        ResultSet r;
        List<Proveedores> datos = new ArrayList<>();
        String c = "'ALTA'";
        String d = "'" + a + "%'";
        String sql = "select id,nombres,apellidos,razon,descripcion from proveedores where estado = " + c + " and nombres like" + d + "";
        try {
            ps = cn.prepareStatement(sql);
            r = ps.executeQuery();
            while (r.next()) {
                Proveedores p = new Proveedores();
                p.setId(r.getInt(1));
                p.setNombres(r.getString(2));
                p.setApellidos(r.getString(3));
                p.setRazon(r.getString(4));
                p.setDescripcion(r.getString(5));
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
