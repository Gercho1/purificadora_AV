package modelo;

import java.sql.Date;

public class Egresos {

    private int id;
    private Date fecha;
    private String tipo;
    private double total;

    public Egresos() {
    }

    public Egresos(int id, Date fecha, String tipo, double total) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
