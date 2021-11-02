import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FadeToBlack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FadeToBlack extends VisualEffect
{
    int transparency = 0;
    int frame = 0;
    int dur = 10;
    
    boolean reverse = false;
    public FadeToBlack(World w, int dur) {
        getImage().scale(w.getWidth(),w.getHeight());
        getImage().setTransparency(transparency);
        this.dur = dur;
    }
    
    public FadeToBlack(World w, int dur, boolean reverse) {
        getImage().scale(w.getWidth(),w.getHeight());
        getImage().setTransparency(transparency);
        this.dur = dur;
        this.reverse = reverse;
        if(reverse) {
            transparency = 255;
        }
    }
    
   
    
    public void act()
    {
        if(!reverse) {
            getImage().setTransparency(transparency);
            if(frame%dur==1 && transparency!=255) {
                transparency+=1;
            }
        } else {
            getImage().setTransparency(transparency);
            if(frame%dur==1 && transparency!=0) {
                transparency-=1;
            }
            if(transparency==0) {
                getWorld().removeObject(this);
            }
        }
        
        frame++;
    }
}
