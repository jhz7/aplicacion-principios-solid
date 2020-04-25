package com.ceiba.solid.infraestructura.repositorio;

import com.ceiba.solid.dominio.contrato.RepositorioEmpleado;
import com.ceiba.solid.infraestructura.entidad.EmpleadoEntity;
import com.ceiba.solid.dominio.modelo.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositorioEmpleadoJPA implements RepositorioEmpleado {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Empleado> consultarTodos() {
        List<EmpleadoEntity> empleadosEntity =
                entityManager.createQuery("SELECT e FROM EMPLEADO e", EmpleadoEntity.class).getResultList();

        return empleadosEntity.stream().map(this::convertirAEmpleado).collect(Collectors.toList());
    }

    private Empleado convertirAEmpleado(EmpleadoEntity entity) {
        return new Empleado(entity.getId(), entity.getNombre(), entity.getApellido(), entity.getCargo(), entity.getFecha());
    }
}
