package com.example.organizadordetareas.entidades;

import java.io.Serializable;

public class tareas implements Serializable {

    private String tarea;
    private String fecha;
    private String hora;
    private String estado;

    public tareas(String tarea, String fecha, String hora) {
        this.tarea = tarea;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "1";
    }

    public tareas() {
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}