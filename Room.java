import java.util.HashMap;

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
    private String description;    
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
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northWest) 
    {
        if(north != null)
            exits.put("north", north);
        if(east != null)
            exits.put("south", south);
        if(south != null)
            exits.put("east", east);
        if(west != null)
            exits.put("west", west);
        if(southEast != null)
            exits.put("southEast", southEast);
        if(northWest != null)
            exits.put("northWest", northWest);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public Room getExit(String direction){
        Room roomToReturn = null;
        if(direction.equals("north")){
            roomToReturn = exits.get("north");
        }
        if(direction.equals("south")){
            roomToReturn = exits.get("south");
        }
        if(direction.equals("east")){
            roomToReturn = exits.get("east");
        }
        if(direction.equals("west")){
            roomToReturn = exits.get("west");
        }
        if(direction.equals("southEast")){
            roomToReturn = exits.get("southEast");
        }
        if(direction.equals("northWest")){
            roomToReturn = exits.get("northWest");
        }
        
        return roomToReturn;
    }
    
    public String getExitString(){
        String exitsDescription = "Exist: ";
        if(exits.get("north") != null)
            exitsDescription += "north ";
        if(exits.get("south") != null)
            exitsDescription += "south ";
        if(exits.get("east") != null)
            exitsDescription += "east ";
        if(exits.get("west") != null)
            exitsDescription += "west ";
        if(exits.get("southEast") != null)
            exitsDescription += "southEast ";
        if(exits.get("northWest") != null)
            exitsDescription += "northWest ";
        
        return exitsDescription;
    }

}
