
package graficos.figuras;

import java.util.HashSet;
import java.util.Set;

public class ControlJuego {
    private String palabra;
    private StringBuilder palabraVisible;
    private int intentosRestantes;
    private Set<Character> letrasIncorrectas;
    
    public ControlJuego(String palabra) {
        this.palabra = palabra.toUpperCase();
        this.intentosRestantes = 6;
        this.palabraVisible = new StringBuilder();
        this.letrasIncorrectas = new HashSet<>();
        for (int i = 0; i < palabra.length(); i++) {
            palabraVisible.append("_");
        }
    }

    public boolean adivinarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        boolean acertado = false;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra) {
                palabraVisible.setCharAt(i, letra);
                acertado = true;
            }
        }
        if (!acertado) {
            letrasIncorrectas.add(letra);
            intentosRestantes--;
        }
        return acertado;
    }

    public String getPalabraVisible() {
        return palabraVisible.toString();
    }

    public boolean juegoTerminado() {
        return intentosRestantes == 0 || !palabraVisible.toString().contains("_");
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }

    public Set<Character> getLetrasIncorrectas() {
        return letrasIncorrectas;
    }

    public boolean palabraAdivinada() {
        return !palabraVisible.toString().contains("_");
    }

    public String getPalabraCompleta() {
        return palabra;
    }
}
