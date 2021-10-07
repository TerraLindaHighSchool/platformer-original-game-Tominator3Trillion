import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class MissileHill here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MissileHill extends Actor
{
    int frame = 0;
    int targetDestination;
    Random rand = new Random();
    

    public void act()
    {
        if(frame==0) {
            targetDestination = getWorld().getHeight() - 300;
        }
        
        if(getY() > targetDestination) {
            setLocation(getX(),getY() - 1);
        }
        frame++;
    }
}
