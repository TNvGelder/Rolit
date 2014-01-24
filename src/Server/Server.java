package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import game.Game;

public class Server extends Thread{
	private int port;
	private Collection<ClientHandler> clients;
	private boolean error;
	private ServerSocket serversocket;
	public ServerGUI serverGUI;
	private List<String> availableplayers = new ArrayList<String>();
	private int gamecounter = 0;
	public Game[] games = new Game[20];

	/**  
	  * Construct a server with a port. Make a collection of clienthandlers possible. 
	  * @throws IOException 
	  */
	
	public Server(int port) throws IOException {
		this.port = port;
		clients = new ArrayList<ClientHandler>();
		try {
			serversocket = new ServerSocket(port);
		} catch (IOException e) {
			throw e;
		} catch (IllegalArgumentException e){
			throw e;
		}
	}

	/**
	 * Luistert op de port van deze Server of er Clients zijn die
	 * een verbinding willen maken. Voor elke nieuwe socketverbinding
	 * wordt een nieuw ClientHandler thread opgestart die de verdere
	 * communicatie met de Client afhandelt.
	 */
	public void run() {
		while(true){
				serverGUI.update("Waiting for Clients...");
				Socket sock;
				try {
					sock = serversocket.accept();
				
				ClientHandler sc = new ClientHandler(this, sock);
				sc.start();
				serverGUI.update("Sock accepted");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	/**
	 * Stuurt een bericht via de collectie van aangesloten ClientHandlers
	 * naar alle aangesloten Clients.
	 * @param msg bericht dat verstuurd wordt
	 */
	public synchronized void messageAll(String msg) {
		serverGUI.update(msg);
		for(Iterator<ClientHandler> iterator = clients.iterator(); iterator.hasNext();){
			((ClientHandler) iterator.next()).sendMessage(msg);
		}
	}
	
	/**
	 * Send message to certain game. Iterate over Collection and 
	 * send message for every clienthandler with this gameNumber.
	 * @param msg
	 * @param gameNumber
	 */
	public void sendGameMessage(String msg, int gameNumber){
		ClientHandler otherHandler;
		for(Iterator<ClientHandler> iterator = clients.iterator(); iterator.hasNext();){
			otherHandler = (ClientHandler) iterator.next();
			if (otherHandler.getGameNumber() == gameNumber){
				otherHandler.sendMessage(msg);
			}
		}
	}
	
	public void serverUpdate(String msg){
		serverGUI.update(msg);
	}

	/**
	 * Voegt een ClientHandler aan de collectie van ClientHandlers toe.
	 * @param handler ClientHandler die wordt toegevoegd
	 */
	public synchronized boolean addHandler(ClientHandler handler) {
		boolean existing = false;
		for(Iterator<ClientHandler> iterator = clients.iterator(); iterator.hasNext();){
			if (((ClientHandler) iterator.next()).getClientName().equals(handler.getClientName())){
				existing = true;
			}
		}
		if (!existing){
			clients.add(handler);
		}
		return !existing;
	}

	/**
	 * Verwijdert een ClientHandler uit de collectie van ClientHandlers.
	 * @param handler ClientHandler die verwijderd wordt
	 */
	public synchronized void removeHandler(ClientHandler handler) {
		clients.remove(handler);
		leaveGame(handler);
	}
	
	public void leaveGame(ClientHandler handler){
		//TODO: destroy game, disconnect client.
	}
	
	public void createGame(String name, int players){
		Game newgame = new Game(gamecounter);
	}
	
	public synchronized void shutdown() {
		messageAll("error You have been disconnected from the server");
		try {
			serversocket.close();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
