import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    //atributos de publico a privado para reducir el acoplamiento
    
    private String description;
    //     private Room northExit;
    //     private Room southExit;
    //     private Room eastExit;
    //     private Room westExit;
    //     private Room southEastExit;
    //     private Room northEastExit;
    
    private HashMap<String, Room> exits;
   
    private ArrayList<Item> items;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<>();
        this.items=new ArrayList<>();
       
    }

    public void  addItem(Item item)
    {
       
        items.add(item);
    }
    
    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */

    
    public void setExit(String direction , Room nextRoom)
    {
        
        exits.put(direction,nextRoom);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction)
    {
        //obtencion de los atributos 
        //comparacion de cadenas --> equals
        
        return exits.get(direction);
    }
    
    public String getExitString()
    {
        Set<String> nameOfDirection = exits.keySet();
        String exitsDescription = "Exit ";
        for( String direction : nameOfDirection)
        {
            exitsDescription += direction + "  " ;
        }
        
        return exitsDescription;
    }
    
    public String showAllItem()
    {
        String itemDescription = null;
        for(Item itemsDesc : items)
        {
            itemDescription +=  itemsDesc.toString() + "\n";
        }
        return itemDescription;
    }
    
    public String getLongDescription()
    {
        String longDescription =  "you are " + description + " .\n" + getExitString()+"\n";
        
        longDescription += showAllItem();
        return longDescription;
    }
    
    //0118
}
