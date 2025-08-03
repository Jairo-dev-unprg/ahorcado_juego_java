package graficos.figuras;

import java.awt.Color;
import java.awt.Graphics;

public class Linea implements Figura {

    private int xInicio;
    private int yInicio;
    private int xFin;
    private int yFin;

    public Linea() {
        // Por defecto puede dibujar una línea neutra
        this.xInicio = 0;
        this.yInicio = 0;
        this.xFin = 0;
        this.yFin = 0;
    }

    public Linea(int xInicio, int yInicio, int xFin, int yFin) {
        this.xInicio = xInicio;
        this.yInicio = yInicio;
        this.xFin = xFin;
        this.yFin = yFin;
    }

    @Override
    
    
    public void dibujar(Graphics g) {
        
        
 // Dibujar estructura del ahorcado compacta, más baja aún
g.setColor(Color.BLACK);

// Base horizontal (más arriba)
g.fillRect(50, 330, 100, 12);  // Antes 350 → ahora 330

// Poste vertical (más corto)
g.fillRect(90, 100, 10, 230);  // Antes altura 260 → ahora 230

// Viga superior
g.fillRect(90, 100, 70, 10);   

// Soporte diagonal (ajustado)
g.drawLine(90, 140, 130, 100); 

// Cuerda centrada debajo de la viga
g.fillRect(155, 100, 3, 40);   // Cuerda más corta7



    }
    
    
}
