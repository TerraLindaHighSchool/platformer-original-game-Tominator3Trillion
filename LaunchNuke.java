import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LaunchNuke here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LaunchNuke extends Actor
{
    
    float velocity = 0.25f;
    int frame = 0;
    int smokes = 0;
    Random rand = new Random();
    //int yHeight = 10;
    
    public LaunchNuke() {
        getImage().scale(70,150);
        getImage().mirrorVertically();
    }
    public void act()
    {
        if(frame==1) {
            Greenfoot.playSound("rocketLaunch.mp3");
            Greenfoot.playSound("flyingAway.mp3");
        }
        if(frame==40) {
            Greenfoot.playSound("flyingAway.mp3");
        }
         if(frame > 45){
            getWorld().addObject(new Smoke(smokes),getX()-10, getY() +20+rand.nextInt(40));
            getWorld().addObject(new Smoke(smokes),getX(), getY()+25+rand.nextInt(10));
            getWorld().addObject(new Smoke(smokes),getX()+10, getY()+20+rand.nextInt(40));
            getWorld().addObject(new Smoke(smokes),getX()-30, getY() +40+rand.nextInt(40));
            getWorld().addObject(new Smoke(smokes),getX()+30, getY()+40+rand.nextInt(40));
            
            smokes++;
        }
        
        if(velocity < 1) {
            setLocation(getX(), Math.random() < velocity? getY()-1 : getY());
        } else {
            setLocation(getX(), Math.round(getY()-velocity));
        }
        
        velocity*=1.1;
        
        if(getY() < -30) {
            getWorld().addObject(new Nuke(),getWorld().getWidth()/2, -3000);
            getWorld().removeObject(this);
            
        }
        frame++;
    }
}
