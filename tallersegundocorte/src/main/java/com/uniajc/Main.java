package com.uniajc;

import com.uniajc.controlador.EstudianteController;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EstudianteController ec = new EstudianteController();
        Scanner sc = new Scanner(System.in);
        int op;

        do {
            System.out.println("""
            1. Crear estudiante
            2. Listar estudiantes
            0. Salir
            """);

            op = sc.nextInt(); sc.nextLine();

            if (op == 1) ec.crear();
            if (op == 2) ec.listar();

        } while (op != 0);
    }
}