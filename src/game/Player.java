package game;

public abstract class Player {
	
	//Instance variables
	private String name;
	private FieldType color;
	
	public Player (String name, FieldType color){
		this.name = name;
		this.color = color;
	}

	//Queries
	
	/**
	 * Returns the name of the player.
	 * @return playerName
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the color of the player.
	 * @return color
	 */
	public FieldType getColor(){
		return color;
	}

	/**
	 * Get the move that is to be made.
	 * @param board
	 * @return
	 */
	public abstract int getMove(Board board);
	
}
