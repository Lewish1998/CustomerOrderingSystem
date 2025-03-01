package com.ordermanager;

import java.util.Scanner;


public class UI {


    public static void main(String[] args) {
        final boolean running = true;

        // Main menu
        while (running) {
            System.out.println("Customer Ordering System starting...");
            System.out.println("""
                    1. Orders
                    2. Customers
                    3. Items
                    4. Exit
                    """);
            String input = choice();
            
            firstCase(input);
        }
    }

    public static String choice() {
        Scanner s = new Scanner(System.in);
        System.out.print(">>> ");
        String userInput = s.nextLine();
        s.close();
        return userInput;
    }

    public static void firstCase(String input) {
        switch(input) {
            case "1":
                System.out.println("Loading orders...");
                orders();
                break;
            case "2":
                System.out.println("Loading customers...");
                customers();
                break;
            case "3":
                System.out.println("Loading items...");
                items();
                break;
            case "4":
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        }
    }

    public static void orders() {
        System.out.println("""
                1. Create order
                2. View orders
                3. Delete order
                3. Back / exit
                """);
        String input = choice();
        switch(input) {
            case "1":
                System.out.println("Pass");
            case "2":
        }
    }

    public static void customers() {
        System.out.println("""
                1. Add customer
                2. View customers
                3. Delete order
                3. Back / exit
                """);
        String input = choice();
        switch(input) {
            case "1":
                System.out.println("Adding customer...");
                break;
        }
    }

    public static void items() {
        System.out.println("""
                1. Add item
                2. View items
                3. 
                4. Back / exit
                """);
        // String input = choice();
    }
    
}
