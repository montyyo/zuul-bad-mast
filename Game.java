import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Stack<Room> room;
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
        room = new Stack<>();
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room pasillo, almacen, laboratorio, despacho, banio,corredor,salida,entrada;
      
        // create the rooms
        entrada = new Room("entrada del juego");
        entrada.addItem(new Item("pergola",23F,true));
        entrada.addItem(new Item("papelera",8F,false));
       
        
        salida = new Room("outside the main entrance of the university");
        salida.addItem(new Item("tablon",23F,true));
        salida.addItem(new Item("rampa",8F,false));
        
        almacen = new Room("in a stock");
        almacen.addItem(new Item("ropa",34F,true));
        almacen.addItem(new Item("maniqui",68F,true));
        
        pasillo= new Room("in the outside");
        pasillo.addItem(new Item("extintor",9F,true));
        pasillo.addItem(new Item("taquilla",8F,true));
        
        laboratorio = new Room("in a computing lab");
        laboratorio.addItem(new Item("probetas",9F,true));
        laboratorio.addItem(new Item("esqueleto",8F,false));
        
        despacho = new Room("in the computing admin office");
        despacho.addItem(new Item("mapas",9F,false));
        despacho.addItem(new Item("PC",8F,false));
        
        banio = new Room("bathroom" );
        laboratorio.addItem(new Item("probetas",9F,false));
        laboratorio.addItem(new Item("esqueleto",8F,true));
        
        corredor = new Room("subway");
        corredor.addItem(new Item("caja",9F,true));
        corredor.addItem(new Item("esqueleto",8F,true));
        
        // initialise room exits
        //salidas entrada
        entrada.setExit("east", pasillo);
        //salidas pasillo
        pasillo.setExit("north", despacho);
        pasillo.setExit("east", almacen);
        pasillo.setExit("west",  entrada);
        //salidas almacen
        almacen.setExit("north", laboratorio);
        almacen.setExit("west", pasillo);
        //salidas laboratorio
        laboratorio.setExit("north", banio);
        laboratorio.setExit("westh", despacho);
        //salidas despacho
        despacho.setExit("north",corredor);
        despacho.setExit("east", laboratorio);
        despacho.setExit("southeast", almacen);
        despacho.setExit("south", pasillo);
        //salidas banio
        banio.setExit("shouth",  laboratorio);
        banio.setExit("east", corredor);
        //salidas corredor
        corredor.setExit("east" ,banio );
        corredor.setExit("southeast", despacho);
        corredor.setExit( "shouth", laboratorio);
        corredor.setExit("west", salida);
                
        //salida salida
        salida.setExit("east", corredor);
        
        
        player.setRoom(entrada);  // start game outside
         
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " );
        player.lookRoom();
        System.out.print("Exits: ");
       
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
           
            
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look"))
        {
            player.lookRoom();
        }
        else if(commandWord.equals("eat"))
        {
            player.eat();
        }
        else if(commandWord.equals("back"))
        {
            player.backRoom();
            player.lookRoom();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
       // parser.getCommands().showAll();
       parser.showCommands();
    }

   

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
   private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        player.goRoom(command.getSecondWord());
        player.lookRoom();
    }
}
