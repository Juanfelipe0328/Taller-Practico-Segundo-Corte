package com.uniajc.modelo;

public class Estudiante {
    public int id;
    public String nombre;
    public String apellido;
    public String email;

    
    public Estudiante(int id, String n, String a, String e) {
        this.id = id;
        nombre = n;
        apellido = a;
        email = e;
    }

    public String toString() {
        return id + " - " + nombre + " " + apellido;
    }
}