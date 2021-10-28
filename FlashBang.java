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
    int dur = 10;
    public FlashBang(World w, int dur) {
        getImage().scale(w.getWidth(),w.getHeight());
        this.dur = dur;
    }
    
    public void act()
    {
        
        if(frame > 60) {
            getImage().setTransparency(transparency);
            if(frame%dur==1) {
                transparency-= ((255-transparency)/10 +1);
            }
        }
        if(transparency < 1) {
            getWorld().removeObject(this);
        }
        
        frame++;
    }
}
