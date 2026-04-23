package com.uniajc.servicio;

import com.uniajc.dao.InscripcionCursoDAO;
import com.uniajc.modelo.InscripcionCurso;
import java.util.*;

public class InscripcionCursoService {
    InscripcionCursoDAO dao = new InscripcionCursoDAO();

    public void crear(InscripcionCurso i) {
        dao.guardar(i);
    }

    public List<InscripcionCurso> listar() {
        return dao.listar();
    }
}