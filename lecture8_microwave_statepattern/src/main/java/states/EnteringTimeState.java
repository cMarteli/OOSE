package edu.curtin.microwave.sp.states;
import edu.curtin.microwave.sp.MicrowaveController;

public class EnteringTimeState implements MicrowaveState
{
    @Override
    public void tenSecs(MicrowaveController mc)
    {
        int time = mc.getTime() + 10;
        mc.setTime(time);
        mc.getDisplay().update(time);
        // Self-transition; no state change
    }
    
    @Override
    public void start(MicrowaveController mc)
    {
        mc.getHeater().on();
        mc.setState(new HeatingState()); // EnteringTime -> Heating
    }
    
    @Override
    public void run(MicrowaveController mc)
    {
        // Ignore
    }
    
    @Override
    public void cancel(MicrowaveController mc)
    {
        mc.getDisplay().update(0);
        mc.setState(new IdleState()); // EnteringTime -> Idle
    }
}
