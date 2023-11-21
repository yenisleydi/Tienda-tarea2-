package Controlador;

import Modelo.Productos;
import Vista.MostrarCarrito;
import Vista.VistaTienda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ControladorCarrito implements ActionListener {

    MostrarCarrito mostrarCarrito;
    public ControladorCarrito(MostrarCarrito mostrarCarrito){
        this.mostrarCarrito = mostrarCarrito;
        mostrarCarrito.jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = mostrarCarrito.jTable.getSelectedRow();
                if (filaSeleccionada != -1) {
                    // cargarDatosSeleccionados(filaSeleccionada);
                }
            }
        });

       mostrar();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            Object objeto=e.getSource();
            if (mostrarCarrito.boton3==objeto){
                regresar();
            }
    }

    public void mostrar() {
        ArrayList<String> carritoList = Array.carrito;

        for (String item : carritoList) {
            String[] datos = item.split(" "); // Dividir el String por el delimitador

            // Asegurarse de que haya suficientes columnas en la tabla
            int numColumnas = Math.min(datos.length, mostrarCarrito.modelo.getColumnCount());

            // Crear una fila para la tabla
            String[] fila = new String[numColumnas];

            // Agregar cada dato a una columna en la fila
            for (int i = 0; i < numColumnas; i++) {
                fila[i] = datos[i];
            }

            // Agregar la fila a la tabla
            mostrarCarrito.modelo.addRow(fila);
        }
    }
    public void regresar() {
        mostrarCarrito.dispose();
        VistaTienda vistaTienda = new VistaTienda();
        vistaTienda.setVisible(true);
    }
}
