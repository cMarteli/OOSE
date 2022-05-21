package edu.curtin.microwave.original;

/** 
 * The MicrowaveController class from the lecture on state. This controls a very simple microwave 
 * with three buttons: "ten secs", "start" and "cancel". These map to the methods tenSecs(), 
 * start() and cancel(). There is also a run() method that is called once per second (by Main), 
 * which allows us to implement timing requirements.
 */
public class MicrowaveController
{
    // Constants for representing the state
    private static final int IDLE = 0;
    private static final int ENTERING_TIME = 1;
    private static final int HEATING = 2;

    private int state = IDLE; // Initial state
    private int time = 0;
    
    private Heater heater;
    private Display display;
    
    public MicrowaveController(Heater heater, Display display) 
    {
        this.heater = heater;
        this.display = display;
    }
    
    public void tenSecs() 
    {
        switch(state)
        {
            case IDLE: // IDLE -> ENTERING_TIME
                time = 10;             // Action
                display.update(time);  // Action
                state = ENTERING_TIME; // New state
                break;
                
            case ENTERING_TIME: // Self-transition
                time += 10;           // Action
                display.update(time); // Action
                break;
            
            default: break; // Nothing to do in the HEATING state
        }
    } 
    
    public void start() 
    {
        if(state == ENTERING_TIME) // Only relevant in this state
        {
            heater.on();     // Action
            state = HEATING; // New state

            // At this point, we could also set up the run() method to be called once-per-second
            // (as explained below), unless another class is already doing that for us.
        }
    }
    
    public void run()
    {
        // This method is expected to be called once per second. I'm deliberately omitting the 
        // detail of *how* that happens, in order to focus on the higher-level state behaviour. 
        // However, it could be done using Thread.sleep() inside a loop, or the Timer task, or 
        // even in other ways.
        
        if(state == HEATING) // Only relevant in this state
        {   
            if(time > 0) // Guard condition 1
            {       
                time--;               // Action
                display.update(time); // Action
            }
            else // Guard condition 2 (which we don't need to write explicitly because it's just 
                 // the inverse of the 1st one)
            { 
                heater.off();    // Action
                state = IDLE;    // New state
            }
        }
    }
    
    public void cancel() 
    {
        switch(state)
        {
            case ENTERING_TIME: // ENTERING_TIME --> IDLE
                display.update(0); // Action
                state = IDLE;      // New state
                break;
                
            case HEATING:
                heater.off();      // Action
                display.update(0); // Action
                state = IDLE;      // New state 
                break;
                
            default: break; // Nothing to do in the IDLE state
        }
    }
}
