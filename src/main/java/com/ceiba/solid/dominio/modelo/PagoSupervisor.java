package com.ceiba.solid.dominio.modelo;

import com.ceiba.solid.dominio.servicio.ServicioFecha;

import java.util.Calendar;
import java.util.Date;

public class PagoSupervisor extends PagoEmpleado {

    private Double salarioMinimo;
    private Empleado empleado;

    public PagoSupervisor(Empleado empleado, Double salarioMinimo) {
        super(empleado.getId());
        this.empleado = empleado;
        this.salarioMinimo = salarioMinimo;
    }

    @Override
    Double calcularSalario() {
        return salarioMinimo * 2d;
    }

    @Override
    Double calcularBonificacion() {
        if(empleado.aniosEnLaCompania() > 2) return calcularSalario() * 0.1d;
        return 0d;
    }

    @Override
    Date obtenerFechaDesembolso() {
        Calendar fecha = ServicioFecha.obtener();
        fecha.add(Calendar.DATE, 3);
        return fecha.getTime();
    }
}
