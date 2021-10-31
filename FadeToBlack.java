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
    public FadeToBlack(World w, int dur) {
        getImage().scale(w.getWidth(),w.getHeight());
        getImage().setTransparency(transparency);
        this.dur = dur;
    }
    
   
    
    public void act()
    {
        
        getImage().setTransparency(transparency);
        if(frame%dur==1 && transparency!=255) {
            transparency+=1;
        }
        
        frame++;
    }
}
