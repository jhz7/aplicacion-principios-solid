package com.ceiba.solid.dominio.modelo;

import com.ceiba.solid.dominio.servicio.ServicioFecha;

import java.util.Calendar;
import java.util.Date;

public class PagoOperario extends PagoEmpleado {

    private Double salarioMinimo;
    private Double auxilioTransporte;
    private Empleado empleado;

    public PagoOperario(Empleado empleado, Double salarioMinimo, Double auxilioTransporte) {
        super(empleado.getId());
        this.empleado = empleado;
        this.salarioMinimo = salarioMinimo;
        this.auxilioTransporte = auxilioTransporte;
    }

    @Override
    Double calcularSalario() {
        return salarioMinimo + auxilioTransporte;
    }

    @Override
    Double calcularBonificacion() {
        if(empleado.aniosEnLaCompania() > 3) return calcularSalario() * 0.1d;
        return 0d;
    }

    @Override
    Date obtenerFechaDesembolso() {
        Calendar fecha = ServicioFecha.obtener();
        fecha.add(Calendar.DATE, 5);
        return fecha.getTime();
    }
}
