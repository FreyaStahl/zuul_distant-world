import java.util.ArrayList;
import java.util.Random ;
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
 * @author  Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class Game 
{
    private Parser parser;
    private Place currentPlace;    
    private Place lastPlace, twiceBack;
    private Item item = new Item();
    private ArrayList itemlist;
    private Gamer gamer;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game(){
        createPlaces();
        parser = new Parser();
        gamer = new Gamer();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createPlaces()
    {
        Place forest , northern_forest, southern_forest, spooky_house, spider_burrow;

        // create the rooms
        forest  = new Place("in the middle of a big dark forest.");
        northern_forest = new Place("in the north part of the forest.");
        southern_forest = new Place("in the south part of the forest");
        spooky_house = new Place("right in front of a abandoned house.");
        spider_burrow = new Place("near a location with a incredible spider burrow.");

        // initialise room exits
        forest.setExits("north", northern_forest);
        forest.setExits("east", spooky_house);
        forest.setExits("south", southern_forest);
        forest.setExits("west", spider_burrow);

        northern_forest.setExits("south", forest);

        spooky_house.setExits("west", forest);
        spooky_house.setExits("north", northern_forest);
        southern_forest.setExits("north", forest);

        spider_burrow.setExits("east", forest);

        currentPlace = forest ;  // start game forest 
    
        //set a random item in forest
        item = getItem();
        item.setItemPosition(forest);    
        //
        forest.setBooleanItem(true);
    }

    /**
     * 
     */
    private Item getItem(){
        //Set an ArrayList
        itemlist =  new ArrayList();
        //create items 
        Item shild = new Item();
        Item nightcap  = new Item();
        Item goldcoin  = new Item();
        Item spider  = new Item();
        Item shoes  = new Item();
        Item mermScale  = new Item();
        Item woodBlock  = new Item();
        Item lamp  = new Item();
        Item glassBottle  = new Item();
        Item pearl  = new Item();
        Item blacknecklace = new Item();
        
        //Items set
        shild.setItemName("Shild");
        shild.setItemWeight(550);
        pearl.setItemName("Pearl");
        pearl.setItemWeight(150);
        nightcap.setItemName("Night cap");
        nightcap.setItemWeight(100);
        goldcoin.setItemName("Gold coin");
        goldcoin.setItemWeight(100);
        spider.setItemName("Spieder");
        spider.setItemWeight(400);
        shoes.setItemName("Shoes");
        shoes.setItemWeight(300);
        mermScale.setItemName("Merm Scale");
        mermScale.setItemWeight(200);
        woodBlock.setItemName("Woodblock");
        woodBlock.setItemWeight(150);
        lamp.setItemName("Lamp");
        lamp.setItemWeight(300);
        glassBottle.setItemName("Glass Bottle");
        glassBottle.setItemWeight(100);
        blacknecklace.setItemName("Black necklace");
        blacknecklace.setItemWeight(250);
        
        //put shild in the list
        itemlist.add(shild);
        itemlist.add(lamp);
        itemlist.add(woodBlock);
        itemlist.add(glassBottle);
        itemlist.add(mermScale);
        itemlist.add(spider);
        itemlist.add(nightcap);
        itemlist.add(goldcoin);
        itemlist.add(shoes);
        itemlist.add(blacknecklace);  
        itemlist.add(pearl);
        
        //Create a random number 
        Random ran = new Random();
        int index  = ran.nextInt(11);
       
        //Return a item object from the itemlist
        return (Item)itemlist.get(index);
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
            Command command = parser.getCommands();
            finished = processCommand(command);
        }
        System.out.println("You've choosen the easy way.\n" +
            "You're awake.\n" +
            "While you think about the last dream you hear a noise. It's right behind you.\n" +
            "For a moment you think you had one of the creppy merm seen.\n" +
            "Now it's dark again.\n" +
            "Is it a dream? Again? ...\n");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println("Distant World\n" + 
            "The last thing you remember is, that you closed your eyes in your bed.\n" + 
            "Now it's dark around you. It is cold. This must be a strange dream, right?\n" +
            "Type 'help' if you don't know what to do.");
        System.out.println();                
        System.out.println(currentPlace.getLongDescription());
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
            System.out.println("I think this is not right...");
            return false;
        }
        
        String commandWord = command.getCommandWord(); 
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goPlace(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look"))
            look();
        else if (commandWord.equals("eat"))
            eat();
        else if (commandWord.equals("drink"))
            drink(); 
        else if (commandWord.equals("back"))
            goBack();
        else if (commandWord.equals("item"))
            showItem();
        else if (commandWord.equals("pick"))
            pick();
        else if (commandWord.equals("bag")) 
            showBagitem();
        
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
        System.out.println("Okay you need help.\n" + 
            "Your command words are:\n" +
            parser.getCommandWords());
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goPlace(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();

        // Try to leave current room.
        Place nextPlace = currentPlace.getExit(direction);
        if (nextPlace == null) {
            System.out.println("There is no way!");
        }
        else {
            //save the lastroom in the twiceback
            twiceBack = lastPlace;
            //save the current in the lastroom
            lastPlace = currentPlace;
            //override the current place with the new place 
            currentPlace = nextPlace;
            //Print the description
            System.out.println(currentPlace.getLongDescription());
        }
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
    
    /**
     * Let the character eat something.
     */
    private void eat()
    {
        System.out.println("You have eaten now and are not hungry any more.");
    }
    
    /**
     *Pick up the item and put it in the bag
     */
    private void pick()
    {
        gamer.addItem(item); 
        currentPlace.setBooleanItem(false);
    }
    
    /**
     * show the bag items 
     */
    private void showBagitem(){
        gamer.getPocket();
    }
    
    /**
     * Let the character look around and
     * gives out the current place description.
     */
    private void look() {
        System.out.println(currentPlace.getLongDescription());
    }
    
    /**
     * Let the character drink, 
     * if he had something to drink.
     */
    private void drink() {
        System.out.println("You grab your drink bottle. Unforgently it's empty.");
    }
    
    /**
     * This method checkt is in this places a item, when yes then 
     * tell me the name and when no then say "There is no Item"
     */
    private void showItem(){
       if( currentPlace.getBooleanItem()== true){
          System.out.println("In this place is "+ item.getItemName()+
          " when you want to pick up the Item.\n write pick ");
        }else {
            System.out.println("There is no Item");
        }
    }
    
    /**
     * go in the last room.
     */
    private void goBack(){ 
        currentPlace = lastPlace;
        lastPlace = twiceBack ;
        System.out.println(currentPlace.getLongDescription());
    }
}
