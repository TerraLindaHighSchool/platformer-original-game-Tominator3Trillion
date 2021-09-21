import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private Health[ ] health;
    private Powerup[] powerup;
    private int healthCount;
    private int speed;
    private int walkIndex;
    private int frame;
    private float yVelocity;
    private boolean isWalking;
    private boolean isJumping;
    private boolean isfacingLeft;
    private final GreenfootImage[] WALK_ANIMATION;
    private final GreenfootImage STANDING_IMAGE;
    private final float JUMP_FORCE;
    private final float GRAVITY;
    private final Class NEXT_LEVEL;
    private final GreenfootSound MUSIC;
    
    public Player(int speed, float jumpForce, float gravity,
    int maxHealth, int maxPowerup, Class nextLevel, GreenfootSound music)
    {
        this.speed = speed;
        JUMP_FORCE = jumpForce;
        GRAVITY = gravity;
        NEXT_LEVEL = nextLevel;
        MUSIC = music;
        STANDING_IMAGE = getImage();
        WALK_ANIMATION = new GreenfootImage[]
        { new GreenfootImage("walk0.png"),
        new GreenfootImage ("walk1.png"),
        new GreenfootImage ("walk2.png"),
        new GreenfootImage ("walk3.png"),
        new GreenfootImage ("walk4.png"),
        new GreenfootImage ("walk5.png")
        };
}
    

    public void act() 
    {     
        animator();
        move(speed);
    } 
    
    private void animator()
    {
        if(frame % (15 - 2 * speed) == 0)
        {
            if(walkIndex < WALK_ANIMATION.length)
            {
                setImage (WALK_ANIMATION[walkIndex]);
                walkIndex++;
            }
            else
            {
                walkIndex = 0;
            }
        }
        frame++;
    }
    
    
    public void addedToWorld(World world) {}
    private void walk() {}
    private void jump() {}
    private void fall() {}
    private void onCollision() {}
    private void mirrorImages() {}
    private void gameOver() {}
    private boolean isOnGround() {
    return false;
    }
}
