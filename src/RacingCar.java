/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Racing Car class inheriting from the Vehicles class
 * that inherits from the Movable which itself inherits
 * from Sprite class.
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class RacingCar extends Vehicles
{
    private float Speed = 0.5f; // Constant speed to be used
    private boolean isLeft;


    /**
     * A constructor that passes the Image of the Racing Car
     * to the Vehicles class(superclass) and initialises its parameters.
     * @param x, x co-ordinate, type float.
     * @param y, y co-ordinate, type float.
     * @param z, flag to check if racing car is at the left or not, type boolean.
     * */

    public RacingCar(float x, float y,boolean z) throws SlickException
    {
        super(ImageSources.RACECAR,x,y);
        isLeft = z;
        if(isLeft == false)
        {
            Speed = -1*Speed;
        }
        else
        {
            Speed = 1*Speed;
        }
    }

    /**
     * Overrides the update method in Vehicles(inherits from Movable
     * that inherits from Sprite) to keep the RacingCars moving in
     * alternating directions.
     * @param input, Keyboard input inserted by the user, type Input.
     * @param delta, Time passed since last frame (milliseconds), type integer.
     **/

    @Override
    public void update(Input input, int delta)
    {
        /**
         * Since the RacingCars are drawn half-centered, the RacingCars have to be
         * translated to accommodate it into the frame so that it keeps
         * on drawing to the screen and adjust opposite directions as well
         **/
        this.setX(((this.getX() + 24) + (delta*(this.Speed))+(App.SCREEN_WIDTH+48))%(App.SCREEN_WIDTH+48)-24);

        /*** Get and set the BoundingBox of the RacingCars. ***/
        BoundingBox bbox  = this.getBbox();
        bbox.setX(this.getX());
    }
}