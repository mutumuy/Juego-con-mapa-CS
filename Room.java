import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
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
    private ArrayList<Item> armas;

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
        armas = new ArrayList<>();
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
                if(armas.isEmpty()) {
            cadenaADevolver = "La sala no contiene ningun arma";
        } else {
            cadenaADevolver = "En la sala hay las siguintes armas: \n";
            for(Item itemActual : armas) {
                cadenaADevolver += itemActual.getDescripcion() + " con un peso de " + itemActual.getPeso() + " gramos" + "\n";
            }
        }                  
        return cadenaADevolver;
    }
    
    public void addItem(String descripcion, int peso, String id, boolean canBePickedUp) {
        Item objetoAAgregar = new Item (descripcion , peso, id, canBePickedUp);
        armas.add(objetoAAgregar);
    }
    
    public Item getItem(String id){
        boolean buscando = true;
        int position = 0;
        Item itemToReturn = null;
        while (buscando && armas.size() > position){
            if (armas.get(position).getId().equals(id)){
                itemToReturn = armas.get(position);
                buscando = false;
            }
            position++;
        }
        return itemToReturn;
    }

    public void removeItem(Item item){
        armas.remove(item);
    }
}
