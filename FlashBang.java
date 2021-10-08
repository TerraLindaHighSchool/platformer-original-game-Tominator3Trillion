import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FlashBang here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlashBang extends VisualEffect
{
    int transparency = 255;
    int frame = 0;
    public FlashBang(World w) {
        getImage().scale(w.getWidth(),w.getHeight());
    }
    
    public void act()
    {
        
        if(frame > 30) {
            getImage().setTransparency(transparency);
            if(Math.random() > 0.9) {
                transparency-= ((255-transparency)/10 +1);
            }
        }
        if(transparency < 1) {
            getWorld().removeObject(this);
        }
        
        frame++;
    }
}
