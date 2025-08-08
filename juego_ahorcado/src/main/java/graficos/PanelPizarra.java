package graficos;

import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import graficos.GestorFiguras.Figura;

public class PanelPizarra extends JPanel {
    ///////REDIMENSION
    private int errores = 0;
    private List<Object> figuras = new ArrayList<>();
    
    public PanelPizarra() {
        super();
        
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                actualizarEscalaFiguras();
            }
        });
    }

    public void actualizarDibujo(int errores) {
        this.errores = errores;
        repaint();
    }

    public List<Object> getFiguras() {
        return figuras;
    }

    public void setFiguras(List<Object> figuras) {
        this.figuras = figuras;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        actualizarEscalaFiguras();
        
        for (Object figura : figuras) {
            if (figura instanceof Figura) {
                ((Figura) figura).dibujar(g);
            }
        }
    }
    
    private void actualizarEscalaFiguras() {
        int ancho = getWidth();
        int alto = getHeight();
        
        if (ancho > 0 && alto > 0) {
            for (Object figura : figuras) {
                try {
                    java.lang.reflect.Method metodo = figura.getClass().getMethod("actualizarEscala", int.class, int.class);
                    metodo.invoke(figura, ancho, alto);
                } catch (Exception e) {
                }
            }
        }
    }

}
