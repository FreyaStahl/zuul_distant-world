import java.util.Set;
import java.util.HashMap;
/**
 * Class Place - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Place" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Place 
{
    private HashMap<String, Place> exits;
    private String description; 
    private boolean itemBool;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Place(String description) 
    {
        exits = new HashMap<String, Place>();
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room.
     * @param direction to the HashMap 
     * @param neighbour to the HashMap
     */
    public void setExits(String direction, Place neighbour) 
    {
       exits.put(direction, neighbour);
    }

    /**
     * @return The description of the place.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return The true or false value for an item or not
     */
    public boolean getBooleanItem()
    {
        return itemBool;
    }
    
    /**
     * @return The true or false value for an item or not
     */
    public boolean setBooleanItem(boolean bool)
    {
        itemBool = bool;
        return itemBool;
    }
    
    /**
     * Getter for the direction of exits
     * @param return exits.get(direction)
     */
    public Place getExit(String direction) {
        return exits.get(direction);
    }
    
    /**
     * Creates an keySet for every exits.
     * @return returnString
     */
    public String getExitString() {
      String returnString = "There are exits in: ";
      Set<String> keys = exits.keySet();
      for(String exit : keys) {
          returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Creates an String with the information
     * about the current descripiton of the place
     * and the available exits.
     * @return You are + description + 
     *           + getExitString
     */
    public String getLongDescription() {
        return "You are " + description + ".\n"
                + getExitString();
    }
}