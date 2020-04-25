package com.ceiba.solid.aplicacion.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SolicitudCrearPagoProveedor {

    private Double valor;
    private Long idProveedor;
}
