package Server;

import java.io.*;
import java.net.*;
import java.util.*;

import game.Game;

public class Server extends Thread{
	private int port;
	private Collection<ClientHandler> threads;
	private boolean error;
	private ServerSocket serversocket;
	public ServerGUI serverGUI;
	private int gamecounter = 0;
	public Game[] games;

	public Server(int port) throws IOException {
	}
	
	public void run() {
	} 

	

	public synchronized boolean addHandler(ClientHandler handler) {
		return false;
	}
	public synchronized void removeHandler(ClientHandler handler) {
	}
	
	public void leaveGame(ClientHandler handler){
	}
	
	public void createGame(String name, int players){
	}
	
	public synchronized void shutdown() {
	}
}
