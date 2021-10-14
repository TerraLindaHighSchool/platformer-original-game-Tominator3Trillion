import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ----------
 * 
 * @Tommy M.
 * @10/13
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
    private final GreenfootSound RAIN_SPLAT = new GreenfootSound("rainSplat.wav");


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
        
        Player player = (Player)getOneObjectAtOffset(0,getImage().getHeight()/2, Player.class);
        if(isOnFloor() || player!=null) {
            //setImage(new GreenfootImage("acid_splash.png"));
            if(player!=null && !isSplashing) {
                setLocation(getX(),getY()+5);
                if(Powerup.getCount() >= 1 ) {
                    getWorld().removeObject(player.powerup[Powerup.getCount() - 1]);
                    Powerup.setCount(Powerup.getCount()-1);
                } else {
                    player.removeHealth();
                }
                //System.out.println("movin");
                
            }
            RAIN_SPLAT.play();
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
                    try {
                        getWorld().removeObject(this);
                    }catch(Exception e) {}
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
