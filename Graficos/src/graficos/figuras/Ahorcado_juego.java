
package graficos.figuras;

public class Ahorcado_juego {
    

public class JuegoAhorcado {
    private String palabraSecreta;
    private StringBuilder palabraVisible;
    private int errores;
    private final int MAX_ERRORES = 6;

    public JuegoAhorcado(String palabra) {
        this.palabraSecreta = palabra.toUpperCase();
        this.palabraVisible = new StringBuilder("_".repeat(palabra.length()));
        this.errores = 0;
    }

    public boolean adivinarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        boolean acertado = false;
        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                palabraVisible.setCharAt(i, letra);
                acertado = true;
            }
        }
        if (!acertado) errores++;
        return acertado;
    }

    public String getPalabraVisible() {
        return palabraVisible.toString();
    }

    public int getErrores() {
        return errores;
    }

    public boolean juegoTerminado() {
        return errores >= MAX_ERRORES || palabraVisible.toString().equals(palabraSecreta);
    }

    public boolean jugadorGano() {
        return palabraVisible.toString().equals(palabraSecreta);
    }
}
}

