/**
 *  Sample Project for SWEN20003: Object Oriented Software Development 2018
 *  @author Eleanor McMurtry, University of Melbourne
 *  @author Syed Ahammad Newaz Saif
 *  Student Number: 684933
 *  Email: snewaz@student.unimelb.edu.au
 *  Might make some changes for future development.
 *  Maybe adding more levels.
 *
 *  Project 2B
 *
 *  Implemented the "Factory Pattern" design for the entire game.
 * 
 *  The heart of the Game that initializes everything.
 **/

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;

/**
 * Main class for the game.
 * Handles initialization, input and rendering.
 */

public class App extends BasicGame {
    /*** Screen width, in pixels ***/
    public static final int SCREEN_WIDTH = 1024;
    /*** Screen height, in pixels ***/
    public static final int SCREEN_HEIGHT = 768;
    public static final int TILE_SIZE = 48;
    /*** The world class containing the game ***/
    private static World world;

     /**
     * App constructor to create the environment,takes no argument
     **/

    public App()
    {
        super("Shadow Leap");
    }

    /**
     * Sets up the initial environment to create the World
     * @param gc The Slick game container object.
     **/

    @Override
    public void init(GameContainer gc)
            throws SlickException
    {
        world = new World(0); /*** Can manually go to any level but made it here to start at default lvl = 0. ***/
    }

    /**
     *  Update the game state for a frame.
     *  @param gc The Slick game container object.
     *  @param delta Time passed since last frame (milliseconds).
     **/
    
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        /*** Get data about the current input (keyboard state). ***/
        Input input = gc.getInput();
        world.update(input, delta);
    }

    /**
     *  Render the entire screen, so it reflects the current game state.
     *  @param gc The Slick game container object.
     *  @param g The Slick graphics object, used for drawing.
     */
    
    public void render(GameContainer gc, Graphics g)
            throws SlickException {
        world.render(g);
    }

    /**
     * Getter to get the World object, takes no argument.
     * @return world, the world object where the game works.
     **/
    public static World getWorld() {
        return world;
    }

    /**
     * Setter to get to the world according to the level
     * in the flow of the game.
     * @param wor , The new world object that game will
     *            be based on, type World.
     **/
    public static void setWorld(World wor) {
        world = wor;
    }

    /**
     *  Start-up method. Creates the game and runs it.
     *  @param args Command-line arguments (ignored).
     **/
    
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}