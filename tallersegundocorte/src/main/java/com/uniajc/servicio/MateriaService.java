package com.uniajc.servicio;

import com.uniajc.dao.MateriaDAO;
import com.uniajc.modelo.Materia;
import java.util.*;

public class MateriaService {
    MateriaDAO dao = new MateriaDAO();

    public void crear(Materia m) {
        dao.guardar(m);
    }

    public List<Materia> listar() {
        return dao.listar();
    }
}
