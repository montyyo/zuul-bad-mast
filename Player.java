import java.util.*;

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public class Player
{
    
    private Room actualRoom;
    private Stack<Room> rooms;
    
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        actualRoom = null;
        rooms = new Stack<>();
    }

    //habitacion actual
   public Room getActualRoom()
   {
       return actualRoom;
   }
   
   //cambio de habitacion
   public void setRoom(Room room)
   {
       actualRoom = room;
   }
   
   public void eat()
    {
        System.out.println("you have eaten now and you not hungry any more");
    }
   
   //mirar habitacion
   public void lookRoom()
   {
       System.out.println(actualRoom.getLongDescription());
   }
   
   //comprueba si la pila de habitaciones esta vacia
   public boolean emptyRoom()
   {
       return rooms.empty();
    }
    
    //Quita el objeto en la parte superior de esta pila y vuelve ese objeto 
   public Room removeRoom()
   {
       return rooms.pop();
   }
    
   //habitacion anterior
   public void backRoom()
   {
       if( emptyRoom()==true)
           {
               System.out.println(" NO HAY MAS HABITACIONES SE ENCUENTRA EN EL INICIO DEL JUEGO");
           }
           else
           {
               //eliminamos la habitacion anterior
              actualRoom = removeRoom();
               
           }
   }
   
   //siguiente habitacion
   public void goRoom(String direction)
   {
       

        // Try to leave current room.
       

        Room nextRoom = actualRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            //añadimos un elemento nuevo  a la pila
            rooms.push(actualRoom);
            actualRoom = nextRoom;
            
           
        }
   }
}
