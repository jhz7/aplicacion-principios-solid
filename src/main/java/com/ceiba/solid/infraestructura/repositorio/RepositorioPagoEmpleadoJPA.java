package com.ceiba.solid.infraestructura.repositorio;

import com.ceiba.solid.dominio.contrato.RepositorioPagoEmpleado;
import com.ceiba.solid.infraestructura.entidad.PagoEntity;
import com.ceiba.solid.dominio.modelo.PagoEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RepositorioPagoEmpleadoJPA implements RepositorioPagoEmpleado {

    @Autowired
    private EntityManager entityManager;

    @Override
    public PagoEmpleado guardar(PagoEmpleado pago) {

        PagoEntity entity = convertirPagoAEntity(pago);
        entityManager.persist(entity);
        pago.setId(entity.getId());

        return pago;
    }

    private PagoEntity convertirPagoAEntity(PagoEmpleado pago) {
        PagoEntity entity = new PagoEntity();

        entity.setId(pago.getId());
        entity.setIdEmpleado(pago.getIdEmpleado());
        entity.setValor(pago.getValor());
        entity.setBonificacion(pago.getBonificacion());
        entity.setFechaDesembolso(pago.getFechaDesembolso());

        return entity;
    }
}
