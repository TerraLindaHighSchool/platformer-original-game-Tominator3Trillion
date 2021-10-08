import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Smoke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Smoke extends Actor
{
    Random rand = new Random();
    
    int transparency = 200;
    
    int xVelocity = rand.nextInt(3)-1;
    int yVelocity = rand.nextInt(3)-1;
    
    int scale = rand.nextInt(50)+50;
    
    private final GreenfootImage[] SMOKES = new GreenfootImage[]
        { //new GreenfootImage("bob1.png"),

        //new GreenfootImage ("acid_splash3.png"),
        new GreenfootImage ("smoke1.png"),
        new GreenfootImage ("smoke2.png"),
        new GreenfootImage ("smoke3.png")
        };
        
        
    public Smoke() {
        setImage(SMOKES[rand.nextInt(SMOKES.length)]);
        
        getImage().scale(scale, scale);
    }
    public void act()
    {
        getImage().setTransparency(transparency);
        transparency-= 1;

        if(rand.nextInt(3) == 0) {
            setLocation(getX()+xVelocity, getY()+yVelocity);
        }
        if(transparency < 1) {
            getWorld().removeObject(this);
        }
    }
}
