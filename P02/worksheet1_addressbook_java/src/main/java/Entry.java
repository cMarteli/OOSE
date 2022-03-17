package edu.curtin.addressbook;

import java.util.*;
        
/**
 * Represents a single address book entry.
 * 
 * @author Caio Marteli 19598552
 */
public class Entry 
{
    String name;
    List<String> email;
    //constructor takes name and email
    public Entry(String n, List<String> e)
    {
        name = n;
        email = new ArrayList<>();
        //adds emails to email list
        for (String s : e) 
        {
            email.add(s);            
        }     
    }

    //Accessor
    public List<String> getEmailList()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return "Name: [" + name + "] Email(s): " + email;
    }
}
