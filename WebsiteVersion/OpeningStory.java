import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class OpeningStory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OpeningStory extends Actor
{
    
    int frame = 0;
    GreenfootSound lo = new GreenfootSound("lightOn.mp3");
    
    /**
     * Act - do whatever the OpeningStory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if(frame==1) {
            Greenfoot.playSound("floodLightActivate.mp3");
            lo.playLoop();
        }
        if(frame >= 675) {
            Greenfoot.setWorld(new Level1());
            lo.stop();
            Greenfoot.playSound("floodLightActivate.mp3");
        }
        frame++;
    }
}
