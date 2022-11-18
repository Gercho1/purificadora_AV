package modelo;

import java.sql.Date;

public class Movimiento {

    private int id;
    private int idingreso;
    private int idegreso;
    private Date fecha;
    private String tipo;
    private double entrada;
    private double salida;
    private String estado;
    private double saldo;

    public Movimiento() {
    }

    public Movimiento(int id, int idingreso, int idegreso, Date fecha, String tipo, double entrada, double salida, String estado, double saldo) {
        this.id = id;
        this.idingreso = idingreso;
        this.idegreso = idegreso;
        this.fecha = fecha;
        this.tipo = tipo;
        this.entrada = entrada;
        this.salida = salida;
        this.estado = estado;
        this.saldo = saldo;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public double getSalida() {
        return salida;
    }

    public void setSalida(double salida) {
        this.salida = salida;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdingreso() {
        return idingreso;
    }

    public void setIdingreso(int idingreso) {
        this.idingreso = idingreso;
    }

    public int getIdegreso() {
        return idegreso;
    }

    public void setIdegreso(int idegreso) {
        this.idegreso = idegreso;
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


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}
