package userinterface;
import domain_model.Controller;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private final Scanner sc = new Scanner(System.in);
    Controller controller = new Controller();

    public UserInterface() {
    }

    public void startProgram() {
        int choice;

        System.out.println("Welcome to the Superhero Universe!");

        do {
            System.out.print("\n1. Create superhero\n2. See list of superheros\n3. Search for a superhero \n4. Edit list of superheros\n5. Delete a superhero\n6. End program\nChoose: ");

            while (!sc.hasNextInt()) {
                System.out.print("You must enter a number: ");
                sc.nextLine();
            }

            choice = sc.nextInt();
            System.out.println();

            if (choice == 1) {
                createSuperhero();

            } else if (choice == 2) {
                listOfSuperheros();

            } else if (choice == 3) {
                searchSuperhero();

            } else if (choice == 4) {
                editList();

            } else if (choice == 5) {
                deleteSuperhero();

            } else if (choice == 6) {
                sortSuperheroList();

            } else if (choice == 7) {
                endProgram();

            } else
                System.out.println("Choose a number from the menu");
        } while (choice != 7);
    }

    public void createSuperhero() {
        System.out.print("Add name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Add real name: ");
        String realName = sc.nextLine();

        System.out.print("Add superpower: ");
        String superPower = sc.nextLine();

        System.out.print("Add year created: ");
        while (!sc.hasNextInt()) {
            System.out.print("You must enter a number: ");
            //Linjen efter skal være af sc.next(); metoden, da denne metode tager alle former for input.
            //Hvis det var sc.nextInt(); ville programmet crashe, hvis man inputer andet end integers, da denne scanner ikke kan indlæse andet end integer.
            sc.next();
        }
        int yearCreated = sc.nextInt();

        System.out.print("Add isHuman: ");
        while (!sc.hasNextBoolean()) {
            System.out.print("You must precisely type true or false: ");
            sc.next();
        }
        Boolean isHuman = sc.nextBoolean();

        System.out.print("Add strength: ");
        while (!sc.hasNextDouble()) {
            System.out.print("You must enter a number: ");
            sc.next();
        }
        double strength = sc.nextDouble();

        try {
            controller.createSuperhero(name, realName, superPower, yearCreated, isHuman, strength);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Superhero added to list, would you like to create a new superhero?");
    }

    public void listOfSuperheros() {
        if (controller.getSuperheroList().isEmpty()) {
            System.out.println("The database is empty");
        } else
            System.out.println(controller.listOfSuperheros());
        System.out.println("Would you like to see the list again?");
    }

    public void searchSuperhero() {
        System.out.print("Input search: ");
        sc.nextLine();
        String search = sc.nextLine();

        if (controller.searchSuperhero(search) == null) {
            System.out.println("There was no superhero matching your search input");

        } else
            System.out.println(controller.searchSuperhero(search));
        System.out.println("Would you like to search again?");
    }

    public void editList() {
        if (controller.getSuperheroList().isEmpty()) {
            System.out.println("The superhero list is empty, therefore there are no superheros in the list to edit");
            return;

        } else if (!controller.getSuperheroList().isEmpty()) {
            System.out.println(controller.listOfSuperheros());
        }

        System.out.print("Choose a superhero from the list to edit: ");

        //This block of code forces user to input a number.
        while (!sc.hasNextInt()) {
            System.out.print("You must enter a number from the list: ");
            sc.nextLine();
        }
        int userChoise = sc.nextInt();

        ArrayList<Object> chosenSuperheroToEdit = controller.getChosenSuperheroToEdit(userChoise);

        System.out.println("\nThe chosen superhero to edit:\n" + chosenSuperheroToEdit);
        System.out.println("\nEdit data and press enter. If you do not wish to edit, just press enter to continue.\n");

        System.out.println("Name: " + chosenSuperheroToEdit.get(0));
        sc.nextLine();
        String newName = sc.nextLine();

        System.out.println("Real name: " + chosenSuperheroToEdit.get(1));
        String newRealName = sc.nextLine();

        System.out.println("Superpower: " + chosenSuperheroToEdit.get(2));
        String newSuperpower = sc.nextLine();

        System.out.println("Year created: " + chosenSuperheroToEdit.get(3));
        String newYearCreated = sc.nextLine();

        System.out.println("Is human: " + chosenSuperheroToEdit.get(4));
        String newIsHuman = sc.nextLine();

        System.out.println("Strength: " + chosenSuperheroToEdit.get(5));
        String newStrength = sc.nextLine();

       try {
           if (controller.editSuperheroList(newName, newRealName, newSuperpower, newYearCreated, newIsHuman, newStrength, userChoise)) {
               System.out.println("The chosen superhero has been edited!\n");

           } else System.out.println("No edits has been performed.");
       } catch (Exception e){
           e.printStackTrace();
       }
        System.out.println("Would you like to edit the list again?");
    }

    public void deleteSuperhero() {
        if (controller.getSuperheroList().isEmpty()) {
            System.out.println("There are no superheros in your list to delete");
            return;

        } else {
            System.out.println(controller.listOfSuperheros());

        }
        System.out.print("\nWhich superhero do you wish to delete? If you regret this option press 0.\nChoice: ");

        while (!sc.hasNextInt()) {
            System.out.print("You must enter a number: ");
            sc.nextLine();
        }

        /* possible solution to catch an indexOUtOfBoundsError.
        while(sc.nextInt()<0 || sc.nextInt()>controller.getSuperheroList().size()){
            System.out.println("You have to choose between the shown superheros\nEnter a number: ");
            sc.nextLine();
        }
         */

        int userChoice = sc.nextInt();
        try {
            String output = controller.deleteSuperhero(userChoice);
            System.out.println(output);

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void sortSuperheroList(){
        System.out.print("What attribute would you like to sort by?" +
                "\n1. Superhero name" +
                "\n2. Real name" +
                "\n3. Superpower" +
                "\n4. Year created" +
                "\n5. Is human" +
                "\n6. Strength" +
                "\nChoise: ");
        String choise = sc.nextLine();

        controller.sortSuperheroList(choise);
    }

    public void endProgram() {
        System.out.println("Program ended");
    }
}
