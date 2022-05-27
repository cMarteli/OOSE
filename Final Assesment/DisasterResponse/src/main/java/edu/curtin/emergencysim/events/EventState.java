/**
 * Event.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.emergencysim.events;

public interface EventState
{
    /**Rescuers arrive set state to cleaning/casualty reduction */
    public void arrive();

    /**Starts event/resets all relevant values*/
    public void reset();

    /**checks for new casualties */
    public void checkCasualty(boolean result);

    /**checks for new property damage */
    public void checkDamage(boolean result);

    /**checks for chemical contamination */
    public void checkContam(boolean result);

}
