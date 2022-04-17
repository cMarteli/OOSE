/**
 * Tile.java
 * Using template method pattern
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;

import java.util.InputMismatchException;

public abstract class ColourTile extends Tile
{
    private String colour;

    //Fields related to Tile location
    public String getClr() {
        return colour;
    }

    //sets to a colour corresponding to integer
    public void setClr(int c)
    {
        if(c == 1){
            colour = "RED";
        }else if(c == 2){
            colour = "GREEN";
        }else if(c == 3){
            colour = "YELLOW";
        }else if(c == 4){
            colour = "BLUE";
        }else if(c == 5){
            colour = "MAGENTA";
        }else if(c == 6){
            colour = "CYAN";
        }else
        {
            throw new InputMismatchException("Invalid parameters for door colour: " + colour);
        }
    }

}
