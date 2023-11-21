package Controlador;

import Modelo.Productos;
import Vista.VistaAdministrador;
import Vista.VistaTienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControladorAdministrador implements ActionListener {
    VistaAdministrador vistaAdministrador;
    public ControladorAdministrador(VistaAdministrador vistaAdministrador){
        this.vistaAdministrador =vistaAdministrador;
        vistaAdministrador.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = vistaAdministrador.jTable.getSelectedRow();
                if (filaSeleccionada != -1) {
                    cargarDatosSeleccionados(filaSeleccionada);
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object objeto = e.getSource();
        if (vistaAdministrador.boton1==objeto){
            agregar();
        }
        if (vistaAdministrador.boton2==objeto){
            eliminar();
        }
        if (vistaAdministrador.boton3==objeto){
            actualizar();
        }
        if (vistaAdministrador.regresar==objeto){
            regresarAVistaTienda();
        }
        if (vistaAdministrador.buscar==objeto){
                buscar();
        }
        if (vistaAdministrador.mostraTodo==objeto){
            mostrar();
        }
    }

    public void agregar() {
        String nombre = vistaAdministrador.text1.getText();
        String codigo = "";
        String precio = "";
        String cantidad = "";
        String categoria = vistaAdministrador.text5.getText();

        boolean codigoValido = false;
        boolean precioValido = false;
        boolean cantidadValida = false;

        while (!codigoValido) {
            codigo = JOptionPane.showInputDialog(null, "Ingrese el código:");
            try {
                int numero = Integer.parseInt(codigo);
                codigoValido = true; // Si se convierte correctamente a número, salir del bucle
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para el código", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (!precioValido) {
            precio = JOptionPane.showInputDialog(null, "Ingrese el precio:");
            try {
                double num = Double.parseDouble(precio);
                precioValido = true; // Si se convierte correctamente a número, salir del bucle
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El precio debe ser un número decimal", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        while (!cantidadValida) {
            cantidad = JOptionPane.showInputDialog(null, "Ingrese la cantidad:");
            try {
                int cantidadNum = Integer.parseInt(cantidad);
                if (cantidadNum >= 0) {
                    cantidadValida = true; // Si se convierte correctamente a número y es no negativo, salir del bucle
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un número positivo para la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido para la cantidad", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Array.productos.add(new Productos(nombre, codigo, precio, cantidad, categoria));
        limpiar();
        mostrar();
    }
    public void eliminar(){
        int filaSeleccionada = vistaAdministrador.jTable.getSelectedRow();
        if (filaSeleccionada != -1) {
            vistaAdministrador.modelo.removeRow(filaSeleccionada);
            Array.productos.remove(filaSeleccionada);

            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void actualizar(){
        int filaSeleccionada = vistaAdministrador.jTable.getSelectedRow();

        if (filaSeleccionada != -1) {
            DefaultTableModel modeloTabla = (DefaultTableModel) vistaAdministrador.jTable.getModel();

            // Obtener los datos de los campos de texto
            String nombre = vistaAdministrador.text1.getText();
            String codigo = vistaAdministrador.text2.getText();
            String precio = vistaAdministrador.text3.getText();
            String cantidad = vistaAdministrador.text4.getText();
            String categoria = vistaAdministrador.text5.getText();

            // Actualizar los datos en la tabla con los datos ingresados en los campos de texto
            modeloTabla.setValueAt(nombre, filaSeleccionada, vistaAdministrador.modelo.findColumn("Nombre"));
            modeloTabla.setValueAt(codigo, filaSeleccionada, vistaAdministrador.modelo.findColumn("Codigo"));
            modeloTabla.setValueAt(precio, filaSeleccionada, vistaAdministrador.modelo.findColumn("Precio"));
            modeloTabla.setValueAt(cantidad, filaSeleccionada, vistaAdministrador.modelo.findColumn("Cantidad"));
            modeloTabla.setValueAt(categoria, filaSeleccionada, vistaAdministrador.modelo.findColumn("Categoria"));

            // Limpia los campos de texto después de la actualización
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione un registro para modificar.");
        }
    }
    private void cargarDatosSeleccionados(int filaSeleccionada) {
        DefaultTableModel modelo = vistaAdministrador.modelo;
        if (filaSeleccionada < modelo.getRowCount()) {
            vistaAdministrador.text1.setText((String) modelo.getValueAt(filaSeleccionada, vistaAdministrador.modelo.findColumn("Nombre")));
            vistaAdministrador.text2.setText((String) modelo.getValueAt(filaSeleccionada, vistaAdministrador.modelo.findColumn("Codigo")));
            vistaAdministrador.text3.setText((String) modelo.getValueAt(filaSeleccionada, vistaAdministrador.modelo.findColumn("Precio")));
            vistaAdministrador.text4.setText((String) modelo.getValueAt(filaSeleccionada, vistaAdministrador.modelo.findColumn("Cantidad")));
            vistaAdministrador.text5.setText((String) modelo.getValueAt(filaSeleccionada, vistaAdministrador.modelo.findColumn("Categoria")));

        }
    }
    public void buscar(){
        System.out.println("ii");
        String textoBusqueda = vistaAdministrador.textoBuscar.getText();

        limpiarTabla();
        System.out.println("ll");
        for (Productos producto :Array.productos) {
            System.out.println("ww");
            if (producto.getNombre().equals(textoBusqueda)) {
                System.out.println("mmm");
                String[] fila = new String[]{producto.getNombre(), producto.getCodigo(), producto.getPrecio(), producto.getCantidad(), producto.getCategoria()};
                vistaAdministrador.modelo.addRow(fila);
            }
        }
    }

    public void limpiar(){
        vistaAdministrador.text1.setText("");
        vistaAdministrador.text2.setText("");
        vistaAdministrador.text3.setText("");
        vistaAdministrador.text4.setText("");
        vistaAdministrador.text5.setText("");
    }
    public void limpiarTabla() {
        for (int i = 0; i < vistaAdministrador.jTable.getRowCount(); i++) {
            vistaAdministrador.modelo.removeRow(i);
            i = i - 1;
        }
    }
    public void regresarAVistaTienda() {
        vistaAdministrador.dispose();
        VistaTienda vistaTienda = new VistaTienda();
        vistaTienda.setVisible(true);
    }
    public void mostrar(){
        limpiarTabla();
        String [] fila = new String[vistaAdministrador.modelo.getColumnCount()];
        vistaAdministrador.modelo.getRowCount();
        for (Productos userTable : Array.productos) {
            fila[0] = userTable.getNombre();
            fila[1] = userTable.getCodigo();
            fila[2] = userTable.getPrecio();
            fila[3] = userTable.getCantidad();
            fila[4] = userTable.getCategoria();

            vistaAdministrador.modelo.addRow(fila);
        }
    }
    }
