import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CreditDebug here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CreditDebug extends Actor
{
    int frame =0;
    public void act()
    {
        if(frame==5) {
            getWorld().addObject(new Credits(false),600, 2000);
            getWorld().addObject(new FadeToBlack(getWorld(), 6),600, getWorld().getHeight()/2);
        }
        frame++;
    }
}
