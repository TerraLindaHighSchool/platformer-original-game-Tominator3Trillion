import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rock here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rock extends Obstacle
{
    /**
     * Act - do whatever the Rock wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public float yVelocity;
    private final float GRAVITY;
    private final GreenfootImage RAIN_PIC;
    private final GreenfootImage[] SPLASH_ANIMATION;


    private int frame = 0;
    private int splashIndex = 0;
    
    private final int START_FRAME = 250;
    
    private boolean isCracking = false;
    public Rock(float gravity) {
        GRAVITY = gravity;
        RAIN_PIC= getImage();
        
        SPLASH_ANIMATION = new GreenfootImage[]
        { //new GreenfootImage("bob1.png"),

        //new GreenfootImage ("acid_splash3.png"),
        new GreenfootImage ("rockCrack1.png"),
        new GreenfootImage ("rockCrack2.png"),
        new GreenfootImage ("rockCrack3.png"),
        new GreenfootImage ("rockCrack4.png"),
        new GreenfootImage ("rockCrack5.png")
        };
    }
    
    public void act()
    {
        Player player = (Player)getOneObjectAtOffset(0,getImage().getHeight()/2, Player.class);        
        if(isOnGround() || player!=null) {
            //setImage(new GreenfootImage("acid_splash.png"));
            if(player!=null&& !isCracking) {
                frame = START_FRAME;
                setLocation(getX(),getY()+7);
                player.removeHealth();
                
            }
            
            isCracking = true;
        } else {
            fall();
        }
        
        if(isCracking) {
            var f = frame - START_FRAME;
            if(frame>=START_FRAME && f % 5 == 0)
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
        //removeOutOfBounds(this);
    }

}
