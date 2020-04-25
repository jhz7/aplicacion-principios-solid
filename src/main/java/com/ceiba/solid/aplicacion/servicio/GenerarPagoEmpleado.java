package com.ceiba.solid.aplicacion.servicio;

import com.ceiba.solid.dominio.modelo.Empleado;
import com.ceiba.solid.dominio.modelo.PagoEmpleado;

public interface GenerarPagoEmpleado {

    boolean aplicaTipoEmpleadoParaPago(Empleado empleado);

    PagoEmpleado generar(Empleado empleado);
}
