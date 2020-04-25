package com.ceiba.solid.infraestructura.repositorio;

import com.ceiba.solid.dominio.contrato.RepositorioPagoProveedor;
import com.ceiba.solid.infraestructura.entidad.PagoEntity;
import com.ceiba.solid.dominio.modelo.PagoProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RepositorioPagoProveedorJPA implements RepositorioPagoProveedor {

    @Autowired
    private EntityManager entityManager;

    @Override
    public PagoProveedor guardar(PagoProveedor pago) {

        PagoEntity entity = convertirPagoAEntity(pago);
        entityManager.persist(entity);
        pago.setId(entity.getId());

        return pago;
    }

    private PagoEntity convertirPagoAEntity(PagoProveedor pago) {
        PagoEntity entity = new PagoEntity();

        entity.setId(pago.getId());
        entity.setValor(pago.getValor());
        entity.setFechaDesembolso(pago.getFechaDesembolso());
        entity.setIdProveedor(pago.getIdProveedor());

        return entity;
    }
}
