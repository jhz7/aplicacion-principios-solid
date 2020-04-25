package com.ceiba.solid.dominio.modelo;

import com.ceiba.solid.dominio.servicio.ServicioFecha;

import java.util.Date;

public class PagoGerente extends PagoEmpleado {

    private Double valorSalarioGenerente;
    private Empleado empleado;

    public PagoGerente(Empleado empleado, Double valorSalarioGenerente) {
        super(empleado.getId());
        this.empleado = empleado;
        this.valorSalarioGenerente = valorSalarioGenerente;
    }

    @Override
    Double calcularSalario() {
        return valorSalarioGenerente;
    }

    @Override
    Double calcularBonificacion() {
        if(empleado.aniosEnLaCompania() > 5) return calcularSalario() * 0.3d;
        return 0d;
    }

    @Override
    Date obtenerFechaDesembolso() {
        return ServicioFecha.obtener().getTime();
    }
}
