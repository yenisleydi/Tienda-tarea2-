package Vista;

import Controlador.ControladorTienda;
import Titulos.TituloTabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaTienda extends JFrame{
    public JPanel paneliz, paneliz2, panelde, panelde2, panelButton, panelForm, panelContainer, panelRadio;
    public JButton boton1,boton3,boton4,ordenar;

    public JTable jTable;
    public DefaultTableModel modelo;

    public VistaTienda() {
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
        boton1 = new JButton("Agregar al carrito");
        boton4 = new JButton("Mostrar carrito");
        boton3 = new JButton("Admin");
        ordenar = new JButton("Ordenar");
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
        panelButton.add(boton1);
        panelButton.add(boton4);
        panelButton.add(boton3);
        panelButton.add(ordenar);


        panelContainer.setLayout(new GridLayout(3, 1));
        panelContainer.add(panelForm);
        panelContainer.add(panelButton);

        add(panelContainer);
    }
   private void addActionListeners() {
        ControladorTienda controlador = new ControladorTienda(this);
        boton1.addActionListener(controlador);
        boton3.addActionListener(controlador);
        boton4.addActionListener(controlador);
        ordenar.addActionListener(controlador);
   }
}
