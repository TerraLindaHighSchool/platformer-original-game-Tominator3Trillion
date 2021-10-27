import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class MissileHill here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MissileHill extends Actor
{
    int frame = 0;
    int f = 0;
    int launchFrame = 0;
    int destinationDelay = 0;
    int targetDestination;
    Random rand = new Random();
    
    boolean atDestination = false;
    
    public boolean bombDetonated = false;
    
    public FrontHole fh;
    public BackHole bh;
    
    GreenfootSound siren = new GreenfootSound("sirenExplosion.mp3");
    GreenfootSound launchStartup = new GreenfootSound("launchStartup.mp3");
    

    public MissileHill() {
        launchStartup.setVolume(100);
    }
    
    public void act()
    {
        if(frame==0) {
            targetDestination = getWorld().getHeight() - 100;
            fh = new FrontHole();
            bh = new BackHole();
            getWorld().addObject(fh,getX()+15, getY()+20);
            getWorld().addObject(bh,getX()+15, getY()+20);
            Greenfoot.playSound("groundShake.mp3");
            Greenfoot.playSound("risingNoise.mp3");
            getWorld().addObject(new CinematicBar(true),600, -200);
            getWorld().addObject(new CinematicBar(false), 600, 900);
            //Greenfoot.playSound("buttonStartup.mp3");
        }
        if(frame==245) {
            //Greenfoot.playSound("mechanicSound1.mp3");
        }
        if(frame==1300) {
            siren.play();
        }
        
        if(getY() > targetDestination) {
            if(frame%4==0) {
                setLocation(getX(),getY() - 1);
                fh.setLocation(fh.getX(),fh.getY() - 1);
                bh.setLocation(bh.getX(),bh.getY() - 1);
            }
        } else {
            
            atDestination = true;
        }
        if(atDestination) {
            destinationDelay++;
        }
        if(atDestination && destinationDelay > 170) {
            launchFrame++;
        }
        if(launchFrame == 1) {
            launchStartup.play();
        }
        if(launchFrame == 100) {
            //Greenfoot.playSound("risingNoise.mp3");
        }
        if(launchFrame < 400 && launchFrame > 100) {
            int hillShake = rand.nextInt(3)-1;
            int groundShake = rand.nextInt(7)-3;
            for(Actor o : getWorld().getObjects(Actor.class)) {
                if(o instanceof CinematicBar) {
                    continue;
                }
                 if(o instanceof Floor) {
                    ((Floor)o).scroll(-groundShake);
                } else 
                if(o instanceof MoneyBox || o instanceof Player) {
                    o.setLocation(o.getX()+groundShake, o.getY());
                } else
                if(o instanceof MissileHill|| o instanceof Flag || o instanceof FrontHole || o instanceof BackHole || o instanceof LaunchNuke) {
                    o.setLocation(o.getX()+hillShake, o.getY());
                } else if(!(o instanceof FlashBang || o instanceof Explosion|| o instanceof HUD || o instanceof Smoke)) {
                    o.setLocation(o.getX()+rand.nextInt(7)-3, o.getY());
                }
            }
        }
        if(launchFrame == 200) {
            getWorld().addObject(new LaunchNuke(),bh.getX(), bh.getY()+55);
        }
        
        if(bombDetonated && f == 0) {
            getWorld().addObject(new Flag(),fh.getX(), fh.getY()-80);
            getWorld().removeObject(fh);
            getWorld().removeObject(bh);
        }
        if(bombDetonated && f < 1420) {
            int hillShake = rand.nextInt(3)-1;
            int groundShake = rand.nextInt(11)-5;
            for(Actor o : getWorld().getObjects(Actor.class)) {
                 if(o instanceof Floor) {
                    ((Floor)o).scroll(-groundShake);
                } else 
                if(o instanceof MoneyBox || o instanceof Player) {
                    o.setLocation(o.getX()+groundShake, o.getY());
                    if(o instanceof Player) {
                        o.setLocation(o.getX(), o.getY()-rand.nextInt(2));
                    }
                } else
                if(o instanceof MissileHill|| o instanceof Flag || o instanceof FrontHole || o instanceof BackHole || o instanceof LaunchNuke) {
                    o.setLocation(o.getX()+hillShake, o.getY());
                } else if(!(o instanceof FlashBang || o instanceof Explosion|| o instanceof HUD || o instanceof Smoke)) {
                    o.setLocation(o.getX()+rand.nextInt(11)-5, o.getY()-rand.nextInt(2));
                }
            }
            f++;
        }
        frame++;
    }
}