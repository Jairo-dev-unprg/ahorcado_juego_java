package logica;

import graficos.PanelPizarra;
import graficos.figuras.EstructuraAhorcado;
import graficos.figuras.Palabras;
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
    private Palabras palabras;
    private EstructuraAhorcado estructuraAhorcado;
    private JPanel panPizarra;
    private JTextField txtPalabra;
    private JTextField txtAyuda;
    
    public LogicaJuego(EstructuraAhorcado estructuraAhorcado, JPanel panPizarra, 
                       JTextField txtPalabra, JTextField txtAyuda) {
        this.estructuraAhorcado = estructuraAhorcado;
        this.panPizarra = panPizarra;
        this.txtPalabra = txtPalabra;
        this.txtAyuda = txtAyuda;
        this.palabras = new Palabras();
        this.letrasIngresadas = new ArrayList<>();
        this.letrasFallidas = new ArrayList<>();
    }
    
    public void iniciarJuego() {
        palabraSeleccionada = palabras.obtenerPalabraAleatoria();
        error1 = 0;
        error2 = 6;
        
        ((PanelPizarra) panPizarra).getFiguras().clear();
        
        if (estructuraAhorcado == null) {
            estructuraAhorcado = new EstructuraAhorcado();
        }
        estructuraAhorcado.reset();
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
        txtAyuda.setText(palabras.obtenerPista(palabraSeleccionada));
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
        estructuraAhorcado.setErrores(error1);
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
            return palabras.obtenerPista(palabraSeleccionada);
        }
        return "No hay pista disponible";
    }
}