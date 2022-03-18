package edu.curtin.addressbook;

import java.util.*;

import edu.curtin.addressbook.AddressBook;

/**
 * Contains email search methods
 *
 * @author Caio Marteli 19598552
 */
public class SearchByEmail implements Option
{
    public Map<String, Entry> entries;

    //constructor; grabs the list of entries
    public SearchByEmail(AddressBook ab)
    {
        entries = ab.getEntryList();
    }

    @Override
    public String doOption(String email) {
        // Searches sublist by email given string
        String outStr = "Email not present.";
        Iterator<String> itr = entries.keySet().iterator();
        while(itr.hasNext())
        {
            String key = itr.next();
            //iterates through email list
            for (String s : entries.get(key).getEmailList())
            {
                if(s.equals(email))
                {
                   outStr = "Found:\n" + entries.get(key);
                }
            }
        }
        return outStr;
    }

    @Override
    public boolean requiresText()
    {
        return true;
    }
}
