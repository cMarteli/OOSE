package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 * 
 * @author ...
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
    //TODO may need to change return to Entry type
    public void addEntry(String n, List<String> e)
    {
        //DEBUG ONLY
        // System.out.println(n);
        // System.out.println("Emails:");
        // for (String s : e) {
        //     System.out.println(s);            
        // }

        //creates new entry object
        entries.put(n,new Entry(n,e));
    }

    public String searchEmail(String email)
    {
        Iterator<String> itr = entries.keySet().iterator();
        while(itr.hasNext())
        {
            String key = itr.next();
            //System.out.println(key);
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

    public void printEntries()
    {
        System.out.println(entries);
    }
    
}
