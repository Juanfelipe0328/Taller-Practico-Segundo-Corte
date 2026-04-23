package com.uniajc.controlador;

import com.uniajc.modelo.InscripcionCurso;
import com.uniajc.servicio.InscripcionCursoService;
import com.uniajc.vista.VistaConsola;

public class InscripcionController {

    InscripcionCursoService service = new InscripcionCursoService();
    VistaConsola vista = new VistaConsola();

    public void crear() {
        service.crear(new InscripcionCurso(
            vista.leerInt("ID"),
            vista.leerInt("ID Estudiante"),
            vista.leerInt("ID Grupo"),
            vista.leerFloat("Nota"),
            vista.leer("Estado")
        ));
    }

    public void listar() {
        vista.mostrar(service.listar());
    }
}
