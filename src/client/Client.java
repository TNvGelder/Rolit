package client;

import game.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Client extends Thread{
    private String				name;
    private Socket				sock;
    private ServerHandler		serverhandler;
	private ClientGUI			clientGui;
	private BasicAI				basicAI;
	private Player 				player;
	private ClientProtocol		protocol;
	private Board				clientBoard;
	private FieldType			currentColor = FieldType.RED;
	private List<Integer>		validMoves;
	private int					playerCount;
	private String 				kindOfPlayer;

	
	public Client(String name, InetAddress address, int port, String kindOfPlayer, int players){
		//TODO: stuff ;maak serverhandler-> doe communicatie met server;
		this.kindOfPlayer = kindOfPlayer;
		playerCount = players;
		this.name = name;
		try {
			sock = new Socket(address, port);
			serverhandler = new ServerHandler(this,sock);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (kindOfPlayer == "Human"){
			clientGui = new ClientGUI(500, this);
			clientBoard = new Board(clientGui);
		}
		else{
			clientBoard = new Board();
		}
		serverhandler.run();
	}
	
	public void setPlayer(Player playertype){
		player = playertype;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	/**
	 * Zend een zet naar de ServerHandler
	 */
	public void sendMove(int move){
		if (!validMoves.contains(move)){
			doMove();
		}
		else{
			currentColor = currentColor.nextFieldType(playerCount);
			serverhandler.sendMessage("Move " + Board.toXCoord(move) + " " + Board.toYCoord(move));
		}
		
	}
	
	public List<Integer> getValidMoves(){
		return clientBoard.getValidList(currentColor);
	}
	
	/**
	 *Zorgt ervoor dat de player weet dat hij aan de beurt is en geeft een lijst van geldige zetten aan de player. 
	 */
	public void doMove(){
		if (kindOfPlayer == "Human"){
			clientGui.makeMove();
		}
		else {
			basicAI.makeMove();
		}
	}
	
	public int getHint(){
		int bestMove = -1;
		int bestGain = 0;
		Iterator<Integer> moveIterator = validMoves.iterator();
		while (moveIterator.hasNext()){
			int tryoutIndex = moveIterator.next();
			Board tryoutBoard = clientBoard.copyBoard();
			int gain = tryBeat(tryoutBoard, tryoutIndex);
			while (gain == 1 && moveIterator.hasNext()){
				tryoutIndex = moveIterator.next();
				gain = tryBeat(tryoutBoard, tryoutIndex);
			}
			if (gain > bestGain){
				bestGain = gain;
				bestMove = tryoutIndex;
			}
		}
		System.out.println("Sending hint to" + currentColor + ". Gain = " + bestGain);
		return bestMove;
	}
	
	public int tryBeat(Board board, int fieldIndex) {
		return board.beat(fieldIndex, currentColor, false);
		
	}
	
	public String getClientName(){
		return name;
	}
	
	
	public static void main(String[] args){
		try {
			new Client("testClient", InetAddress.getLocalHost() ,2727, "Human", 4);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
