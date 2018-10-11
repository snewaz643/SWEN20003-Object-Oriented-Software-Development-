/**
 * SWEN20003 Object Oriented Software Development
 * Project 2B
 * @author Syed Ahammad Newaz Saif
 * Student Number: 684933
 * Email: snewaz@student.unimelb.edu.au
 *
 * Tree class inheriting from the Tile class
 * which itself inherits from the Sprite class.
 **/

import org.newdawn.slick.SlickException;

public class Tree extends Tile
{
    /**
     * Tree constructor that passes the image and sets
     * co-ordinates before passing to the Tile class
     * that passes to Sprite class to make the Tree objects.
     * @param x, x co-ordinate, type float.
     * @param y, y co-ordinate, type float.
     **/

    public Tree(float x, float y) throws SlickException
    {
        super(ImageSources.TREE,x,y);
    }
}
