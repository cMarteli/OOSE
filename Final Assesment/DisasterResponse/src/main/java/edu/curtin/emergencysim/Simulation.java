/**
 * Simulation.java
 * OBSERVER
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim;
//import static edu.curtin.emergencysim.Constants.*; //imports GFX class
import java.util.Random;

public class Simulation
{
    private EventNotifier en;
    private Random rand;

    public Simulation(EventNotifier inEn)
    {
        en = inEn;
        rand = new Random();
    }

    public void start()
    {
        //this.setNews((String) news);
        System.out.println("Starting Simulation...");
        update(en.getNext()); //sends next event to update
    }

    public void update(Emergency nxt)
    {
        System.out.println("Next: ");
        //System.out.println(en.getNext().toString()); //DEBUG
        switch (nxt.getDis())
        {
            case FIRE:
                //String intensity;
                //if(rand) TODO Add randomization here
                System.out.println("Fire has started in: " +
                nxt.getLocation());

                break;

            case FLOOD:
                System.out.println("Flood has started in: " +
                nxt.getLocation());


                break;

            case CHEMICAL:
                System.out.println("Chemical spill reported in: " +
                nxt.getLocation());


                break;

            default:
                break;
        }

    }
}
