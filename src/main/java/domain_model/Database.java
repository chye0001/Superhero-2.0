package domain_model;
import datasource.FileHandler;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    private ArrayList<Superhero> superheroList;
    private final Scanner sc = new Scanner(System.in);
    private FileHandler fileHandler = new FileHandler();

    //public Database(){}

    //Midlertidig hardkodet superhelte så jeg ikke konstant skal oprette nye superheros når jeg vil teste koden.
    /*
    public Database() {
        Superhero ironMan = new Superhero("Iron Man", "Tony Stark", "Has big brain", 2005, true, 800);
        Superhero captainAmerica = new Superhero("Captain America", "Steve Rogers", "Superhuman Strength", 1941, true, 999);
        superheroList.add(ironMan);
        superheroList.add(captainAmerica);
    }
    */
    public Database() {
        try {
            this.superheroList = fileHandler.loadListOfSuperhero();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //Get-methods
    public int getSize() {
        return superheroList.size();
    }

    //Denne metode anvendes til at få fat i listen med superhelte i klassen userinterface.UserInterface, og anvendes til at printe "fejlmeddeles", hvis listen er tom.
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
                             int yearCreated, Boolean isHuman, double strength) {
        Superhero newSuperhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
        superheroList.add(newSuperhero);
    }

    public String listOfSuperhero() {
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

    public ArrayList<Superhero> searchSuperhero(String search) {
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

    public boolean editSuperheroList(String newName, String newRealName, String newSuperpower, String newYearCreated, String newIsHuman, String newStrength, int userChoise) {
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
            chosenSuperheroToEdit.setStrength(Integer.parseInt(newStrength));
        }
        if (!newName.isEmpty() || !newRealName.isEmpty() ||
                !newSuperpower.isEmpty() || !newYearCreated.isEmpty() ||
                !newIsHuman.isEmpty() || !newStrength.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String deleteSuperhero(int userChoice) {
        if (userChoice == 0) {
            return "No superheros were deleted";

        } else if (userChoice > 0 && userChoice <= superheroList.size()) {
            superheroList.remove(userChoice - 1);
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
            return "\nThe superhero was deleted from your superhero list.\nWould you like to delete another superhero?";
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
