package edu.curtin.microwave.sp.states;
import edu.curtin.microwave.sp.MicrowaveController;

public class IdleState implements MicrowaveState
{
    @Override
    public void tenSecs(MicrowaveController mc)
    {
        mc.setTime(10);
        mc.getDisplay().update(10);
        mc.setState(new EnteringTimeState());
    }
    
    @Override
    public void start(MicrowaveController mc)
    {
        // Ignore
    }
    
    @Override
    public void run(MicrowaveController mc)
    {
        // Ignore
    }
    
    @Override
    public void cancel(MicrowaveController mc)
    {
        // Ignore
    }
}
