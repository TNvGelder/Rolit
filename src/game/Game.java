package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Game {
	
	Set<Player> waiters = new HashSet<Player>();
	List<Player> players = new ArrayList<Player>();
	Board gameBoard = new Board();
	public Game(){
	}
	
	public void add(Player player){
		waiters.add(player);
	}
	public void doMove(int position, FieldType clr){
		gameBoard.move(position, clr);
	}
	public void remove(Player player){
		waiters.remove(player);
	}
	
	public Board getBoard(){
		return gameBoard.copyBoard();
	}
	
	public void startGame(){
		if (waiters.size() < 2){
			System.out.println("Not enough players");
			return;
		}
		Iterator<Player> wait = waiters.iterator();
		for (int i = 0; i < waiters.size(); i++){
			Player nxtPlay = wait.next();
			players.add(nxtPlay);
			nxtPlay.setGame(this);
			System.out.println(nxtPlay.getName() + " joined.");
		}
		if (players.size() == 2){
			players.get(0).setColour(FieldType.RED);
			players.get(1).setColour(FieldType.GREEN);
		}
		if (players.size() >= 3){
			players.get(0).setColour(FieldType.RED);
			players.get(1).setColour(FieldType.YELLOW);
			players.get(2).setColour(FieldType.GREEN);
		}
		if (players.size() == 4){
			players.get(3).setColour(FieldType.BLUE);
		}
		int i = 0;
		while (i < players.size()){
			doMove(players.get(i).giveMove(),players.get(i).getColor());
			System.out.println(gameBoard.toString());
			if (gameBoard.isFull()){
				System.out.println("Red: " + gameBoard.getClrCount(FieldType.RED));
				System.out.println("Yellow: " + gameBoard.getClrCount(FieldType.YELLOW));
				System.out.println("Green: " + gameBoard.getClrCount(FieldType.GREEN));
				System.out.println("Blue: " + gameBoard.getClrCount(FieldType.BLUE));
				i = 9001;
			}
			if (i==players.size() -1){
				i = 0;
			}
			else{
				i++;
			}
		}
		
	}
	

}
