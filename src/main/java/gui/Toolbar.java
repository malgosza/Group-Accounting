package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Toolbar extends JPanel implements ActionListener {

    private JButton rozliczButton;
    private TextPanel textPanel;
    private ArrayList<FormEvent> listaFormEventow;

    public Toolbar(ArrayList<FormEvent> formEvents, TextPanel textPanel) {
        setBorder(BorderFactory.createEtchedBorder());
        rozliczButton = new JButton("Rozlicz");

        this.listaFormEventow=formEvents;
        this.textPanel=textPanel;

        rozliczButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(rozliczButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        Integer sizeL=listaFormEventow.size();
        if (clicked == rozliczButton) {

            textPanel.appendText(sizeL.toString());
        }
    }
}
