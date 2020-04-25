package com.ceiba.solid.infraestructura.controller;

import com.ceiba.solid.aplicacion.dto.SolicitudCrearPagoProveedor;
import com.ceiba.solid.aplicacion.servicio.HacerPagoProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagoProveedor {

    @Autowired
    private HacerPagoProveedor pagoProveedor;

    @PostMapping("provider-payment")
    public void payProvider(@RequestBody SolicitudCrearPagoProveedor pago) {
        pagoProveedor.ejecutar(pago);
    }
}
