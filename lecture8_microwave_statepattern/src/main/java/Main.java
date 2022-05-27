package edu.curtin.microwave.sp;

import java.io.*;

/**
 * Crudely simulates a set of microwave controls using console input, in order to demonstrate the
 * MicrowaveController class. We also make sure to call MicrowaveController.run() once per second,
 * via Thread.sleep(1000), and by checking whether user input is ready before attempting to read 
 * it (so we don't get stuck waiting for it).
 */
public class Main
{
    public static void main(String[] args)
    {
        Heater heater = new Heater();
        Display display = new Display();
        MicrowaveController mc = new MicrowaveController(heater, display);

        // We must use BufferedReader to read user input here, instead of Scanner, because Scanner
        // cannot check whether user input is actually ready; it simply waits if needed.
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in)))
        {   
            boolean quit = false;
            while(!quit)
            {
                try
                {
                    System.out.printf(
                        "\r[time=%s, heater=%s] (t)en secs, (s)tart, (c)ancel, (q)uit? >",
                        display.getTime(), 
                        heater.isOn() ? "on" : "off");
                        
                    Thread.sleep(1000);
                    if(input.ready())
                    {
                        // By checking for input.ready(), we avoid waiting for user input, so we 
                        // can do other things while the user is typing, if necessary.
                        switch(input.readLine().toLowerCase())
                        {
                            case "t": mc.tenSecs(); break;
                            case "s": mc.start(); break;
                            case "c": mc.cancel(); break;
                            case "q": quit = true; break;
                            default: break;
                        }
                        System.out.print("\033[2A\n\033[K"); // Move cursor up, erase line (if the terminal supports it)
                    }
                    
                    // This call happens (at least roughly) once per second.
                    mc.run(); 
                }
                catch(InterruptedException e) { throw new AssertionError(e); }    
            }
        }
        catch(IOException e)
        {
            System.out.println("Input error: " + e.getMessage());
        }
    }
}
