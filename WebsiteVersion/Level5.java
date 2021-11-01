import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Level5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level5 extends World
{

    /**
     * Constructor for objects of class Level5.
     * 
     */
    private final float GRAVITY = 0.0667f;
    private final GreenfootSound MUSIC = null;
    private int SPEED = 3;
    private final float JUMP_FORCE = 5.6f;
    private int MAX_HEALTH = 3;
    private int MAX_POWERUP = 1;
    private Class NEXT_LEVEL = Level2.class;
    
    
    public static String bgImageName;
    public static double scrollSpeed;
    private int scrollFrame = 0;
    private static int PIC_WIDTH;
    public static GreenfootImage bgImage, bgBase;
    public static int scrollPosition;
    
    private int LEVEL_WIDTH = 2000;
    
    private MegaPhone megaPhone;

    GreenfootSound m1 = new GreenfootSound("city.mp3");
    
    
    public Level5()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 700, 1, false); 
        bgImageName = "skyBox1.png";
        PIC_WIDTH = (new GreenfootImage(bgImageName)).getWidth();
        scrollSpeed = 0.1f;
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
        addObject(player,150,600);
        Floor floor = new Floor("floor_grass.png");
        addObject(floor,600,680);
        
        CloseBackground cb = new CloseBackground("city.png", 0.25f);
        addObject(cb,1500,305);
        
        CloseBackground cb2 = new CloseBackground("haze.png", 0f);
        addObject(cb2,600,350);
        
        SmallBuilding outsideBuilding = new SmallBuilding();
        addObject(outsideBuilding,-200,415);
        


        TallWall tw = new TallWall();
        addObject(tw, 23,-20);
        TallWall tw2 = new TallWall();
        addObject(tw2, -2000,415);
        TallWall tw3 = new TallWall();
        addObject(tw3, 5000,415);

        DontPressSign dps = new DontPressSign();
        addObject(dps,4000,410);
        
        PressM pm = new PressM();
        addObject(pm,3600,300);
        
        PressM pm2 = new PressM();
        addObject(pm2,-1200,300);
        
        MoneyBox moneyBox = new MoneyBox();
        addObject(moneyBox,100,605);//4000
        
        SelfDestructionBox selfDestructionBox = new SelfDestructionBox();
        addObject(selfDestructionBox,300,605);//-1000

        TallWall tallWall = new TallWall();
        addObject(tallWall,1076,415);
        

        SmBrickWall smBrickWall = new SmBrickWall();
        addObject(smBrickWall,1500,300);

        SmBrickWall smBrickWall2 = new SmBrickWall();
        addObject(smBrickWall2,400,325);

        BrickWall brickWall = new BrickWall();
        addObject(brickWall,820,464);

        SmBrickWall smBrickWall3 = new SmBrickWall();
        addObject(smBrickWall3,400,569);

        SmBrickWall smBrickWall4 = new SmBrickWall();
        addObject(smBrickWall4,900,230);

        SmallBuilding smallBuilding = new SmallBuilding();
        addObject(smallBuilding,2052,415);

        SmallBuilding smallBuilding2 = new SmallBuilding();
        addObject(smallBuilding2,2836,415);

        BrickWall brickWall2 = new BrickWall();
        addObject(brickWall2,2454,160);



        AcidPool acidPool = new AcidPool();
        addObject(acidPool,2442,565);
        
        Phasable phasable = new Phasable();
        addObject(phasable,1075,607);
        
        Phasable phasable2 = new Phasable();
        addObject(phasable2,23,200);
        
        megaPhone = new MegaPhone(new GreenfootSound("speechNoTime.mp3"));
        addObject(megaPhone,60,300);
        
        setPaintOrder(Credits.class, FadeToBlack.class,FlashBang.class, Explosion.class, CinematicBar.class, HUD.class, Player.class,Door.class, SelfDestructionBox.class, Platform.class,AcidPool.class, Obstacle.class, Collectable.class
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
    }
    
    public void nextLevel() {
        m1.stop();
    }
    
    public void act()
    {
        if(scrollFrame==1) {
            megaPhone.play();
            m1.playLoop();
        }
        
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
        if(!MoneyBox.hasBeenPressed) {
            spawn();
        }
        scrollFrame++;
    }
}
