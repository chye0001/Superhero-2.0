package datasource;

import domain_model.Superhero;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final ArrayList<Superhero> LOADED_FILE = new ArrayList<>();

    public void saveSuperhero(ArrayList<Superhero> listOfHeroesToSave, File fileToSaveto) throws FileNotFoundException {
        PrintStream saveToFile = new PrintStream(fileToSaveto);

        for (Superhero superhero : listOfHeroesToSave) {
            saveToFile.println(superhero.getName() + ";" +
                    superhero.getRealName() + ";" +
                    superhero.getSuperpower() + ";" +
                    superhero.getYearCreated() + ";" +
                    superhero.getIsHuman() + ";" +
                    superhero.getStrength());
        }

    }

    public ArrayList<Superhero> loadListOfSuperhero(File fileToLoadFrom) throws FileNotFoundException {
        Scanner loadFromFile = new Scanner(fileToLoadFrom);

        while (loadFromFile.hasNext()) {
            String linje = loadFromFile.nextLine();
            String[] attributter = linje.split(";");

            Superhero superheroToAdd;
            superheroToAdd = new Superhero(attributter[0].trim(),
                    attributter[1].trim(),
                    attributter[2].trim(),
                    Integer.parseInt(attributter[3].trim()),
                    Boolean.parseBoolean(attributter[4].trim()),
                    Double.parseDouble(attributter[5].trim()));

            if (!LOADED_FILE.contains(superheroToAdd)){
                LOADED_FILE.add(superheroToAdd);
            }
        }
        loadFromFile.close();
        return LOADED_FILE;
    }

}
