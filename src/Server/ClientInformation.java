package Server;

import game.Game;

public class ClientInformation {
	
	public String 		name;
	public boolean 		handshake = false;
	public int 			supports = 0;
	public boolean 		inGame = false;
    public boolean 		ownsGame = false;
	public boolean 		hasGame = false;
	public String 		version = "";
	public Game 		Game;
}
