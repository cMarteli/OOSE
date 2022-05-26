import edu.curtin.emergencysim.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
    public void isSame() {
        //Simulation sim = new Simulation(INPUT_FILE);
        Event e1 = new Event(6, "FIRE", "Midtown");

        Event e2 = new Event(8, "FIRE", "Midtown");

        Event e3 = new Event(69, "FLOOD", "Midtown");

        //Fire Midtown both events, should be true
        assertEquals(true, e1.isSame(e2));

        assertEquals(true, e1.isSame("FIRE", "Midtown"));

        assertEquals(true, e1.isSame("fire", "Midtown"));

        //Flood != fire Should be false
        assertEquals(false, e1.isSame(e3));

        assertEquals(false, e1.isSame("flood", "Midtown"));

        assertEquals(false, e1.isSame("FIRE", "Anywhere"));
    }

    // @Test
    // public void getActEvent()
    // {
    //     en.addEvent(24, "FLOOD", "Midtown");
    //     en.addEvent(6, "FIRE", "Midtown");
    //     en.addEvent(12, "FIRE", "Hill Valley");
    //     en.addEvent(24, "FLOOD", "Hill Valley");


    // }


}
