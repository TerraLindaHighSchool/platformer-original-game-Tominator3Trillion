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
    private int SPEED = 3;
    private final float JUMP_FORCE = 5.6f;
    private int MAX_HEALTH = 3;
    private int MAX_POWERUP = 3;
    private Class NEXT_LEVEL = Level2.class;
    
    
    private static final String BG_IMAGE_NAME = "skyBox.png";
    private static final double SCROLL_SPEED = 1.25;
    private static final int PIC_WIDTH = (new GreenfootImage(BG_IMAGE_NAME)).getWidth();
 
    private GreenfootImage bgImage, bgBase;
    private int scrollPosition = 0;
    
    private int LEVEL_WIDTH = 2000;

     
    
    
    public Level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1, false); 
        setBackground(BG_IMAGE_NAME);
        bgImage = new GreenfootImage(getBackground());
        bgBase = new GreenfootImage(PIC_WIDTH, getHeight());
        bgBase.drawImage(bgImage, 0, 0);
        prepare();
    }
    
     
     
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */

    private void prepare()
    {

        Player player = new Player(SPEED,JUMP_FORCE, GRAVITY, MAX_HEALTH, MAX_POWERUP, NEXT_LEVEL, MUSIC);
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
        addObject(new TrapDoor(GRAVITY), 470, 160);
        addObject(new Bomb(GRAVITY), 1050, 765);
        addObject(new Gem(), 975, 160);
        addObject(new Gem(), 1030, 160);

        setPaintOrder(Player.class, Platform.class, Obstacle.class, Collectable.class,
            Door.class,MoneyBox.class, MissileHill.class, HUD.class);
        player.setLocation(96,627);

        Bomb bomb2 = new Bomb(1);
        addObject(bomb2,608,76);
        MoneyBox moneyBox = new MoneyBox();
        addObject(moneyBox,200,600);

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
    
    private void paint(int position)
    {
        GreenfootImage bg = getBackground();
        bg.drawImage(bgBase, position, 0);
        bg.drawImage(bgImage, position + PIC_WIDTH, 0);
    }
    
    public void act()
    {
        scrollPosition -= SCROLL_SPEED;
        while(SCROLL_SPEED > 0 && scrollPosition < -PIC_WIDTH) scrollPosition += PIC_WIDTH;
        while(SCROLL_SPEED < 0 && scrollPosition > 0) scrollPosition -= PIC_WIDTH;
        paint(scrollPosition);
        spawn();
    }
}
