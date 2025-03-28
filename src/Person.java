import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Person implements Comparable<Person>{
    private String name;
    private String surname;
    private LocalDate birthDate;
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

    public Person(String name, String surname, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        children = new HashSet<>();
    }

    @Override
    public String toString() {
        return name + " " + surname + ", urodzony: " + birthDate;
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
}
