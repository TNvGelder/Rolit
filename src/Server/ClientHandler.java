package Server;

import game.FieldType;

import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {

	private Server          server;
	private Socket          sock;
	private int				gameNumber = -1;
	private BufferedReader  in;
	private BufferedWriter  out;
	private String          clientName = "[clientName]";
	private boolean			handshaked = false;

	public ClientHandler(Server server, Socket sock) throws IOException {
		this.server = server;
		this.sock = sock;
		out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	}
	
	
	public void run() {
		//TODO: a lot
		try {
			while (true){
		
			//TODO: run program, listen to input
			String input = in.readLine();
			//TODO: seperate commands using protocol
			}
		}
		catch (IOException e){
			shutdown();
		}
	}

	/**
	 * Zend een message naar de client.
	 * @param msg De message voor de client
	 */
	public synchronized void sendMessage(String msg) {
		try {
			out.write(msg + "\n");
			out.flush();
		} catch (IOException e) {
			shutdown();
		}
	}
	
	/**
	 * Sluit de ClientHandler op een nette manier en licht de Server hierover in.
	 */
	public synchronized void shutdown() {
		server.removeHandler(this);
		server.serverUpdate(clientName + " shut down.");
		try {
			sock.close();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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


	public void addPlayer(FieldType color) {
		// TODO add a player, human (or AI)
		
	}


	public int getMove() {
		// TODO Get move from the client.
		return 0;
	}

}
