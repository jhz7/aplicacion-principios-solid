package com.ceiba.solid.dominio.modelo;

import com.ceiba.solid.dominio.modelo.Empleado;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.fail;

class EmpleadoTest {

    @Test
    void empleadoSinCargoDebeGenerarError() {

        try {
            Empleado empleado = new Empleado(1L, "Jhon", "Zambrano", null, new Date());
            fail();
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Cargo es requerido", e.getMessage());
        }
    }
}
