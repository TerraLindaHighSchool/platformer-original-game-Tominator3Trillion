import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ----------
 * 
 * @Tommy M.
 * @10/13
 */
public class Floor extends Platform
{
 
    
    private static final String bgImageName = "floor_grass.png";
    //private static final double scrollSpeed = 2.5;
    private static final int picWidth = (new GreenfootImage(bgImageName)).getWidth();
 
    private GreenfootImage bgImage, bgBase;
    public int scrollPosition = 0;
     
    public Floor()
    {    
        //super(800, 400, 1);
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
