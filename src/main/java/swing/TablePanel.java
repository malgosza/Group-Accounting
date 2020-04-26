package swing;

import model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TablePanel extends JPanel {

    private JTable table;
    private PersonTabModel tabModel;

    public TablePanel(){

        tabModel=new PersonTabModel();
        table=new JTable(tabModel);

        setLayout(new BorderLayout());

        add( new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> db){
        tabModel.setData(db);
    }

    public void refresh() {
        tabModel.fireTableDataChanged();
    }
}
