package DataBase;

import javax.swing.*;
import java.sql.*;

public class Test  {

    public static void main(String[] args) throws SQLException {
        EtudiantManager etudiantManager = new EtudiantManager();
        //etudiantManager.insertEtudiant(163754882, "ghassen", "gaston", 18);
        GestionEtudiant gestionEtudiant = new GestionEtudiant();
        etudiantManager.selectEtudiant();
    }

}