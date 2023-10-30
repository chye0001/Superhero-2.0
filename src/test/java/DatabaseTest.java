import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Database db = new Database();
    Superhero ironMan = new Superhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);
    //Udføres én gang før hver test
    /*
    @BeforeEach
    void setUp() {
        db.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);
    }
     */

    @Test
    void getSuperheroList() {
        ArrayList<Superhero> expectedList = new ArrayList<Superhero>(List.of(new Superhero ("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800), new Superhero("Captain America", "Steve Rogers", "Superhuman Strength", 1941, true, 999)));
        ArrayList<Superhero> actualList = db.getSuperheroList();

        assertEquals(expectedList, actualList);
        //assertSame(expectedList, actualList);
    }

    @Test
    void addSuperhero() {
        //startSize er 2 fordi der allerede er hardcoded 2 superheros.
        int startSize = 2;
        db.addSuperhero("Captain America", "Steve Rogers", "Superhuman Strength", 1941, true, 999);

        //expectedSize bliver 2 efter vi har added captain america.
        int expectedSize = 3;
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void addMultipleSuperheros() {
        //startSize er 2 fordi der allerede er hardcoded 2 superheros.
        int startSize = 2;
        db.addSuperhero("Captain America", "Steve Rogers", "Superhuman Strength", 1941, true, 999);
        db.addSuperhero("Karl", "KarlJones", "Fly", 2001, true, 12);

        int expectedSize = 4;
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void deleteSuperhero(){
        //Der starter med at være 3 superhelte i databasen, fordi der allerede er hardcoded 2 superheros, og vi ligger en ekstra ind forneden:
        db.addSuperhero("Karl", "KarlJones", "Fly", 2001, true, 12);

        //bruger vælger at slette den første superhelt.
        int userChoice = 1;
        int expectedSize = 2;

        db.deleteSuperhero(userChoice);
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void deleteMultipleSuperheros() {
        db.addSuperhero("Karl", "KarlJones", "Fly", 2001, true, 12); //Befinder sig på index plads 1.
        db.addSuperhero("Superman", "Clark Kent", "Superhuman strength, laser eyes, fly", 1958, false, 99999); //Index plads 2.

        int expectedSize = 2;
        db.deleteSuperhero(3); // deletes Superman
        db.deleteSuperhero(2); // deletes Karl
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void noSuperheroDeleted(){

        int userChoise = 0;
        db.deleteSuperhero(userChoise);

        int expectedSize = 2;
        int actualSize = db.getSize();

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void noSearchResultsReachedSuperhero() {
        int expectedSize = 0;

        ArrayList<Superhero> searchResult = db.searchSuperhero("");
        int actualSize = 0;

        assertEquals(expectedSize, actualSize);
    }

    @Test
    void searchSuperhero() {
        //Vi kigger efter size fordi når vi søger på en superhelt, tilføjes denne til en arraylist.
        //og vi ved at vi har netop 1 i denne arraylist fordi vi kun har en iron man i listen.
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
}