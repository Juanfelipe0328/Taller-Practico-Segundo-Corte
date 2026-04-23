package com.uniajc.controlador;

import com.uniajc.modelo.Docente;
import com.uniajc.servicio.DocenteService;
import com.uniajc.vista.VistaConsola;

public class DocenteController {

    DocenteService service = new DocenteService();
    VistaConsola vista = new VistaConsola();

    public void crear() {
        service.crear(new Docente(
            vista.leerInt("ID"),
            vista.leer("Nombre"),
            vista.leer("Especialidad")
        ));
    }

    public void listar() {
        vista.mostrar(service.listar());
    }
}