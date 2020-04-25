package com.ceiba.solid.aplicacion.servicio;

import com.ceiba.solid.dominio.contrato.RepositorioParametros;
import com.ceiba.solid.dominio.modelo.Empleado;
import com.ceiba.solid.dominio.modelo.PagoEmpleado;
import com.ceiba.solid.dominio.modelo.PagoGerente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerarPagoGerente implements GenerarPagoEmpleado {

    private final Long idParametroSalarioGerente = 3L;

    @Autowired
    private RepositorioParametros repositorioParametros;

    @Override
    public boolean aplicaTipoEmpleadoParaPago(Empleado empleado) {
        return "GERENTE".equals(empleado.getCargo());
    }

    @Override
    public PagoEmpleado generar(Empleado empleado) {

        PagoEmpleado pago = new PagoGerente(
                empleado, obtenerValorNumerico(repositorioParametros.obtenerParametro(idParametroSalarioGerente)));

        pago.calcular();
        return pago;
    }

    private Double obtenerValorNumerico(String texto) {
        try { return Double.parseDouble(texto); }
        catch (NumberFormatException e){ return 0d; }
    }
}
