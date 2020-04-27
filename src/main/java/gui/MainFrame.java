package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    ArrayList<String> ludzie=new ArrayList<>();
    ArrayList<String> pieniadze=new ArrayList<>();

    public MainFrame() {
        super("Rozliczacz");

        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel=new FormPanel();

        toolbar.setTextPanel(textPanel);

        formPanel.setFormListener(new FormListener() {
            @Override
            public void formEventOccurred(FormEvent e) {
                String imie = e.getImie();
                String kwota = e.getKwota();

                textPanel.appendText(imie + " : " + kwota + "\n");

            }
        });

        add(formPanel,BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}