package com.example.organizadordetareas.entidades;

public class tareas {


    private String tarea;
    private String fecha;
    private String hora;

    public tareas(String tarea, String fecha, String hora) {
        this.tarea = tarea;
        this.fecha = fecha;
        this.hora = hora;


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
}