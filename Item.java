import java.util.ArrayList;
 /**
  * Item - create a item with the properties - position, name and the weight
  */
public class Item
{
    private Place itemPosition;
    private String itemName ;
    private int itemWeight;
        
    /**
     * Set the item position
     */
    public void setItemPosition(Place itemPosition){
        this.itemPosition = itemPosition;
    }
    
    /**
     *return the position from item
     */
    public Place getItemPosition(){
        return itemPosition;
    }
    
    /**
     * return the name from the item
     */
    public String getItemName(){
        return itemName;
    }
  
    /**
     *  return the name from the item 
     */
    public String setItemName(String word){
        itemName = word ;
        return itemName;
    }
    
    /**
     *  Set the item weight
     */
    public int setItemWeight(int itemW ){
        itemWeight = itemW;
        return itemWeight;
    }
    
    /**
     * return the item weight
     */
     public int getItemWeight(){
         return itemWeight;
    }
}