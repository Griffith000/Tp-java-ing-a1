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

    }
    int AjouterEtudiant (int id , String name, String surname, double score) throws SQLException{
        int a = manager.insertEtudiant(id,name,surname,score);
        if (a>0){
            data.add(new Object[]{id,name,surname,score});
        }
        fireTableDataChanged();
        return a;
    }
}
// click droite al jtable il affiche une boutton supprimer et il le suprimme de la base at l'interface graphique
// setValuAt tu dois modifer les donnèes dans la base de donnèes
// inserer gestionEtudiant dans le frame bureau
//ajouter zone de saisie dans la partie south pour la recherche selon le nom et le prenom(keylistener pour le textfield)ustiliser les classe externes pour tous les action
