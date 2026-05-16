package com.uniajc.vista;

import com.uniajc.controlador.EstudianteController;
import com.uniajc.modelo.Estudiante;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

// Ahora es un JPanel para poder meterlo dentro de las pestañas
public class EstudiantePanel extends JPanel {
    
    private JTextField txtId, txtNombre, txtApellido, txtEmail;
    private JButton btnGuardar, btnListar;
    private JTextArea txtAreaLista;
    private EstudianteController controlador;

    public EstudiantePanel() {
        controlador = new EstudianteController();
        
        // Configuración del panel base
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Fondo blanco institucional

        // --- PANEL CENTRAL CONTENEDOR ---
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // --- FORMULARIO ---
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 15));
        panelCampos.setBackground(Color.WHITE);

        Font fontLabels = new Font("Arial", Font.PLAIN, 14);

        panelCampos.add(crearLabel("ID / Cédula:", fontLabels));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(crearLabel("Nombre:", fontLabels));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        panelCampos.add(crearLabel("Apellido:", fontLabels));
        txtApellido = new JTextField();
        panelCampos.add(txtApellido);

        panelCampos.add(crearLabel("Email:", fontLabels));
        txtEmail = new JTextField();
        panelCampos.add(txtEmail);

        panelCentro.add(panelCampos);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio

        // --- BOTONES ---
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 0));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar Estudiante");
        btnGuardar.setBackground(new Color(0, 51, 102)); // Azul UNIAJC
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        btnListar = new JButton("Listar Estudiantes");
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
        
        JLabel lblLista = new JLabel("Estudiantes registrados:");
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

        // --- EVENTOS (Tu lógica intacta conectada a tu controlador) ---
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String nombre = txtNombre.getText();
                    String apellido = txtApellido.getText();
                    String email = txtEmail.getText();

                    if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor llena todos los campos.");
                        return;
                    }

                    // Aquí se conecta directo a tu controlador actual
                    controlador.crearDesdeGrafico(id, nombre, apellido, email);
                    JOptionPane.showMessageDialog(null, "¡Estudiante guardado con éxito!");
                    
                    txtId.setText("");
                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtEmail.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLista.setText(""); 
                List<Estudiante> lista = controlador.listarParaGrafico();
                
                if (lista.isEmpty()) {
                    txtAreaLista.setText("No hay estudiantes registrados.");
                } else {
                    for (Estudiante est : lista) {
                        txtAreaLista.append(est.id + " - " + est.nombre + " " + est.apellido + " (" + est.email + ")\n");
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