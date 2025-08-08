package graficos.teclado;

import java.awt.Color;
import javax.swing.JButton;

public class GestorTeclado {
    
    private JButton[] btns;
    private TecladoListener listener;
    
    public interface TecladoListener {
        void onLetraPresionada(char letra);
    }
    
    public GestorTeclado(JButton[] botones, TecladoListener listener) {
        this.btns = botones;
        this.listener = listener;
    }
    
    public void inicializar() {
        configurarBotonesDesactivados();
        asignarListeners();
    }
    
    public void configurarBotonesDesactivados() {
        Color colorDesactivado = new Color(200, 200, 200);
        Color textoDesactivado = new Color(120, 120, 120);
        
        for (JButton btn : btns) {
            if (btn != null) {
                configurarBoton(btn, colorDesactivado, textoDesactivado, false);
            }
        }
    }
    
    public void configurarBotonesIniciales() {
        Color colorInicial = new Color(240, 240, 240);
        Color textoInicial = Color.BLACK;
        
        for (JButton btn : btns) {
            if (btn != null) {
                configurarBoton(btn, colorInicial, textoInicial, true);
            }
        }
    }
    
    private void asignarListeners() {
        if (btns.length >= 27) {
            btns[0].addActionListener(e -> listener.onLetraPresionada('A'));
            btns[1].addActionListener(e -> listener.onLetraPresionada('B'));
            btns[2].addActionListener(e -> listener.onLetraPresionada('C'));
            btns[3].addActionListener(e -> listener.onLetraPresionada('D'));
            btns[4].addActionListener(e -> listener.onLetraPresionada('E'));
            btns[5].addActionListener(e -> listener.onLetraPresionada('F'));
            btns[6].addActionListener(e -> listener.onLetraPresionada('G'));
            btns[7].addActionListener(e -> listener.onLetraPresionada('H'));
            btns[8].addActionListener(e -> listener.onLetraPresionada('I'));
            btns[9].addActionListener(e -> listener.onLetraPresionada('J'));
            btns[10].addActionListener(e -> listener.onLetraPresionada('K'));
            btns[11].addActionListener(e -> listener.onLetraPresionada('L'));
            btns[12].addActionListener(e -> listener.onLetraPresionada('M'));
            btns[13].addActionListener(e -> listener.onLetraPresionada('N'));
            btns[14].addActionListener(e -> listener.onLetraPresionada('Ñ'));
            btns[15].addActionListener(e -> listener.onLetraPresionada('O'));
            btns[16].addActionListener(e -> listener.onLetraPresionada('P'));
            btns[17].addActionListener(e -> listener.onLetraPresionada('Q'));
            btns[18].addActionListener(e -> listener.onLetraPresionada('R'));
            btns[19].addActionListener(e -> listener.onLetraPresionada('S'));
            btns[20].addActionListener(e -> listener.onLetraPresionada('T'));
            btns[21].addActionListener(e -> listener.onLetraPresionada('U'));
            btns[22].addActionListener(e -> listener.onLetraPresionada('V'));
            btns[23].addActionListener(e -> listener.onLetraPresionada('W'));
            btns[24].addActionListener(e -> listener.onLetraPresionada('X'));
            btns[25].addActionListener(e -> listener.onLetraPresionada('Y'));
            btns[26].addActionListener(e -> listener.onLetraPresionada('Z'));
        }
    }
    
    public void resetear() {
        for (JButton btn : btns) {
            if (btn != null && btn.getActionListeners().length > 0) {
                for (var listener : btn.getActionListeners()) {
                    btn.removeActionListener(listener);
                }
            }
        }
        asignarListeners();
    }
    
    public void configurarBoton(JButton boton, Color colorFondo, Color colorTexto, boolean habilitado) {
        if (boton != null) {
            boton.setBackground(colorFondo);
            boton.setForeground(colorTexto);
            boton.setEnabled(habilitado);
            boton.setOpaque(true);
            boton.setBorderPainted(true);
            boton.setContentAreaFilled(true);
            boton.repaint();
        }
    }
    
    public void deshabilitarTodos() {
        for (JButton btn : btns) {
            if (btn != null) {
                btn.setEnabled(false);
            }
        }
    }
}