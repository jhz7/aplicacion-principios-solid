package com.ceiba.solid.dominio.modelo;

import java.util.Date;

public abstract class PagoEmpleado {

    private Long id;
    private Double valor;
    private Double bonificacion;
    private Date fechaDesembolso;
    private Long idEmpleado;

    PagoEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    abstract Double calcularSalario();

    abstract Double calcularBonificacion();

    abstract Date obtenerFechaDesembolso();

    public void calcular() {
        this.valor = calcularSalario() + calcularBonificacion();
        this.fechaDesembolso = obtenerFechaDesembolso();
        this.bonificacion = calcularBonificacion();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getValor() {
        return valor;
    }

    public Double getBonificacion() {
        return bonificacion;
    }

    public Date getFechaDesembolso() {
        return fechaDesembolso;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }
}
