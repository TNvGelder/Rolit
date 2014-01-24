package rolit.server;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {

	private Server          server;
	private Socket          sock;
	private int				gameNumber = -1;
	private BufferedReader  in;
	private BufferedWriter  out;
	private String          clientName = "[clientName]";

	public ClientHandler(Server server, Socket sock) throws IOException {
	}
	
	
	public void run() {
	}

	public synchronized void sendMessage(String msg) {
	}
	
	public synchronized void shutdown() {
	}
	
	public String getClientName(){
		return clientName;
	}
	
	public void setGameNumber(int number){
		gameNumber = number;
	}
	
	public int getGameNumber(){
		return gameNumber;
	}

}
