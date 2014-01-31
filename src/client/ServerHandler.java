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
			}
		}	
		catch (IOException e){
			shutdown();
		}
	}
	
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
