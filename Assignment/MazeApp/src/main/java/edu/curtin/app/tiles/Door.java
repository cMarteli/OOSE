/**
 * Key.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

public interface Door
{
    public Boolean use(String colour);

}

// class Unlocks implements Key
// {
//     public String colour;
//     public Boolean use(String clr)
//     {
//         System.out.println("Door was unlocked!");
//         return true;
//     }
// }

// class DoesNotUnlock implements Key
// {
//     public String colour;
//     public Boolean use(String clr)
//     {
//         System.out.println("No matching key...");
//         return false;
//     }
// }
