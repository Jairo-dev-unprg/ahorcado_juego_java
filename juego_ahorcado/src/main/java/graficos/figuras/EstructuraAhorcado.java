package graficos.figuras;

import java.awt.Color;
import java.awt.Graphics;

public class EstructuraAhorcado implements Figura {
    private int errores = 0;

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.BLACK);

        g.fillRect(50, 330, 100, 12);

        g.fillRect(90, 100, 10, 230);

        g.fillRect(90, 100, 70, 10);

        g.drawLine(90, 140, 130, 100);

        g.fillRect(155, 110, 3, 20);
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

   // ======== PARTES DEL CUERPO ========
    private void dibujarCabeza(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(135, 130, 40, 40);

        g.fillOval(143, 142, 4, 4);
        g.fillOval(158, 142, 4, 4);

        g.drawLine(152, 148, 152, 152);

        g.drawArc(145, 155, 15, 10, 45, 90);
    }

    private void dibujarCuerpo(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(155, 170, 155, 220);
        
        g.drawLine(155, 170, 155, 175);
    }

    private void dibujarBrazoIzquierdo(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(155, 185, 135, 200);
        g.drawLine(135, 200, 125, 215);
        
        g.fillOval(122, 213, 6, 6);
    }

    private void dibujarBrazoDerecho(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(155, 185, 175, 200);
        g.drawLine(175, 200, 185, 215);
        
        g.fillOval(182, 213, 6, 6);
    }

    private void dibujarPiernaIzquierda(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(155, 220, 140, 250);
        g.drawLine(140, 250, 135, 275);
        
        g.drawLine(135, 275, 130, 275);
    }

    private void dibujarPiernaDerecha(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(155, 220, 170, 250);
        g.drawLine(170, 250, 175, 275);
        
        g.drawLine(175, 275, 180, 275);
    }
}