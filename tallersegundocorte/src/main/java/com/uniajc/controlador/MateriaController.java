package com.uniajc.controlador;

import com.uniajc.modelo.Materia;
import com.uniajc.servicio.MateriaService;
import com.uniajc.vista.VistaConsola;

public class MateriaController {

    MateriaService service = new MateriaService();
    VistaConsola vista = new VistaConsola();

    public void crear() {
        service.crear(new Materia(
            vista.leerInt("ID"),
            vista.leer("Nombre"),
            vista.leerInt("Créditos")
        ));
    }

    public void listar() {
        vista.mostrar(service.listar());
    }

    
    public void crearDesdeGrafico(int id, String nombre, int creditos) {
        service.crear(new Materia(id, nombre, creditos));
    }

    public java.util.List<Materia> listarParaGrafico() {
        return service.listar();
    }
}