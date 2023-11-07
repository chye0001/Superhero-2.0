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

    FileHandler fileHandler = new FileHandler();
    File testCSVFile = new File("TestDatabase.csv");

    @Test
    void addSuperhero() {
        ArrayList<Superhero> superheroes = new ArrayList<>(List.of(new Superhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800)));

        int expectedSize = 1;
        int actualSize = 0;
        try{
            fileHandler.saveSuperhero(superheroes, testCSVFile);
            actualSize = fileHandler.loadListOfSuperhero(testCSVFile).size();
        }catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(expectedSize, actualSize);
    }


    @Test
    void addMultipleSuperheros() {
        ArrayList<Superhero> superheroes = new ArrayList<>(List.of(new Superhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800),
                new Superhero("Batman", "Bruce Wayne", "Knows martialsart and has lots of money", 1915, true, 900)));

        int expectedSize = 2;
        int actualSize = 0;
        try{
            fileHandler.saveSuperhero(superheroes, testCSVFile);
            actualSize = fileHandler.loadListOfSuperhero(testCSVFile).size();
        }catch (Exception e){
            e.printStackTrace();
        }
        assertEquals(expectedSize, actualSize);
    }

}