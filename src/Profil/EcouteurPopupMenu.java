package Profil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EcouteurPopupMenu implements ActionListener {
    GestionProfil gestionProfil;
    Object pseudo;
    public EcouteurPopupMenu(GestionProfil gestionProfil, Object pseudo) {
        this.gestionProfil = gestionProfil;
        this.pseudo = pseudo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gestionProfil.itemSupprimerTout) {
            gestionProfil.model.clear();
            gestionProfil.jtp.removeAll();
            gestionProfil.TabulationListe.clear();
            gestionProfil.profileTable.data.clear();
        }

        if (e.getSource() == gestionProfil.itemSupprimer) {
            FormPanel formToDelete = null;
            String pseudoToDelete = this.pseudo.toString();
            Profil P = gestionProfil.profileTable.RechercheSelonPseudo(pseudoToDelete);
            gestionProfil.model.removeElement(P.getPseudo());
            gestionProfil.profileTable.data.remove(P);
            int idxToDelete = gestionProfil.jtp.indexOfTab(pseudoToDelete);
            if (idxToDelete != -1) {
                gestionProfil.jtp.remove(idxToDelete);
            }
            for (FormPanel listItem : gestionProfil.TabulationListe) {
                if (listItem.pseudo.equals(pseudoToDelete)) {
                    formToDelete = listItem;
                    break;
                }
            }
            gestionProfil.TabulationListe.remove(formToDelete);
        }

        // todo! : make this function correct
        if (e.getSource() == gestionProfil.itemModifier) {
            FormPanel formToDelete = null;
            String pseudoToDelete = this.pseudo.toString();
            Profil P = gestionProfil.profileTable.RechercheSelonPseudo(pseudoToDelete);

            gestionProfil.pseudoTA.setEditable(false);
            gestionProfil.nomTA.setText(P.getNom());
            gestionProfil.prenomTA.setText(P.getPrenom());
            gestionProfil.pseudoTA.setText(P.getPseudo());

            gestionProfil.model.removeElement(P.getPseudo());
            gestionProfil.profileTable.data.remove(P);
            int idxToDelete = gestionProfil.jtp.indexOfTab(pseudoToDelete);
            if (idxToDelete != -1) {
                gestionProfil.jtp.remove(idxToDelete);
            }
            for (FormPanel listItem : gestionProfil.TabulationListe) {
                if (listItem.pseudo.equals(pseudoToDelete)) {
                    formToDelete = listItem;
                    break;
                }
            }
            gestionProfil.TabulationListe.remove(formToDelete);

            // add and remove an already existing entry cuz fuck it
        }
    }
}
