package com.uniajc.servicio;

import com.uniajc.dao.DocenteDAO;
import com.uniajc.modelo.Docente;
import java.util.*;

public class DocenteService {
    DocenteDAO dao = new DocenteDAO();

    public void crear(Docente d) {
        dao.guardar(d);
    }

    public List<Docente> listar() {
        return dao.listar();
    }
}
