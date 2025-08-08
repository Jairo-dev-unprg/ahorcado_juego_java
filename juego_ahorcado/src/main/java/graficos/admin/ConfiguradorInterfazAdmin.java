package graficos.admin;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;

public class ConfiguradorInterfazAdmin {
    
    public static void configurarVentana(JFrame ventana) {
        ventana.setTitle("Administración de Palabras");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
    }
    
    public static void configurarTabla(JTable tabla, JScrollPane scrollPane) {
        tabla.setFillsViewportHeight(true);
        scrollPane.setViewportView(tabla);
    }
    
    public static void configurarBotones(JButton btnAgregar, JButton btnActualizar, 
                                        JButton btnEliminar, JButton btnLimpiar, 
                                        JButton btnCerrarSesion,
                                        ActionListener agregarListener,
                                        ActionListener actualizarListener,
                                        ActionListener eliminarListener,
                                        ActionListener limpiarListener,
                                        ActionListener cerrarSesionListener) {
        
        btnAgregar.setText("Agregar");
        btnActualizar.setText("Actualizar");
        btnEliminar.setText("Eliminar");
        btnLimpiar.setText("Limpiar");
        btnCerrarSesion.setText("Cerrar Sesión");
        
        btnAgregar.addActionListener(agregarListener);
        btnActualizar.addActionListener(actualizarListener);
        btnEliminar.addActionListener(eliminarListener);
        btnLimpiar.addActionListener(limpiarListener);
        btnCerrarSesion.addActionListener(cerrarSesionListener);
    }
    
    public static void configurarEtiquetas(JLabel lblPalabra, JLabel lblPista) {
        lblPalabra.setText("Palabra:");
        lblPista.setText("Pista:");
    }
    
    public static void configurarCamposTexto(JTextField txtPalabra, JTextField txtPista) {
        txtPalabra.setColumns(20);
        txtPista.setColumns(30);
    }
}