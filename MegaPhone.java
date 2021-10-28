import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MegaPhone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MegaPhone extends Actor
{
    
    public GreenfootSound audio;
    int goal = 50;
    int currentScale = 50;
    boolean justPlayed = true;
    
    String im = "megaPhone.png";
    public MegaPhone(GreenfootSound audio) {
        this.audio = audio;

    }
    
    
    public void play() {
        audio.play();
    }
    
    public void act()
    {
        currentScale = getImage().getHeight();
        if(currentScale == goal) {
            goal = Greenfoot.getRandomNumber(10)+50;
        }
        if (audio.isPlaying()) {
            currentScale = currentScale + ((currentScale-goal>0)?-1:1);
            setImage(im);
            getImage().scale(getImage().getWidth(),currentScale);
        } else if(justPlayed) {
            setImage(im);
            justPlayed = false;
        }
        
        
    }
}
