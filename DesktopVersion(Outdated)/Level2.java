import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level2 extends World
{

    /**
     * Constructor for objects of class Level1.
     * 
     */
    private final float GRAVITY = 0.0667f;
    private final GreenfootSound MUSIC = null;
    private int SPEED = 3;
    private final float JUMP_FORCE = 5.6f;
    private int MAX_HEALTH = 3;
    private int MAX_POWERUP = 1;
    private Class NEXT_LEVEL = Level3.class;
    
    
    public static String bgImageName;
    private int scrollFrame = 0;
    private static int PIC_WIDTH;
    public static GreenfootImage bgImage, bgBase;
    public static int scrollPosition;
    
    private int LEVEL_WIDTH = 2000;

    private GreenfootSound m1 = new GreenfootSound("factory1.mp3");
    
    
    public Level2()
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
        
        CloseBackground cb = new CloseBackground("longFence.png", 1f);
        addObject(cb,1000,450);


        OutsideBuilding outsideBuilding = new OutsideBuilding();
        addObject(outsideBuilding,4746,155);

        TallWall tallWall = new TallWall();
        addObject(tallWall,3600,415);
        TallWall tallWall3 = new TallWall();
        addObject(tallWall3,3600,100);
        
        TallWall tallWall2 = new TallWall();
        addObject(tallWall2,-150,415);

        SmBrickWall smBrickWall = new SmBrickWall();
        addObject(smBrickWall,1700,300);
        
        SmBrickWall smBrickWall5 = new SmBrickWall();
        addObject(smBrickWall5,1400,300);

        SmBrickWall smBrickWall2 = new SmBrickWall();
        addObject(smBrickWall2,500,325);

        BrickWall brickWall = new BrickWall();
        addObject(brickWall,100,464);

        SmBrickWall smBrickWall3 = new SmBrickWall();
        addObject(smBrickWall3,400,500);

        SmBrickWall smBrickWall4 = new SmBrickWall();
        addObject(smBrickWall4,900,230);

        SmallBuilding smallBuilding = new SmallBuilding();
        addObject(smallBuilding,2052,415);

        SmallBuilding smallBuilding2 = new SmallBuilding();
        addObject(smallBuilding2,3200,415);

        BrickWall brickWall2 = new BrickWall();
        addObject(brickWall2,2350,300);

        Door door = new Door();
        addObject(door,4270,613);

        
        Phasable phasable = new Phasable();
        addObject(phasable,3600,607);

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
    
    public void nextLevel() {
        m1.stop();
    }
    
    public void act()
    {
        if(scrollFrame==1) {
            m1.playLoop();
        }
        
        if(!MoneyBox.hasBeenPressed) {
            spawn();
        }
        scrollFrame++;
    }
}
