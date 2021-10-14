import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ----------
 * 
 * @Tommy M.
 * @10/13
 */
public class Bomb extends Obstacle
{
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private float yVelocity;
    private final float GRAVITY;

    public Bomb(float gravity) {
        GRAVITY = gravity;
    }
    
    public void act()
    {
        fall();
    }

    protected void fall()
    { 
        if(!isOnGround()) {
            yVelocity += GRAVITY;
            setLocation(getX(), getY() + (int) yVelocity);
        }
    }
}
