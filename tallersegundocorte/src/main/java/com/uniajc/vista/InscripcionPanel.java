package com.uniajc.vista;

import com.uniajc.controlador.InscripcionController;
import com.uniajc.modelo.InscripcionCurso;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InscripcionPanel extends JPanel {
    
    private JTextField txtId, txtIdEstudiante, txtIdGrupo, txtNota, txtEstado;
    private JButton btnGuardar, btnListar;
    private JTextArea txtAreaLista;
    private InscripcionController controlador;

    public InscripcionPanel() {
        controlador = new InscripcionController();
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- PANEL CENTRAL CONTENEDOR ---
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // --- FORMULARIO (5 campos) ---
        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 10, 12));
        panelCampos.setBackground(Color.WHITE);

        Font fontLabels = new Font("Arial", Font.PLAIN, 14);

        panelCampos.add(crearLabel("ID Inscripción:", fontLabels));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(crearLabel("ID Estudiante:", fontLabels));
        txtIdEstudiante = new JTextField();
        panelCampos.add(txtIdEstudiante);

        panelCampos.add(crearLabel("ID Grupo:", fontLabels));
        txtIdGrupo = new JTextField();
        panelCampos.add(txtIdGrupo);

        panelCampos.add(crearLabel("Nota Final:", fontLabels));
        txtNota = new JTextField();
        panelCampos.add(txtNota);

        panelCampos.add(crearLabel("Estado (Activo/Inactivo):", fontLabels));
        txtEstado = new JTextField();
        panelCampos.add(txtEstado);

        panelCentro.add(panelCampos);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- BOTONES ---
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 0));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar Inscripción");
        btnGuardar.setBackground(new Color(0, 51, 102));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        btnListar = new JButton("Listar Inscripciones");
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
        
        JLabel lblLista = new JLabel("Inscripciones registradas:");
        lblLista.setFont(new Font("Arial", Font.BOLD, 12));
        lblLista.setForeground(Color.GRAY);
        panelResultado.add(lblLista, BorderLayout.NORTH);
        
        txtAreaLista = new JTextArea(6, 20);
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
                    int idEstudiante = Integer.parseInt(txtIdEstudiante.getText());
                    int idGrupo = Integer.parseInt(txtIdGrupo.getText());
                    float nota = Float.parseFloat(txtNota.getText());
                    String estado = txtEstado.getText();

                    if (estado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor llena todos los campos.");
                        return;
                    }

                    controlador.crearDesdeGrafico(id, idEstudiante, idGrupo, nota, estado);
                    JOptionPane.showMessageDialog(null, "¡Inscripción guardada con éxito!");
                    
                    txtId.setText("");
                    txtIdEstudiante.setText("");
                    txtIdGrupo.setText("");
                    txtNota.setText("");
                    txtEstado.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Verifique los campos numéricos (ID, Estudiante, Grupo deben ser enteros; Nota debe ser decimal).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLista.setText(""); 
                List<InscripcionCurso> lista = controlador.listarParaGrafico();
                
                if (lista.isEmpty()) {
                    txtAreaLista.setText("No hay inscripciones registradas.");
                } else {
                    for (InscripcionCurso ins : lista) {
                        // Nota: Si tus atributos son privados en InscripcionCurso, cámbialos por getters (ins.getId(), etc.)
                        txtAreaLista.append("Inscripción ID: " + ins.id + " | Estudiante ID: " + ins.idEstudiante + " | Grupo ID: " + ins.idGrupo + " | Nota: " + ins.nota + " | Estado: " + ins.estado + "\n");
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
