package com.ceiba.solid.aplicacion.servicio;

import com.ceiba.solid.aplicacion.dto.ResultadoGenerarPagoEmpleado;
import com.ceiba.solid.dominio.contrato.RepositorioEmpleado;
import com.ceiba.solid.dominio.contrato.RepositorioPagoEmpleado;
import com.ceiba.solid.dominio.modelo.Empleado;
import com.ceiba.solid.dominio.modelo.PagoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HacerPagoNomina {

    @Autowired
    private RepositorioEmpleado repositorioEmpleado;

    @Autowired
    private RepositorioPagoEmpleado repositorioPagoEmpleado;

    @Autowired
    private List<GenerarPagoEmpleado> estrategiasGenerarPagoEmpleado;

    @Transactional
    public List<ResultadoGenerarPagoEmpleado> ejecutar() {
        return repositorioEmpleado.consultarTodos().stream()
            .map(this::generarPago)
            .map(pago -> repositorioPagoEmpleado.guardar(pago))
            .map(this::generarResultadoPago)
            .collect(Collectors.toList());
    }

    private PagoEmpleado generarPago(Empleado empleado) {
        return estrategiasGenerarPagoEmpleado.stream()
            .filter(estrategia -> estrategia.aplicaTipoEmpleadoParaPago(empleado))
            .findFirst()
            .orElseThrow(RuntimeException::new)
            .generar(empleado);
    }

    private ResultadoGenerarPagoEmpleado generarResultadoPago(PagoEmpleado pago) {
        return ResultadoGenerarPagoEmpleado.builder()
            .id(pago.getId())
            .bonificacion(pago.getBonificacion())
            .idEmpleado(pago.getIdEmpleado())
            .fechaDesembolso(pago.getFechaDesembolso())
            .valor(pago.getValor()).build();
    }
}
