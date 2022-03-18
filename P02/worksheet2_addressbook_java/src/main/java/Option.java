package edu.curtin.addressbook;

import java.util.*;

/**
 * Interface for Search classes
 *
 * @author Caio Marteli 19598552
 */
public interface Option
{
    public String doOption(String s);

    public boolean requiresText();
}
