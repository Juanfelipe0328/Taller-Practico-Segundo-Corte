package com.uniajc.servicio;

import com.uniajc.dao.EstudianteDAO;
import com.uniajc.modelo.Estudiante;
import java.util.*;

public class EstudianteService {
    EstudianteDAO dao = new EstudianteDAO();

    public void crear(Estudiante e) {
        dao.guardar(e);
    }

    public List<Estudiante> listar() {
        return dao.listar();
    }
}