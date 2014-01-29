package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Server.Server;

public class ConnectGUI extends JFrame implements ActionListener{
	private JButton 	connect;
    private JTextField  port;
    private JTextArea   taMessages;
    private JTextArea   taPlayers;
    private Server      server;
	
	public ConnectGUI(){
		super("Connector");
		init();
		addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.exit(0);
            }
        }
    );
	}
	
	public void init(){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
