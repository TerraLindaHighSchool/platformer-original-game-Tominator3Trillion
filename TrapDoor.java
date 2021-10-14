import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ----------
 * 
 * @Tommy M.
 * @10/13
 */
public class TrapDoor extends Obstacle
{
    /**
     * Act - do whatever the TrapDoor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private float yVelocity;
    private final float GRAVITY;

    public TrapDoor(float gravity) {
        //getImage().scale(120,34);
        GRAVITY = gravity;
    }
    
    public void act()
    {

    }

    protected void fall()
    { 

    }
}
