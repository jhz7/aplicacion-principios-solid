package com.ceiba.solid.aplicacion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ResultadoGenerarPagoEmpleado {

    private Long id;
    private Double valor;
    private Double bonificacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date fechaDesembolso;
    private Long idEmpleado;
    private Long idProveedor;
}
