package graficos;

import java.awt.Graphics;
import java.awt.Color;

/**
 * Gestor de figuras para el juego del ahorcado.
 * Contiene la interfaz Figura y la implementación de EstructuraAhorcado
 * con funcionalidades de redimensionamiento.
 */
public class GestorFiguras {
    
    /**
     * Interfaz para objetos que pueden ser dibujados en el panel.
     */
    public interface Figura {
        void dibujar(Graphics g);
    }
    
    /**
     * Clase que representa la estructura del ahorcado con capacidades de redimensionamiento.
     */
    public static class EstructuraAhorcado implements Figura {
        private int errores = 0;
        private double escalaX = 1.0;
        private double escalaY = 1.0;
        private static final int ANCHO_BASE = 400;
        private static final int ALTO_BASE = 400;

        @Override
        public void dibujar(Graphics g) {
            g.setColor(Color.BLACK);

            // Base de la horca
            g.fillRect(escalar(50), escalar(330), escalar(100), escalar(12));

            // Poste vertical
            g.fillRect(escalar(90), escalar(100), escalar(10), escalar(230));

            // Viga horizontal
            g.fillRect(escalar(90), escalar(100), escalar(70), escalar(10));

            // Soporte diagonal
            g.drawLine(escalar(90), escalar(140), escalar(130), escalar(100));

            // Cuerda
            g.fillRect(escalar(155), escalar(110), escalar(3), escalar(20));
            
            if (errores >= 1) dibujarCabeza(g);
            if (errores >= 2) dibujarCuerpo(g);
            if (errores >= 3) dibujarBrazoIzquierdo(g);
            if (errores >= 4) dibujarBrazoDerecho(g);
            if (errores >= 5) dibujarPiernaIzquierda(g);
            if (errores >= 6) dibujarPiernaDerecha(g);
        }

        public void setErrores(int errores) {
            this.errores = errores;
        }

        public int getErrores() {
            return errores;
        }

        public void incrementarErrores() {
            this.errores++;
        }

        public void reset() {
            this.errores = 0;
        }
        
        /**
         * Actualiza la escala de la figura basándose en las dimensiones del panel.
         * @param anchoPanel Ancho actual del panel
         * @param altoPanel Alto actual del panel
         */
        public void actualizarEscala(int anchoPanel, int altoPanel) {
            this.escalaX = (double) anchoPanel / ANCHO_BASE;
            this.escalaY = (double) altoPanel / ALTO_BASE;
        }
        
        /**
         * Escala un valor basándose en la escala actual.
         * @param valor Valor original a escalar
         * @return Valor escalado
         */
        private int escalar(int valor) {
            return (int) (valor * Math.min(escalaX, escalaY));
        }

        private void dibujarCabeza(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawOval(escalar(140), escalar(130), escalar(30), escalar(30));
            
            // Ojos
            g.fillOval(escalar(148), escalar(140), escalar(3), escalar(3));
            g.fillOval(escalar(159), escalar(140), escalar(3), escalar(3));
            
            // Boca
            g.drawArc(escalar(148), escalar(148), escalar(14), escalar(8), 0, -180);
        }

        private void dibujarCuerpo(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawLine(escalar(155), escalar(160), escalar(155), escalar(220));
        }

        private void dibujarBrazoIzquierdo(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawLine(escalar(155), escalar(180), escalar(130), escalar(200));
            
            // Mano
            g.fillOval(escalar(127), escalar(197), escalar(6), escalar(6));
        }

        private void dibujarBrazoDerecho(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawLine(escalar(155), escalar(180), escalar(180), escalar(200));
            
            // Mano
            g.fillOval(escalar(177), escalar(197), escalar(6), escalar(6));
        }

        private void dibujarPiernaIzquierda(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawLine(escalar(155), escalar(220), escalar(135), escalar(250));
            
            // Pie
            g.drawLine(escalar(135), escalar(250), escalar(125), escalar(250));
        }

        private void dibujarPiernaDerecha(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawLine(escalar(155), escalar(220), escalar(175), escalar(250));
            
            // Pie
            g.drawLine(escalar(175), escalar(250), escalar(185), escalar(250));
        }
    }
}