import javax.swing.*;
import Forme.CurriculumForm;
import Profil.GestionProfil;
import DataBase.GestionEtudiant;
import java.sql.SQLException;

public class Bureau extends JFrame{
    JMenuBar mb;
    JMenuItem itemTP1, itemTP2;
    JMenu swingTP, baseTP;
    JDesktopPane desktop;
    GestionProfil gestionProfil;
    CurriculumForm curriculumForm;
    GestionEtudiant gestionEtudiant;
    Bureau() throws SQLException {
        curriculumForm = new CurriculumForm();
        mb = new JMenuBar();
        desktop = new JDesktopPane();
        gestionProfil = new GestionProfil();
        itemTP1 = new JMenuItem("TP1");
        itemTP2 = new JMenuItem("TP2");
        swingTP = new JMenu("TP Swing");
        baseTP = new JMenu("TP Base");
        swingTP.add(itemTP1);
        swingTP.add(itemTP2);

        mb.add(swingTP);
        mb.add(baseTP);

        gestionEtudiant = new GestionEtudiant();
        gestionEtudiant.setSize(800, 600);
        gestionEtudiant.setLocation(50, 50);

        desktop.add(gestionProfil);
        desktop.add(curriculumForm);
        desktop.add(gestionEtudiant);

        this.add(mb);
        this.setJMenuBar(mb);

        this.add(desktop);

        this.setSize(1000,1000);

        this.setVisible(true);
    }
}
