package edu.curtin.microwave.sp.states;
import edu.curtin.microwave.sp.MicrowaveController;

public class HeatingState implements MicrowaveState
{
    @Override
    public void tenSecs(MicrowaveController mc)
    {
        // Ignore
    }
    
    @Override
    public void start(MicrowaveController mc)
    {
        // Ignore
    }
    
    @Override
    public void run(MicrowaveController mc)
    {
        int time = mc.getTime();
        if(time > 0)
        {       
            time--;
            mc.setTime(time);
            mc.getDisplay().update(time);
            // Self-transition; no state change.
        }
        else            
        { 
            mc.getHeater().off();
            mc.setState(new IdleState()); // Heating -> Idle
        }
    }
    
    @Override
    public void cancel(MicrowaveController mc)
    {
        mc.getHeater().off();
        mc.getDisplay().update(0);
        mc.setState(new IdleState()); // Heating -> Idle
    }
}
