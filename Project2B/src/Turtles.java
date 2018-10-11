/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Turtles class inheriting from the Rideable class
 * that itself inherits from Movable class(inherits
 * from Sprite class).
 **/

import org.newdawn.slick.*;
import utilities.BoundingBox;

public class Turtles extends Rideable
{
    private float Speed = 0.085f; // Constant speed to be used
    private boolean isLeft;


    /**
     * Passes the Image of the Turtles
     * to the Rideable class that then passes
     * to Movable class that finally passes to
     * Sprite class(superclass) to make the turtles objects.
     * @param x, x co-ordinate, type float
     * @param y, y co-ordinate, type float
     **/

    public Turtles(float x, float y,boolean z) throws SlickException
    {
        super(ImageSources.TURTLES,x,y);
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
     * Getter for getting the Turtle's Speed.
     * @return Speed, the current speed of the Turtle, type float.
     **/

    public float getSpeed() {
        return Speed;
    }

    /**
     * Setter for Turtles object's Speed.
     * @param speed, the speed of the Turtles object, type float.
     **/

    public void setSpeed(float speed) {
        Speed = speed;
    }

    /**
     * Overrides the update method in Sprite that Movable
     *  inherits and then Rideable inherits (from which
     *  Turtles class inherits) to keep the turtles moving
     *  in alternating directions.
     *  @param input, Keyboard input inserted by the user, type Input.
     *  @param delta, Time passed since last frame (milliseconds), type integer.
     */

    @Override
    public void update(Input input, int delta)
    {
        /**
         * Since the Turtles are drawn half-centered, the turtles have to be
         * translated to accommodate it into the frame so that it keeps
         * on drawing to the screen and adjust opposite directions as well
         **/

        this.setX(((this.getX() + 24) + (delta * (this.Speed)) + (App.SCREEN_WIDTH + 48)) % (App.SCREEN_WIDTH + 48) - 24);

        /*** Get and set the BoundingBox of the turtles. ***/
        BoundingBox bbox = this.getBbox();
        bbox.setX(this.getX());
    }
}