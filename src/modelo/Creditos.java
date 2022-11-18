package modelo;

import java.sql.Date;

public class Creditos {

    private int id;
    private int idcliente;
    private int idvendedor;
    private String nombrevendedor;
    private String apellidovendedor;
    private String nombres;
    private String apellidos;
    private String direccion;
    private int telefono;
    private Date fecha;
    private double total;
    private double pendiente;
    private String estado;
    private String detalle;

    public Creditos() {
    }

    public Creditos(int id, int idcliente, Date fecha, double total, double pendiente, String estado, String detalle) {
        this.id = id;
        this.idcliente = idcliente;
        this.fecha = fecha;
        this.total = total;
        this.pendiente = pendiente;
        this.estado = estado;
        this.detalle = detalle;
    }

    public Creditos(int id, String nombres, String apellidos, Date fecha, double total, double pendiente, String estado, String detalle) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.total = total;
        this.pendiente = pendiente;
        this.estado = estado;
        this.detalle = detalle;
    }

    public Creditos(int id, int idcliente, int idvendedor, String nombrevendedor, String apellidovendedor, String nombres, String apellidos, String direccion, Date fecha, double total, double pendiente, String estado, String detalle) {
        this.id = id;
        this.idcliente = idcliente;
        this.idvendedor = idvendedor;
        this.nombrevendedor = nombrevendedor;
        this.apellidovendedor = apellidovendedor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fecha = fecha;
        this.total = total;
        this.pendiente = pendiente;
        this.estado = estado;
        this.detalle = detalle;
    }

    public Creditos(String nombrevendedor, String nombres, String direccion, Date fecha, double total, double pendiente) {
        this.nombrevendedor = nombrevendedor;
        this.nombres = nombres;
        this.direccion = direccion;
        this.fecha = fecha;
        this.total = total;
        this.pendiente = pendiente;
    }

    

    
    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNombrevendedor() {
        return nombrevendedor;
    }

    public void setNombrevendedor(String nombrevendedor) {
        this.nombrevendedor = nombrevendedor;
    }

    public String getApellidovendedor() {
        return apellidovendedor;
    }

    public void setApellidovendedor(String apellidovendedor) {
        this.apellidovendedor = apellidovendedor;
    }

    

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
