/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Rideable class inheriting from the Movable class.
 **/

import org.newdawn.slick.SlickException;

public class Rideable extends Movable
{
    /**
     * Rideable constructor that initialises parameters and passes to Movable
     * superclass that passes to Sprite superclass to create the Rideable objects.
     * @param imageSrc, the image file source containing the sprite image, type String.
     * @param x, x co-ordinate, type float.
     * @param y, y co-ordinate, type float.
     **/

    public Rideable(String imageSrc,float x, float y) throws SlickException
    {
        super(imageSrc, x, y);
    }
}