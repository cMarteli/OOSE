package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author Caio Marteli 19598552
 */
public class AddressBook
{
    //Using hashmap key is name
    public Map<String, Entry> entries;

    // Constructor - initializes list
    public AddressBook()
    {
        entries = new HashMap<>();     
    }
    public void addEntry(String n, List<String> e)
    {
        entries.put(n,new Entry(n,e));
    }
    
    //uses iterator to go through each key then checks each email list for parsed String
    public String searchEmail(String email)
    {
        Iterator<String> itr = entries.keySet().iterator();
        while(itr.hasNext())
        {
            String key = itr.next();
            //iterates through email list
            for (String s : entries.get(key).getEmailList())
            {
                if(s.equals(email))
                {
                   return("FOUND:\n" + entries.get(key));
                }
            }
        }
        return("NOT FOUND");

    }
    //debugging method prints all entries
    public void printEntries()
    {
        System.out.println(entries);
    }
    
}
