package edu.curtin.microwave.sp.states;
import edu.curtin.microwave.sp.MicrowaveController;

public interface MicrowaveState
{
    void tenSecs(MicrowaveController mc);
    void start(MicrowaveController mc);
    void run(MicrowaveController mc);
    void cancel(MicrowaveController mc);
}
