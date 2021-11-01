import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Powerup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Powerup extends HUD
{
    private static int count;

    public static void setCount(int change)
    {
        count = change;
       // if(count < 0) count = 0;
    }

    public static int getCount()
    {
        return count;
    }

    public static void clear()
    {
        count = 0;
    }
}
