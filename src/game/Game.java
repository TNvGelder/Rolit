package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import Server.ClientHandler;
import Server.Server;

public class Game extends Observable {
	
	private int gameID;
	private int players;
	private List<ClientHandler> playerlist = new ArrayList<ClientHandler>();
	private Board board;
	private FieldType current = FieldType.RED;
	
	public Game(int id){
		gameID = id;
		board = new Board();
	}
	
	public void add(ClientHandler player){
		playerlist.add(player);
	}
	public void remove(ClientHandler player){
		playerlist.remove(player);
	}
	public void doMove(int position, FieldType color){
		board.move(position, color);
		System.out.println("\n" + board.toString()+ "\n");
		setChanged();
		if (board.getValidList(color).contains(position)){
			notifyObservers(position);	
		}
	}
	
	public int getGameNumber(){
		return gameID;
	}
	
	public FieldType getCurrent(){
		return current;
	}
	
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Start this game: create a board and keep things updated.
	 */
	public void startGame(){
		if (playerlist.size()<2 || playerlist.size()>4) {
			//TODO: Error: verkeerd aantal spelers.
			
		}
		if (playerlist.size()==2){
			playerlist.get(0).addPlayer(FieldType.RED);
			playerlist.get(1).addPlayer(FieldType.YELLOW);
			players = 2;

			while (!board.isFull()){
				doMove(playerlist.get(nextPlayer()-1).getMove(), current);
			}
		}
		if (playerlist.size()==3){
			playerlist.get(0).addPlayer(FieldType.RED);
			playerlist.get(1).addPlayer(FieldType.YELLOW);
			playerlist.get(2).addPlayer(FieldType.GREEN);
			players = 3;
			

			while (!board.isFull()){
				doMove(playerlist.get(nextPlayer()-1).getMove(), current);
			}		
		}

		if (playerlist.size()==4){
			playerlist.get(0).addPlayer(FieldType.RED);
			playerlist.get(1).addPlayer(FieldType.YELLOW);
			playerlist.get(2).addPlayer(FieldType.GREEN);
			playerlist.get(3).addPlayer(FieldType.BLUE);
			players = 4;
			

			while (!board.isFull()){
				doMove(playerlist.get(nextPlayer()-1).getMove(), current);
			}
		
		}
		
		System.out.println("game over");
		//TODO: exit gracefully;
		
	}

	private int nextPlayer() {
		if (current.ordinal()>players || current.ordinal()==0){
			current = FieldType.values()[1];
			return 2;
		}
		else {
			current = FieldType.values()[(current.ordinal()+1)];
			return current.ordinal();
		}
	}
	
	public void update(){
		//TODO: update the board with the new move
	}
	
	public int[] getScore(){
		int red = 0;
		int yellow = 0;
		int green = 0;
		int blue = 0;
		for (int i=0; i<(board.getDim()^2); i++){
			if (board.getField(i)==FieldType.RED){++red;}
			else if (board.getField(i)==FieldType.YELLOW){++yellow;}
			else if (board.getField(i)==FieldType.GREEN){++green;}
			else if (board.getField(i)==FieldType.BLUE){++blue;}
		}
		int[] score = {red, yellow, green, blue};
		return score; 
		
	}

}
