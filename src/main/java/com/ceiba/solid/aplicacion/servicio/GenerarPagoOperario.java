package com.ceiba.solid.aplicacion.servicio;

import com.ceiba.solid.dominio.contrato.RepositorioParametros;
import com.ceiba.solid.dominio.modelo.Empleado;
import com.ceiba.solid.dominio.modelo.PagoEmpleado;
import com.ceiba.solid.dominio.modelo.PagoOperario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerarPagoOperario implements GenerarPagoEmpleado{

    private final Long idParametroSalarioMinimo = 1L;
    private final Long idParametroAuxilioTransporte = 2L;

    @Autowired
    private RepositorioParametros repositorioParametros;

    @Override
    public boolean aplicaTipoEmpleadoParaPago(Empleado empleado) {
        return "OPERARIO".equals(empleado.getCargo());
    }

    @Override
    public PagoEmpleado generar(Empleado empleado) {
        PagoEmpleado pago = new PagoOperario(
                empleado,
                obtenerValorNumerico(repositorioParametros.obtenerParametro(idParametroSalarioMinimo)),
                obtenerValorNumerico(repositorioParametros.obtenerParametro(idParametroAuxilioTransporte))
        );

        pago.calcular();
        return pago;
    }

    private Double obtenerValorNumerico(String texto) {
        try { return Double.parseDouble(texto); }
        catch (NumberFormatException e){ return 0d; }
    }
}
