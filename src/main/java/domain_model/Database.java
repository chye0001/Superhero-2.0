package domain_model;
import datasource.FileHandler;
import domain_model.comparators.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Database {

    private ArrayList<Superhero> superheroList;
    private final Scanner sc = new Scanner(System.in);
    private final FileHandler FILE_HANDLER = new FileHandler();
    private final File CSVFile = new File("superheroDatabase.csv");

    public Database() {
        try {
            this.superheroList = FILE_HANDLER.loadListOfSuperhero();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Get-methods
    public int getSize() {
        try {
            superheroList = FILE_HANDLER.loadListOfSuperhero();
        }catch (Exception e){
            e.printStackTrace();
        }

        return superheroList.size();
    }

    /* original getSize-metode.
    public int getSize() {
        return superheroList.size();
    }
     */


    //Denne metode anvendes til at få fat i listen med superhelte i klassen UserInterface. UserInterface, og anvendes til at printe "fejlmeddeles", hvis listen er tom.
    public ArrayList<Superhero> getSuperheroList() {
        return superheroList;
    }

    public ArrayList<Object> getChosenSuperheroToEdit(int userChoise) {
        Superhero ChosenSuperheroToEdit = superheroList.get(userChoise - 1);
        ArrayList<Object> ChosenSuperheroToEditAttributes = new ArrayList<>();

        ChosenSuperheroToEditAttributes.add(ChosenSuperheroToEdit.getName());
        ChosenSuperheroToEditAttributes.add(ChosenSuperheroToEdit.getRealName());
        ChosenSuperheroToEditAttributes.add(ChosenSuperheroToEdit.getSuperpower());
        ChosenSuperheroToEditAttributes.add(ChosenSuperheroToEdit.getYearCreated());
        ChosenSuperheroToEditAttributes.add(ChosenSuperheroToEdit.getIsHuman());
        ChosenSuperheroToEditAttributes.add(ChosenSuperheroToEdit.getStrength());

        return ChosenSuperheroToEditAttributes;
    }


    //Service-methods
    public void addSuperhero(String name, String realName, String superPower,
                             int yearCreated, Boolean isHuman, double strength) throws FileNotFoundException {
        Superhero newSuperhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);

        superheroList.add(newSuperhero);
        FILE_HANDLER.saveSuperhero(superheroList, CSVFile);
    }

    public String listOfSuperhero() {

        Collections.sort(superheroList, new NameComparator());

        int count = 1;
        StringBuilder sb = new StringBuilder();

        for (Superhero superhero : superheroList) {
            sb.append(count++)
              .append(": ")
              .append(superhero.getName())
              .append(", ")
              .append(superhero.getRealName())
              .append(", ")
              .append(superhero.getSuperpower())
              .append(", ")
              .append(superhero.getYearCreated())
              .append(", ")
              .append(superhero.getIsHuman())
              .append(", ")
              .append(superhero.getStrength())
              .append("\n");
        }
        return sb.toString();
    }

    public ArrayList<Superhero> searchSuperhero(String search) { //TODO - fix search so that it works properly
        ArrayList<Superhero> searchResults = new ArrayList<>();
        for (Superhero superhero : superheroList) {
            if (superhero.getName().toLowerCase().contains(search.toLowerCase()) || superhero.getRealName().toLowerCase().contains(search.toLowerCase())) {
                if (!searchResults.contains(superhero)) {
                    searchResults.add(superhero);
                }
            }
        }
        if (searchResults.isEmpty()) {
            return null;

        } else
            return searchResults;
    }

    public boolean editSuperheroList(String newName,
                                     String newRealName,
                                     String newSuperpower,
                                     String newYearCreated,
                                     String newIsHuman,
                                     String newStrength,
                                     int userChoise) throws FileNotFoundException{

        Superhero chosenSuperheroToEdit = superheroList.get(userChoise - 1);

        if (!newName.isEmpty()) {
            chosenSuperheroToEdit.setName(newName);
        }
        if (!newRealName.isEmpty()) {
            chosenSuperheroToEdit.setRealName(newRealName);
        }
        if (!newSuperpower.isEmpty()) {
            chosenSuperheroToEdit.setSuperPower(newSuperpower);
        }
        if (!newYearCreated.isEmpty()) {
            chosenSuperheroToEdit.setYearCreated(Integer.parseInt(newYearCreated));
        }
        if (!newIsHuman.isEmpty()) {
            chosenSuperheroToEdit.setIsHuman(Boolean.parseBoolean(newIsHuman));
        }
        if (!newStrength.isEmpty()) {
            chosenSuperheroToEdit.setStrength(Double.parseDouble(newStrength));
        }


        if (!newName.isEmpty() || !newRealName.isEmpty() ||
                !newSuperpower.isEmpty() || !newYearCreated.isEmpty() ||
                !newIsHuman.isEmpty() || !newStrength.isEmpty()) {

            FILE_HANDLER.saveSuperhero(superheroList, CSVFile);

            return true;
        } else {
            return false;
        }
    }

    public String deleteSuperhero(int userChoice) throws FileNotFoundException{
        if (userChoice == 0) {
            return "No superheros were deleted";

        } else if (userChoice > 0 && userChoice <= superheroList.size()) {
            superheroList.remove(userChoice - 1);
            FILE_HANDLER.saveSuperhero(superheroList, CSVFile);
            return "\nThe superhero was deleted from your superhero list.";

        } else {
            while (userChoice > superheroList.size() || userChoice < 0) {
                System.out.print("You have to choose within the list size: ");
                userChoice = sc.nextInt();
            }
            if (userChoice == 0) {
                return "No superheros were deleted";

            }
            superheroList.remove(userChoice - 1);
            FILE_HANDLER.saveSuperhero(superheroList, CSVFile);

            return "\nThe superhero was deleted from your superhero list.\nWould you like to delete another superhero?";
        }
    }

    public Comparator primaryComparator(int primaryChoise){
        switch (primaryChoise) {
            case 1 -> {return new NameComparator();}
            case 2 -> {return new RealNameComparator();}
            case 3 -> {return new SuperPowerComparator();}
            case 4 -> {return new YearCreatedComparator();}
            case 5 -> {return new IsHumanComparator();}
            case 6 -> {return new StrengthComparator();}
            default -> {return null;}
        }
    }

    public Comparator secondaryComparator(int secondaryChoise){
        switch (secondaryChoise) {
            case 1 -> {
                return new NameComparator();
            }
            case 2 -> {
                return new RealNameComparator();
            }
            case 3 -> {
                return new SuperPowerComparator();
            }
            case 4 -> {
                return new YearCreatedComparator();
            }
            case 5 -> {
                return new IsHumanComparator();
            }
            case 6 -> {
                return new StrengthComparator();
            }
            default -> {
                return null;
            }
        }
    }

    public String sortSuperheroList(int choise1, int choise2){

        Comparator primaryChoise = primaryComparator(choise1);
        Comparator secondaryChoise = secondaryComparator(choise2);

        if (primaryChoise == null || secondaryChoise == null){
            return "null";

        } else {
            Collections.sort(superheroList, primaryChoise.thenComparing(secondaryChoise));

            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (Superhero superhero : superheroList) {
                sb.append(count++)
                        .append(": ")
                        .append(superhero.getName())
                        .append(", ")
                        .append(superhero.getRealName())
                        .append(", ")
                        .append(superhero.getSuperpower())
                        .append(", ")
                        .append(superhero.getYearCreated())
                        .append(", ")
                        .append(superhero.getIsHuman())
                        .append(", ")
                        .append(superhero.getStrength())
                        .append("\n");
            }
            return "\nList has been sorted\n" + sb;
        }
    }

    public String toString() {
        StringBuilder list = new StringBuilder();
        for (Superhero superhero : superheroList) {
            list.append("domain_model.Superhero" + "\nName: ").append(superhero.getName())
                    .append("\nReal name: ").append(superhero.getRealName())
                    .append("\nSuperpower: ").append(superhero.getSuperpower())
                    .append("\nYear created: ").append(superhero.getYearCreated())
                    .append("\nIs human: ");

            if (superhero.getIsHuman()) {
                list.append("Yes");
            } else {
                list.append("No");
            }
            list.append("\nStrength: ").append(superhero.getStrength()).append("\n\n");
        }
        return list.toString();
    }
}
