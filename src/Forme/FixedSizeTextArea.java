package Forme;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.*;
import javax.swing.ScrollPaneConstants;

public class FixedSizeTextArea {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Fixed Size TextArea with Scroll");
        frame.setPreferredSize(new Dimension(400, 300));

        JTextArea textArea = new JTextArea();
        textArea.setRows(5);
        textArea.setColumns(40);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}