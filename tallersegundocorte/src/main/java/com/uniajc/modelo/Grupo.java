package com.uniajc.modelo;

public class Grupo {
    public int id;
    public int idMateria;
    public int idDocente;
    public String aula;
    public String horario;

    public Grupo(int id, int m, int d, String a, String h) {
        this.id = id;
        idMateria = m;
        idDocente = d;
        aula = a;
        horario = h;
    }
}