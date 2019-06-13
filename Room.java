import java.util.HashMap;
import java.util.Set;
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
    private Item arma;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description, String descripcion, int peso) 
    {
        this.description = description;
        exits = new HashMap<>();
        arma = new Item(descripcion, peso);
    }
    
    public void setExit(String direction, Room nextRoom){
        exits.put(direction, nextRoom);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public Room getExit(String direction){
        return exits.get(direction);
    }
    
    public String getExitString(){
        Set<String> namesOfDirection = exits.keySet();
        String exitsDescription = "Exit: ";
        for(String direction: namesOfDirection){
            exitsDescription += direction + " ";
        }
        return exitsDescription;
    }
    
    /**
     * Devuelve un texto con la descripcion larga de la habitacion del tipo:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return Una descripcion de la habitacion incluyendo sus salidas
     */
    public String getLongDescription(){
        return "You are " + description + ".\n"+ getDescripcionArma() + "\n" + getExitString();
    }
    
     public String getDescripcionArma() {
        String cadenaADevolver = "";
        cadenaADevolver += "En esta sala hay " + arma.getDescripcion() + "con un peso de " + arma.getPeso() + " gramos";
        return cadenaADevolver;
    }
}
