package client;

import game.Board;
import game.FieldType;

public abstract class Player {
	
	//Instance variables
	private String name;
	private Client client;
	
	public Player (String name, Client cl){
		this.name = name;
		client = cl;
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
	 * Get the move that is to be made.
	 * @param board
	 * @return
	 */
	public abstract int getMove(Board board);
	
}
