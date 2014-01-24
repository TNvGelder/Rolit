package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import Server.ClientHandler;

public class Game extends Observable {
	
	private int gameID;
	private Set<ClientHandler> waiters = new HashSet<ClientHandler>();
	private List<ClientHandler> players = new ArrayList<ClientHandler>();
	private Board board;
	private FieldType current;
	
	public Game(int id){
		gameID = id;
	}
	
	public void add(ClientHandler player){
		waiters.add(player);
	}
	public void remove(ClientHandler player){
		waiters.remove(player);
	}
	public void doMove(int position, FieldType color){
		board.move(position, color);
	}
	
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Start this game: create a board and keep things updated.
	 */
	public void startGame(){
		if (waiters.size()<2) {
			
		}
		
	}
	

}
