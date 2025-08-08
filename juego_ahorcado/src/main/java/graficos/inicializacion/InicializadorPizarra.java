package graficos.inicializacion;

import graficos.PanelPizarra;
import graficos.figuras.EstructuraAhorcado;
import java.util.ArrayList;
import javax.swing.JPanel;

public class InicializadorPizarra {
    
    public static EstructuraAhorcado inicializar(JPanel panPizarra) {
        ((PanelPizarra) panPizarra).setFiguras(new ArrayList<>());
        EstructuraAhorcado estructuraAhorcado = new EstructuraAhorcado();
        ((PanelPizarra) panPizarra).getFiguras().add(estructuraAhorcado);
        panPizarra.repaint();
        return estructuraAhorcado;
    }
}