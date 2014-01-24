package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Client extends Thread implements Observer{
    private String				name;
    private Socket				sock;
    private BufferedReader		in;
    private BufferedWriter		out;
	private ClientGUI			clientGui;
	private boolean 			connect = true;
	
	public void run(){
		
	}
	
	public String getClientName(){
		return name;
	}
	
	public void shutdown(){
		try{
			in.close();
			out.close();
			sock.close();
		}catch(IOException e){
			System.out.println("Connection closed");
		}
	}
	
	public void sendMessage(String msg){
		if (msg != null){
			try {
				out.write(msg + "\n");
				out.flush();
			} catch (IOException e) {
				System.out.println("Shutdown failed");
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		clientGui.update(o,arg);
		
	}

}
