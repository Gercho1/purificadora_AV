package modelo;

import java.sql.Date;


public class Ventas {

    private int id;
    private int idvendedor;
    private String nombre;
    private String apellidos;
    private Date fecha;
    private double total;
    private String estado;

    public Ventas() {
    }

    public Ventas(int id, int idvendedor, Date fecha, double total, String estado) {
        this.id = id;
        this.idvendedor = idvendedor;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
    }

    public Ventas(int id, int idvendedor, String nombre, String apellidos,Date fecha, double total, String estado) {
        this.id = id;
        this.idvendedor = idvendedor;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
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

    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

}
