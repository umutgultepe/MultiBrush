/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Umut Cemal Yetgin
 */

import java.awt.*;

public class Blocker extends Rect
{
    protected boolean blocked;

    public Blocker(Color color,Point corner,int w,int h)
    {
        super(color, corner, w, h);
    }

    public void blockIt(){

        blocked = true;
    }

    public void unBlockIt(){
        blocked = false;
    }

    public boolean isBlocked(){
        return blocked;
    }

    public Point getBlockStartPoint(){
        return this.upperLeft;
    }

    public int getBlockWidth(){
        return this.width;
    }

    public int getBlockHeight(){
        return this.height;
    }

    public void draw (Graphics page)
    {
       page.setColor (chosenColor);
       page.drawRect (upperLeft.x,upperLeft.y,width,height);
    }


    @Override
    public boolean isBlocker() {
        return true;
    }
   
}
