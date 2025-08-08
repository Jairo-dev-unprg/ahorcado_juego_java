package graficos.admin;

import modelo.Palabra;
import modelo.PalabrasTableModel;
import persistencia.GestorPalabrasXML;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

public class GestorPalabrasUI {
    
    private GestorPalabrasXML gestorPalabras;
    private PalabrasTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtPalabra;
    private JTextField txtPista;
    private boolean modoEdicion;
    private int filaSeleccionada;
    
    public GestorPalabrasUI(JTable tabla, JTextField txtPalabra, JTextField txtPista) {
        this.tabla = tabla;
        this.txtPalabra = txtPalabra;
        this.txtPista = txtPista;
        this.modoEdicion = false;
        this.filaSeleccionada = -1;
        inicializar();
    }
    
    private void inicializar() {
        gestorPalabras = new GestorPalabrasXML();
        
        configurarTabla();
        cargarDatos();
        configurarSeleccion();
    }
    
    private void configurarTabla() {
        modeloTabla = new PalabrasTableModel();
        tabla.setModel(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla.getColumnModel().getColumn(2).setPreferredWidth(300);
        }
    }
    
    private void configurarSeleccion() {
        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    filaSeleccionada = tabla.getSelectedRow();
                    if (filaSeleccionada >= 0) {
                        Palabra palabraSeleccionada = modeloTabla.getPalabraAt(filaSeleccionada);
                        txtPalabra.setText(palabraSeleccionada.getPalabra());
                        txtPista.setText(palabraSeleccionada.getPista());
                    }
                }
            }
        });
    }
    
    public void cargarDatos() {
        List<Palabra> palabras = gestorPalabras.cargarPalabras();
        modeloTabla.setPalabras(palabras);
    }
    
    public boolean validarCampos() {
        if (txtPalabra.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La palabra no puede estar vacía", "Error de validación", JOptionPane.ERROR_MESSAGE);
            txtPalabra.requestFocus();
            return false;
        }
        
        if (txtPista.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La pista no puede estar vacía", "Error de validación", JOptionPane.ERROR_MESSAGE);
            txtPista.requestFocus();
            return false;
        }
        
        return true;
    }
    
    public void agregarPalabra() {
        if (!validarCampos()) {
            return;
        }
        
        String palabra = txtPalabra.getText().trim();
        String pista = txtPista.getText().trim();
        
        try {
            gestorPalabras.agregarPalabra(palabra, pista);
            JOptionPane.showMessageDialog(null, "Palabra agregada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatos();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la palabra: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarPalabra() {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una palabra para actualizar", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validarCampos()) {
            return;
        }
        
        String palabra = txtPalabra.getText().trim();
        String pista = txtPista.getText().trim();
        
        Palabra palabraOriginal = modeloTabla.getPalabraAt(filaSeleccionada);
        
        if (gestorPalabras.actualizarPalabra(palabraOriginal.getPalabra(), palabra, pista)) {
            JOptionPane.showMessageDialog(null, "Palabra actualizada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarDatos();
            limpiarCampos();
            resetearModo();
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar la palabra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarPalabra() {
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una palabra para eliminar", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(null, 
            "¿Está seguro de que desea eliminar esta palabra?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            Palabra palabraAEliminar = modeloTabla.getPalabraAt(filaSeleccionada);
            if (gestorPalabras.eliminarPalabra(palabraAEliminar.getPalabra())) {
                JOptionPane.showMessageDialog(null, "Palabra eliminada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatos();
                limpiarCampos();
                resetearModo();
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar la palabra", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public void limpiarCampos() {
        txtPalabra.setText("");
        txtPista.setText("");
        tabla.clearSelection();
        filaSeleccionada = -1;
    }
    
    public void resetearModo() {
        modoEdicion = false;
        filaSeleccionada = -1;
    }
    
    public boolean isModoEdicion() {
        return modoEdicion;
    }
    
    public void setModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
    }
}