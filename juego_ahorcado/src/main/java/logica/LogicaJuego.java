package logica;

import graficos.PanelPizarra;
// Clases consolidadas ahora están en JFPizarra
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogicaJuego {
    
    private String palabraSeleccionada;
    private String[] res;
    private int error1;
    private int error2;
    private List<Character> letrasIngresadas;
    private List<Character> letrasFallidas;
    private Object palabras;
    private Object estructuraAhorcado;
    private JPanel panPizarra;
    private JTextField txtPalabra;
    private JTextField txtAyuda;
    
    public LogicaJuego(Object estructuraAhorcado, JPanel panPizarra, 
                       JTextField txtPalabra, JTextField txtAyuda) {
        this.estructuraAhorcado = estructuraAhorcado;
        this.panPizarra = panPizarra;
        this.txtPalabra = txtPalabra;
        this.txtAyuda = txtAyuda;
        this.palabras = null; // Se inicializará desde JFPizarra
        this.letrasIngresadas = new ArrayList<>();
        this.letrasFallidas = new ArrayList<>();
    }
    
    public void setPalabras(Object palabras) {
        this.palabras = palabras;
    }
    
    public void iniciarJuego() {
        if (palabras != null) {
            try {
                java.lang.reflect.Method obtenerPalabraAleatoriaMethod = palabras.getClass().getMethod("obtenerPalabraAleatoria");
                palabraSeleccionada = (String) obtenerPalabraAleatoriaMethod.invoke(palabras);
            } catch (Exception e) {
                palabraSeleccionada = "ERROR";
            }
        } else {
            palabraSeleccionada = "ERROR";
        }
        error1 = 0;
        error2 = 6;
        
        ((PanelPizarra) panPizarra).getFiguras().clear();
        
        if (estructuraAhorcado != null) {
            try {
                java.lang.reflect.Method resetMethod = estructuraAhorcado.getClass().getMethod("reset");
                resetMethod.invoke(estructuraAhorcado);
            } catch (Exception e) {
                // Ignorar si el método no existe
            }
        }
        ((PanelPizarra) panPizarra).getFiguras().add(estructuraAhorcado);
        
        panPizarra.repaint();
        txtPalabra.setText("");
        
        String palabraActual = palabraSeleccionada.toUpperCase();
        String[] palabrasSeparadas = palabraActual.split(" ");
        int totalLetras = 0;
        for (String palabra : palabrasSeparadas) {
            totalLetras += palabra.length();
        }
        
        int tamanoTotal = totalLetras + (palabrasSeparadas.length - 1);
        res = new String[tamanoTotal];
        
        int j = 0;
        for (int k = 0; k < palabrasSeparadas.length; k++) {
            String palabra = palabrasSeparadas[k];
            for (int i = 0; i < palabra.length(); i++) {
                res[j++] = "_";
            }
            if (k < palabrasSeparadas.length - 1) {
                res[j++] = " ";
            }
        }
        
        actualizarVisualizacionPalabra();
        letrasFallidas.clear();
        letrasIngresadas.clear();
        
        if (palabras != null) {
            try {
                java.lang.reflect.Method obtenerPistaMethod = palabras.getClass().getMethod("obtenerPista", String.class);
                String pista = (String) obtenerPistaMethod.invoke(palabras, palabraSeleccionada);
                txtAyuda.setText(pista);
            } catch (Exception e) {
                txtAyuda.setText("No hay pista disponible.");
            }
        } else {
            txtAyuda.setText("No hay pista disponible.");
        }
    }
    
    public boolean procesarLetra(char letra) {
        if (letrasIngresadas.contains(letra)) {
            return false;
        }
        
        letrasIngresadas.add(letra);
        
        if (palabraSeleccionada.toUpperCase().contains(String.valueOf(letra))) {
            actualizarPalabra(letra);
            return true;
        } else {
            letrasFallidas.add(letra);
            error1++;
            actualizarEstructuraAhorcado();
            return false;
        }
    }
    
    private void actualizarPalabra(char letra) {
        String palabraActual = palabraSeleccionada.toUpperCase();
        String[] palabrasSeparadas = palabraActual.split(" ");
        
        int j = 0;
        for (int k = 0; k < palabrasSeparadas.length; k++) {
            String palabra = palabrasSeparadas[k];
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra) {
                    res[j] = String.valueOf(letra);
                }
                j++;
            }
            if (k < palabrasSeparadas.length - 1) {
                j++;
            }
        }
        actualizarVisualizacionPalabra();
    }
    
    private void actualizarVisualizacionPalabra() {
        StringBuilder sb = new StringBuilder();
        for (String s : res) {
            sb.append(s).append(" ");
        }
        txtPalabra.setText(sb.toString().trim());
    }
    
    private void actualizarEstructuraAhorcado() {
        if (estructuraAhorcado != null) {
            try {
                java.lang.reflect.Method setErroresMethod = estructuraAhorcado.getClass().getMethod("setErrores", int.class);
                setErroresMethod.invoke(estructuraAhorcado, error1);
            } catch (Exception e) {
                // Ignorar si el método no existe
            }
        }
        panPizarra.repaint();
    }
    
    public boolean juegoGanado() {
        for (String s : res) {
            if ("_".equals(s)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean juegoPerdido() {
        return error1 >= 6;
    }
    
    public String getPalabraSeleccionada() {
        return palabraSeleccionada;
    }
    
    public int getErrores() {
        return error1;
    }
    
    public List<Character> getLetrasFallidas() {
        return new ArrayList<>(letrasFallidas);
    }
    
    public String obtenerPista() {
        if (palabraSeleccionada != null && palabras != null) {
            try {
                java.lang.reflect.Method obtenerPistaMethod = palabras.getClass().getMethod("obtenerPista", String.class);
                return (String) obtenerPistaMethod.invoke(palabras, palabraSeleccionada);
            } catch (Exception e) {
                return "No hay pista disponible.";
            }
        }
        return "No hay pista disponible";
    }
}