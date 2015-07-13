import java.util.ArrayList;
/**
 * The Gamer class save the data from the bag and you have a 
 * Health counter 
 * 
 * @author Freya Stahl
 * @version 1
 */
public class Gamer
{
    private ArrayList<Item> pocket;
    public int health ;
    private Item item = new Item();

    public Gamer(){
        health = 100;
        pocket = new ArrayList<Item>();
    }
  
    /**
     * return your current health
     */
    public int getHealth(){
        return health ;
    }
    
    /**
     * Set the  current health
     */
     public int setHealth(int currenthealth){
        health = currenthealth;
        return health ;
    }
        
    /**
     * Add the new Item and put that in the pocket
     */
    public void addItem(Item item){
        pocket.add(item);
    }

    /**
     *  return the ArrayList "pocket"
     */
    public void getPocket(){
        for (int i =0; i != pocket.size(); i++){
            System.out.println("Item : "+ pocket.get(i).getItemName());
        }
    }
   
    public void removeItem(Item it){
        pocket.remove(it);
    }
}
