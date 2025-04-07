package DataBase;

import javax.swing.*;

import Profil.EcouteurFocus;


import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionEtudiant extends JInternalFrame {
    private enum Mode {ADD, EDIT}
    private Mode mode = Mode.ADD;
    JLabel id,name,surname,score,searchLabel;
    JPanel pn, searchPanel;
    JTextField searchField;
    JTextField idTF,nameTF,surnameTF,scoreTF;
    JButton addButton;
    MyTableModel model;
    JTable jt;
    ResultSet rs;
    EtudiantManager etudiantManager;
    public GestionEtudiant () throws SQLException {
        super("Gestion Etudiant", true, true, true, true);
        setLayout(new BorderLayout(10,10));
        
        // Initialize etudiantManager first
        etudiantManager = new EtudiantManager();
        String req = "select * from Etudiant";
        rs = etudiantManager.selectEtudiant(req);
        
        // Initialize UI components
        id = new JLabel("ID");
        name = new JLabel("Name");
        surname = new JLabel("Surname");
        score = new JLabel("Score");
        idTF = new JTextField(15);
        nameTF = new JTextField(15);
        surnameTF = new JTextField(15);
        scoreTF = new JTextField(15);
        addButton = new JButton("Ajouter");

        pn = new JPanel();
        pn.setLayout(new FlowLayout(FlowLayout.LEADING,10,20));
        pn.add(id);
        pn.add(idTF);
        pn.add(name);
        pn.add(nameTF);
        pn.add(surname);
        pn.add(surnameTF);
        pn.add(score);
        pn.add(scoreTF);
        pn.add(addButton);

        
        idTF.addFocusListener(new EcouteurFocus(this));
        nameTF.addFocusListener(new EcouteurFocus(this));
        surnameTF.addFocusListener(new EcouteurFocus(this));
        scoreTF.addFocusListener(new EcouteurFocus(this));
        addButton.addActionListener(e -> {
            if (mode == Mode.ADD) {
            try {
              
                int id = Integer.parseInt(idTF.getText());
                String nameVal = nameTF.getText();
                String surnameVal = surnameTF.getText();
                double scoreVal = Double.parseDouble(scoreTF.getText());

             
                int result = etudiantManager.insertEtudiant(id, nameVal, surnameVal, scoreVal);
                if (result > 0) {
                
                    rs = etudiantManager.selectEtudiant("select * from Etudiant");
                    model = new MyTableModel(rs, etudiantManager);
                    jt.setModel(model);

                    
                    idTF.setText("");
                    nameTF.setText("");
                    surnameTF.setText("");
                    scoreTF.setText("");

                    JOptionPane.showMessageDialog(null, "Étudiant ajouté avec succès!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erreur: Veuillez vérifier le format des données (ID doit être un nombre entier et Score un nombre décimal)");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout: " + ex.getMessage());
            }
        } else if (mode == Mode.EDIT) {
            try {

                int id = Integer.parseInt(idTF.getText());
                String nameVal = nameTF.getText();
                String surnameVal = surnameTF.getText();
                double scoreVal = Double.parseDouble(scoreTF.getText());

                
                int result = etudiantManager.updateEtudiant(id, nameVal, surnameVal, scoreVal);
                if (result > 0) {
                    
                    rs = etudiantManager.selectEtudiant("select * from Etudiant");
                    model = new MyTableModel(rs, etudiantManager);
                    jt.setModel(model);

                    
                    idTF.setText("");
                    nameTF.setText("");
                    surnameTF.setText("");
                    scoreTF.setText("");
                    mode = Mode.ADD;
                    addButton.setText("Ajouter");

                    JOptionPane.showMessageDialog(null, "Étudiant modifié avec succès!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Erreur: Veuillez vérifier le format des données (ID doit être un nombre entier et Score un nombre décimal)");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour: " + ex.getMessage());
            }
        }   
        });
        add(pn,BorderLayout.NORTH);
        jt = new JTable(model);
        add(jt,BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane(jt);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add search panel
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchLabel = new JLabel("Search:");
        searchField = new JTextField(20);
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        add(searchPanel, BorderLayout.SOUTH);
        
        // Add popup menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        JMenuItem updateItem = new JMenuItem("Update");
        popupMenu.add(deleteItem);
        popupMenu.add(updateItem);
        
        jt.setComponentPopupMenu(popupMenu);
        
        deleteItem.addActionListener(_ -> {
            int row = jt.getSelectedRow();
            if (row != -1) {
                model.deleteRow(row);
            }
        });

        updateItem.addActionListener(_ -> {
            int row = jt.getSelectedRow();
            if (row != -1) {
                mode = Mode.EDIT;
                idTF.setText(model.getValueAt(row, 0).toString());
                nameTF.setText(model.getValueAt(row, 1).toString());
                surnameTF.setText(model.getValueAt(row, 2).toString());
                scoreTF.setText(model.getValueAt(row, 3).toString());
                addButton.setText("Modifier");
            }
        });
        // Add search functionality
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String searchText = searchField.getText().toLowerCase();
                try {
                    String query = "SELECT * FROM Etudiant WHERE LOWER(name) LIKE '%" + 
                                  searchText + "%' OR LOWER(surname) LIKE '%" + searchText + "%'";
                    ResultSet rs = etudiantManager.selectEtudiant(query);
                    model = new MyTableModel(rs, etudiantManager);
                    jt.setModel(model);
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error searching: " + e.getMessage());
                }
            }
        });

        model = new MyTableModel(rs, etudiantManager);
        jt.setModel(model);
        setVisible(true);
    }
    public GestionEtudiant getFrame() {
        return this;
    }
}
