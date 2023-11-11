package pl.coderslab;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class TaskManager {

    private static boolean usedExit;
    private static List<String> tasks;


    public static void main(String[] args) throws IOException {

        loadTasksFromFile();
        while (!usedExit) {
            displayMenu();
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            System.out.println(choice);

            switch (choice) {
                case "add" -> handleAddOption();
                case "remove" -> handleRemoveOption();
                case "list" -> handleListOption();
                case "exit" -> handleExitOption();
                default -> handleDefaultOption();
            }
        }

    }

    private static void displayMenu() {
        System.out.println();
        System.out.print(ConsoleColors.BLUE);
        System.out.println("Please select an option");
        System.out.print(ConsoleColors.RESET);
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");

    }

    private static void handleAddOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please add task description");
        String description = scanner.next();
        System.out.println("Please add task due date");
        String date = scanner.next();
        System.out.println("Is your task important? (true / false)");
        boolean important = scanner.nextBoolean();
        tasks.add(description + "," + date + "," + important);
    }

    private static void handleRemoveOption() {
        System.out.println("Please select number to remove");
        Scanner scanner = new Scanner(System.in);
        int indexToRemove = scanner.nextInt();
        while (indexToRemove > tasks.size()){
            System.out.println("Please correct index number");
            scanner.nextInt();
        }
        tasks.remove(indexToRemove);
    }

    private static void handleListOption() throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + " : " + tasks.get(i).replace(',', '\t'));
        }
    }

    private static void handleExitOption() throws IOException {
        System.out.print(ConsoleColors.RED);
        System.out.println("Bye bye");
        usedExit = true;
        saveTasksToFile();

    }

    private static void handleDefaultOption() {
        System.out.println("Correct your choice");
    }

    private static void loadTasksFromFile() throws IOException {
        Path path = Path.of("tasks.csv");
        tasks = Files.readAllLines(path);
    }

    private static void saveTasksToFile() throws IOException {
        Files.write(Path.of("tasks.csv"), tasks);
    }



}

