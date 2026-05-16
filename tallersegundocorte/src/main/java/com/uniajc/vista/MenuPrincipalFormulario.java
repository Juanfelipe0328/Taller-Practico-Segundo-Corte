package com.uniajc.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPrincipalFormulario extends JFrame {

    private JPanel panelContenidoPrincipal;
    private CardLayout navegador;

    // Colores del tema moderno
    private final Color AZUL_OSCURO = new Color(0, 51, 102);
    private final Color AZUL_HOVER = new Color(0, 76, 153);
    private final Color BLANCO = Color.WHITE;
    private final Color GRIS_FONDO = new Color(245, 247, 250);

    public MenuPrincipalFormulario() {
        setTitle("Sistema de Gestión Académica - UNIAJC");
        setSize(850, 650); // Más ancho para el menú lateral
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- 1. BARRA LATERAL IZQUIERDA (MENÚ) ---
        JPanel panelMenuLateral = new JPanel();
        panelMenuLateral.setBackground(AZUL_OSCURO);
        panelMenuLateral.setPreferredSize(new Dimension(230, 650));
        panelMenuLateral.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        // Logo o Texto de Bienvenida en el menú
        JLabel lblLogo = new JLabel("UNIAJC MENÚ");
        lblLogo.setFont(new Font("Arial", Font.BOLD, 18));
        lblLogo.setForeground(BLANCO);
        lblLogo.setBorder(BorderFactory.createEmptyBorder(20, 10, 30, 10));
        panelMenuLateral.add(lblLogo);

        // Crear los botones estilizados para el menú
        JButton btnEstudiantes = crearBotonMenu("🧑‍🎓  Estudiantes");
        JButton btnDocentes = crearBotonMenu("👨‍🏫  Docentes");
        JButton btnMaterias = crearBotonMenu("📚  Materias");
        JButton btnGrupos = crearBotonMenu("👥  Grupos");
        JButton btnInscripciones = crearBotonMenu("📝  Inscripciones");

        panelMenuLateral.add(btnEstudiantes);
        panelMenuLateral.add(btnDocentes);
        panelMenuLateral.add(btnMaterias);
        panelMenuLateral.add(btnGrupos);
        panelMenuLateral.add(btnInscripciones);

        add(panelMenuLateral, BorderLayout.WEST);

        // --- 2. CONTENEDOR CENTRAL DINÁMICO (CardLayout) ---
        navegador = new CardLayout();
        panelContenidoPrincipal = new JPanel(navegador);
        panelContenidoPrincipal.setBackground(GRIS_FONDO);

        // Inyectamos los paneles que ya creaste con un "nombre clave"
        panelContenidoPrincipal.add(new EstudiantePanel(), "Estudiantes");
        panelContenidoPrincipal.add(new DocentePanel(), "Docentes");
        panelContenidoPrincipal.add(new MateriaPanel(), "Materias");
        panelContenidoPrincipal.add(new GrupoPanel(), "Grupos");
        panelContenidoPrincipal.add(new InscripcionPanel(), "Inscripciones");

        // Panel de bienvenida por defecto al abrir el programa
        JPanel panelInicio = new JPanel(new GridBagLayout());
        panelInicio.setBackground(BLANCO);
        JLabel lblBienvenida = new JLabel("Selecciona un módulo en el menú lateral");
        lblBienvenida.setFont(new Font("Arial", Font.ITALIC, 16));
        lblBienvenida.setForeground(Color.GRAY);
        panelInicio.add(lblBienvenida);
        panelContenidoPrincipal.add(panelInicio, "Inicio");

        add(panelContenidoPrincipal, BorderLayout.CENTER);

        // Mostrar la pantalla de inicio al arrancar
        navegador.show(panelContenidoPrincipal, "Inicio");

        // --- 3. ACCIONES DE LOS BOTONES DEL MENÚ ---
        btnEstudiantes.addActionListener(e -> navegador.show(panelContenidoPrincipal, "Estudiantes"));
        btnDocentes.addActionListener(e -> navegador.show(panelContenidoPrincipal, "Docentes"));
        btnMaterias.addActionListener(e -> navegador.show(panelContenidoPrincipal, "Materias"));
        btnGrupos.addActionListener(e -> navegador.show(panelContenidoPrincipal, "Grupos"));
        btnInscripciones.addActionListener(e -> navegador.show(panelContenidoPrincipal, "Inscripciones"));
    }

    // Método auxiliar para construir botones estilizados con efectos visuales
    private JButton crearBotonMenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(210, 45));
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setForeground(BLANCO);
        boton.setBackground(AZUL_OSCURO);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto Hover: Cambia de color cuando el mouse pasa por encima
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(AZUL_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(AZUL_OSCURO);
            }
        });

        return boton;
    }
}