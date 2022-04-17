/**
 * @class ColourKey.java
 * 2022/OOSE Assignment
 * @author Caio Marteli (19598552)
 */
package edu.curtin.app.tiles;
import static edu.curtin.app.Graphics.*; //imports GFX class

public class Key extends ColourTile
{

    public Key(int inX, int inY, int clr)
    {
        setX(inX);
        setY(inY);
        setClr(clr);
    }

    @Override
    public Object getContent()
    {
        String k;
        if(getClr().equals("RED")){
            k = RED_KEY;
        }else if(getClr().equals("GREEN")){
            k = GRN_KEY;
        }else if(getClr().equals("YELLOW")){
            k = YLW_KEY;
        }else if(getClr().equals("BLUE")){
            k = BLU_KEY;
        }else if(getClr().equals("MAGENTA")){
            k = MGT_KEY;
        }else if(getClr().equals("CYAN")){
            k = CYA_KEY;
        }else
        {
            k = KEY_SYMBOL;
        }
        return k;
    }

    @Override
    public void setContent(Object o)
    {
       System.out.println("Not applicable");
    }





}
