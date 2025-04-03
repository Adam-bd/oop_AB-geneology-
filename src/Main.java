import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person kacper = new Person("Kacper", "Kowalski", LocalDate.of(2000, 11, 27));
        Person adam = new Person("Adam", "Nowak", LocalDate.of(2002, 3, 15));
        Person anna = new Person("Anna", "Dąbrowska", LocalDate.of(2005, 5, 31));
        Person franek = new Person("Franek", "Kowalski", LocalDate.of(2024, 1, 10));
        Person kamil = new Person("Kamil", "Kowalski", LocalDate.of(2025, 3, 20));

        personList.add(kacper);
        personList.add(adam);
        personList.add(anna);

        for(Person person: personList){
            System.out.println(person);
        }

        kacper.adopt(kamil);
        System.out.println("Adopcja: " + kacper.adopt(franek));

        Person youngest = kacper.getYoungestChild();
        System.out.println("Najmłodsze dziecko Kacpra: " + (youngest != null ? youngest : "Brak dzieci"));

        System.out.println("Dzieci Kacpra posortowane od najstarszego do najmłodszego:");
        for (Person child : kacper.getChildren()) {
            System.out.println(child);
        }

        Family family = new Family();
        family.add(kacper);
        family.add(anna);
        family.add(kacper,franek);


        Person jan2 = new Person("Jan", "Kowalski", LocalDate.of(1985, 3, 10));
        family.add(jan2);
        System.out.println("Wyszukane osoby o imieniu Jan Kowalski:");
        for (Person p : family.get("Jan Kowalski")) {
            System.out.println(p);
        }
    }
}