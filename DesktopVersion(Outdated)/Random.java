/**
 * Write a description of class Random here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Random  
{
    // instance variables - replace the example below with your own
    //private int x;

    /**
     * Constructor for objects of class Random
     */
    public Random()
    {
    }


    public int nextInt(int max)
    {
        // put your code here
        int output = (int)(Math.random()*max);
        
        return output;
    }
}
