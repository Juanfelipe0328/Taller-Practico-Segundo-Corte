package com.uniajc.vista;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Hilo seguro de Swing para iniciar interfaces gráficas
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crea la ventana principal y la hace visible
                new MenuPrincipalFormulario().setVisible(true);
            }
        });
    }
}