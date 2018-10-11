/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Movable class inheriting from the Sprite class.
 **/

import org.newdawn.slick.*;

public abstract class Movable extends Sprite
{
    /**
     * Movable constructor that initialises parameters and passes to Sprite
     * superclass.
     * @param imageSrc, the image file source containing the sprite image, type String.
     * @param x, x co-ordinate, type float.
     * @param y, y co-ordinate, type float.
     **/

    public Movable (String imageSrc,float x, float y) throws SlickException
    {
        super(imageSrc, x, y);
    }
}