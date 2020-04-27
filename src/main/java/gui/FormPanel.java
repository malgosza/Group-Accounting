package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormPanel extends JPanel {

    private JLabel imieLabel;
    private JLabel kwotaLabel;
    private JTextField imieField;
    private JTextField kwotaField;
    private JButton dodajButton;

    ArrayList<FormEvent> listaFormEventow;
    TextPanel textPanel;

    public FormPanel(ArrayList<FormEvent> lista, TextPanel text) {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        this.listaFormEventow=lista;
        this.textPanel=text;

        imieLabel = new JLabel("Imie: ");
        kwotaLabel = new JLabel("Kwota: ");
        imieField = new JTextField(10);
        kwotaField = new JTextField(10);

        dodajButton = new JButton("Dodaj");

        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String imie=imieField.getText();
                String kwota=kwotaField.getText();

                FormEvent ev = new FormEvent(imie,kwota);

                textPanel.appendText(imie + " : " + kwota + "\n");
                listaFormEventow.add(ev);
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Dodaj osobe");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void layoutComponents() {
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        /////////// First row /////////////////

        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(imieLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(imieField, gc);

        /////////// Second row /////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(kwotaLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(kwotaField, gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=2.0;

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(dodajButton,gc);
    }
}
