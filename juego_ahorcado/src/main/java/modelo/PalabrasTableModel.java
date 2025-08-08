package modelo;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;

public class PalabrasTableModel extends AbstractTableModel {
    private List<Palabra> palabras;
    private final String[] columnNames = {"#", "Palabra", "Pista"};
    
    public PalabrasTableModel() {
        this.palabras = new ArrayList<>();
    }
    
    public PalabrasTableModel(List<Palabra> palabras) {
        this.palabras = palabras != null ? palabras : new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return palabras.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= palabras.size()) {
            return null;
        }
        
        Palabra palabra = palabras.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return palabra.getPalabra();
            case 2:
                return palabra.getPista();
            default:
                return null;
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
            case 2:
                return String.class;
            default:
                return Object.class;
        }
    }
    

    public void setPalabras(List<Palabra> palabras) {
        this.palabras = palabras != null ? palabras : new ArrayList<>();
        fireTableDataChanged();
    }

    public Palabra getPalabraAt(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= palabras.size()) {
            return null;
        }
        return palabras.get(rowIndex);
    }

    public List<Palabra> getPalabras() {
        return new ArrayList<>(palabras);
    }
}