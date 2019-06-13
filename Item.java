
public class Item
{
    private String descripcion;
    private int peso;
    private String id;
    private boolean canBePickedUp;
    
    public Item(String descripcion, int peso, String id, boolean canBePickedUp)
    {
        this.descripcion= descripcion;
        this.peso=peso;
        this.id = id;
        this.canBePickedUp  = canBePickedUp ;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

     public int getPeso() {
        return peso;
    }
    
    public boolean getCanBePickedUp(){
        return canBePickedUp;
    }
    
    public String getId(){
        return id;
    }
}