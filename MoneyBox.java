import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MoneyBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoneyBox extends Platform
{
    
    
    //private final GreenfootImage RAIN_PIC;
    private final GreenfootImage[] PRESS_ANIMATION;


    private int frame = 0;
    private int pressIndex = 0;
    
    public boolean isPressed = false;
    
    public MoneyBox() {
        getImage().scale(100,100);
        PRESS_ANIMATION = new GreenfootImage[]
        { //new GreenfootImage("bob1.png"),

        //new GreenfootImage ("acid_splash3.png"),
        new GreenfootImage ("moneyBoxUp.png"),
        new GreenfootImage ("moneyBoxMid.png"),
        new GreenfootImage ("moneyBoxPressed.png"),
        new GreenfootImage ("moneyBoxDown.png"),
        new GreenfootImage ("moneyBoxDown.png"),
        new GreenfootImage ("moneyBoxDown.png"),
        new GreenfootImage ("moneyBoxMid.png"),
        new GreenfootImage ("moneyBoxUp.png")
        };
    }

    
    public void act()
    {
        if(Greenfoot.isKeyDown("m") && isTouching(Player.class) && !isPressed) {
            isPressed = true;
            Greenfoot.playSound("buttonDown.wav");
        }
        if(isPressed) {
            if( frame % 3 == 0)
            {
                if(pressIndex < PRESS_ANIMATION.length)
                {
                    PRESS_ANIMATION[pressIndex].scale(100,100);
                    setImage (PRESS_ANIMATION[pressIndex]);
                    pressIndex++;
                }
                else
                {
                   getWorld().addObject(new MissileHill(),getWorld().getWidth()/2,getWorld().getHeight());
                   Greenfoot.playSound("buttonUp.wav");
                   isPressed = false;
                   frame = 0;
                   pressIndex = 0;
                }
            }
            frame++;
        }
    }
}
