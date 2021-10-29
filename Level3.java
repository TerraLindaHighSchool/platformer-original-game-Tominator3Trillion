import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level3 extends World
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    private final float GRAVITY = 0.0667f;
    private final GreenfootSound MUSIC = null;
    private int SPEED = 3;
    private final float JUMP_FORCE = 5.6f;
    private int MAX_HEALTH = 10;
    private int MAX_POWERUP = 3;
    private Class NEXT_LEVEL = Level4.class;
    
    
    public static String bgImageName;
    private int scrollFrame = 0;
    private static int PIC_WIDTH;
    public static GreenfootImage bgImage, bgBase;
    public static int scrollPosition;
    
    private int LEVEL_WIDTH = 2000;

     
    
    
    public Level3()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1, false); 
        bgImageName = "indoor1.png";
        PIC_WIDTH = (new GreenfootImage(bgImageName)).getWidth();
        setBackground(bgImageName);
        bgImage = new GreenfootImage(getBackground());
        bgBase = new GreenfootImage(PIC_WIDTH, getHeight());
        scrollPosition = 1;
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
        addObject(player,95,600);
        Floor floor = new Floor("floor_metal.png");
        addObject(floor,600,680);
        CloseBackground cb = new CloseBackground("coalPiles.png", 1f);
        addObject(cb,2000,450);

        OutsideBuilding outsideBuilding = new OutsideBuilding();
        addObject(outsideBuilding,5000,155);

        TallWall tallWall = new TallWall();
        addObject(tallWall,1200,300);
        
        TallWall tallWall2 = new TallWall();
        addObject(tallWall2,-150,300);

        SmBrickWall smBrickWall = new SmBrickWall();
        addObject(smBrickWall,1500,300);

        SmBrickWall smBrickWall2 = new SmBrickWall();
        addObject(smBrickWall2,678,350);

        BrickWall brickWall = new BrickWall();
        addObject(brickWall,820,500);

        SmBrickWall smBrickWall3 = new SmBrickWall();
        addObject(smBrickWall3,589,732);

        SmBrickWall smBrickWall4 = new SmBrickWall();
        addObject(smBrickWall4,837,230);

        SmallBuilding smallBuilding = new SmallBuilding();
        addObject(smallBuilding,2052,345);

        SmallBuilding smallBuilding2 = new SmallBuilding();
        addObject(smallBuilding2,2836,415);
        
        SmallBuilding smallBuilding3 = new SmallBuilding();
        addObject(smallBuilding3,3700,515);


        BrickWall brickWall2 = new BrickWall();
        addObject(brickWall2,2454,160);

        Door door = new Door();
        addObject(door,4515,613);


        


        setPaintOrder(FlashBang.class, Explosion.class,HUD.class, Player.class,Door.class, Platform.class,AcidPool.class, Obstacle.class, Collectable.class
        ,MoneyBox.class, Flag.class, FrontHole.class, LaunchNuke.class,Smoke.class,BackHole.class, MissileHill.class, Nuke.class);
        //player.setLocation(96,627);


        //addObject(new MoneyBox(),2000,600);

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
        bg.drawImage(bgImage, position - PIC_WIDTH, 0);
    }
    
    public void scroll(double scrollSpeed)
    {
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
    
    public void act()
    {
        if(!MoneyBox.hasBeenPressed) {
            spawn();
        }
        scrollFrame++;
    }
}
