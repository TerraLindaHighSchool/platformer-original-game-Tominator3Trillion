import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
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
    int targetDestination;
    Random rand = new Random();
    
    boolean atDestination = false;
    
    public boolean bombDetonated = false;
    
    public FrontHole fh;
    public BackHole bh;
    

    public void act()
    {
        if(frame==0) {
            targetDestination = getWorld().getHeight() - 100;
            fh = new FrontHole();
            bh = new BackHole();
            getWorld().addObject(fh,getX()-5, getY()-10);
            getWorld().addObject(bh,getX()-5, getY()-10);
        }
        if(frame==605) {
            Greenfoot.playSound("sirenExplosion.mp3");
        }
        
        if(getY() > targetDestination) {
            if(frame%5==0) {
                setLocation(getX(),getY() - 1);
                fh.setLocation(fh.getX(),fh.getY() - 1);
                bh.setLocation(bh.getX(),bh.getY() - 1);
            }
        } else {
            if(!atDestination) {
                Greenfoot.playSound("rocketLaunch.mp3");
                getWorld().addObject(new LaunchNuke(),getX(), getY()-30);
                
            }
            atDestination = true;
        }
        if(bombDetonated && f == 0) {
            getWorld().addObject(new Flag(),fh.getX(), fh.getY()-50);
            getWorld().removeObject(fh);
            getWorld().removeObject(bh);
        }
        if(bombDetonated && f < 1500) {
            int hillShake = rand.nextInt(3)-1;
            for(Actor o : getWorld().getObjects(Actor.class)) {
                if(!(o instanceof FlashBang || o instanceof Explosion|| o instanceof Floor|| o instanceof MissileHill || o instanceof MoneyBox|| o instanceof HUD|| o instanceof Flag )) {
                    o.setLocation(o.getX()+rand.nextInt(11)-5, o.getY()-rand.nextInt(2));
                } if(o instanceof Floor) {
                    ((Floor)o).scroll(rand.nextInt(11)-5);
                }
                if(o instanceof MoneyBox) {
                    o.setLocation(o.getX()+rand.nextInt(11)-5, o.getY());
                }
                if(o instanceof MissileHill|| o instanceof Flag) {
                    o.setLocation(o.getX()+hillShake, o.getY());
                }
            }
            f++;
        }
        frame++;
    }
}
