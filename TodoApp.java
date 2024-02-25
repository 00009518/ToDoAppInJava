import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 * front end
 * |   Task description     |   Due date    |   Status  |
 */

public class TodoApp {
    static Scanner scanner=new Scanner(System.in);
    static final Scanner scannerSTR=new Scanner(System.in);
    private static final DateTimeFormatter DATE_PARSER
            = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    static String[][] view=new String[11][11];
    static int i =1;

    public static void main(String[] args) {
        System.out.println(" Welcome to Task management Application! ");;
        CommandsMenu();
    }
     static void AddingTask() {
        System.out.println("Enter description of the new task");
        view[i][0]=scannerSTR.next(); //task
        System.out.println("What is the due date of your task? (dd-MM-year)");
        view[i][1]=scanner.next(); //dateInput
        LocalDate today=LocalDate.now();
        LocalDate userDate=LocalDate.parse(view[i][1], DATE_PARSER);
        view[i][2]=status(today, userDate);
        i++;
        DisplayList();
     }

    private static void CommandsMenu() {
        // commands menu
        System.out.print("| Press (+) to add a new task |");
        System.out.print(" Press (x) to delete a task |");
        System.out.print(" Type (edit) to update a task |");
        String input=scanner.next();
        if (input.equals("+")) {
            AddingTask();
        } else if (input.equals("x")) {
            DeleteTask();
        } else if (input.equals("edit")) {
            UpdateTask();
        } else {
            System.out.println("Please enter a valid command");
            CommandsMenu();
        }
    }

    private static void UpdateTask() {
        System.out.println("Which task do you want to update?");
        int indexUpdate=scanner.nextInt();
        if (view[indexUpdate][0] != null) {
            System.out.println("Enter description of the new task");
            view[indexUpdate][0]=scanner.next(); //task
            System.out.println("What is the due date of your task? (dd-MM-year)");
            view[indexUpdate][1]=scanner.next(); //dateInput
            LocalDate today=LocalDate.now();
            LocalDate userDate=LocalDate.parse(view[indexUpdate][1], DATE_PARSER);
            view[indexUpdate][2]=status(today, userDate);
            DisplayList();

        } else {
            System.out.println("There is no such a task");
            CommandsMenu();
        }

    }


    private static void DeleteTask() {
        System.out.println("Which task do you want to delete?");
        int indexDelete=scanner.nextInt();
        if (view[indexDelete][0] != null) {
            for (int j = 0; j<view.length; j++) {
                view[indexDelete][j]=null;
            }
        } else {
            System.out.println("There is no such a task");
            CommandsMenu();
        }
        DisplayList();
    }
    static String status(LocalDate today, LocalDate userDate) {
        int i=0;
        while (today.isBefore(userDate)) {
            today=today.plusDays(1);
            i++;
        }
        String status=i+" days left";
        if (today.isAfter(userDate)) {
            System.out.println("Please enter a valid date");
            String dateInput=scanner.next();
            LocalDate userDate2=LocalDate.parse(dateInput, DATE_PARSER);
            status(today, userDate2);
        }

        return status;
    }


    static void DisplayList() {
        System.out.println("|  Task Description  |  Due Date  |  Status  |");
        for (int i = 1; i < view.length; i++) {
            System.out.print(i+")");
            for (int j = 0; j<view.length; j++) {
                if (view[i][j] != null) {
                    System.out.print("|  "+view[i][j]+"  ");
                }
            }
            System.out.println();
        }
        CommandsMenu();
    }
}
