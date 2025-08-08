package graficos.configuracion;

public class ConfiguradorLookAndFeel {
    
    public static void configurar() {
        try {
            javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            
        }
    }
}