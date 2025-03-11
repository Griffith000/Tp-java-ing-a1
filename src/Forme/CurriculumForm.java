package Forme;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

public class CurriculumForm extends JInternalFrame {

    JTextField name_field, surname_field;
    JFormattedTextField phone_field;
    JFormattedTextField cin_field;
    JTextArea description_field = new JTextArea();

    JLabel lb_title;
    JButton btQuitter, btValider;

    public CurriculumForm() {
        try {
            MaskFormatter mask_cin = new MaskFormatter("########");
            MaskFormatter mask_phone = new MaskFormatter("########");
            phone_field = new JFormattedTextField(mask_phone);
            cin_field = new JFormattedTextField(mask_cin);
        } catch (ParseException e){
            System.out.println("Couldn't parse mask");
        }
        this.setResizable(false);
        btQuitter = new JButton("Quitter");
        this.add(btQuitter);
        btValider = new JButton("Valider");
        this.add(btValider);
        description_field.setColumns(34);
        description_field.setLineWrap(true);

        this.setTitle("CV For beginners!");
        this.setSize(400, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        lb_title = new JLabel("CV");
        lb_title.setForeground(Color.WHITE);
        lb_title.setOpaque(true);
        lb_title.setBackground(Color.MAGENTA);
        lb_title.setFont(new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 20));
        lb_title.setPreferredSize(new Dimension(800, 80));
        lb_title.setHorizontalAlignment(JLabel.CENTER);

        // maybe for homework
        name_field = new JTextField(30);
        surname_field = new JTextField(28);
        cin_field.setColumns(30);
        phone_field.setColumns(30);

        description_field.setRows(24);
        description_field.setColumns(35);

        JScrollPane scrollPane = new JScrollPane(description_field);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        this.setClosable(true);
        this.setIconifiable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // event
        this.add(lb_title);
        this.add(new JLabel("CIN:"));
        this.add(cin_field);
        this.add(new JLabel("Name:"));
        this.add(name_field);
        this.add(new JLabel("Surname:"));
        this.add(surname_field);
        this.add(new JLabel("Phone:"));
        this.add(phone_field);
        this.add(new JLabel("Description:"));
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        this.add(btQuitter);
        this.add(btValider);

        JLabel lb_nom = new JLabel("Nom");
        JTextField nom_field = new JTextField();
        JLabel lb_prenom = new JLabel("Prenom");
        JTextField prenom_field = new JTextField();

        btQuitter.addActionListener(new Ecouteur());
        btValider.addActionListener(new Ecouteur());

        this.setVisible(true);
    }

    class Ecouteur implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btQuitter) {
                System.exit(0);
            }

            if (e.getSource() == btValider) {
                File f = new File("cv.html");
                System.out.println("Credentials Found!");
                System.out.println(name_field.getText());
                System.out.println(surname_field.getText());

                if (name_field.getText().isEmpty() || surname_field.getText().isEmpty()) {
                    System.out.println("Name and/or Surname is Empty, please verify!");
                    return;
                }

                try {
                    FileWriter fz = new FileWriter(f, false);
                    fz.write(
                            "<html>" + "\n" +
                                    "<head>" + "\n" +
                                    "<title>Vote CV</title>" + "\n" +
                                    "</head>" + "\n" +
                                    "<body>" + "\n" +
                                    "<h1>Numero CIN: " + cin_field.getText() + "</h1>" + "\n" +
                                    "<h1>Nom et Prenom:</h1>" + "\n" +
                                    "<h2>Info</h2>" + "\n" +
                                    "<h3>Nom: " + name_field.getText() + "</h3>" + "\n" +
                                    "<h3>Prenom: " + surname_field.getText() + "</h3>" + "\n" +
                                    "<h3>Numero Telephone: " + phone_field.getText() + "</h3>" + "\n" +
                                    "<h4>Description: " + description_field.getText() + "</h4>" + "\n" +
                                    "</body>" + "\n" +
                                    "</html>"
                    );
                    fz.close();

                    String osName = System.getProperty("os.name");
                    if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec(new String[] {"rundll32.exe", "url.windows", "cv.html"});
                    } else if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec(new String[] {"open", "cv.html"});
                    } else {
                        Runtime.getRuntime().exec(new String[] {"xdg-open", "cv.html"});
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                    // welp I love how easy it is to do stuff in java
                    // maybe one day ill become a minecraft developer
                }
            }
        }
    }
}


