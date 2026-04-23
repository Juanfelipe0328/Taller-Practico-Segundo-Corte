package com.uniajc.controlador;

import com.uniajc.modelo.Grupo;
import com.uniajc.servicio.GrupoService;
import com.uniajc.vista.VistaConsola;

public class GrupoController {

    GrupoService service = new GrupoService();
    VistaConsola vista = new VistaConsola();

    public void crear() {
        service.crear(new Grupo(
            vista.leerInt("ID Grupo"),
            vista.leerInt("ID Materia"),
            vista.leerInt("ID Docente"),
            vista.leer("Aula"),
            vista.leer("Horario")
        ));
    }

    public void listar() {
        vista.mostrar(service.listar());
    }
}