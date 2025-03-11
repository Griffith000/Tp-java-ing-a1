package Profil;

import Profil.GestionProfil;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class EcouteurFocus implements FocusListener {
    GestionProfil gestionProfil;

    public EcouteurFocus(GestionProfil gestionProfil) {
        this.gestionProfil = gestionProfil;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == gestionProfil.nomTA) {
            if (!(gestionProfil.nomTA.getText().equals("") || gestionProfil.nomTA.getText().equals("Example Nom"))) {
                return;
            }
            gestionProfil.nomTA.setText("");
        }

        if (e.getSource() == gestionProfil.prenomTA) {
            if (!(gestionProfil.prenomTA.getText().equals("") || gestionProfil.prenomTA.getText().equals("Example Prenom"))) {
                return;
            }
            gestionProfil.prenomTA.setText("");
        }

        if (e.getSource() == gestionProfil.pseudoTA) {
            if (!(gestionProfil.pseudoTA.getText().equals("") || gestionProfil.pseudoTA.getText().equals("pseudonyme_1234"))) {
                return;
            }
            gestionProfil.pseudoTA.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == gestionProfil.nomTA) {
            if (gestionProfil.nomTA.getText().equals("") || gestionProfil.nomTA.getText().equals("Example Nom")) {
                gestionProfil.nomTA.setText("Example Nom");
            } else {return;}
        }

        if (e.getSource() == gestionProfil.prenomTA) {
            if (gestionProfil.prenomTA.getText().equals("") || gestionProfil.prenomTA.getText().equals("Example Prenom")) {
                gestionProfil.prenomTA.setText("Example Prenom");
            } else {return;}
        }

        if (e.getSource() == gestionProfil.pseudoTA) {
            if (gestionProfil.pseudoTA.getText().equals("") || gestionProfil.pseudoTA.getText().equals("pseudonyme_1234")) {
                gestionProfil.pseudoTA.setText("pseudonyme_1234");
            } else {return;}
        }
    }
}
