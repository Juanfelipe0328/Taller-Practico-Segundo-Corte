package com.uniajc.vista;

import com.uniajc.controlador.GrupoController;
import com.uniajc.modelo.Grupo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GrupoPanel extends JPanel {
    
    private JTextField txtId, txtIdMateria, txtIdDocente, txtAula, txtHorario;
    private JButton btnGuardar, btnListar;
    private JTextArea txtAreaLista;
    private GrupoController controlador;

    public GrupoPanel() {
        controlador = new GrupoController();
        
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- PANEL CENTRAL CONTENEDOR ---
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // --- FORMULARIO (5 campos según tu modelo Grupo) ---
        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 10, 12));
        panelCampos.setBackground(Color.WHITE);

        Font fontLabels = new Font("Arial", Font.PLAIN, 14);

        panelCampos.add(crearLabel("ID Grupo:", fontLabels));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(crearLabel("ID Materia:", fontLabels));
        txtIdMateria = new JTextField();
        panelCampos.add(txtIdMateria);

        panelCampos.add(crearLabel("ID Docente:", fontLabels));
        txtIdDocente = new JTextField();
        panelCampos.add(txtIdDocente);

        panelCampos.add(crearLabel("Aula:", fontLabels));
        txtAula = new JTextField();
        panelCampos.add(txtAula);

        panelCampos.add(crearLabel("Horario:", fontLabels));
        txtHorario = new JTextField();
        panelCampos.add(txtHorario);

        panelCentro.add(panelCampos);
        panelCentro.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- BOTONES ---
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 15, 0));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar Grupo");
        btnGuardar.setBackground(new Color(0, 51, 102));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);

        btnListar = new JButton("Listar Grupos");
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
        
        JLabel lblLista = new JLabel("Grupos registrados:");
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
                    int idMateria = Integer.parseInt(txtIdMateria.getText());
                    int idDocente = Integer.parseInt(txtIdDocente.getText());
                    String aula = txtAula.getText();
                    String horario = txtHorario.getText();

                    if (aula.isEmpty() || horario.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor llena todos los campos.");
                        return;
                    }

                    controlador.crearDesdeGrafico(id, idMateria, idDocente, aula, horario);
                    JOptionPane.showMessageDialog(null, "¡Grupo guardado con éxito!");
                    
                    txtId.setText("");
                    txtIdMateria.setText("");
                    txtIdDocente.setText("");
                    txtAula.setText("");
                    txtHorario.setText("");
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Los IDs de Grupo, Materia y Docente deben ser números enteros válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaLista.setText(""); 
                List<Grupo> lista = controlador.listarParaGrafico();
                
                if (lista.isEmpty()) {
                    txtAreaLista.setText("No hay grupos registrados.");
                } else {
                    for (Grupo g : lista) {
                        txtAreaLista.append("Grupo: " + g.id + " | Materia ID: " + g.idMateria + " | Docente ID: " + g.idDocente + " | Aula: " + g.aula + " (" + g.horario + ")\n");
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
