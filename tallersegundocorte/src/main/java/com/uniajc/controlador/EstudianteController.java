package com.uniajc.controlador;

import com.uniajc.modelo.Estudiante;
import com.uniajc.servicio.EstudianteService;
import com.uniajc.vista.VistaConsola;

public class EstudianteController {

    EstudianteService service = new EstudianteService();
    VistaConsola vista = new VistaConsola();

    public void crear() {
        service.crear(new Estudiante(
            vista.leerInt("ID"),
            vista.leer("Nombre"),
            vista.leer("Apellido"),
            vista.leer("Email")
        ));
    }

    public void listar() {
        vista.mostrar(service.listar());
    }
}