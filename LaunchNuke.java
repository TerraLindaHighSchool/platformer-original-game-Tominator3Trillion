import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LaunchNuke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LaunchNuke extends Actor
{
    
    float velocity =1f;
    
    public LaunchNuke() {
        getImage().scale(75,150);
        getImage().mirrorVertically();
    }
    public void act()
    {
        setLocation(getX(), (int)(getY()-velocity));
        
        getWorld().addObject(new Smoke(),getX(), getY());
        
        velocity*=1.1;
        
        if(getY() < -30) {
            getWorld().addObject(new Nuke(),getX(), -3000);
            getWorld().removeObject(this);
            
        }
    }
}
