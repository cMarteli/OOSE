package edu.curtin.emergencysim;

import org.junit.jupiter.api.*;

public class UnitTest
{
    EventNotifier<Event> en;
    FileIO<Event> fio;


    final String INPUT_FILE = "input2.txt";
    //initializes classes for tests

    public UnitTest() {
        en = new EventNotifierImpl();
        fio = new FileIO<>();

        try {
            fio.readFile(INPUT_FILE, en);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }


    }
    @Test
    public void testAdd() {
        assertEquals(42, Integer.sum(19, 23));
    }


}
