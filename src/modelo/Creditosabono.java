package modelo;

import java.sql.Date;

public class Creditosabono {

    private int id;
    private int idcredito;
    private Date fecha;
    private double abono;
    private String estado;

    public Creditosabono() {
    }

    public Creditosabono(int id, int idcredito, Date fecha, double abono, String estado) {
        this.id = id;
        this.idcredito = idcredito;
        this.fecha = fecha;
        this.abono = abono;
        this.estado = estado;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdcredito() {
        return idcredito;
    }

    public void setIdcredito(int idcredito) {
        this.idcredito = idcredito;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
