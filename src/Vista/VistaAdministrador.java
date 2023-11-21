package Vista;

import Controlador.ControladorAdministrador;
import Modelo.Productos;
import Titulos.TituloTabla;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class VistaAdministrador extends JFrame implements ItemListener {


    public JPanel paneliz, panelde, panelButton, panelForm, panelContainer,panelSuperior,bottomPanel;
    public JLabel categoria, codigo, nombre, precio,cantidad;
    public JTextField text1, text2, text3, text4,text5,textoBuscar;
    public JButton boton1, boton2, boton3,regresar,buscar,mostraTodo,inventario;
    public JComboBox<String> jComboBox;
    private static final ArrayList<String> categorias = TituloTabla.categorias();

    //Interfaz de Tabla
    public JTable jTable;
    public DefaultTableModel modelo;

    public VistaAdministrador() {
        initializeComponents();
        setupFrame();
        setupPanelContainer();
        llenarCombo();
        addActionListeners();
        add(panelContainer);
        jComboBox.addItemListener(this);

    }

    private void initializeComponents() {
        paneliz = new JPanel();
        panelde = new JPanel();
        panelButton = new JPanel();
        panelForm = new JPanel();
        panelContainer = new JPanel();
        bottomPanel = new JPanel();
        categoria = new JLabel("Categoria");
        codigo = new JLabel("Codigo");
        nombre = new JLabel("Nombre");
        precio = new JLabel("Precio");
        cantidad =new JLabel("Cantidad");
        text1 = new JTextField();
        text2 = new JTextField();
        text3 = new JTextField();
        text4 = new JTextField();
        text5 = new JTextField();
        textoBuscar = new JTextField();
        boton1 = new JButton("Agregar");
        boton2 = new JButton("Eliminar");
        boton3 = new JButton("Actualizar");
        regresar = new JButton("Regresar");
        buscar = new JButton("Buscar");
        mostraTodo = new JButton("Mostrar todo");
        inventario = new JButton("Inventario");

        jComboBox =new JComboBox<>();

        modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(TituloTabla.misTitulos1);
        jTable = new JTable(modelo);
    }
    public void llenarCombo(){
        for (String s : categorias) {
            jComboBox.addItem(s);
        }
    }

    private void setupFrame() {
        setTitle("Administrador");
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


        paneliz.setLayout(new GridLayout(5, 0));
        paneliz.add(nombre);
        paneliz.add(codigo);
        paneliz.add(precio);
        paneliz.add(cantidad);
        paneliz.add(categoria);

        panelde.setLayout(new GridLayout(5, 0));
        panelde.add(text1);
        panelde.add(text2);
        panelde.add(text3);
        panelde.add(text4);
        panelde.add(jComboBox);


        panelButton.setLayout(new FlowLayout());
        panelButton.add(boton1);
        panelButton.add(boton2);
        panelButton.add(boton3);
        panelButton.add(regresar);
        panelButton.add(inventario);

        panelForm.setLayout(new GridLayout(1, 1));
        panelForm.setPreferredSize(new Dimension(460, 115));
        panelForm.add(paneliz);
        panelForm.add(panelde);

        panelSuperior = new JPanel(new GridLayout(1,0));
        panelSuperior.setPreferredSize(new Dimension(400,20));
        panelSuperior.add(textoBuscar);
        panelSuperior.add(buscar);
        panelSuperior.add(mostraTodo);

        panelContainer.setLayout(new BorderLayout());
        panelContainer.add(panelSuperior, BorderLayout.NORTH);

        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(scrollPane, BorderLayout.NORTH);
        bottomPanel.add(panelForm, BorderLayout.CENTER);
        bottomPanel.add(panelButton, BorderLayout.SOUTH);

        panelContainer.add(bottomPanel, BorderLayout.CENTER);

        add(panelContainer);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == jComboBox){
            String item2 = (String) jComboBox.getSelectedItem();
            text5.setText(item2);
        }
    }
    private void addActionListeners() {
        ControladorAdministrador controlador = new ControladorAdministrador(this);
        boton1.addActionListener(controlador);
        boton2.addActionListener(controlador);
        boton3.addActionListener(controlador);
        regresar.addActionListener(controlador);
        buscar.addActionListener(controlador);
        mostraTodo.addActionListener(controlador);
    }
}
