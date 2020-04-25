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
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JTextField taxField;
    private JLabel taxLabel;

    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;

    public FormPanel(){
        Dimension dim=getPreferredSize();
        dim.width=250;
        setPreferredSize(dim);

        nameLabel=new JLabel("Name: ");
        occupationLabel=new JLabel("Occupation: ");
        nameField=new JTextField(10);
        occupationField=new JTextField(10);
        ageList=new JList();
        empCombo = new JComboBox();
        citizenCheck=new JCheckBox();
        taxField= new JTextField(10);
        taxLabel=new JLabel("Tax ID: ");

        maleRadio=new JRadioButton("male");
        femaleRadio=new JRadioButton("female");

        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        genderGroup=new ButtonGroup();

        maleRadio.setSelected(true);

        // Set up gender radios
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        // Set up tax ID
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked=citizenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
        });

        // Set up list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0,"Under 18"));
        ageModel.addElement(new AgeCategory(1,"18 to 65"));
        ageModel.addElement(new AgeCategory(2,"65 or over"));
        ageList.setModel(ageModel);

        ageList.setPreferredSize(new Dimension(110,70));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        ageList.setSelectedIndex(1);

        // Set up combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("employed");
        empModel.addElement("self - employed");
        empModel.addElement("unemployed");
        empCombo.setModel(empModel);
        empCombo.setSelectedIndex(0);
        empCombo.setEditable(true);

        okButton=new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCat=(AgeCategory)ageList.getSelectedValue();
                String empCat=(String)empCombo.getSelectedItem();
                String taxId=taxField.getText();
                boolean usCitizen=citizenCheck.isSelected();

                String gender=genderGroup.getSelection().getActionCommand();

                System.out.println(empCat);

                FormEvent ev = new FormEvent(this, name, occupation, ageCat.getId(), empCat, taxId, usCitizen, gender);

                if (formListener != null) {
                    formListener.formEventOccurred(ev);
                }
            }
        });

        Border innerBorder=BorderFactory.createTitledBorder("Add Person");
        Border outerBorder=BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        layoutComponents();
    }

    public void layoutComponents(){
        setLayout(new GridBagLayout());

        GridBagConstraints gc=new GridBagConstraints();

        /////////// First row /////////////////

        gc.gridy=0;

        gc.weightx=1;
        gc.weighty=0.1;

        gc.gridx=0;
        gc.fill=GridBagConstraints.NONE;
        gc.anchor=GridBagConstraints.LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(nameLabel, gc);

        gc.gridx=1;
        gc.gridy=0;
        gc.insets=new Insets(0,0,0,0);
        gc.anchor=GridBagConstraints.LINE_START;
        add(nameField,gc);

        /////////// Second row /////////////////

        gc.gridy++;

        gc.weightx=1;
        gc.weighty=0.1;

        gc.gridx=0;
        gc.anchor=GridBagConstraints.LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(occupationLabel,gc);

        gc.gridx=1;
        gc.insets=new Insets(0,0,0,0);
        gc.anchor=GridBagConstraints.LINE_START;
        add(occupationField,gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=0.2;

        gc.gridx=0;
        gc.anchor=GridBagConstraints.FIRST_LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(new JLabel("Age: "),gc);

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(ageList,gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=0.2;

        gc.gridx=0;
        gc.anchor=GridBagConstraints.FIRST_LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(new JLabel("Employment: "),gc);

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(empCombo,gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=0.2;

        gc.gridx=0;
        gc.anchor=GridBagConstraints.FIRST_LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(new JLabel("Us Citizen: "),gc);

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(citizenCheck,gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=0.2;

        gc.gridx=0;
        gc.anchor=GridBagConstraints.FIRST_LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(taxLabel,gc);

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(taxField,gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=0.05;

        gc.gridx=0;
        gc.anchor=GridBagConstraints.LINE_END;
        gc.insets=new Insets(0,0,0,5);
        add(new JLabel("Gender"),gc);

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(maleRadio,gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=0.2;

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(femaleRadio,gc);

        /////////// Next row /////////////////
        gc.gridy++;

        gc.weightx=1;
        gc.weighty=2.0;

        gc.gridx=1;
        gc.anchor=GridBagConstraints.FIRST_LINE_START;
        gc.insets=new Insets(0,0,0,0);
        add(okButton,gc);
    }

    public void setFormListener(FormListener formListener){
        this.formListener=formListener;
    }
}

class AgeCategory {
    private int id;
    public String text;

    public AgeCategory(int id, String text){
        this.id=id;
        this.text=text;
    }

    public String toString(){
        return text;
    }

    public int getId() {
        return id;
    }
}
