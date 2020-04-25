package com.ceiba.solid.dominio.modelo;

import com.ceiba.solid.dominio.servicio.ServicioFecha;

import java.util.Date;

public class Empleado {

    private Long id;
    private String nombre;
    private String apellido;
    private String cargo;
    private Date fecha;

    public Empleado(Long id, String nombre, String apellido, String cargo, Date fecha) {

        if(cargo == null) throw new IllegalArgumentException("Cargo es requerido");

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public Date getFecha() {
        return fecha;
    }

    public int aniosEnLaCompania() {
        return ServicioFecha.calcularDiferencia(fecha, ServicioFecha.obtener().getTime());
    }
}
