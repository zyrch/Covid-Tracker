import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static java.time.temporal.ChronoUnit.DAYS;

public class Screen extends JFrame{

    private JPanel mainPanel;
    private JPanel headPanel;
    private JPanel inputPanel;
    private JScrollPane activePanel;
    private JScrollPane recoveredPanel;
    private JPanel detailPanel;
    private JPanel activeLabelPanel;
    private JPanel recoveredLabelPanel;
    private JList activeList;
    private JList recoveredList;
    private JLabel heading;
    private JTextField textInputDate;
    private JButton runButton;
    private JCheckBox aCheckBox;
    private JCheckBox bCheckBox;
    private JCheckBox cCheckBox;
    private JCheckBox dCheckBox;
    private JTextField textName;
    private JTextField textAge;
    private JTextField textTower;
    private JTextField textDateofReport;
    private JTextField textRecoveryDate;
    private JPanel programStatusPanel;
    private JTextField textProgramStatus;
    private JTextField recoveredCasesCount;
    private JTextField activeCasesCount;
    public static DefaultListModel activeListModel, recoveredListModel;
    private static ArrayList<Patient> patients;
    private static ArrayList<Patient> active;
    private static ArrayList<Patient> recovered;

    public static void main(String[] args) {

        Screen screen = new Screen();
        screen.populateDataBase();

    }

    Screen() {
        super("Covid Tracker");

        activeListModel = new DefaultListModel();
        activeList.setModel(activeListModel);

        recoveredListModel = new DefaultListModel();
        recoveredList.setModel(recoveredListModel);

        Image icon = Toolkit.getDefaultToolkit().getImage("icon.png");
        this.setIconImage(icon);
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        activeList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = activeList.getSelectedIndex();
                if (index == -1) return;
                Patient p = active.get(index);
                textName.setText(p.getName());
                textAge.setText(String.valueOf(p.getAge()));
                textTower.setText(String.valueOf(p.getTower()));
                textDateofReport.setText(p.getDateOfReporting().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                textRecoveryDate.setText(p.getDateOfRecovery().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });

        recoveredList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                int index = recoveredList.getSelectedIndex();
                if (index == -1) return;
                Patient p = recovered.get(index);
                textName.setText(p.getName());
                textAge.setText(String.valueOf(p.getAge()));
                textTower.setText(String.valueOf(p.getTower()));
                textDateofReport.setText(p.getDateOfReporting().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                textRecoveryDate.setText(p.getDateOfRecovery().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Query();
            }
        });
    }

    void Query() {
        boolean correct = checkDate();
        if (!correct) {
            textProgramStatus.setText("Incorrect Date format, expected format : dd/mm/yyyy");
            return;
        }
        LocalDate curDate = LocalDate.parse(textInputDate.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        active = new ArrayList<>();
        recovered = new ArrayList<>();
        ArrayList<Character> towers = new ArrayList<>();
        if (aCheckBox.isSelected()) {
            towers.add('A');
        }
        if (bCheckBox.isSelected()) {
            towers.add('B');
        }
        if (cCheckBox.isSelected()) {
            towers.add('C');
        }
        if (dCheckBox.isSelected()) {
            towers.add('D');
        }

        if (towers.size() == 0) {
            textProgramStatus.setText("No Towers Chosen.");
            return;
        }

        long diff;
        for (Patient p : patients) {
            if (towers.contains(p.getTower())) {
                diff = DAYS.between(p.getDateOfReporting(), curDate);
                if (diff > 21) {
                    recovered.add(p);
                }else if (diff >= 0){
                    active.add(p);
                }
            }
        }
        activeCasesCount.setText(String.valueOf(active.size()));
        recoveredCasesCount.setText(String.valueOf(recovered.size()));
        textProgramStatus.setText("Run Successful.");
        activeListRefreshor();
        recoveredListRefreshor();
    }

    private Boolean checkDate() {
        String date = textInputDate.getText();
        if (date.length() != 10) return false;

        if (date.charAt(2) != '/' || date.charAt(5) != '/') return false;
        if (date.charAt(0) < '0' || date.charAt(0) > '3') return false;
        if (date.charAt(1) < '0' || date.charAt(1) > '9') return false;
        if (date.charAt(3) < '0' || date.charAt(3) > '1') return false;
        if (date.charAt(4) < '0' || date.charAt(4) > '9') return false;
        if (date.charAt(6) < '0' || date.charAt(6) > '9') return false;
        if (date.charAt(7) < '0' || date.charAt(7) > '9') return false;
        if (date.charAt(8) < '0' || date.charAt(8) > '9') return false;
        if (date.charAt(9) < '0' || date.charAt(9) > '9') return false;

        if (date.charAt(0) == '3' && date.charAt(1) > '1') return false;
        if (date.charAt(3) == '1' && date.charAt(4) > '2') return false;

        return true;
    }

    public void activeListRefreshor() {
        activeListModel.removeAllElements();
        for (Patient p : active) {
            activeListModel.addElement(p.getName());
        }
    }

    public void recoveredListRefreshor() {
        recoveredListModel.removeAllElements();
        for (Patient p : recovered) {
            recoveredListModel.addElement(p.getName());
        }
    }

    public void populateDataBase() {
        patients = new ArrayList<>();
        Patient patient;
        patient = new Patient("Flora", 6, 'A', "01/04/2020");
        patients.add(patient);
        patient = new Patient("Denys", 24, 'B', "01/04/2020");
        patients.add(patient);
        patient = new Patient("Jim", 42, 'C', "18/05/2020");
        patients.add(patient);
        patient = new Patient("Hazel", 87, 'D', "23/06/2020");
        patients.add(patient);
        patient = new Patient("Carey", 72, 'A', "01/06/2020");
        patients.add(patient);
        patient = new Patient("David", 7, 'B', "14/06/2020");
        patients.add(patient);
        patient = new Patient("Kevim", 37, 'D', "05/06/2020");
        patients.add(patient);
        patient = new Patient("Tom", 67, 'D', "20/06/2020");
        patients.add(patient);
        patient = new Patient("Bob", 74, 'A', "04/07/2020");
        patients.add(patient);
        patient = new Patient("Rachel", 48, 'C', "24/06/2020");
        patients.add(patient);
        patient = new Patient("Thomas", 21, 'C', "11/06/2020");
        patients.add(patient);
        patient = new Patient("Mary", 17, 'D', "21/07/2020");
        patients.add(patient);
        patient = new Patient("Smith", 89, 'A', "07/08/2020");
        patients.add(patient);
        patient = new Patient("Pearson", 47, 'B', "04/06/2020");
        patients.add(patient);
        patient = new Patient("Anderson", 62, 'B', "27/07/2020");
        patients.add(patient);
        patient = new Patient("Johnson", 10, 'D', "01/08/2020");
        patients.add(patient);
        patient = new Patient("Robertz", 50, 'A', "09/08/2020");
        patients.add(patient);
        patient = new Patient("Julie", 86, 'B', "02/05/2020");
        patients.add(patient);
        patient = new Patient("Edith", 42, 'D', "07/06/2020");
        patients.add(patient);
        patient = new Patient("John", 95, 'D', "01/06/2020");
        patients.add(patient);
    }

}
