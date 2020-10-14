import javax.swing.*;
import java.awt.*;

public class NewEntry extends JFrame {
    private JPanel mainPanel;
    private JPanel headPanel;
    private JPanel entryListPanel;
    private JPanel newEntryPanel;
    private JList entryList;
    private JLabel heading;
    private JTextField textPatientName;
    private JTextField textAge;
    private JTextField textTower;
    private JButton saveAddButton;
    private JButton finishedButton;
    private JLabel patientName;
    private JLabel age;
    private JLabel tower;
    private JLabel dateOfReporting;
    private JTextField textDateOfReporting;
    private JPanel bottonPanel;
    private JPanel leftButtonPanel;
    private JPanel rightButtonPanel;

    NewEntry() {
        super("New Entry");
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        NewEntry newEntry = new NewEntry();
    }
}
