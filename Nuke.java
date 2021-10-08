import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Nuke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Nuke extends Actor
{
    public float yVelocity;
    private final float GRAVITY = 0.0227f;
    
    private int frame = 0;
    
    public Nuke() {
        getImage().scale(5,10);
    }
    
    public void act()
    {
        fall();
        if(isTouching(MissileHill.class)) {
            frame++;
        }
        if(frame==1) {
            Greenfoot.playSound("explosion.mp3");
        }
        if(frame == 14) {
            setImage("nukeExplosion.png");
        }
        if(frame >= 15) {
            //setImage("nukeExplosion.png");
            getWorld().addObject(new Explosion(),getWorld().getWidth()/2,getWorld().getHeight()/2);
            getWorld().addObject(new FlashBang(getWorld()),getWorld().getWidth()/2,getWorld().getHeight()/2);
            
            getWorld().setBackground("nukeBackground.png");
            Level1.bgImage= new GreenfootImage("nukeBackground.png");
            Level1.bgBase= new GreenfootImage("nukeBackground.png");
            Level1.scrollSpeed = 0;
            Level1.scrollPosition = 1;
            
            
            MissileHill mh = (MissileHill)getOneIntersectingObject(MissileHill.class);
            mh.bombDetonated = true;
            mh.setImage("missileHillLaunch.png");
            getWorld().removeObject(this);
            
            
        }
    }
    
    protected void fall()
    { 
        yVelocity += GRAVITY;
        setLocation(getX(), getY() + (int) yVelocity);
        //removeOutOfBounds(this);
    }
}
