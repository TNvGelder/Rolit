package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import game.Game;

public class Server extends Observable implements Runnable{
	private int 						port;
	private Collection<ClientHandler> 	clients;
	private ServerSocket 				serversocket;
	public ServerGUI 					serverGUI;
	private List<ClientHandler> 		lobby 				= new ArrayList<ClientHandler>();
	private int 						gamecounter = 0;
	public Game[] 						games 				= new Game[100];
	public ServerInformation			serverInfo;

	/**  
	  * De constructor start server en luistert op de aangegeven poort. Ook linkt hij de server met zijn GUI. 
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
	
	public void moveDone(int x, int y){
		messageAll("moveDone " + "playername " + x + " "+y);
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
				
					ClientHandler client = new ClientHandler(this, sock);
					serverGUI.update("Clienthandler created");
					client.start();
					lobby.add(client);
					serverGUI.update(lobby.get(0).getName());
					if (lobby.size() == 2){
						createGame("game1");
						joinGame(lobby.get(0),gamecounter);
						joinGame(lobby.get(0),gamecounter);
						startGame(gamecounter);
					}
					/* Put handler in waitinglist;
					while (sc.getGameNumber()==-1){
						int i = 0;
						if (games[i].getPlayerList().size()<4){
							games[i].add(sc);
							sc.setGameNumber(i);
						}
						i++;
					}*/
					this.messageAll("test");
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
	public void messageAll(String msg) {
		serverGUI.update("Sending " + msg + " to all ClientHandlers");
		setChanged();
		notifyObservers(msg);
		/*
		Iterator<ClientHandler> iterator = lobby.iterator();
		while(iterator.hasNext()){
			ClientHandler receiver = iterator.next();
			serverGUI.update("Sending message to " + receiver.getClientName());
			receiver.sendMessage(msg);
		}
			*/
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
			addObserver(handler);
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
	
	public void createGame(String name){
		if (games[gamecounter] != null){
			++gamecounter;
		}
			games[gamecounter] = new Game(gamecounter, this);
			//lobby.remove(lobby.indexOf(name));

	}
	
	public void joinGame(ClientHandler client, int gameID){
		games[gameID].add(client);
		lobby.remove(client);
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
		boolean result = false;
		for (ClientHandler it : clients){
			if (it.getClientName().equals(clientName)){
				result = true;
			}
		}
		// TODO is clientName already in use?
		return result;
	}

	public ClientHandler getClient(String name) {
		// TODO return ClientHandler object from a string
		return null;
	}
}
