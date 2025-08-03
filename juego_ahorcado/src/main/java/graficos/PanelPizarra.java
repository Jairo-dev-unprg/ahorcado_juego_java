package graficos;

import graficos.figuras.Figura;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PanelPizarra extends JPanel {
    
    
   private int errores = 0;
  
 private List<Figura> figuras = new ArrayList<>();
 
 public void actualizarDibujo(int errores) {
    this.errores = errores;
    repaint(); // vuelve a pintar el componente
}

  public List<Figura> getFiguras() {
    return figuras;
  }

  public void setFiguras(List<Figura> figuras) {
    this.figuras = figuras;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    
//    g.setColor(Color.blue);
//    g.drawOval(100, 100, 50, 50);
//    g.drawLine(100, 150, 100, 250);



    for (Figura figura : figuras) {
      figura.dibujar(g);
    }
  }
  
  
  
}
