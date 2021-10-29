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
    private boolean isFacingLeft=false;
    private final GreenfootImage[] WALK_ANIMATION;
    private final GreenfootImage STANDING_IMAGE;
    private final float JUMP_FORCE;
    private final float GRAVITY;
    private final Class NEXT_LEVEL;    
    private final GreenfootSound MUSIC;
    private final GreenfootSound JUMP = new GreenfootSound("jump.mp3");
    
    public Player(int speed, float jumpForce, float gravity,
    int maxHealth, int maxPowerup, Class nextLevel, GreenfootSound music)
    {
        getImage().scale(70, 70);
        this.speed = speed;
        JUMP_FORCE = jumpForce;
        GRAVITY = gravity;
        NEXT_LEVEL = nextLevel;
        MUSIC = music;
        JUMP.setVolume(50);
        
        healthCount = maxHealth;
        health = new Health[maxHealth];
        powerup = new Powerup[maxPowerup];
        
        STANDING_IMAGE = getImage();
        WALK_ANIMATION = new GreenfootImage[]
        { //new GreenfootImage("bob1.png"),
        new GreenfootImage ("bob1.png"),
        new GreenfootImage ("bob2.png"),
        new GreenfootImage ("bob3.png"),
        new GreenfootImage ("bob4.png"),
        new GreenfootImage ("bob5.png")
        };
        STANDING_IMAGE.scale(70,70);
        for(int i =0; i < WALK_ANIMATION.length; i++) {
            WALK_ANIMATION[i].scale(70,70);
        }
}
    

    public void act() 
    {     
        walk();
        jump();
        fall();
        onCollision();
        gameOver();
        if(getY() > getWorld().getHeight()) {
            setLocation((int)(getWorld().getWidth()/2), getWorld().getHeight()- 100);
        }
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
    
    
    public void addedToWorld(World world) {
        for(int i = 0; i < health.length; i++)
        {
             health[i] = new Health();
             world.addObject(health[i], 30 + 42 * i, 36);
        }
        for(int i = 0; i < powerup.length; i++)
        {
             powerup[i] = new Powerup();
             world.addObject(powerup[i], world.getWidth() -( 30 + 42 * i), 36);
        }
        Powerup.setCount(powerup.length);
    }
    
    private void walk() {
        boolean isTouchingWall=true;
        int transparency = getImage().getTransparency();
        if(isWalking) {
            animator();
        } else {
            setImage(STANDING_IMAGE);
            walkIndex = 0;
        }
        if(Greenfoot.isKeyDown("r")) {
            Greenfoot.setWorld(new Level1());
        }
        
        
        getImage().setTransparency(transparency);
        
        if(Greenfoot.isKeyDown("right")  && !Greenfoot.isKeyDown("left") && (getOneObjectAtOffset(getImage().getWidth()/2,0, Platform.class)==null||isTouching(Phasable.class))) {
            
            if(isFacingLeft) {
                mirrorImages();
            }
            isTouchingWall=false;
            isWalking = true;
            isFacingLeft = false;
            
            if(getX() < getWorld().getWidth()/2 && getX()+speed > getWorld().getWidth()/2) {
                setLocation(getWorld().getWidth()/2, getY());
            }
            
            //move(speed);
            if(getX() != (int)(getWorld().getWidth()/2)&& getX() < getWorld().getWidth()) {
                //setLocation(getX()+speed, getY());
                move(speed);
            }
            for(Actor o : getWorld().getObjects(Actor.class)) {
                if(!(o instanceof Player || o instanceof Floor || o instanceof HUD || o instanceof VisualEffect || o instanceof Nuke||o instanceof CloseBackground || o instanceof CinematicBar)) {
                    if(!(getX() != (int)(getWorld().getWidth()/2))) {
                        if(o instanceof MissileHill||o instanceof FakeHill || o instanceof LaunchNuke|| o instanceof Smoke || o instanceof FrontHole|| o instanceof BackHole|| o instanceof Flag ) {
                            o.setLocation(o.getX()-(frame%2==0?1:0), o.getY());
                        } else {
                            o.setLocation(o.getX()-speed, o.getY());
                        }
                    }
                }
            }
        }if(Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right") && (getOneObjectAtOffset(-(getImage().getWidth()/2),0, Platform.class)==null||isTouching(Phasable.class))) {
            if(!isFacingLeft) {
                mirrorImages();
            }
            isTouchingWall=false;
            isWalking = true;
            isFacingLeft = true;
            //move(-speed);
            if(getX() > getWorld().getWidth()/2 && getX()-speed < getWorld().getWidth()/2) {
                setLocation(getWorld().getWidth()/2, getY());
            }
            if(getX() != (int)(getWorld().getWidth()/2) && getX() > 0) {
                //setLocation(getX()-speed, getY());
                move(-speed);
            }
            
            for(Actor o : getWorld().getObjects(Actor.class)) {
                if(!(o instanceof Player || o instanceof Floor || o instanceof HUD || o instanceof VisualEffect|| o instanceof Nuke||o instanceof CloseBackground || o instanceof CinematicBar)) {
                    if(!(getX() != (int)(getWorld().getWidth()/2))) {
                        if(o instanceof MissileHill|| o instanceof FakeHill || o instanceof LaunchNuke|| o instanceof Smoke || o instanceof FrontHole|| o instanceof BackHole|| o instanceof Flag) {
                            o.setLocation(o.getX()+(frame%2==0?1:0), o.getY());
                        } else {
                            o.setLocation(o.getX()+speed, o.getY());
                        }
                    }
                }
            }
        }
        if(isWalking  && !isTouchingWall && getX() == (int)(getWorld().getWidth()/2)) {
            Floor f = getWorld().getObjects(Floor.class).get(0);
            f.scroll(isFacingLeft ? -speed : speed);
            for(CloseBackground cb : getWorld().getObjects(CloseBackground.class)) {
                cb.scroll(isFacingLeft ? -1 : 1);
            }
            
            if(getWorld() instanceof Level2) {
                ((Level2)getWorld()).scroll(isFacingLeft ? (-speed)/5f : speed/5f);
            }else if(getWorld() instanceof Level3) {
                ((Level3)getWorld()).scroll(isFacingLeft ? (-speed)/5f : speed/5f);
            }else if(getWorld() instanceof Level4) {
                ((Level4)getWorld()).scroll(isFacingLeft ? (-speed)/5f : speed/5f);
            }
                
        }
        
        if(!(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")) || (Greenfoot.isKeyDown("left") && Greenfoot.isKeyDown("right")))
        {
            isWalking = false;
        }
    }
    private void jump() {
        if(Greenfoot.isKeyDown("space") && isOnGround() && !touchingRoof()) 
        {
            yVelocity = JUMP_FORCE;
            isJumping = true;
            JUMP.play();
        }
        if(isJumping && yVelocity > 0) {
            setLocation(getX(), getY() - (int) yVelocity);
            yVelocity -= GRAVITY;
        } else {
            isJumping = false;
        }
    }
    private void fall() {
        if(!isJumping && (!isOnGround() || (touchingRoof() &&getIntersectingObjects(Platform.class).size()<2))) {
            setLocation(getX(), getY() - (int) yVelocity);
            yVelocity -= GRAVITY;
        }
    }
    private void onCollision( )
    {
        if(isTouching(Door.class))
        {
            //MUSIC.stop();
            Greenfoot.playSound("door_open.wav");
            World world = null;
            try 
            {
                world = (World) NEXT_LEVEL.newInstance();
            }   
            catch (InstantiationException e) 
            {
                System.out.println("Class cannot be instantiated");
            } catch (IllegalAccessException e) {
                System.out.println("Cannot access class constructor");
            } 
            Greenfoot.setWorld(world);
        }
        if(isTouching(Gem.class) && Powerup.getCount() < powerup.length) {
            Powerup.setCount(Powerup.getCount()+1);
            powerup[Powerup.getCount()-1] = new Powerup();
            getWorld().addObject(powerup[Powerup.getCount()-1], getWorld().getWidth() -( 30 + 42 * (Powerup.getCount()-1)), 36);

            Greenfoot.playSound("collectable.wav");
            getWorld().removeObject(getOneIntersectingObject(Gem.class));
        }
        if(isTouching(Obstacle.class) && !isTouching(AcidRain.class) && !isTouching(Rock.class)) {
            removeHealth();
            getWorld().removeObject(getOneIntersectingObject(Obstacle.class));
        }
        if(isTouching(AcidPool.class)) {
            removeHealth();
        }
        
        if(isTouching(Platform.class) || (getY() + getImage().getHeight()/4) <=0) {
            yVelocity=-1;
            fall();
        }
    }
    
    public void removeHealth() {
        if(Powerup.getCount() >= 1 ) {
            getWorld().removeObject(powerup[Powerup.getCount() - 1]);
            Powerup.setCount(Powerup.getCount()-1);
        } else {
            Greenfoot.playSound("hurt.mp3");
            getWorld().removeObject(health[healthCount - 1]);
            healthCount--;
        }
    }
    
    private void mirrorImages() {
        for(int i =0; i < WALK_ANIMATION.length; i++) {
            WALK_ANIMATION[i].mirrorHorizontally();
        }
        STANDING_IMAGE.mirrorHorizontally();
    }
    private void gameOver()
    {
        if(healthCount == 0)
        {
            //MUSIC.stop();
            Greenfoot.setWorld(new Level1());
        }
    }
    private boolean isOnGround() {
        Actor ground = getOneObjectAtOffset(0,getImage().getHeight()/2, Platform.class);
        
        return ground != null || isTouching(Floor.class);
    }
    private boolean touchingRoof() {
        Actor ceiling = getOneObjectAtOffset(0,-(getImage().getHeight()/2), Platform.class);
        
        return ceiling != null;
    }
}
