package com.uniajc.vista;

import com.uniajc.controlador.MateriaController;
import com.uniajc.modelo.Materia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MateriaPanel extends JPanel {
    
    private JTextField txtId, txtNombre, txtCreditos;
    private JButton btnGuardar, btnListar;
    private JTextArea txtAreaLista;
    private MateriaController controlador;

    public MateriaPanel() {
        controlador = new MateriaController();
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- PANEL CENTRAL CONTENEDOR ---
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // --- FORMULARIO (3 campos: ID, Nombre, Créditos) ---
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 15));
        panelCampos.setBackground(Color.WHITE);

        Font fontLabels = new Font("Arial", Font.PLAIN, 14);

        panelCampos.add(crearLabel("ID Materia:", fontLabels));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(crearLabel("Nombre de Materia:", fontLabels));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(crearLabel("Número de Créditos:", fontLabels));
        txtCreditos = new JTextField();
        panelCampos.add(txtCreditos);

        panelCentro.add(panelCampos);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- BOTONES ---
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 0));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar Materia");
        btnGuardar.setBackground(new Color(0, 51, 102));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        btnListar = new JButton("Listar Materias");
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
        
        JLabel lblLista = new JLabel("Materias registradas:");
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
                    int creditos = Integer.parseInt(txtCreditos.getText());

                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor llena todos los campos.");
                        return;
                    }

                    controlador.crearDesdeGrafico(id, nombre, creditos);
                    JOptionPane.showMessageDialog(null, "¡Materia guardada con éxito!");
                    
                    txtId.setText("");
                    txtNombre.setText("");
                    txtCreditos.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID y los Créditos deben ser números enteros válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLista.setText(""); 
                List<Materia> lista = controlador.listarParaGrafico();
                
                if (lista.isEmpty()) {
                    txtAreaLista.setText("No hay materias registradas.");
                } else {
                    for (Materia mat : lista) {
                        // Nota: Si tus atributos son privados en Materia, usa getters (mat.getId(), etc.)
                        txtAreaLista.append(mat.id + " - " + mat.nombre + " (" + mat.creditos + " Créditos)\n");
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
