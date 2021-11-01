import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Smoke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Smoke extends Actor
{
    Random rand = new Random();
    
    float transparency = 200;
    
    int xVelocity = rand.nextInt(3)-1;
    int yVelocity = rand.nextInt(5)-2;
    
    int scale = rand.nextInt(50)+50;
    float decay = (float)Math.random()+0.5f;
    
    private final GreenfootImage[] SMOKES = new GreenfootImage[]
        { //new GreenfootImage("bob1.png"),

        //new GreenfootImage ("acid_splash3.png"),
        new GreenfootImage ("smoke1.png"),
        new GreenfootImage ("smoke2.png"),
        new GreenfootImage ("smoke3.png")
        };
        
        
    public Smoke(int smokeId) {
        setImage(SMOKES[rand.nextInt(SMOKES.length)]);
        
        getImage().scale(scale, scale);
        
        if(smokeId < 7) {
            yVelocity= rand.nextInt(3)-1;
            xVelocity = rand.nextInt(11)-6;
        }
    }
    public void act()
    {
        getImage().setTransparency((int)transparency);
        transparency-= decay;

        
        if(rand.nextInt(3) == 0) {
            setLocation(getX()+xVelocity, getY()+yVelocity);
        }
        if(isTouching(FrontHole.class) || isTouching(BackHole.class)) {
            setLocation(getX(), getY()-1);

            transparency-= decay*2;
        }
        if(transparency < 1) {
            getWorld().removeObject(this);
        }
        
    }
}
