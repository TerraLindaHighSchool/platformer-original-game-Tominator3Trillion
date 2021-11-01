import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    int size = 2000;
    GreenfootImage im = new GreenfootImage("glow.png");
    public void act()
    {
        setImage(im);
        getImage().scale(size, size);
        
        if(size<=1) {
            getWorld().removeObject(this);
        } else {
            size-=1;
        }
    }
}
