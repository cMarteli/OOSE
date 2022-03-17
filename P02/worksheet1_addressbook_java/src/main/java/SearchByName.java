package edu.curtin.addressbook;

import java.util.*;

/**
 * Contains name search methods
 *
 * @author Caio Marteli 19598552
 */
public class SearchByName implements Option
{
    public Map<String, Entry> entries;

    //constructor; grabs the list of entries
    public SearchByName(AddressBook ab)
    {
        entries = ab.getEntryList();
    }

    /**
     * Searches list by name given a string
     * then displays it if present
     */
    @Override
    public String doOption(String s)
    {
        String outStr = "Name not present.";
        if(entries.containsKey(s))
        {
            outStr = "Found:\n" + entries.get(s);
        }
        return outStr;
    }
    
    @Override
    public boolean requiresText()
    {
        return true;
    }
}