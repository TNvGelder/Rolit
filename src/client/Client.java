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
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Client extends Thread implements Observer{
    private String				name;
    private Socket				sock;
    private ServerHandler		serverhandler;
	private ClientGUI			clientGui;
	private Player 				player;
	private ClientProtocol		protocol;
	private Board				clientBoard;
	private FieldType			currentColor = FieldType.RED;
	private List<Integer>		validMoves;

	
	public Client(String name, InetAddress address, int port, String kindOfPlayer){
		//TODO: stuff ;maak serverhandler-> doe communicatie met server;
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
			serverhandler.sendMessage("Move " + Board.toXCoord(move) + " " + Board.toYCoord(move));
		}
		
	}
	
	public List<Integer> getValidList(){
		return validMoves;
	}
	
	/**
	 *Zorgt ervoor dat de player weet dat hij aan de beurt is en geeft een lijst van geldige zetten aan de player. 
	 */
	public void doMove(){
		validMoves = clientBoard.getValidList(currentColor);
	}
	
	public int getHint(){
		return 0;
	}
	
	public String getClientName(){
		return name;
	}
	

	@Override
	public void update(Observable o, Object arg) {
		clientGui.update(o,arg);
		
	}
	
	public static void main(String[] args){
		try {
			new Client("testClient", InetAddress.getLocalHost() ,2727, "Human");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
