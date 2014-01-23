package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Game {
	
	Set<Player> waiters = new HashSet<Player>();
	List<Player> players;
	Board gameBoard;
	public Game(){
		Board board = new Board();
	}
	
	public void add(Player player){
		players.add(player);
	}
	
	public void remove(Player player){
		players.remove(player);
	}
	
	public void startGame(){
		if (waiters.size() < 2){
			System.out.println("Not enough players");
			return;
		}
		Iterator<Player> wait = waiters.iterator();
		for (int i = 0; i < waiters.size(); i++){
			Player nxtPlay = wait.next();
			players.set(i, nxtPlay);
			System.out.println(nxtPlay.getName() + " joined.");
		}
		if (players.size() == 2){
			
		}
		players.get(0).setColour(FieldType.RED);
		players.get(1).setColour(FieldType.GREEN);
		if (players.get(2) != null){
			players.get(2).setColour(FieldType.YELLOW);	
		}
		if (players.get(3) != null){
			players.get(3).setColour(FieldType.BLUE);	
		}
		
	}
	

}
