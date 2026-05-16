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

    
    public void crearDesdeGrafico(int id, String nombre, String especialidad) {
        // Creamos el docente con los datos que vienen de las cajas de texto
        // Nota: Tu constructor de Docente usa 3 parámetros según tu código (id, nombre, especialidad)
        service.crear(new Docente(id, nombre, especialidad));
    }

    public java.util.List<Docente> listarParaGrafico() {
        // Retorna la lista directa de la base de datos o memoria para la JTextArea
        return service.listar();
    }

}