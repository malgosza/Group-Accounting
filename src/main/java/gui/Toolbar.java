package gui;

import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {

    private JButton rozliczButton;

    public Toolbar(MainFrame.RozliczListener listener) {
        setBorder(BorderFactory.createEtchedBorder());
        rozliczButton = new JButton("Rozlicz");

        rozliczButton.addActionListener(listener);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(rozliczButton);
    }
}
