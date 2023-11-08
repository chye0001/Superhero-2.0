package domain_model;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {

    Database database = new Database();

    public Controller() {
        database.loadDatabase();
    }

    public void createSuperhero(String name, String realName, String superPower,
                                int yearCreated, Boolean isHuman, double strength) throws FileNotFoundException {
        database.addSuperhero(name, realName, superPower, yearCreated, isHuman, strength);
    }

    public String listOfSuperheros() {
        return database.listOfSuperhero();
    }

    public ArrayList<Superhero> searchSuperhero(String search){
        return database.searchSuperhero(search);
    }

    public boolean editSuperheroList(String newName,
                                     String newRealName,
                                     String newSuperpower,
                                     String newYearCreated,
                                     String newIsHuman,
                                     String newStrength,
                                     int userChoise) throws FileNotFoundException{
        return database.editSuperheroList(newName, newRealName, newSuperpower, newYearCreated, newIsHuman, newStrength, userChoise);
    }

    public String deleteSuperhero(int userChoise) throws FileNotFoundException {
        return database.deleteSuperhero(userChoise);
    }

    public boolean sortSuperheroList(int primaryChoise, int secondaryChoise) {
       return database.sortSuperheroList(primaryChoise, secondaryChoise);
    }

    public String sortedSuperheroList() {
        return database.sortedSuperheroList();
    }

    public ArrayList<Object> getChosenSuperheroToEdit(int userChoise) {
        return database.getChosenSuperheroToEdit(userChoise); //Hjælpe metode til deleteSuperhero()
    }
    public ArrayList<Superhero> getSuperheroList(){
        return database.getSuperheroList();
    }
}
