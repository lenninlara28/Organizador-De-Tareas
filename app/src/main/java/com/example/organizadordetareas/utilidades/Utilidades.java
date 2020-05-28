package com.example.organizadordetareas.utilidades;

public class Utilidades {

    public static final String TABLA_TAREAS = "tareas";
    public static final String CAMPO_TAREA = "tarea";
    public static final String CAMPO_FECHA = "fecha";
    public static final String CAMPO_HORA = "hora";
    public static final String CAMPO_ESTADO = "estado";

    public static final String CREAR_TABLA = "CREATE TABLE "+ TABLA_TAREAS +" ("+ CAMPO_TAREA +" TEXT,"+CAMPO_FECHA+" TEXT,"+CAMPO_HORA+" TEXT,"+CAMPO_ESTADO+" TEXT)";

}
