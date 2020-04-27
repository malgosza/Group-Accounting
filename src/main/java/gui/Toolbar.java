package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {
    private JButton rozliczButton;

    private TextPanel textPanel;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        rozliczButton = new JButton("Rozlicz");

        rozliczButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(rozliczButton);
    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();

        if (clicked == rozliczButton) {
            textPanel.appendText(1 + 3);
        }
    }
}
