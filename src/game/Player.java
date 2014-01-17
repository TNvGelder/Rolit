package game;

public class Player {
	
	//Instance variables
	private String playerName;
	
	FieldType colour;
	
	// Constructor
	/**
	 * creates a new Player. The playername should be given as argument.
	 * @param name
	 */
	public Player(String name){
		playerName = name;
	}
	
	//Commands
	/**
	 * Changes the player's colour.
	 * The colour will be determined in class Game, depending on the ammount of players in the game.
	 * @param newColour
	 */
	public void setColour(FieldType newColour){
		colour = newColour;
	}
	
	//@requires colour != null;
	public void doMove(Board board, int fieldIndex){
		board.move(fieldIndex, colour);
	}
	
	//Queries
	
	/**
	 * Returns the name of the player.
	 * @return playerName
	 */
	public String getName(){
		return playerName;
	}

}
