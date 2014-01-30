package game;

import client.BasicAI;
import client.Player;

public class BasicAITest {

	static Game testGame = new Game();
	static Player p1 = new BasicAI("Computer1");
	static Player p2 = new BasicAI("Computer2");
	static Player p3 = new BasicAI("Computer3");
	static Player p4 = new BasicAI("Computer4");
	
	public static void main(String[] args){

		testGame.add(p1);
		testGame.add(p2);
		testGame.add(p3);
		testGame.add(p4);
		testGame.startGame();
	}
}
