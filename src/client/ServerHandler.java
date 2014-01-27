package client;

import game.FieldType;

import java.io.*;
import java.net.*;

public class ServerHandler extends Thread {

	private Client          client;
	private Socket          sock;
	private BufferedReader  in;
	private BufferedWriter  out;
	private String          clientName = "[clientName]";

	public ServerHandler(Client client, Socket sock) throws IOException {
		this.client = client;
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

	public synchronized void shutdown() {
	}
}
