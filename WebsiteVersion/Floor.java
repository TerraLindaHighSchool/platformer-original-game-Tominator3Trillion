import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Floor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Floor extends Platform
{
 
    
    private static String bgImageName;
    //private static final double scrollSpeed = 2.5;
    private static int picWidth;
 
    private GreenfootImage bgImage, bgBase;
    public int scrollPosition = 0;
     
    public Floor(String im)
    {    
        //super(800, 400, 1);
        
        bgImageName = im;
        picWidth = (new GreenfootImage(bgImageName)).getWidth();
        setImage(bgImageName);
        bgImage = new GreenfootImage(getImage());
        bgBase = new GreenfootImage(picWidth, getImage().getHeight());
        bgBase.drawImage(bgImage, 0, 0);
    }
     
     public void scroll(double scrollSpeed)
    {
        scrollPosition -= scrollSpeed;
        while(scrollSpeed > 0 && scrollPosition < -picWidth) scrollPosition += picWidth;
        while(scrollSpeed < 0 && scrollPosition > 0) scrollPosition -= picWidth;
        paint(scrollPosition);
    }
     
     private void paint(int position)
    {
        GreenfootImage bg = getImage();
        bg.drawImage(bgBase, position, 0);
        bg.drawImage(bgImage, position + picWidth, 0);
    }
}
