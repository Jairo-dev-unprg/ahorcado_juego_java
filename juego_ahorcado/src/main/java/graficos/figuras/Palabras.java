
package graficos.figuras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import persistencia.GestorPalabrasXML;
import modelo.Palabra;


public class Palabras {

   private List<String> listaPalabras;
    private Map<String, String> pistas;
    private GestorPalabrasXML gestorPalabras;

    public Palabras() {
        listaPalabras = new ArrayList<>();
        pistas = new HashMap<>();
        gestorPalabras = new GestorPalabrasXML();
        cargarPalabras();
    }

    private void cargarPalabras() {
        List<Palabra> palabrasXML = gestorPalabras.cargarPalabras();
        for (Palabra palabra : palabrasXML) {
            listaPalabras.add(palabra.getPalabra().toUpperCase());
            pistas.put(palabra.getPalabra().toUpperCase(), palabra.getPista());
        }
    }

    public String obtenerPalabraAleatoria() {
        Random rand = new Random();
        int indice = rand.nextInt(listaPalabras.size());
        return listaPalabras.get(indice);
    }

   public String obtenerPista(String palabraSeleccionada) {
        String pista = pistas.get(palabraSeleccionada);
        if (pista != null) {
            return pista;
        } else {
            return "No hay pista disponible para esta palabra.";
        }
    }
}