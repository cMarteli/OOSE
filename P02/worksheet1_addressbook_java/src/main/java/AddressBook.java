package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains all the address book entries.
 *
 * @author Caio Marteli 19598552
 */
public class AddressBook
{
    //Instantiates hashmap, key is name
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

    //Acessor for entries list
    public Map<String, Entry> getEntryList()
    {
        return entries;
    }

    //debugging method prints all entries
    public void printEntries()
    {
        System.out.println(entries);
    }

}
