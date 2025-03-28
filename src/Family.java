import java.util.HashMap;

public class Family {
    HashMap<String, Person> familyMembers;

    public Family() {
        this.familyMembers = new HashMap<>();
    }

    public void add(Person... person){
        familyMembers.put(person.getName() + " " + person.getSurname(), person);
    }
    public Person get(String string){
        return familyMembers.get(string);
    }
}
