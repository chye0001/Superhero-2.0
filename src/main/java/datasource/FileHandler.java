package datasource;

import domain_model.Superhero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final File CSVFile = new File("superheroDatabase.csv");
    private final ArrayList<Superhero> LOADED_FILE = new ArrayList<>();

    public void saveSuperhero(ArrayList<Superhero> listOfHeroesToSave) throws FileNotFoundException {
        PrintStream saveToFile = new PrintStream(CSVFile);

        for (Superhero hero : listOfHeroesToSave) {
            saveToFile.println(hero.getName() + "," +
                    hero.getRealName() + "," +
                    hero.getSuperpower() + "," +
                    hero.getYearCreated() + "," +
                    hero.getIsHuman() + "," +
                    hero.getStrength());
        }
    }

    public ArrayList<Superhero> loadListOfSuperhero() throws FileNotFoundException {
        Scanner loadFromFile = new Scanner(CSVFile);

        while (loadFromFile.hasNext()) {
            String linje = loadFromFile.nextLine();
            String[] attributter = linje.split(",");

            Superhero superheroToAdd;
            superheroToAdd = new Superhero(attributter[0].trim(),
                    attributter[1].trim(),
                    attributter[2].trim(),
                    Integer.parseInt(attributter[3].trim()),
                    Boolean.parseBoolean(attributter[4].trim()),
                    Integer.parseInt(attributter[5].trim()));

            LOADED_FILE.add(superheroToAdd);
        }
        loadFromFile.close();
        return LOADED_FILE;
    }

}
