package Titulos;

import java.util.ArrayList;

public class TituloTabla {
    public static final String [] misTitulos1={"Nombre","Codigo","Precio","Cantidad","Categoria"};

    public static ArrayList<String> categorias(){
        ArrayList<String> listaEstado_civil=new ArrayList<>();
        listaEstado_civil.add("Salud y belleza");
        listaEstado_civil.add("Electrodomesticos");
        listaEstado_civil.add("Abarrotes");
        listaEstado_civil.add("Bebidas alcoholicas");
        return listaEstado_civil;
    }
}
