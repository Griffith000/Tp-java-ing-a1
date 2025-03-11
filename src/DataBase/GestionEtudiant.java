package DataBase;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionEtudiant extends JFrame {
    JLabel id,name,surname,score;
    JPanel pn;
    JTextField idTF,nameTF,surnameTF,scoreTF;
    MyTableModel model;
    JTable jt;
    ResultSet rs;
    EtudiantManager etudiantManager;
    public GestionEtudiant () throws SQLException {
        setLayout(new BorderLayout());
        id = new JLabel("ID");
        name = new JLabel("Name");
        surname = new JLabel("Surname");
        score = new JLabel("Score");
        idTF = new JTextField(15);
        nameTF = new JTextField(15);
        surnameTF = new JTextField(15);
        scoreTF = new JTextField(15);

        pn = new JPanel();
        pn.setLayout(new FlowLayout( FlowLayout.CENTER));
        pn.add(id);
        pn.add(idTF);
        pn.add(name);
        pn.add(nameTF);
        pn.add(surname);
        pn.add(surnameTF);
        pn.add(score);
        pn.add(scoreTF);
        add(pn,BorderLayout.NORTH);
        jt = new JTable(model);
        add(jt,BorderLayout.CENTER);
        this.add(new JScrollPane(jt));

        String req= "select * from Etudiant";
        EtudiantManager etudiantManager = new EtudiantManager();
        ResultSet rs = etudiantManager.selectEtudiant(req);
        model = new MyTableModel(rs,etudiantManager);
        jt.setModel(model);
        setVisible(true);
    }
    public static void main(String[] args) throws SQLException {

         GestionEtudiant gestionEtudiant = new GestionEtudiant();
         gestionEtudiant.setSize(500,500);
         gestionEtudiant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }
}
