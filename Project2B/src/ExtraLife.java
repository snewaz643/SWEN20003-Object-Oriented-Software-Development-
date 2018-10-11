/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Extralife class inheriting from the Sprite class.
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class ExtraLife extends Sprite
{
    /**
     * Passes the Image of the extralife
     * to the Sprite class(superclass).
     * @param x, x co-ordinate, type float.
     * @param y, y co-ordinate, type float.
     **/
    public ExtraLife(float x,float y) throws SlickException
    {
        super(ImageSources.EXTRALIFE,x,y);
    }


    /**
     * Overrides the method in Sprite to make Extralife interact.
     * @param input, Keyboard input inserted by the user,type Input.
     * @param delta, Time passed since last frame (milliseconds),type integer.
     **/
    @Override
    public void update(Input input, int delta)
    {

        /*** Updates the Bounding box for the extralife object which detects collision in the world. ***/

        BoundingBox bbox = this.getBbox();
        bbox.setY(this.getY());
        bbox.setX(this.getX());
    }
}