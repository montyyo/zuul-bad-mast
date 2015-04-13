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

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west,Room southEast,Room northEast) 
    {
        if(north != null)
            exits.put("north",  north);
        if(east != null)
            exits.put("east",  east);
        if(south != null)
             exits.put("south",  south);
        if(west != null)
             exits.put ("west",  west);
        if(southEast != null)
             exits.put("southEast", southEast);
        if(northEast != null)
             exits.put("northEast", northEast);
            
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
        Room roomToReturn = null;
            if(direction.equals("north"))
            {
                roomToReturn=exits.get("northExit");
            }
            else if(direction.equals("south"))
            {
                roomToReturn=exits.get("southExit");
            }
             else if(direction.equals("east"))
            {
                roomToReturn=exits.get("eastExit");
            }
             else if(direction.equals("southEast"))
            {
                roomToReturn=exits.get("southEastExit");
            }
            else if(direction.equals("northEast"))
            {
                roomToReturn=exits.get("northEastExit");
            }
        return roomToReturn;
    }
    
    public String getExitString()
    {
        String exitsDescription="";
        
        if(exits.get("northExit") != null)
        {
            exitsDescription += "north";
        }
        if(exits.get("southExit") != null)
        {
            exitsDescription += "south";
        }
        if(exits.get("eastExit") != null)
        {
            exitsDescription += "east";
        }
        if(exits.get("eastExit") != null)
        {
            exitsDescription += "west";
        }
        if (exits.get("southEastExit") != null) 
        {
            exitsDescription +="southEast";
        }
        if (exits.get("northEastExit") != null) 
        {
            exitsDescription +="northEast";
        }
        return exitsDescription;
    }
}
