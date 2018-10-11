/**
 * BoundingBox complete class for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 *
 * Project 2B
 *
 * Utility function to aid in working the game.
 **/
package utilities;

import org.newdawn.slick.Image;

public class BoundingBox
{
	private static final float FUZZ = 0.95f;
	
	private float left;
	private float top;
	private float width;
	private float height;

	/**
	* This constructor makes the BoundingBox of an object
	* @param x, x co-ordinate, type float.
	* @param y, y co-ordinate, type float.
	* @param width, the width of the object, type float.
	* @param height, the height of the object, type float.
	**/
	public BoundingBox(float x, float y, float width, float height)
	{
		setWidth(width);
		setHeight(height);
		setX(x);
		setY(y);
	}

	/**
	 * This constructor makes the BoundingBox of an object.
	 * An overloaded method for making the Bounding Box.
	 * @param img, Image of the object used to get width and height, type Image.
	 * @param x, x co-ordinate, type float.
	 * @param y, y co-ordinate, type float.
	 **/
	public BoundingBox(Image img, float x, float y) {
		setWidth(img.getWidth());
		setHeight(img.getHeight());
		setX(x);
		setY(y);
	}

	/**
	* This constructor makes the BoundingBox of an object.
	* An overloaded method for making the Bounding Box.
	* @param bb, the Bounding box through which we can get
	 *               width, height, left-end top and can be
	 *               instantiated, type BoundingBox
	**/

	public BoundingBox(BoundingBox bb)
	{
		width = bb.width;
		height = bb.height;
		left = bb.left;
		top = bb.top;
	}

	 /**
	 * Sets the x position at the centre of the bounding box.
	 * @param x, x co-ordinate, type float.
	 **/

	public void setX(float x) {
		left = x - width / 2;
	}

	/**
	 * Sets the y position at the centre of the bounding box.
	 * @param y, y co-ordinate, type float.
	 **/

	public void setY(float y) {
		top = y - height / 2;
	}

	/**
	 * Sets the width of the bounding box.
	 * @param w, width, type float.
	 **/

	public void setWidth(float w) {
		width = w * FUZZ;
	}

	/**
	 * Sets the height of the bounding box.
	 * @param h, height, type float.
	 **/

	public void setHeight(float h) {
		height = h * FUZZ;
	}

	/**
	 * Gets the left side of the bounding box's co-ordinate of an object .
	 * @return left, the left side of the bounding box, type float.
	 **/

	public float getLeft() {
		return left;
	}

	/**
	 * Gets the top of the bounding box's co-ordinate of an object.
	 * @return top, the top of the bounding box of the object, type float.
	 **/

	public float getTop() {
		return top;
	}

	/**
	 * Gets the right side of the bounding box's co-ordinate of an object.
	 * @return left+width, the right side of the bounding box of the
	 * object, type float.
	 **/
	public float getRight() {
		return left + width;
	}

	/**
	 * Gets the bottom of the bounding box's co-ordinate of an object.
	 * @return left+width, the bottom of the bounding box of the
	 * object, type float.
	 **/

	public float getBottom() {
		return top + height;
	}

	/**
	 * Gets the width of the bounding box's co-ordinate of an object.
	 * @return width, the width of the bounding box of the object, type float.
	 **/

	public float getWidth() {
		return width;
	}

	/**
	 * Gets the height of the bounding box's co-ordinate of an object.
	 * @return height, the height of the bounding box of the object, type float.
	 **/

	public float getHeight() {
		return height;
	}

	/**
	 * This method checks if one object crosses the boundary of the other.
	 * The boundaries being the top, bottom, left and right of the objects.
	 * @param other, the other BoundingBox to be compared the first
	 *                  BoundingBox, type BoundingBox.
	 **/

	public boolean intersects(BoundingBox other)
	{
		return !(other.left > getRight() // bus moving away to left
			  || other.getRight()  < left //bus moving away to right
			  || other.top > getBottom()
			  || other.getBottom() < top);
	}
}