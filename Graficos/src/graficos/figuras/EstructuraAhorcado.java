package graficos.figuras;


import java.awt.Color;
import java.awt.Graphics;

public class EstructuraAhorcado implements Figura {
    private int errores = 0;

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.BLACK);

   
        // Dibujar partes del cuerpo según errores
        if (errores >= 1) dibujarCabeza(g);
        if (errores >= 2) dibujarCuerpo(g);
        if (errores >= 3) dibujarBrazoIzquierdo(g);
        if (errores >= 4) dibujarBrazoDerecho(g);
        if (errores >= 5) dibujarPiernaIzquierda(g);
        if (errores >= 6) dibujarPiernaDerecha(g);
    }

    private void dibujarCabeza(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(140, 130, 40, 40); // Cabeza
    }

    private void dibujarCuerpo(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(160, 170, 160, 230); // Cuerpo
    }

    private void dibujarBrazoIzquierdo(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(160, 180, 130, 200); // Brazo izquierdo
    }

    private void dibujarBrazoDerecho(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(160, 180, 190, 200); // Brazo derecho
    }

    private void dibujarPiernaIzquierda(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(160, 230, 135, 260); // Pierna izquierda
    }

    private void dibujarPiernaDerecha(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(160, 230, 185, 260); // Pierna derecha
    }
    
    
}
    

