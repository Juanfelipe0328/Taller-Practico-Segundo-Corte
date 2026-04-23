package com.uniajc.modelo;

public class InscripcionCurso {
    public int id;
    public int idEstudiante;
    public int idGrupo;
    public float nota;
    public String estado;

    public InscripcionCurso(int id, int e, int g, float n, String es) {
        this.id = id;
        idEstudiante = e;
        idGrupo = g;
        nota = n;
        estado = es;
    }
}