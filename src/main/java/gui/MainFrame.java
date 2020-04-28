package gui;

import logic.StartApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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
//            Integer sizeL = listaFormEventow.size();
//            textPanel.appendText(sizeL.toString());
//            textPanel.appendText(listaFormEventow.get(1).getImie().toString());

            rozliczanieKwoty();
        }

        public void rozliczanieKwoty() {
            ArrayList<BigDecimal> kwoty = new ArrayList<>();
            for (int i = 0; i < listaFormEventow.size(); i++) {
                kwoty.add(BigDecimal.valueOf(Long.parseLong(listaFormEventow.get(i).getKwota())));
            }

//            StartApp.settlementOfAllPeople(kwoty);
            textPanel.appendText("Srednia kwota na jedna osobe: " + StartApp.equallySplittedAmount(kwoty).toString() + "\n");
            for (int i = 0; i < listaFormEventow.size(); i++) {
                amountToBeRefundedOrPaid(StartApp.equallySplittedAmount(kwoty), listaFormEventow.get(i));
            }
        }

        public void amountToBeRefundedOrPaid(BigDecimal wholeAmountDividedIntoOnePerson, FormEvent event) {
            BigDecimal amount = wholeAmountDividedIntoOnePerson.subtract(BigDecimal.valueOf(Long.parseLong(event.getKwota())));
            DecimalFormat df = new DecimalFormat("#.##");
            if (amount.compareTo(BigDecimal.ZERO) > 0) {
                textPanel.appendText(event.getImie() + " musi dopłacić: " + df.format(amount)
                );
            } else if (amount.compareTo(BigDecimal.ZERO) < 0) {
                textPanel.appendText(event.getImie() + " -> nalezy jej/jemu zwrocic: " + df.format(amount.abs())
                );
            } else {
                textPanel.appendText(event.getImie() + " nie musisz ani dopłacać ani oczekiwać na zwrot");
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