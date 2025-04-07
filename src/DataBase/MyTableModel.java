package DataBase;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    ArrayList<Object[]> data = new ArrayList<>();

    ResultSetMetaData rsmd;
    EtudiantManager manager;
    public MyTableModel(ResultSet rs, EtudiantManager manager) throws SQLException {
        this.manager = manager;
        rsmd=rs.getMetaData();
        while(rs.next()){
           Object[] ligne = new Object[rsmd.getColumnCount()];
              for (int i=0; i<rsmd.getColumnCount(); i++){
                ligne[i]=rs.getObject(i+1);
              }
                data.add(ligne);
        }

    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        try {
            return rsmd.getColumnName(columnIndex + 1 );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return getColumnName(columnIndex).equalsIgnoreCase("score");
    }
    public void setValueAt(Object value, int rowIndex, int columnIndex){
        data.get(rowIndex)[columnIndex]=value;
        try {
            int id = (int) data.get(rowIndex)[0];
            String name = (String) data.get(rowIndex)[1];
            String surname = (String) data.get(rowIndex)[2];
            double score = Double.parseDouble(data.get(rowIndex)[3].toString());
            manager.updateEtudiant(id, name, surname, score);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating database: " + e.getMessage());
        }

    }
    int AjouterEtudiant (int id , String name, String surname, double score) throws SQLException{
        int a = manager.insertEtudiant(id,name,surname,score);
        if (a>0){
            data.add(new Object[]{id,name,surname,score});
        }
        fireTableDataChanged();
        return a;
    }
    
    public void deleteRow(int rowIndex) {
        try {
            int id = (int) data.get(rowIndex)[0];
            int result = manager.deleteEtudiant(id);
            if (result > 0) {
                data.remove(rowIndex);
                fireTableDataChanged();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error deleting from database: " + e.getMessage());
        }
    }
    public void updateRow(int rowIndex, int id, String name, String surname, double score) {
        data.get(rowIndex)[0] = id;
        data.get(rowIndex)[1] = name;
        data.get(rowIndex)[2] = surname;
        data.get(rowIndex)[3] = score;
        fireTableDataChanged();
    }
}
