
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private String name;
    private float weight;
    /**
     * Constructor for objects of class Item
     */
    public Item(String name,float weight)
    {
        this.name=name;
        this.weight = weight;
    }

   
    public String getName()
    {
        return name;
    }
    
    public float getWeight()
    {
        return weight;
    }
    
    public String toString()
    {
        return " nombre = " + name + "\n"+ "peso = " + weight;
    }
}
