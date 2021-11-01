import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CloseBackground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CloseBackground extends Actor
{
    public String bgImageName;
    private int scrollFrame = 0;
    private  int PIC_WIDTH;
    public GreenfootImage bgImage, bgBase;
    public  int scrollPosition;
    private float sSpeed = 3f;
     
    public CloseBackground(String im, float speed)
    {    
        //super(800, 400, 1);
        
        sSpeed = speed;
        bgImageName = im;
        PIC_WIDTH = (new GreenfootImage(bgImageName)).getWidth();
        setImage(bgImageName);
        bgImage = new GreenfootImage(getImage());
        bgBase = new GreenfootImage(PIC_WIDTH, getImage().getHeight());
        scrollPosition = 1;
        bgBase.drawImage(bgImage, 0, 0);
    }
     
    
    private void paint(int position)
    {
        GreenfootImage bg = getImage();
        bg.clear();
        bg.drawImage(bgBase, position, 0);
        bg.drawImage(bgImage, position + PIC_WIDTH, 0);
        bg.drawImage(bgImage, position - PIC_WIDTH, 0);
    }
    
    public void scroll(int v)
    {
        float scrollSpeed = sSpeed *v;
        boolean isNegative = Math.abs(scrollSpeed)!=scrollSpeed;
        scrollSpeed=Math.abs(scrollSpeed);
        double s = scrollSpeed;
        if(scrollSpeed < 1f) {
            s = scrollFrame % (1f/ scrollSpeed)<=0.1f  ? 1: 0;
        }
        if(isNegative) {
            scrollPosition += s;
        } else {
            scrollPosition -= s;
        }
        
        
        while(s > 0 && scrollPosition < -PIC_WIDTH) scrollPosition += PIC_WIDTH;
        while(s < 0 && scrollPosition > 0) scrollPosition -= PIC_WIDTH;
        paint(scrollPosition);
        
    }
    public void act() {
        scrollFrame++;
    }
}
