
package graficos.figuras;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Palabras {
    
   private List<String> listaPalabras;

    public Palabras() {
        listaPalabras = new ArrayList<>();
        cargarPalabras(); // Llena la lista al iniciar
    }

    private void cargarPalabras() {
        // Aquí puedes añadir las palabras que desees
        listaPalabras.add("PROGRAMACION");
        listaPalabras.add("JAVA");
        listaPalabras.add("AHORCADO");
        listaPalabras.add("COMPUTADORA");
        listaPalabras.add("UNIVERSIDAD");
        listaPalabras.add("SOFTWARE");
        listaPalabras.add("CLASE");
        listaPalabras.add("VARIABLE");
    }

    public String obtenerPalabraAleatoria() {
        Random rand = new Random();
        int indice = rand.nextInt(listaPalabras.size());
        return listaPalabras.get(indice);
    }

    public String obtenerPista(String palabraSeleccionada) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}