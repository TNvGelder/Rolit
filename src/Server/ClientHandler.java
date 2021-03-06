package Server;

import game.FieldType;

import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.Observer;

import network.ServerProtocol;

public class ClientHandler extends Thread implements Observer{

	public Server          	server;
	private Socket          sock;
	private int				gameNumber = -1;
	private BufferedReader  in;
	private BufferedWriter  out;
	private String          clientName = "[clientName]";
	private boolean			handshaked = false;
	private boolean 		active = true;
	private int				move = -1;
	private ServerProtocol  protocol = new ServerProtocol(this);
	private FieldType		fieldtype;
	public ClientInformation clientInfo;
	

	public ClientHandler(Server server, Socket sock) throws IOException {
		this.server = server;
		this.sock = sock;
		out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		server.serverUpdate("Client connected to the server");
	}
	
	
	public void run() {
		// TODO: a lot
		try {
			while (active) {
				String msg = in.readLine();
				server.serverUpdate(msg);
				if (msg.startsWith("test")){
					server.serverUpdate("TestMessage received!");
				}
				
				if (protocol.isValidCommand(msg)) {
					try {
						protocol.doCommand(msg);
					} catch (IllegalArgumentException e) {
						server.serverUpdate("Illegal arguments" + msg);
					}
				}
			}
			

		} catch (IOException e) {
			shutdown();
		}
		
	}

	/**
	 * Zend een message naar de client.
	 * @param msg De message voor de client
	 */
	public void sendMessage(String msg) {
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


	public void setColor(FieldType color) {
		fieldtype = color;
		
	}

	public synchronized void move(int index){
		move = index;
		System.out.println(move);
		notify();
	}
	
	public synchronized int getMove() {
		int result;
		sendMessage("move");
		while (move == -1){
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());;
			}
		}
		result = move;
		move = -1;
		System.out.println("move" + move + "returned by getMove()");
		return result;
		
	}


	public void sendCommand(String handshake, String[] strings) {
		// TODO Auto-generated method stub
		
	}


	public FieldType getColor() {
		return fieldtype;
	}


	@Override
	public void update(Observable o, Object arg) {
		System.out.println("observer notified");
		sendMessage((String) arg);
		
	}

}
