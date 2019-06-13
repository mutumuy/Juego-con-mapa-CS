import java.util.ArrayList;
import java.util.Stack;
public class Player
{
    // instance variables - replace the example below with your own
    private Room currentRoom;
    private Stack<Room> roomStack;
    private ArrayList<Item> bag;
    private int bagWeigth;
    private static int MAXWEIGTH = 10000;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables
        currentRoom = null;
        roomStack = new Stack<Room>();
        bag = new ArrayList<>();
        bagWeigth = 0;
    }

    public void setCurrentRoom(Room room){
        currentRoom = room;
    }

    
    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Esa sala no existe");
        }
        else {
            roomStack.push(currentRoom);
            // Try to leave current room.
            currentRoom = currentRoom.getExit(direction);
            System.out.println(currentRoom.getLongDescription());
            System.out.println();
            look();
        }
    }

    
    public void look() {   
        System.out.println(currentRoom.getLongDescription());       
    }

    
    public void back() 
    {
        if (!roomStack.empty()){
            currentRoom = roomStack.pop();
            look();
        }
        else{
            System.out.println("No puedes volver atras, estas en la posicion inicial");
        }
    }

    
    public void eat() 
    {
        System.out.println("You have eaten now and you are not hungry any more");
    }

    public void take(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the item to take...
            System.out.println("No has indicado el ID del arma");
            return;
        }
        String positionItem = command.getSecondWord();
        Item itemToTake = currentRoom.getItem(positionItem);

        if (itemToTake != null && bagWeigth + itemToTake.getPeso() < MAXWEIGTH ){
            System.out.println("Has cogido " + "\n");
            System.out.println(itemToTake.getDescripcion() + "con un peso de " + itemToTake.getPeso()+ " gramos");
            bagWeigth += itemToTake.getPeso();
            bag.add(itemToTake);
            currentRoom.removeItem(itemToTake);
        }

        else{            
            if (itemToTake == null){
                System.out.println("No hay objetos en la habitacion");
            }
            else{
                System.out.println("Te has pasado del peso de la mochila");
            }                       
        }
    }

    public void drop(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know the item to take...
            System.out.println("No has indicado el ID del arma");
        }
        String itemID = command.getSecondWord();
        Item itemToDrop = null;
        int cont = 0;
        while (itemToDrop == null && bag.size() > cont){
            if (bag.get(cont).getId().equals(itemID)){
                itemToDrop = bag.get(cont);
            }
            cont++;
        }

        if (bag.size() > 0 && itemToDrop != null){
            System.out.println("Has dejado el siguiente arma:" + "\n");
            System.out.println(itemToDrop.getDescripcion() + "con un peso de " + itemToDrop.getPeso()+ " gramos");
            bagWeigth -= itemToDrop.getPeso();
            bag.remove(itemToDrop);
            currentRoom.addItem(itemToDrop.getDescripcion() , itemToDrop.getPeso(), itemToDrop.getId(), itemToDrop.getCanBePickedUp());
        }
        else{
            System.out.println("No hay armas en la mochila para dejar");
        }
    }

    public void items(){
        if (bag.size() > 0){
            System.out.println("Llevas: ");
            for (int i = 0; i < bag.size(); i++){
                System.out.println(bag.get(i).getDescripcion() + "con un peso de " + bag.get(i).getPeso()+ " gramos");
            }
        }
        else{
            System.out.println("Tu mochila esta vacia");
        }
    }
    
    public void aumentarPeso(){
        MAXWEIGTH = 15000;
    }
        
    public void equipBackpack(){
        System.out.print("Ahora puedes llevar mas peso en tu inventario");
        aumentarPeso();
    }
    
} 