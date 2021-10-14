import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ----------
 * 
 * @Tommy M.
 * @10/13
 */
public abstract class Obstacle extends Actor
{
    
    protected abstract void fall();
    
    protected boolean isOnFloor() {
        Actor floor = getOneObjectAtOffset(0,getImage().getHeight()/2, Floor.class);
        
        return floor != null;
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
        if(obstacle.getY() > getWorld().getHeight() + obstacle.getImage().getWidth() / 2) {
            getWorld().removeObject(obstacle);
        }
    
    }
}
