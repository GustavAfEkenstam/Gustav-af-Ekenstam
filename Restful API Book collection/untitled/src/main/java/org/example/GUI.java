package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Main.*;


public class GUI {
    private List<String> temp = new ArrayList<>();
    private int option, tempInt;
    private String  tempString;

    private Scanner scanner = new Scanner(System.in);

    public GUI(){

        while(option != 4){


            System.out.println("1.Retrieve a list of all books");
            System.out.println("2.Retrieve a specific book by its title");
            System.out.println("3.Create a new book");
            System.out.println("4.Exit");
            System.out.print("Select an option: ");

            Scanner s = new Scanner(System.in);
            option = s.nextInt();

            if(option == 1){
                getAllRecords();

            } else if(option == 2){
                System.out.print("Enter title: ");


                tempString = scanner.nextLine();
                tempString = tempString.replace(" ", "%20");
                getRecordByTitle(tempString);

            } else if(option == 3){
                System.out.print("Enter published year: ");
                temp.add(scanner.nextLine());
                System.out.print("Enter book title: ");
                temp.add(scanner.nextLine());
                System.out.print("Enter book description: ");
                temp.add(scanner.nextLine());
                System.out.print("Enter book category: ");
                temp.add(scanner.nextLine());
                System.out.print("Enter book author: ");
                temp.add(scanner.nextLine());

                addRecord(new Book(Integer.parseInt(temp.get(0)), temp.get(1), temp.get(2), temp.get(3), temp.get(4)));
                temp.clear();
            } else if(option == 4){
                System.exit(0);
            }
        }
    }
}
