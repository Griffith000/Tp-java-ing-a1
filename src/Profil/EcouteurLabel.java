package Profil;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EcouteurLabel extends MouseAdapter {
    GestionProfil gestionProfil;
    // lorce que le curseur passe sur l'un des champs, sa couleur change
    // coleur nul = default, setForeground
    public EcouteurLabel(GestionProfil gestionProfil) {
        this.gestionProfil = gestionProfil;
    }

    public void mouseEntered(MouseEvent e) {

        if (e.getSource() == gestionProfil.nomL) {
            gestionProfil.help.setText("Help: " + "Le champs ou votre Nom doit eter saisi!");
        }
        if (e.getSource() == gestionProfil.prenomL) {
            gestionProfil.help.setText("Help: " + "Le champs ou votre prenom doit eter saisi!");
        }
        if (e.getSource() == gestionProfil.pseudoL) {
            gestionProfil.help.setText("Help: " + "Le champs ou votre pseudo doit eter saisi (Un unique pseudonyme pour chaque personne)!");
        }
        if (e.getSource() == gestionProfil.enregistrer) {
            gestionProfil.help.setText("Help: " + "Clickez ici pour enregistrer un nouveau profil!");
        }
    }

    public void mouseExited(MouseEvent e) {
        gestionProfil.help.setText("Help");
    }
}
