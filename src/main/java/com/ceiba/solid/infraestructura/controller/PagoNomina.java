package com.ceiba.solid.infraestructura.controller;

import com.ceiba.solid.aplicacion.servicio.HacerPagoNomina;
import com.ceiba.solid.aplicacion.dto.ResultadoGenerarPagoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("payroll")
public class PagoNomina {

    @Autowired
    private HacerPagoNomina pagoNomina;

    @GetMapping("apply-salary")
    public List<ResultadoGenerarPagoEmpleado> apply() {
        return pagoNomina.ejecutar();
    }

}
