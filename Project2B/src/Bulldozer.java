/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Bulldozer class inheriting from the Vehicles class
 * that inherits from the Movable class that inherits
 * from the Sprite class.
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Bulldozer extends Vehicles
{
    private float Speed = 0.05f; /*** Constant speed to be used for the Bulldozer. ***/
    private boolean isLeft; /*** A flag that keeps checking if Bulldozer is at the left or not. ***/


    /**
     *  Passes the Image of the Bulldozer
     *  to the Vehicles class that inherits
     *  from Movable class that inherits from
     *  Sprite class(superclass).
     *  @param x, x co-ordinate, type float.
     *  @param y, y co-ordinate, type float.
     *  @param z, flag if it's left, type boolean.
     * */

    public Bulldozer(float x, float y,boolean z) throws SlickException
    {
        super(ImageSources.BULLDOZER,x,y);
        isLeft = z;
        if(isLeft == false)
        {
            Speed = -1* Speed;
        }
        else{
            Speed = 1* Speed;
        }
    }

    /**
     * Overrides the update method in Vehicles that inherits the
     * update from Movable which itself inherits from the Sprite(superclass) to
     * keep the Bulldozers moving in alternating directions.
     * @param input, Keyboard input inserted by the user, type Input.
     * @param delta, Time passed since last frame (milliseconds), type integer.
     */

    @Override
    public void update(Input input, int delta)
    {
        /**
         * Since the Bulldozers are drawn half-centered, the Bulldozers have to be
         * translated to accommodate it into the frame so that it keeps
         * on drawing to the screen and adjust opposite directions as well.
         **/

        this.setX(((this.getX() + 24) + (delta*(this.Speed))+(App.SCREEN_WIDTH+48))%(App.SCREEN_WIDTH+48)-24);
        BoundingBox bbox  = this.getBbox();
        bbox.setX(this.getX());
    }
}