package com.uniajc.servicio;

import com.uniajc.dao.GrupoDAO;
import com.uniajc.modelo.Grupo;
import java.util.*;

public class GrupoService {
    GrupoDAO dao = new GrupoDAO();

    public void crear(Grupo g) {
        dao.guardar(g);
    }

    public List<Grupo> listar() {
        return dao.listar();
    }
}
