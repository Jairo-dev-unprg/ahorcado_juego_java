package graficos.inicializacion;

import graficos.figuras.Palabras;
import javax.swing.JTextField;

public class InicializadorPalabras {
    
    public static String inicializar(JTextField txtAyuda) {
        Palabras palabras = new Palabras();
        String palabraSeleccionada = palabras.obtenerPalabraAleatoria();
        txtAyuda.setText(palabras.obtenerPista(palabraSeleccionada));
        return palabraSeleccionada;
    }
}