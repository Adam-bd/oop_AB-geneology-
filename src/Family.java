import java.util.HashMap;

public class Family {
    HashMap<String, Person> familyMembers;

    public Family() {
        this.familyMembers = new HashMap<>();
    }

    public void add(Person... person){
        for(Person p: person){
            familyMembers.put(p.getName() + ' ' + p.getSurname(), p);
        }
//        familyMembers.put(person.getName() + " " + person.getSurname(), person);
    }
    public Person get(String string){
        return familyMembers.get(string);
    }
}
