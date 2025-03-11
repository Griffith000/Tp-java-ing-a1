package Profil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GestionProfil extends JInternalFrame
{
    ArrayList<FormPanel> TabulationListe = new ArrayList<>();
    JPanel pn;
    JTextField nomTA, prenomTA, pseudoTA;
    JButton enregistrer;
    JSplitPane jsp;
    JTabbedPane jtp;
    JList jl;
    Data profileTable = new Data();
    DefaultListModel model;
    JLabel nomL, prenomL, pseudoL, help;
    JPopupMenu contextList;
    JMenuItem itemSupprimer, itemSupprimerTout, itemModifier;
    public GestionProfil(){
        pn = new JPanel();
        pn.setLayout(new FlowLayout());

         nomL = new JLabel("Nom: ");
        nomTA = new JTextField(15);

         prenomL = new JLabel("Prenom: ");
        prenomTA = new JTextField(15);

         pseudoL = new JLabel("Pseudo: ");
        pseudoTA = new JTextField(15);

        enregistrer = new JButton("Enregistrer");

        pn.add(nomL);
        pn.add(nomTA);
        pn.add(prenomL);
        pn.add(prenomTA);
        pn.add(pseudoL);
        pn.add(pseudoTA);
        pn.add(enregistrer);

        help = new JLabel("Help");

        jsp = new JSplitPane();
        jtp = new JTabbedPane();


        model = new DefaultListModel();
        jl = new JList(model);

        jsp.setLeftComponent(jl);
        jsp.setRightComponent(jtp);
        jsp.setDividerLocation(180);

        jl.setModel(model);
        this.setTitle("Gestion Profil");
        this.setSize(800,350);
        this.setLocation(200,200);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setLayout(new BorderLayout());

        this.add(pn, BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
        this.add(help, BorderLayout.SOUTH);

        // Event
        enregistrer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // sauvegarder les informations dans la base des donnees
                // a faire: test si il est vide
                pseudoTA.setEditable(true);
                Profil P = profileTable.RechercheSelonPseudo(pseudoTA.getText());
                // check if the pseudo already exists

                if (P != null) {
                    JOptionPane.showMessageDialog(null, "Pseudo already exists");
                    return;
                }
                Profil newProfile = new Profil(nomTA.getText(), prenomTA.getText(), pseudoTA.getText());
                if (nomTA.getText().equals("") || prenomTA.getText().equals("") || pseudoTA.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields");
                    return;
                }
                profileTable.PushData(newProfile);
                model.addElement(pseudoTA.getText());
            }
        });

        nomTA.addFocusListener(new EcouteurFocus(this));
        prenomTA.addFocusListener(new EcouteurFocus(this));
        pseudoTA.addFocusListener(new EcouteurFocus(this));

        nomL.addMouseListener(new EcouteurLabel(this));
        prenomL.addMouseListener(new EcouteurLabel(this));
        pseudoL.addMouseListener(new EcouteurLabel(this));
        enregistrer.addMouseListener(new EcouteurLabel(this));

        nomTA.setText("Example Nom");
        prenomTA.setText("Example Prenom");
        pseudoTA.setText("pseudonyme_1234");
        this.setVisible(true);


        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // double click
                if (e.getClickCount() == 2) {
                    Profil P = profileTable.RechercheSelonPseudo(jl.getSelectedValue() + "");
                    for (FormPanel listItem: TabulationListe){
                        if (listItem.pseudo == P.getPseudo()) {
                            return;
                        }
                    }
                    FormPanel pn = new FormPanel(P);
                    TabulationListe.add(pn);
                    jtp.addTab(jl.getSelectedValue() + "", pn);
                }

                if (e.getButton() == MouseEvent.BUTTON3) {
                    // right click context menu
                    if (jl.getSelectedValue() == null) return; // if this is invalid
                    JPopupMenu contextList = new JPopupMenu();
                    itemModifier = new JMenuItem("Modifier");
                    itemSupprimer = new JMenuItem("Supprimer");
                    itemSupprimerTout = new JMenuItem("Supprimer Tout");
                    contextList.add(itemModifier);
                    contextList.add(itemSupprimer);
                    contextList.add(itemSupprimerTout);
                    contextList.show(GestionProfil.this, e.getX(), e.getY());
                    itemSupprimer.addActionListener(new EcouteurPopupMenu(GestionProfil.this, jl.getSelectedValue()));
                    itemSupprimerTout.addActionListener(new EcouteurPopupMenu(GestionProfil.this, null));
                    itemModifier.addActionListener(new EcouteurPopupMenu(GestionProfil.this, jl.getSelectedValue()));
                }
            }
        });
    }
}
