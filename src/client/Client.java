package client;

import game.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Client extends Thread implements Observer{
    private String				name;
    private Socket				sock;
    private BufferedReader		in;
    private BufferedWriter		out;
	private ClientGUI			clientGui;
	private Player 				player;
	
	public Client(){
		//TODO: stuff ;maak serverhandler-> doe communicatie met server; 
		run();
	}
	
	public void run(){
		//TODO:Maak serverhandler en voer het uit.
		
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
	
	public void shutdown(){
		try{
			in.close();
			out.close();
			sock.close();
		}catch(IOException e){
			System.out.println("Connection closed");
		}
	}
	
	public void sendMessage(String msg){
		if (msg != null){
			try {
				out.write(msg + "\n");
				out.flush();
			} catch (IOException e) {
				System.out.println("Failed to send message");
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		clientGui.update(o,arg);
		
	}

}
