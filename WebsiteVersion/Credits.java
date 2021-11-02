import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Credits here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Credits extends VisualEffect
{
    private float velocity = 1f;
    int frame = 0;
    
    GreenfootImage im;
    
    int startLocation = 0;
    
    public Credits(boolean won) {
        im = new GreenfootImage(1200, 2400);
        if(won) {
            im.drawImage(new GreenfootImage("goodEnding.png"),0,150);
        } else {
            im.drawImage(new GreenfootImage("badEnding.png"),0,150);
        }
        im.drawImage(new GreenfootImage("credits.png"),0,0);
        setImage(im);
        
        
        
    }
    
    public void act()
    {
        if(getY()<-1250) {
            
            Greenfoot.setWorld(new Level5(true));
        }
        
        if(frame%1==0)
            setLocation(getX(), getY()-(int)velocity);
        frame++;
    }
}
