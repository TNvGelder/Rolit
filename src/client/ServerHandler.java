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
	private boolean connected = true;

	public ServerHandler(Client client, Socket sock) throws IOException {
		this.client = client;
		this.sock = sock;
		out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	}
	
	
	public void run() {
		//TODO: a lot
		try {
			while (connected){
				String msg = in.readLine();
				System.out.println(msg);
				if (msg.startsWith("test")){
					System.out.println("TestMessage received!");
					sendMessage("test");
				}
				if (msg.toLowerCase().startsWith("movedone")){
					System.out.println("moved");
					String[] argumentArray = msg.split(" ");
					client.move(Integer.parseInt(argumentArray[2]), Integer.parseInt(argumentArray[3]));
				}
				if (msg.toLowerCase().startsWith("move")){
					client.doMove();
				}
			}
		}	
		catch (IOException e){
			shutdown();
		}
	}
	
	/**
	 * Stuurt een message naar de clienthandler.
	 */
	public void sendMessage(String msg){
		System.out.println("Sending: " + msg);
		if (msg != null){
			try {
				out.write(msg + "\n");
				out.flush();
			} catch (IOException e) {
				System.out.println("Failed to send message");
			}
		}
	}

	public synchronized void shutdown() {
		connected = false;
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			System.out.println("Failed to close the in/output");
		}
	}
}
