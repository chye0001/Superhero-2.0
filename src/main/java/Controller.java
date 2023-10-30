import java.util.ArrayList;

public class Controller {

    Superhero superhero = new Superhero();
    Database database = new Database();

    public Controller() {
    }

    public void createSuperhero(String name, String realName, String superPower,
                                int yearCreated, Boolean isHuman, double strength) {
        database.addSuperhero(name, realName, superPower, yearCreated, isHuman, strength);
    }

    public String listOfSuperheros(){
        return database.listOfSuperhero();
    }

    public ArrayList<Superhero> searchSuperhero(String search){
        return database.searchSuperhero(search);
    }

    public boolean editSuperheroList(String newName, String newRealName, String newSuperpower, String newYearCreated, String newIsHuman, String newStrength, int userChoise){
        return database.editSuperheroList(newName, newRealName, newSuperpower, newYearCreated, newIsHuman, newStrength, userChoise);
    }

    public String deleteSuperhero(int userChoise) {
        return database.deleteSuperhero(userChoise);
    }

    public ArrayList<Object> getChosenSuperheroToEdit(int userChoise) {
        return database.getChosenSuperheroToEdit(userChoise); //Hjælpe metode til deleteSuperhero()
    }
    public ArrayList<Superhero> getSuperheroList() {
        return database.getSuperheroList();
    }
}
