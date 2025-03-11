package Profil;

import javax.swing.*;
import java.awt.*;

public class FormPanel extends JPanel {
    String pseudo;
    JLabel Bienvenue = new JLabel("Bienvenue");
    JLabel nom, prenom;
    JPanel panel;
    JButton button = new JButton("Valider");
    public FormPanel(Profil P) {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        nom = new JLabel(P.getNom());
        prenom = new JLabel(P.getPrenom());
        this.pseudo = P.getPseudo();
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Bienvenue.setAlignmentY(Component.CENTER_ALIGNMENT);
        nom.setAlignmentY(Component.CENTER_ALIGNMENT);
        prenom.setAlignmentY(Component.CENTER_ALIGNMENT);

        panel.add(Bienvenue);
        panel.add(Box.createHorizontalStrut(3));
        panel.add(nom);
        panel.add(Box.createHorizontalStrut(3));
        panel.add(prenom);


        this.add(panel, BorderLayout.NORTH);

        this.add(button, BorderLayout.SOUTH);
        this.setVisible(true);
    }


}
