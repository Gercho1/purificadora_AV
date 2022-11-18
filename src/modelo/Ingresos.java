package modelo;

import java.sql.Date;

public class Ingresos {

    private Date fecha;
    private String tipo;
    private String vendedor;
    private double total;

    public Ingresos() {
    }

    public Ingresos(Date fecha, String tipo, String vendedor, double total) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.vendedor = vendedor;
        this.total = total;
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

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
