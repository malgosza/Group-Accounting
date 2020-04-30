package gui;

import logic.SettlementDirection;
import logic.SettlementResult;
import logic.StartApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    ArrayList<FormEvent> listaFormEventow = new ArrayList<>();

    public MainFrame() {
        super("Rozliczacz");

        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        toolbar = new Toolbar(new RozliczListener());
        formPanel = new FormPanel(new DodajListener());

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 400));
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public class RozliczListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            rozliczanieKwoty();
        }

        public void rozliczanieKwoty() {
            ArrayList<BigDecimal> kwoty = new ArrayList<>();

//            List<BigDecimal> collect = listaFormEventow.stream()
//                    .map(FormEvent::getKwota)
//                    .map(BigDecimal::new)
//                    .collect(Collectors.toList());

            for (FormEvent formEvent : listaFormEventow) {
                kwoty.add(new BigDecimal(formEvent.getKwota()));
            }

            BigDecimal sredniaKwota = StartApp.equallySplittedAmount(kwoty);
            textPanel.appendText("Srednia kwota na jedna osobe: " + sredniaKwota.toString() + "\n");
            for (FormEvent event : listaFormEventow) {
                SettlementResult result = StartApp.amountToBeRefundedOrPaid(sredniaKwota, new BigDecimal(event.getKwota()));
                if (result.direction == SettlementDirection.zaplacilaZaDuzo) {
                    textPanel.appendText("Zwrot " + result.amount + " dla " + event.getImie());
                }
                else if(result.direction==SettlementDirection.niedoplacila){
                    textPanel.appendText(event.getImie()+" musi doplacic "+result.amount);
                }
                else {
                    textPanel.appendText(event.getImie()+" jest na czysto");
                }
            }
        }

    }

    public class DodajListener {
        public void click(FormEvent event) {
            textPanel.appendText(event.getImie() + " : " + event.getKwota() + "\n");
            listaFormEventow.add(event);
        }

        public void usun() {
            listaFormEventow.clear();
            textPanel.clearText();
        }
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