import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelfDestructionBox here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelfDestructionBox extends Actor
{
     private final GreenfootImage[] PRESS_ANIMATION;


    private int frame = 0;
    private int pressIndex = 0;
    
    public boolean isPressed = false;
    
    private final GreenfootSound  BUTTON_DOWN = new GreenfootSound("buttonDown.wav");
    private final GreenfootSound BUTTON_UP = new GreenfootSound("buttonUp.wav");
    
    public SelfDestructionBox() {
        MoneyBox.hasBeenPressed = false;
        getImage().scale(100,100);
        
        BUTTON_DOWN.setVolume(75);
        BUTTON_UP.setVolume(75);
        
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
        if(Greenfoot.isKeyDown("m") && isTouching(Player.class) && !isPressed && !MoneyBox.hasBeenPressed) {
            isPressed = true;
            MoneyBox.hasBeenPressed = true;
            BUTTON_DOWN.play();
            
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
                   getWorld().addObject(new FailHill(),getWorld().getWidth()/2,getWorld().getHeight()+100);
                   BUTTON_UP.play();
                   isPressed = false;
                   frame = 0;
                   pressIndex = 0;
                }
            }
            frame++;
        }
    }
}
