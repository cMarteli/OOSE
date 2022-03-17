package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

import edu.curtin.addressbook.SearchByEmail;
import edu.curtin.addressbook.SearchByName;

/**
 * A simple address book application.
 * @author Dave and Caio Marteli 19598552
 */
public class AddressBookApp
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    /** Container for option objects */
    private static Map<Integer, Option> opt;

    public static void main(String[] args)
    {
        String fileName;
        String entryName;
        //initialising options hashmap
        opt = new HashMap<>();
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();

        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            //adds to hash map if succesfull on read
            Option sbn = new SearchByName(addressBook);
            Option sbe = new SearchByEmail(addressBook);
            opt.put(1, sbn); //set SearchByName as option w/ label '1'
            opt.put(2, sbe); //set SearchByEmail as option w/ label '2'

            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                String[] parts = line.split(":");
                // Note:
                // parts[0] contains the person's name.
                // parts[1], parts[2], etc. contain the person's email address(es).

                //creates new arrays list to store emails
                List<String> email = new ArrayList<>();
                //starting loop at index 1 to skip name
                for (int i = 1; i < parts.length; i++) {
                    email.add(parts[i]);
                }
                //adds entry
                addressBook.addEntry(parts[0],email);

                line = reader.readLine();
            }
        }

        return addressBook;
    }

    /**
     * Show the main menu, offering the user options to (1) search entries by
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */
    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        while(!done)
        {
            int option;
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");

            try
            {
                int menuOpt = Integer.parseInt(input.nextLine());
                if(menuOpt == 1 || menuOpt == 2)
                {
                    System.out.print("Enter name or email address: ");
                    String searchTerm = input.nextLine();
                    //prints the string returned by option requested by user
                    System.out.println(opt.get(menuOpt).doOption(searchTerm));
                }
                else if(menuOpt == 3)
                {
                    System.out.println("Good bye...");
                    done = true;
                }
                else
                {
                    System.out.println("Enter a valid number");
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }
}
