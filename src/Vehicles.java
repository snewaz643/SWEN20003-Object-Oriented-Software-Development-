/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Vehicles class inheriting from the Movable class
 * that inherits from the Sprite class.
 **/

import org.newdawn.slick.*;

public class Vehicles extends Movable
{
    /**
     * Vehicles constructor that initialises parameters and passes to Movable
     * superclass which then passes to Sprite superclass to create the vehicles object.
     * @param imageSrc, the image file source containing the vehicle image, type String.
     * @param x, x co-ordinate, type float.
     * @param y, y co-ordinate, type float.
     **/
    public Vehicles (String imageSrc,float x, float y) throws SlickException
    {
        super(imageSrc, x, y);
    }
}