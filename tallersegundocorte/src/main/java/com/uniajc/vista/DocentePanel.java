package com.uniajc.vista;

import com.uniajc.controlador.DocenteController;
import com.uniajc.modelo.Docente;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DocentePanel extends JPanel {
    
    private JTextField txtId, txtNombre, txtEspecialidad;
    private JButton btnGuardar, btnListar;
    private JTextArea txtAreaLista;
    private DocenteController controlador;

    public DocentePanel() {
        controlador = new DocenteController();
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- PANEL CENTRAL CONTENEDOR ---
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // --- FORMULARIO (3 filas ahora: ID, Nombre, Especialidad) ---
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 15));
        panelCampos.setBackground(Color.WHITE);

        Font fontLabels = new Font("Arial", Font.PLAIN, 14);

        panelCampos.add(crearLabel("ID / Cédula Docente:", fontLabels));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(crearLabel("Nombre Completo:", fontLabels));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(crearLabel("Especialidad:", fontLabels));
        txtEspecialidad = new JTextField();
        panelCampos.add(txtEspecialidad);

        panelCentro.add(panelCampos);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- BOTONES ---
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 0));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar Docente");
        btnGuardar.setBackground(new Color(0, 51, 102));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        btnListar = new JButton("Listar Docentes");
        btnListar.setBackground(Color.WHITE);
        btnListar.setForeground(new Color(0, 51, 102));
        btnListar.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102), 1));
        btnListar.setFocusPainted(false);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnListar);
        
        panelCentro.add(panelBotones);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- PANEL DE RESULTADOS ---
        JPanel panelResultado = new JPanel(new BorderLayout(0, 5));
        panelResultado.setBackground(Color.WHITE);
        
        JLabel lblLista = new JLabel("Docentes registrados:");
        lblLista.setFont(new Font("Arial", Font.BOLD, 12));
        lblLista.setForeground(Color.GRAY);
        panelResultado.add(lblLista, BorderLayout.NORTH);
        
        txtAreaLista = new JTextArea(8, 20);
        txtAreaLista.setEditable(false);
        txtAreaLista.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(txtAreaLista);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        
        panelResultado.add(scrollPane, BorderLayout.CENTER);
        panelCentro.add(panelResultado);

        add(panelCentro, BorderLayout.CENTER);

        // --- EVENTOS DE LOS BOTONES ---
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nombre = txtNombre.getText();
                    String especialidad = txtEspecialidad.getText();

                    if (nombre.isEmpty() || especialidad.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor llena todos los campos.");
                        return;
                    }

                    // Llama al nuevo método que pusimos en tu controlador
                    controlador.crearDesdeGrafico(id, nombre, especialidad);
                    JOptionPane.showMessageDialog(null, "¡Docente guardado con éxito!");
                    
                    txtId.setText("");
                    txtNombre.setText("");
                    txtEspecialidad.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLista.setText(""); 
                List<Docente> lista = controlador.listarParaGrafico();
                
                if (lista.isEmpty()) {
                    txtAreaLista.setText("No hay docentes registrados.");
                } else {
                    for (Docente doc : lista) {
                        // Nota: Si tus atributos en la clase Docente son privados, cambia esto por doc.getId(), doc.getNombre(), etc.
                        txtAreaLista.append(doc.id + " - " + doc.nombre + " (" + doc.especialidad + ")\n");
                    }
                }
            }
        });
    }

    private JLabel crearLabel(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(new Color(50, 50, 50));
        return label;
    }
}
