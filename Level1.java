import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level1 extends World
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    private final float GRAVITY = 0.0667f;
    private final GreenfootSound MUSIC = new GreenfootSound("zapsplat_024.mp3");
    
    public Level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1, false); 
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {

        Player player = new Player(3, 5.6f, GRAVITY, 3, 3, Level1.class, MUSIC);
        addObject(player,95,650);
        Door door = new Door();
        addObject(door,1171, 44);

        addObject(new Floor(), 600, 675);
        addObject(new BrickWall(), 380, 500);
        addObject(new BrickWall(), 780, 300);
        addObject(new BrickWall(), 960, 100);
        addObject(new SmBrickWall(), 1120, 600);
        addObject(new SmBrickWall(), 880, 600);
        addObject(new SmBrickWall(), 420, 160);
        addObject(new SmBrickWall(), 1000, 200);
        addObject(new SmBrickWall(), 220, 280);
        addObject(new TrapDoor(GRAVITY), 60, 400);
        addObject(new TrapDoor(GRAVITY), 523, 167);
        addObject(new Bomb(GRAVITY), 1050, 765);
        addObject(new Gem(), 975, 160);
        addObject(new Gem(), 1030, 160);

        setPaintOrder(Player.class, Platform.class, Obstacle.class, Collectable.class,
            Door.class, HUD.class);
        player.setLocation(96,627);

    }
    
     private void spawn()
    {
        if(Math.random() < 0.0025)
        {
            addObject(new Rock(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
        if(Math.random() < 0.01)
        {
            addObject(new AcidRain(GRAVITY), Greenfoot.getRandomNumber(1200), -30);
        }
    }
    
    public void act()
    {
        spawn();
    }
}