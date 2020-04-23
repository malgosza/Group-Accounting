package swing;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okButton;
    private FormListener formListener;
    private JList ageList;

    public FormPanel(){
        Dimension dim=getPreferredSize();
        dim.width=250;
        setPreferredSize(dim);

        nameLabel=new JLabel("Name: ");
        occupationLabel=new JLabel("Occupation: ");
        nameField=new JTextField(10);
        occupationField=new JTextField(10);
        ageList=new JList();

        okButton=new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();

                FormEvent ev = new FormEvent(this, name, occupation);

                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });

        Border innerBorder=BorderFactory.createTitledBorder("Add Person");
        Border outerBorder=BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();

        /////////// First row /////////////////

        gc.weightx=1;
        gc.weighty=0.1;

        gc.gridx=0;
        gc.gridy=0;
        gc.fill=GridBagConstraints.NONE;
        gc.anchor=GridBagConstraints.LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(nameLabel, gc);

        gc.gridx=1;
        gc.gridy=0;
        gc.insets=new Insets(0,0,0,0);
        gc.anchor=GridBagConstraints.LINE_START;
        add(nameField,gc);

        /////////// First row /////////////////

        gc.weightx=1;
        gc.weighty=0.1;

        gc.gridy=1;
        gc.gridx=0;
        gc.anchor=GridBagConstraints.LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(occupationLabel,gc);

        gc.gridx=1;
        gc.gridy=1;
        gc.insets=new Insets(0,0,0,0);
        gc.anchor=GridBagConstraints.LINE_START;
        add(occupationField,gc);

        /////////// Third row /////////////////
        gc.weightx=1;
        gc.weighty=2.0;

        gc.gridx=1;
        gc.gridy=2;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(okButton,gc);
    }

    public void setFormListener(FormListener formListener){
        this.formListener=formListener;
    }
}
