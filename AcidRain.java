import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AcidRain here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AcidRain extends Obstacle
{
    /**
     * Act - do whatever the AcidRain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public float yVelocity;
    private final float GRAVITY;
    private final GreenfootImage RAIN_PIC;
    private final GreenfootImage[] SPLASH_ANIMATION;


    private int frame = 0;
    private int splashIndex = 0;
    
    public boolean isSplashing = false;
    public AcidRain(float gravity) {
        GRAVITY = gravity;
        RAIN_PIC= getImage();
        
        SPLASH_ANIMATION = new GreenfootImage[]
        { //new GreenfootImage("bob1.png"),

        //new GreenfootImage ("acid_splash3.png"),
        new GreenfootImage ("acid_splash.png"),
        new GreenfootImage ("acid_splash2.png"),
        new GreenfootImage ("acid_splash3.png")
        };
    }
    
    public void act()
    {
        
        if(isOnFloor()) {
            //setImage(new GreenfootImage("acid_splash.png"));
            isSplashing = true;
        } else {
            fall();
        }
        
        if(isSplashing) {
            if( frame % 5 == 0)
            {
                if(splashIndex < SPLASH_ANIMATION.length)
                {
                    setImage (SPLASH_ANIMATION[splashIndex]);
                    splashIndex++;
                }
                else
                {
                    getWorld().removeObject(this);
                    splashIndex = 0;
                }
            }
            frame++;
        }
    }
    
    


    protected void fall()
    { 
        yVelocity += GRAVITY;
        setLocation(getX(), getY() + (int) yVelocity);
        removeOutOfBounds(this);
    }
}
