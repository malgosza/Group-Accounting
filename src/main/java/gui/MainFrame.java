package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    ArrayList<FormEvent> listaFormEventow=new ArrayList<>();

    public MainFrame() {
        super("Rozliczacz");

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        toolbar = new Toolbar(listaFormEventow, textPanel);
        formPanel=new FormPanel(listaFormEventow,textPanel);

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