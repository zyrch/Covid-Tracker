import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String name;
    private int age;
    private char tower;
    private LocalDate dateOfReporting;
    private LocalDate dateOfRecovery;

    public Patient(String name, int age, char tower, LocalDate dateOfReporting) {
        this.name = name;
        this.age = age;
        this.tower = tower;
        this.dateOfReporting = dateOfReporting;
        this.dateOfRecovery = this.dateOfReporting.plusDays(21);
    }

    public Patient(String name, int age, char tower, String dateOfReporting) {
        this.name = name;
        this.age = age;
        this.tower = tower;
        this.setDateOfReporting(dateOfReporting);
        this.dateOfRecovery = this.dateOfReporting.plusDays(21);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getTower() {
        return tower;
    }

    public void setTower(char tower) {
        this.tower = tower;
    }

    public LocalDate getDateOfReporting() {
        return dateOfReporting;
    }

    public void setDateOfReporting(LocalDate dateOfReporting) {
        this.dateOfReporting = dateOfReporting;
    }

    public void setDateOfReporting(String dateOfReporting) {
        this.dateOfReporting = LocalDate.parse(dateOfReporting, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public LocalDate getDateOfRecovery() {
        return dateOfRecovery;
    }

    public void setDateOfRecovery(LocalDate dateOfRecovery) {
        this.dateOfRecovery = dateOfRecovery;
    }
}


