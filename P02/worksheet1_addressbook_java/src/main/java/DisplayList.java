package edu.curtin.addressbook;

import java.util.*;

/**
 * Display option class
 *
 * @author Caio Marteli 19598552
 */
public class DisplayList implements Option
{
    public Map<String, Entry> entries;

    //constructor; grabs the list of entries
    public DisplayList(AddressBook ab)
    {
        entries = ab.getEntryList();
    }
    
    public String doOption(String s)
    {
        return entries.toString();
    }

    @Override
    public boolean requiresText()
    {
        return false;
    }
}
