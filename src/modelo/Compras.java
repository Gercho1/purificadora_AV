package modelo;

import java.sql.Date;

public class Compras {

    int id;
    int idusuario;
    int idproveedor;
    String nombres;
    String apellidos;
    String razon;
    Date fecha;
    String factura;
    String descripcion;
    double total;
    String estado;

    public Compras() {
    }

    public Compras(int id, int idusuario, int idproveedor, Date fecha, String factura, String descripcion, double total, String estado) {
        this.id = id;
        this.idusuario = idusuario;
        this.idproveedor = idproveedor;
        this.fecha = fecha;
        this.factura = factura;
        this.descripcion = descripcion;
        this.total = total;
        this.estado = estado;
    }

    public Compras(int id, int idusuario, int idproveedor, String nombres, String apellidos, String razon, Date fecha, String factura, String descripcion, double total, String estado) {
        this.id = id;
        this.idusuario = idusuario;
        this.idproveedor = idproveedor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.razon = razon;
        this.fecha = fecha;
        this.factura = factura;
        this.descripcion = descripcion;
        this.total = total;
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
