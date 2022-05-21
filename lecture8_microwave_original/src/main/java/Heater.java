package edu.curtin.microwave.original;

/**
 * This is just a stub, to help demonstrate the MicrowaveController class.
 */
public class Heater
{
    private boolean on = false;
    public void on()      { on = true; }
    public void off()     { on = false; }
    public boolean isOn() { return on; }
}
