/**
 * Door.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;
import static edu.curtin.app.Graphics.*; //imports GFX class

public class Door extends ColourTile
{
    private String orientation;

    public Door(int inX, int inY, int clr, String o)
    {
        setX(inX);
        setY(inY);
        orientation = o;
        setClr(clr);
    }



    public String getOrientation() {
        return orientation;
    }



    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }



    @Override
    public Object getContent()
    {
        String k;
        if(getClr().equals("RED")){
            k = RED_DOOR;
        }else if(getClr().equals("GREEN")){
            k = GRN_DOOR;
        }else if(getClr().equals("YELLOW")){
            k = YLW_DOOR;
        }else if(getClr().equals("BLUE")){
            k = BLU_DOOR;
        }else if(getClr().equals("MAGENTA")){
            k = MGT_DOOR;
        }else if(getClr().equals("CYAN")){
            k = CYA_DOOR;
        }else
        {
            k = DOOR_SYMBOL;
        }
        return k;
    }

    @Override
    public void setContent(Object o)
    {
        setOrientation(o.toString());
    }



}