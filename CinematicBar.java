import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CinematicBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CinematicBar extends Actor
{
    private float velocity = 0f;
    int frame = 0;
    
    public CinematicBar(boolean isTop) {
        velocity = isTop?1f:-1f;
    }
    
    public void act()
    {
        if((getY() > 760||getY() < -60)&& frame%3==0)
            setLocation(getX(), getY()+(int)velocity);
        frame++;
    }
}
