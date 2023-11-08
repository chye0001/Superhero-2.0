import domain_model.Database;
import domain_model.Superhero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    Database database;

    @BeforeEach
    void setup(){
        database = new Database();
    }

    @Test
    void addSuperhero() throws FileNotFoundException {
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);

        int expectedSize = 1;
        int actualSize = database.getSize();

        assertEquals(expectedSize, actualSize);
    }

   @Test
    void addMultipleSuperheros() throws FileNotFoundException{
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900);

        int expectedSize = 2;
        int actualSize = database.getSize();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void seeListOfSuperheros() throws FileNotFoundException{
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800.0);

        String expectedList = "1: Ant man, Scott Lang, Size-shifting, 1962, true, 800.0\n";
        String actualList = database.listOfSuperhero();
        assertEquals(expectedList, actualList);
    }

    @Test
    void noSearchResultsReachedSuperhero() throws FileNotFoundException{
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900);

        ArrayList<Superhero> actualSearchResult = database.searchSuperhero("Thor"); //database returns null, if the search input does not match any hero
        assertNull(actualSearchResult);
    }

    @Test
    void searchSuperhero() throws FileNotFoundException{
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900);

        int expectedSearchResult = 1;
        int actualSearchResult = database.searchSuperhero("BatMan").size();
        assertEquals(expectedSearchResult, actualSearchResult);
    }

    @Test
    void searchForMultipleSuperheros() throws FileNotFoundException {
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900);

        int expectedSearchResult = 3;
        int actualSearchResult = database.searchSuperhero("Man").size();
        assertEquals(expectedSearchResult, actualSearchResult);
    }

    @Test
    void editSuperhero() throws FileNotFoundException{
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);                           // hero 1
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);                            // hero 2
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900); // hero 3

        int userChoise = 1;

        boolean expectedEdit = true;
        boolean actualEdit = database.editSuperheroList("",         // Name
                "",                                                 // realName
                "Has lots of money, and above avg. intelligence",   // superpower
                "",                                                 // yearCreated
                "",                                                 // isHuman
                "10000",                                            // strength
                userChoise);
        assertEquals(expectedEdit, actualEdit);
    }

    @Test
    void noEditToSuperhero() throws FileNotFoundException{
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);                           // hero 1
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);                            // hero 2
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900); // hero 3

        int userChoise = 2;

        boolean expectedEdit = false;
        boolean actualEdit = database.editSuperheroList("",         // Name
                "",                                                 // realName
                "",                                                 // superpower
                "",                                                 // yearCreated
                "",                                                 // isHuman
                "",                                                 // strength
                userChoise);
        assertEquals(expectedEdit, actualEdit);
    }

    @Test
    void deleteMultipleSuperheros() throws FileNotFoundException{
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);                           // hero 1
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);                            // hero 2
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900); // hero 3

        database.deleteSuperhero(1); // delete works by selecting a hero from a menu to delete, therefore you cannot delete using the hero's index.
        database.deleteSuperhero(1);

        int expectedSize = 1;
        int actualSize = database.getSize();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void noSuperheroDeleted() throws FileNotFoundException{
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);                           // hero 1
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);                            // hero 2
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900); // hero 3

        database.deleteSuperhero(0); // delete works by selecting a hero from a menu to delete, therefore you cannot delete using the hero's index.
                                     // if user don't wish to delete any hero, the user must input 0.
        int expectedSize = 3;
        int actualSize = database.getSize();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    void sortSuperheroList() throws FileNotFoundException {
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);                           // hero 1
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);                            // hero 2
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900); // hero 3

        //Attributes to sort by:
        //1. name
        //2. realName
        //3. superPower
        //4. yearCreated
        //5. isHuman
        //6. strength

        boolean expectedSort = true;
        boolean actualSort = database.sortSuperheroList(1, 6);
        assertEquals(expectedSort, actualSort);
    }

    @Test
    void noSortingOfSuperheroList() throws FileNotFoundException {
        database.addSuperhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);                           // hero 1
        database.addSuperhero("Ant man", "Scott Lang", "Size-shifting", 1962, true, 800);                            // hero 2
        database.addSuperhero("Batman", "Bruce Wayne", "Knows martial-arts and has lots of money", 1915, true, 900); // hero 3

        //Attributes to sort by:
        //1. name
        //2. realName
        //3. superPower
        //4. yearCreated
        //5. isHuman
        //6. strength

        boolean expectedSort = false;
        boolean actualSort = database.sortSuperheroList(-2, 8); //no sorting occurs when user inputs numbers outside the menu-scope.
        assertEquals(expectedSort, actualSort);
    }
}