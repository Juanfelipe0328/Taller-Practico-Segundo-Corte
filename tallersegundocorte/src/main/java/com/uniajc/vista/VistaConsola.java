package com.uniajc.vista;

import java.util.*;
import java.util.Scanner;

public class VistaConsola {

    Scanner sc = new Scanner(System.in);

    public String leer(String m) {
        System.out.print(m + ": ");
        return sc.nextLine();
    }

    public int leerInt(String m) {
        return Integer.parseInt(leer(m));
    }

    public float leerFloat(String m) {
        return Float.parseFloat(leer(m));
    }

    public void mostrar(List<?> lista) {
        for (Object o : lista) {
            System.out.println(o);
        }
    }
}