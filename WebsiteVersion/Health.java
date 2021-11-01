import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Health here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Health extends HUD
{
    int offset = 0;
    int f = 0;
    int frame = 0;
    
    public void act()
    {
        if(frame>offset) {
            if(f==5) {
                getImage().scale(getImage().getWidth()+4,getImage().getHeight()+4);
            } 
            if(f==10) {
                getImage().scale(getImage().getWidth()-4,getImage().getHeight()-4);
            } 
            if(f==15) {
                getImage().scale(getImage().getWidth()+4,getImage().getHeight()+4);
            } 
            if(f==20) {
                getImage().scale(getImage().getWidth()-4,getImage().getHeight()-4);
            } 
            
            f++;
            if(f==300) {
                f = 0;
            }
        }
        
        frame++;
    }
}
