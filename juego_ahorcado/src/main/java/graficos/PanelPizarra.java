package graficos;

import graficos.figuras.Figura;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class PanelPizarra extends JPanel {

    private int errores = 0;

    private List<Figura> figuras = new ArrayList<>();

    public void actualizarDibujo(int errores) {
        this.errores = errores;
        repaint();
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

        for (Figura figura : figuras) {
            figura.dibujar(g);
        }
    }

}
