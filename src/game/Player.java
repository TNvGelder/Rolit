package game;

public interface Player {
	
	//Instance variables



	public void setGame(Game g);
	public int giveMove();
	
	//Commands
	/**
	 * Changes the player's color.
	 * The color will be determined in class Game, depending on the ammount of players in the game.
	 * @param newColour
	 */
	public void setColour(FieldType newColour);
	
	
	//Queries
	
	/**
	 * Returns the name of the player.
	 * @return playerName
	 */
	public String getName();
	/**
	 * Returns the color of the player.
	 * @return color
	 */
	public FieldType getColor();

}
