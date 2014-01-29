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
import java.util.Observable;
import java.util.Observer;

public class Client extends Thread implements Observer{
    private String				name;
    private Socket				sock;
    private ServerHandler		serverhandler;
	private ClientGUI			clientGui;
	private Player 				player;
	private ClientProtocol		protocol;
	
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
		serverhandler.run();
	}
	
	public void setPlayer(Player playertype){
		player = playertype;
	}
	
	public Player getPlayer(){
		return player;
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
			new Client("testClient", InetAddress.getByName("130.89.178.217") ,2727, "noPlayer");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
