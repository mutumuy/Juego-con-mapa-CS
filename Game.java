import java.util.ArrayList;
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
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        player = new Player();
        player.setCurrentRoom(createRooms());
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms()
    {
        Room spawnCT, z,middle, garage, car, tree, bombA, dor, larga, main, bombB, toxic, spawnT;
      
        // create the rooms
        spawnCT = new Room("in spawn antiterrorist");
        z = new Room("in Z");
        middle = new Room("in the middle of the map");
        garage = new Room("in the garage");
        car = new Room("behind the car");
        bombA = new Room("on the A site");
        dor = new Room("behind the door");
        larga = new Room("entrando por long");
        main = new Room("in main");
        tree = new Room("covering the tree");
        bombB = new Room("on the B site");
        toxic = new Room("in toxic");
        spawnT = new Room("on the T spawn");
        
        
        // initialise room exits n ,e ,s ,o, se, no
        spawnT.setExit("north", garage);
        spawnT.addItem("Un fusil AK-47 ", 4500, "AK-47", true);
        
        garage.setExit("north", middle);
        garage.setExit("east", main);
        garage.setExit("south", spawnT);
        garage.setExit("west", toxic);
        garage.setExit("northWest", bombB);
        garage.addItem( "Un fusil Galil ", 3600, "Galil", true);
        garage.addItem( "Una pistola Tec-9 ", 4500, "Tec-9", false);
        
        main.setExit("north", bombA);
        main.setExit("east", larga);
        main.setExit("west", garage);
        main.addItem("Una pistola Eagle ", 1500, "Eagle", false);
        
        larga.setExit("north", dor);
        larga.setExit("west", main);
        larga.addItem("Un fusil FAMAS ", 4500, "Famas", true );
        larga.addItem("Una pistola Eagle ", 1500 , "Eagle", true );       
        
        toxic.setExit("north", bombB); 
        toxic.setExit("south", garage);
        toxic.addItem("Una escopeta Recortada ", 3000, "Recortada", true);
        
        dor.setExit("south", larga);
        dor.setExit("west", bombA);
        
        bombA.setExit("north", car);
        bombA.setExit("east", dor);
        bombA.setExit("south", main);
        bombA.addItem("Una bomba C4 ", 1200, "C4", true);
        
        middle.setExit("north", z);
        middle.setExit("south", garage);
        middle.addItem("Un rifle AWP ", 6500, "AWP", true);
        middle.addItem("Un fusil AK-47 ", 4500, "AK-47", true);
        middle.addItem("Un fusil M4A4 ",4100, "M4A4", false);
        
        bombB.setExit("north", tree); 
        bombB.setExit("south", toxic);
        bombB.setExit("southEast", garage);
        bombB.addItem("Una bomba C4 ", 1200, "C4", true);
        
        car.setExit("south", bombA);
        car.setExit("west", z);
        car.addItem("Una escopeta NOVA ", 2800, "Nova", false);
        
        z.setExit("north", spawnCT);
        z.setExit("east", car);
        z.setExit("south", middle);
        z.setExit("west", tree);
        
        tree.setExit("east", z);
        tree.setExit("south", bombB);
        
        spawnCT.setExit("south", z);
        spawnCT.addItem("Un fusil M4A4 ",4100, "M4A4", false);
                
        
        return spawnT;  // start game outside
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
        System.out.println();
        player.look();
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
            player.goRoom(command);
        }
        else if (commandWord.equals("look")) {  
            player.look();
        }
        else if (commandWord.equals("eat")) {   
            player.eat();
        }
        else if (commandWord.equals("quit")) {  
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("back")) {  
            player.back();
        }
         else if (commandWord.equals("take")) {
            player.take(command);
        }
        else if (commandWord.equals("drop")) {
            player.drop(command);
        }
        else if (commandWord.equals("items")) {
            player.items();
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
        System.out.println(parser.showCommands());
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
    
}
