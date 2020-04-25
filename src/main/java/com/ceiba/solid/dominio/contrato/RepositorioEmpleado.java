package com.ceiba.solid.dominio.contrato;

import com.ceiba.solid.dominio.modelo.Empleado;

import java.util.List;

public interface RepositorioEmpleado {

    List<Empleado> consultarTodos();
}
