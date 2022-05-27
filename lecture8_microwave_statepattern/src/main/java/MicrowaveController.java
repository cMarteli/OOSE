package edu.curtin.microwave.sp;

import edu.curtin.microwave.sp.states.MicrowaveState;
import edu.curtin.microwave.sp.states.IdleState;

/** 
 * The MicrowaveController class, modified to implement the State Pattern. This class has very 
 * little functionality compared to its original version. It is mostly a wrapper around (and a 
 * place to keep track of) the state object, which determines what actually happens.
 *
 * The state object is an instance of one of the MicrowaveState implementations: IdleState, 
 * EnteringTimeState and HeatingState. On each state transition, a new state object is created that
 * replaces the old one (via the setState() method).
 *
 * There are some additional getters and setters to let the state object get/set the time, and get
 * references to the Heater and Display objects.
 */
public class MicrowaveController
{
    private MicrowaveState state = new IdleState(); // Initial state
    private int time = 0;    
    private Heater heater;
    private Display display;
    
    public MicrowaveController(Heater heater, Display display) 
    {
        this.heater = heater;
        this.display = display;
    }
    
    
    // Getters and setters
    // ===================
    
    public void setState(MicrowaveState state)
    {
        this.state = state;
    }
    
    public void setTime(int time)
    {
        this.time = time;
    }
    
    public int getTime()        { return time; }
    public Heater getHeater()   { return heater; }
    public Display getDisplay() { return display; }
    
    
    // State-dependent methods
    // =======================
    
    public void tenSecs() 
    {
        state.tenSecs(this);
    }
    
    public void start()
    {
        state.start(this);
    }
    
    public void run()
    {
        state.run(this);
    }
    
    public void cancel()
    {
        state.cancel(this);
    }
}
