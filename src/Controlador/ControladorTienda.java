package Controlador;

import Modelo.Productos;
import Vista.Login;
import Vista.MostrarCarrito;
import Vista.VistaAdministrador;
import Vista.VistaTienda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ControladorTienda extends JFrame implements ActionListener {

    VistaTienda vistaTienda;
    public ControladorTienda(VistaTienda vistaTienda){
        this.vistaTienda =vistaTienda;
        vistaTienda.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = vistaTienda.jTable.getSelectedRow();
                if (filaSeleccionada != -1) {
                   // cargarDatosSeleccionados(filaSeleccionada);
                }
            }
        });

        mostrar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object objeto = e.getSource();
        if (vistaTienda.boton1==objeto){
            boton1();
        }
        if (vistaTienda.boton4==objeto){
            mostarCarrito();
        }
        if (vistaTienda.boton3==objeto){
            login();
        }
        if (vistaTienda.ordenar==objeto){
            ordenarPorCategoria();
        }

    }
public void mostrar(){
    limpiarTabla();
    String [] fila = new String[vistaTienda.modelo.getColumnCount()];
    vistaTienda.modelo.getRowCount();
    for (Productos userTable : Array.productos) {
        fila[0] = userTable.getNombre();
        fila[1] = userTable.getCodigo();
        fila[2] = userTable.getPrecio();
        fila[3] = userTable.getCantidad();
        fila[4] = userTable.getCategoria();

        vistaTienda.modelo.addRow(fila);
    }

}
    public void boton1() {
        vistaTienda.boton1.addActionListener(e -> {
            int filaSeleccionada = vistaTienda.jTable.getSelectedRow();
            if (filaSeleccionada != -1) {
                String[] filaSeleccionadaArray = new String[vistaTienda.modelo.getColumnCount()];
                for (int i = 0; i < vistaTienda.modelo.getColumnCount(); i++) {
                    filaSeleccionadaArray[i] = (String) vistaTienda.modelo.getValueAt(filaSeleccionada, i);
                }

                String cantidadTexto = JOptionPane.showInputDialog(null, "Ingrese la cantidad a llevar:");
                try {
                    int cantidad = Integer.parseInt(cantidadTexto);

                    // Obtener el producto correspondiente a la fila seleccionada
                    Productos productoSeleccionado = Array.productos.get(filaSeleccionada);

                    // Verificar si hay suficiente cantidad disponible
                    int cantidadDisponible = Integer.parseInt(productoSeleccionado.getCantidad());
                    if (cantidad <= cantidadDisponible) {
                        // Restar la cantidad seleccionada del producto
                        cantidadDisponible -= cantidad;
                        productoSeleccionado.setCantidad(String.valueOf(cantidadDisponible));

                        // Actualizar la tabla con la nueva cantidad
                        vistaTienda.modelo.setValueAt(String.valueOf(cantidadDisponible), filaSeleccionada, 3);

                        // Ahora puedes guardar esta cantidad junto con los datos en el ArrayList
                        String datosCantidad = String.join(" ", filaSeleccionadaArray) + " Cantidad: " + cantidad;
                        Array.carrito.add(datosCantidad);
                        System.out.println("Datos seleccionados: " + Array.carrito);
                    } else {
                        JOptionPane.showMessageDialog(null, "No hay suficiente cantidad disponible", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione una fila para guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
   public void login() {
       vistaTienda.dispose();
       Login login = new Login();
       login.setVisible(true);
   }
    public void mostarCarrito() {
        vistaTienda.dispose();
        MostrarCarrito mostrarCarrito = new MostrarCarrito();
        mostrarCarrito.setVisible(true);
    }
    public void limpiarTabla() {
        for (int i = 0; i < vistaTienda.jTable.getRowCount(); i++) {
            vistaTienda.modelo.removeRow(i);
            i = i - 1;
        }
    }
    public void ordenarPorCategoria() {
        Collections.sort(Array.productos, new ComparadorPorCategoria());
        mostrar(); // Actualiza la tabla con la información ordenada por categoría
    }

    static class ComparadorPorCategoria implements Comparator<Productos> {
        @Override
        public int compare(Productos p1, Productos p2) {
            return p1.getCategoria().compareTo(p2.getCategoria());
        }
    }

}
