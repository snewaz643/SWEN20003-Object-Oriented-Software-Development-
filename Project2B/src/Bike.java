/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Bike class inheriting from the Vehicles class
 * which inherits from the Movable class that
 * itself inherits from the Sprite class.
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Bike extends Vehicles
{
    private float Speed = 0.2f; /*** Bike needs to have constant Speed. ***/
    private boolean isLeft; /*** Checks if the Bike is left or not. ***/


    /**
     * Passes the Image of the Bike
     * to the Vehicles class(superclass) that passes
     * Movable which passes to Sprite class to create
     * the bike object.
     * @param x, x co-ordinate, type float
     * @param y, y co-ordinate, type float
     **/

    public Bike(float x, float y,boolean z) throws SlickException
    {
        super(ImageSources.BIKE,x,y);
        isLeft = z;
    }

     /**
     * Overrides the update method in Vehicles that gets its update from Movable that
     * itself gets from Sprite class to keep the bikes moving, and once it hits the
     * edge and makes it move in the opposite direction.
     * @param input, Keyboard input inserted by the user, type Input.
     * @param delta, Time passed since last frame (milliseconds),type integer.
     */

    @Override
    public void update(Input input, int delta)
    {
        /**
        * Reaches the edge and needs to change direction.
        **/

        if(this.getX() >= ConstantValues.RIGHTEDGE || this.getX() <= ConstantValues.LEFTEDGE)
        {
            this.Speed = -1*this.Speed;
        }

        /**
        * As bikes drawn half-centred,it needs to be adjusted to
        * make it not go out of bounds.
        **/
        this.setX(((this.getX()+24)+(delta*(this.Speed)))-24);

        /*** Updates the BoundingBox of the bikes. **/
        BoundingBox bbox  = this.getBbox();
        bbox.setX(this.getX());
    }
}