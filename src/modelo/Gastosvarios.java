package modelo;

import java.sql.Date;

public class Gastosvarios {

    int id;
    Date fecha;
    String tipopago;
    String documento;
    String descripcion;
    Double total;
    String estado;

    public Gastosvarios() {
    }

    public Gastosvarios(int id, Date fecha, String tipopago, String documento, String descripcion, Double total, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.tipopago = tipopago;
        this.documento = documento;
        this.descripcion = descripcion;
        this.total = total;
        this.estado = estado;
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

    public String getTipopago() {
        return tipopago;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
