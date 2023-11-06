import datasource.FileHandler;
import domain_model.Database;
import domain_model.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Database db = new Database();
    FileHandler fileHandler = new FileHandler();
    ArrayList<Superhero> superheroes = new ArrayList<>();
    File testCSVFile = new File("superheroDatabase.csv");
/*
    //Udføres én gang før hver test
    @BeforeEach
    void setUp() {
        Superhero ironMan = new Superhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);
        superheroes.add(ironMan);

        try {
            fileHandler.saveSuperhero(superheroes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/
    @Test
    void addSuperhero() {
        //startSize er 0 da der ikke er persistet nongle superheros endnu.
        //int startSize = 0;
        Superhero captainAmerica = new Superhero("Captain America", "Steve Rogers", "Superhuman Strength", 1941, true, 999);
        superheroes.add(captainAmerica);
        try {
            fileHandler.saveSuperhero(superheroes, testCSVFile);
        }catch (Exception e){
            e.printStackTrace();
        }

        //expectedSize bliver 1 efter vi har added captain america.
        int expectedSize = 1;
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);

    }

    @Test
    void addMultipleSuperheros() {
        //startSize er 1 da der blev added 1 superheros, tidligere.
        //int startSize = 1;
        Superhero captainAmerica2 = new Superhero("Captain America2", "Steve Rogers2", "Superhuman Strength", 1941, true, 999);
        Superhero karl = new Superhero("Karl", "KarlJones", "Fly", 2001, true, 12);
        superheroes.add(captainAmerica2);
        superheroes.add(karl);
        try {
            fileHandler.saveSuperhero(superheroes, testCSVFile);
        }catch (Exception e){
            e.printStackTrace();
        }

        int expectedSize = 3;
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void deleteSuperhero(){
        //Der starter med at være 3 superhelte i databasen, da vi tidligere har added 3 heros ialt:

        //bruger vælger at slette den første superhelt.
        int userChoice = 1;
        int expectedSize = 2;

        try {
            db.deleteSuperhero(userChoice);
        }catch (Exception e){
            e.printStackTrace();
        }

        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void deleteMultipleSuperheros() {
        //Der er 3 superheros i databasen, da vi tilføjer en eksta efter at have fjernet en superhero tidligere.

        Superhero superman = new Superhero("Superman", "Clark Kent", "Superhuman strength, laser eyes, fly", 1958, false, 99999);
        superheroes.add(superman);
        try {
            db.deleteSuperhero(0);
            db.deleteSuperhero(1);
            fileHandler.saveSuperhero(superheroes, testCSVFile);
        }catch (Exception e){
            e.printStackTrace();
        }

        int expectedSize = 1;
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void noSuperheroDeleted(){

        int userChoise = 0;
        try {
            db.deleteSuperhero(userChoise);
        }catch (Exception e){
            e.printStackTrace();
        }

        int expectedSize = 1;
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    /*
    @Test
    void noSearchResultsReachedSuperhero() {
        int expectedSize = 0;

        int searchResult = db.searchSuperhero("").size();
        int actualSize = 0;

        assertEquals(expectedSize, searchResult);
        //TODO generel fejl med searchmetoden .
    }

    @Test
    void searchSuperhero() {
        //Vi kigger efter size fordi når vi søger på en superhelt, tilføjes denne til en arraylist.
        //Og vi ved at vi har netop 1 i denne arraylist fordi vi kun har en iron man i listen.
        //Derfor når vi søger skal listen have en størrelse på 1.
        int expectedSize = 1;

        ArrayList<Superhero> searchResult = db.searchSuperhero("Iron Man");
        int actualSize = searchResult.size();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void searchForMultipleSuperheros() {
        db.addSuperhero("Superman", "Clark Kent", "Superhuman strength, laser eyes, fly", 1958, false, 99999);
        int expectedSize = 2;

        ArrayList<Superhero> searchResult = db.searchSuperhero("man");
        int actualSize = searchResult.size();

        assertEquals(expectedSize, actualSize);
    }

     */


}