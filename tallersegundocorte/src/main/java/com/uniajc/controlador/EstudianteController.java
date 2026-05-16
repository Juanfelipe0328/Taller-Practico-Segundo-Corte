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

  public void crearDesdeGrafico(int id, String nombre, String apellido, String email) {
    service.crear(new Estudiante(id, nombre, apellido, email));
   }

  public java.util.List<Estudiante> listarParaGrafico() {
    return service.listar();
  }

}
