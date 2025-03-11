import javax.swing.*;
import Forme.CurriculumForm;
import Profil.GestionProfil;

public class Bureau extends JFrame{
    JMenuBar mb;
    JMenuItem itemTP1, itemTP2;
    JMenu swingTP, baseTP;
    JDesktopPane desktop;
    GestionProfil gestionProfil;
    CurriculumForm curriculumForm;
    Bureau() {
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

        desktop.add(gestionProfil);
        desktop.add(curriculumForm);

        this.add(mb);
        this.setJMenuBar(mb);

        this.add(desktop);

        this.setSize(1000,1000);

        this.setVisible(true);
    }
}
