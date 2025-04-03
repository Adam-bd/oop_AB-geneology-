import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Person implements Comparable<Person>{
    private String name;
    private String surname;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private HashSet<Person> children;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDeathDate() {
        return deathDate;
    }
    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public Person(String name, String surname, LocalDate birthDate, LocalDate deathDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        children = new HashSet<>();
    }

    @Override
    public String toString() {
        return name + " " + surname + ", urodzony: " + birthDate + ", zgon: " + deathDate;
    }

    public boolean adopt(Person child){
        return children.add(child);
    }

    public Person getYoungestChild(){
        Person youngest = null;
        for(Person child: children){
            if(youngest == null || child.compareTo(youngest) > 0/*child.birthDate.isAfter(youngest.birthDate)*/){
                youngest = child;
            }
        }
        return youngest;
    }

    public List<Person> getChildren(){
        List<Person> getChildren = new ArrayList<>(children);
        Collections.sort(getChildren);
        return getChildren;
    }

    @Override
    public int compareTo(Person o) {
        return this.birthDate.compareTo(o.birthDate);
    }

    public static Person fromCsvLine(String line){
        String[] dane = line.split(",");
        String[] nameAndSurname = dane[0].split(" ");
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthDate = LocalDate.parse(dane[1], formatted);
        LocalDate deathDate = null;
        if(!dane[2].isEmpty()) {
            deathDate = LocalDate.parse(dane[2], formatted);
        }
        return new Person(nameAndSurname[0], nameAndSurname[1], birthDate, deathDate);
    }

    void checkLifespanException() throws NegativeLifespanException {
        if(deathDate != null && (birthDate.isAfter(deathDate))){
            throw new NegativeLifespanException(this);
        }
    }

    public static List<Person> fromCsv(String filePath) throws IOException {
        FileReader file = new FileReader(filePath);
        BufferedReader reader = new BufferedReader(file);
        String line = reader.readLine();
        line = reader.readLine();
        List<Person> personList = new ArrayList<>();
        while(line != null){
            Person person = Person.fromCsvLine(line);
            try {
                person.checkLifespanException();
            } catch (NegativeLifespanException e) {
                System.err.println(e.getMessage());
            }
            personList.add(person);
            line = reader.readLine();
        }
        return personList;
    }


}
