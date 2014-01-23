package game;

public class Player {
	
	//Instance variables
	private String playerName;
	private FieldType color;
	
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
	 * Changes the player's color.
	 * The color will be determined in class Game, depending on the ammount of players in the game.
	 * @param newColour
	 */
	public void setColour(FieldType newColour){
		color = newColour;
	}
	
	//@requires color != null;
	public void doMove(Board board, int fieldIndex){
		board.move(fieldIndex, color);
	}
	
	//Queries
	
	/**
	 * Returns the name of the player.
	 * @return playerName
	 */
	public String getName(){
		return playerName;
	}
	/**
	 * Returns the color of the player.
	 * @return color
	 */
	public FieldType getColor(){
		return color;
	}

}
