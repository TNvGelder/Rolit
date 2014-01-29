package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import game.Game;

public class Server extends Thread{
	private int 						port;
	private Collection<ClientHandler> 	clients;
	private ServerSocket 				serversocket;
	public ServerGUI 					serverGUI;
	private List<ClientHandler> 		lobby 				= new ArrayList<ClientHandler>();
	private int 						gamecounter = 0;
	public Game[] 						games 				= new Game[100];
	public ServerInformation			serverInfo;

	/**  
	  * Construct a server with a port. Make a collection of clienthandlers possible. 
	  * @throws IOException 
	  */
	
	public Server(int port, ServerGUI serverGUI) throws IOException {
		this.port = port;
		this.serverGUI = serverGUI;
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
					serverGUI.update("Clienthandler created");
					sc.start();
					/* Put handler in waitinglist;
					while (sc.getGameNumber()==-1){
						int i = 0;
						if (games[i].getPlayerList().size()<4){
							games[i].add(sc);
							sc.setGameNumber(i);
						}
						i++;
					}*/
					lobby.add(sc);
				} catch (IOException e) {
					serverUpdate("Something went wrong: " + e.getMessage());
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
	
	/**
	 * Send message to server only.
	 * @param msg
	 */
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
		handler.shutdown();
	}
	
	/**
	 * Verwijdert client uit game.
	 * @param handler
	 */
	public void leaveGame(ClientHandler handler){
		
		//TODO: destroy game, disconnect client.
		messageAll("Client " + handler.getName() + " has left the game");
	}
	
	public void createGame(String creator, int players){
		games[gamecounter] = new Game(gamecounter, players);
		lobby.remove(lobby.indexOf(creator));
	}
	
	public void joinGame(ClientHandler client, int gameID){
		games[gameID].add(client);
	}
	
	public void startGame(int id){
		games[id].startGame();
	}
	
	public void shutdown() {
		this.messageAll("Shutting down server...");
        try {
            this.serversocket.close();
        } catch (Exception e) { }
	}

	public boolean hasClient(String clientName) {
		// TODO is clientName already in use?
		return false;
	}

	public ClientHandler getClient(String name) {
		// TODO return ClientHandler object from a string
		return null;
	}
}
