import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Obstacle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Obstacle extends Actor
{
    
    protected boolean isDeleted = true;
    
    protected abstract void fall();
    
    protected boolean isOnFloor() {
        Actor floor = getOneObjectAtOffset(0,getImage().getHeight()/2, Platform.class);
        
        return floor != null &&(floor instanceof Floor || floor instanceof SmallBuilding);
    }
    
    protected boolean isOnGround() {
        Actor ground = getOneObjectAtOffset(0,getImage().getHeight()/2, Platform.class);
        
        return ground != null;
    }
    
    protected boolean isOnPlayer() {
        Actor player = getOneObjectAtOffset(0,getImage().getHeight()/2, Player.class);
        
        return player != null;
    }
    
    protected void removeOutOfBounds(Obstacle obstacle)  {

        if(!isDeleted && obstacle.getY() > getWorld().getHeight() + obstacle.getImage().getWidth() / 2) {
            getWorld().removeObject(obstacle);
        }

    }
}
