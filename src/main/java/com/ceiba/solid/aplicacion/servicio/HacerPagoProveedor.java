package com.ceiba.solid.aplicacion.servicio;

import com.ceiba.solid.aplicacion.dto.SolicitudCrearPagoProveedor;
import com.ceiba.solid.dominio.contrato.RepositorioPagoProveedor;
import com.ceiba.solid.dominio.modelo.PagoProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class HacerPagoProveedor {

    @Autowired
    private RepositorioPagoProveedor repositorioPagoProveedor;

    @Transactional
    public void ejecutar(SolicitudCrearPagoProveedor solicitudPago) {
        if (esSolicitudValida(solicitudPago))
            repositorioPagoProveedor.guardar(generarPago(solicitudPago));
    }

    private boolean esSolicitudValida(SolicitudCrearPagoProveedor solicitudPago) {
        return solicitudPago.getIdProveedor() != null && solicitudPago.getValor() != null;
    }

    private PagoProveedor generarPago(SolicitudCrearPagoProveedor solicitudPago) {
        PagoProveedor pago = new PagoProveedor();

        pago.setIdProveedor(solicitudPago.getIdProveedor());
        pago.setValor(solicitudPago.getValor());
        pago.setFechaDesembolso(obtenerFechaDesembolso());

        return pago;
    }

    private Date obtenerFechaDesembolso() {
        LocalDate fechaLocalActual = LocalDate.now();
        LocalDate diaUnoProximoMes = fechaLocalActual.withDayOfMonth(1).withMonth(fechaLocalActual.getMonthValue() + 1);

        return Date.from(diaUnoProximoMes.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
