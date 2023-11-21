package Vista;

import Controlador.ControladorCarrito;
import Controlador.ControladorTienda;
import Titulos.TituloTabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MostrarCarrito extends JFrame{
    public JPanel paneliz, paneliz2, panelde, panelde2, panelButton, panelForm, panelContainer, panelRadio;
    public JButton boton2,boton3,boton4;

    public JTable jTable;
    public DefaultTableModel modelo;

    public MostrarCarrito() {
        initializeComponents();
        setupFrame();
        setupPanelContainer();
        addActionListeners();
        add(panelContainer);
    }

    private void initializeComponents() {
        panelRadio= new JPanel();
        paneliz = new JPanel();
        paneliz2 = new JPanel();
        panelde = new JPanel();
        panelde2 = new JPanel();
        panelButton = new JPanel();
        panelForm = new JPanel();
        panelContainer = new JPanel();
        boton4 = new JButton("Eliminar del carrito");
        boton2 = new JButton("Comprar");
        boton3 = new JButton("Regresar");
        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(TituloTabla.misTitulos1);
        jTable = new JTable(modelo);
    }

    private void setupFrame() {
        setTitle("TERRESTRES");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setSize(1000, 420);
    }
    private void setupPanelContainer() {

        jTable.setGridColor(new Color(92, 88, 214));
        jTable.setPreferredScrollableViewportSize(new Dimension(950, 130));
        JScrollPane scrollPane = new JScrollPane(jTable);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        panelButton.setLayout(new FlowLayout());
        panelButton.setPreferredSize(new Dimension(550, 50));
        panelButton.add(boton4);
        panelButton.add(boton2);
        panelButton.add(boton3);

        panelContainer.setLayout(new GridLayout(3, 1));
        panelContainer.add(panelForm);
        panelContainer.add(panelButton);

        add(panelContainer);
    }
    private void addActionListeners() {
       ControladorCarrito controlador = new ControladorCarrito(this);
        boton2.addActionListener(controlador);
        boton3.addActionListener(controlador);
        boton4.addActionListener(controlador);
    }
}
