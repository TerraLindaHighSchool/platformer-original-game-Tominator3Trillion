import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CinematicBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CinematicBar extends Actor
{
    private float velocity = 1f;
    
    public CinematicBar(boolean isTop) {
        velocity = isTop?1f:-1f;
    }
    
    public void act()
    {
        if(getY() > 750||getY() < -50)
        setLocation(getX(), getY()+(int)velocity);
    }
}
