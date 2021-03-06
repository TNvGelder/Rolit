package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import Server.ClientHandler;
import Server.Server;

public class Game{
	
	private int gameID;
	private int players;
	private List<ClientHandler> playerlist = new ArrayList<ClientHandler>();
	private Board board;
	private FieldType current = FieldType.RED;
	private Server server;
	public  ClientHandler ownerClient;
	public boolean moveDone = false;
	public  int status = 0;
	
	
	public Game(int id, Server serv){
		gameID = id;
		board = new Board();
		server = serv;
	}
	public Game(int id, Server serv, int players){
		gameID = id;
		server = serv;
		this.players = players;
	}
	
	public void add(ClientHandler player){
		playerlist.add(player);
	}
	
	/**
	 * Remove player from the game. If the game was started, also remove the rest of the players.
	 * @param player
	 */
	public void remove(ClientHandler player){
		playerlist.remove(player);
		if (status == 1 && playerlist.size()>0){
			for (ClientHandler client : playerlist){
				remove(client);
			}
		}
	}
	
	/**
	 * Execute given move.
	 * @param position
	 * @param color
	 */
	public synchronized void doMove(int position, FieldType color){
		board.move(position, color);
		System.out.println("\n" + board.toString()+ "\n");
		if (board.getValidList(color).contains(position)){
		}
		server.moveDone(Board.toXCoord(position), Board.toYCoord(position));
		moveDone = true;
		this.notify();
	}
	
	/**
	 * Return playerlist.
	 * @return
	 */
	public List<ClientHandler> getPlayerList(){
		return playerlist;
	}
	
	/**
	 * Return this gameID.
	 * @return
	 */
	public int getGameNumber(){
		return gameID;
	}
	
	/**
	 * Returns current player.
	 * @return
	 */
	public FieldType getCurrent(){
		return current;
	}
	
	/**
	 * Return the board that is played on.
	 * @return
	 */
	public Board getBoard(){
		return board;
	}
	
	/**
	 * Start this game, and ask for moves by iterating over the clienthandlers.
	 */
	public synchronized void startGame(){
		if (playerlist.size()<2 || playerlist.size()>4) {
			//TODO: Error: verkeerd aantal spelers.
			
		}
		if (playerlist.size()==2){
			playerlist.get(0).setColor(FieldType.RED);
			playerlist.get(1).setColor(FieldType.GREEN);
		}
		
		if (playerlist.size() >= 3){
			playerlist.get(0).setColor(FieldType.RED);
			playerlist.get(1).setColor(FieldType.YELLOW);
			playerlist.get(2).setColor(FieldType.GREEN);
		}	

		if (playerlist.size()==4){
			playerlist.get(3).setColor(FieldType.BLUE);	
		}
		
		int i = 0;
		while (!board.isFull() && i < playerlist.size()){
			ClientHandler currentPlay = playerlist.get(i);
			System.out.println(currentPlay.getColor().toString());
			doMove(currentPlay.getMove(), currentPlay.getColor());
			if (i == playerlist.size() - 1){
				i = 0;
			}
			else{
				i++;
			}
			while (moveDone == false){
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			moveDone = false;

		}
		System.out.println("game over");
		//TODO: exit gracefully;
		
	}
	
	/**
	 * Current.ordinal() = current.ordinal()+1
	 * @return
	 */
	
	/*
<<<<<<< Updated upstream
<<<<<<< Updated upstream
	private int nextPlayer() {
		
		this.current = playerlist.current.ordinal()];
=======
	private void nextPlayer() {
		
		this.current = playerlist.get((current.ordinal() + 1) % playerlist.size()).getPlayer();
>>>>>>> Stashed changes
=======
	private void nextPlayer() {
		
		this.current = playerlist.get((current.ordinal() + 1) % playerlist.size()).getPlayer();
>>>>>>> Stashed changes
		//TODO: 2 players meand red and green, NOT RED AND YELLOW.
		/*if (current.ordinal()>players-1 || current.ordinal()==0){
			current = FieldType.values()[1];
			return 2;
		}
		else {
			current = FieldType.values()[(current.ordinal()+1)];
			return current.ordinal();
		}
	}*/
	
	public void update(){
		//TODO: update the board with the new move.
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

	public int getPlayers() {
		return playerlist.size();
	}

}
