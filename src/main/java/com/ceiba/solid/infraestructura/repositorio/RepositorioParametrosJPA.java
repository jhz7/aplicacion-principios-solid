package com.ceiba.solid.infraestructura.repositorio;

import com.ceiba.solid.dominio.contrato.RepositorioParametros;
import com.ceiba.solid.infraestructura.entidad.ParametroSistemaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class RepositorioParametrosJPA implements RepositorioParametros {

    @Autowired
    private EntityManager entityManager;

    @Override
    public String obtenerParametro(Long id) {
        return entityManager.find(ParametroSistemaEntity.class, id).getValue();
    }
}
