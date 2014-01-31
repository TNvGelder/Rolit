package client;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Server.Server;

public class ConnectGUI extends JFrame implements ActionListener{
	private JButton 	connect =		new JButton("Connect");
    private JTextField  tfAdress = 		new JTextField(getHostAddress(), 12);;
    private JLabel		lbAdress = 		new JLabel("Adress:");
    private JTextField  tfPort =		new JTextField(2727);
    private JLabel		lbPort = 		new JLabel("Port:");
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
		JPanel flowPanel = new JPanel(new FlowLayout());
		JPanel gridPanel = new JPanel(new GridLayout());
		
		connect.setText("connect");
	}
	
    /** Levert het Internetadres van deze computer op. */
    private String getHostAddress() {
        try {
            InetAddress iaddr = InetAddress.getLocalHost();
            return iaddr.getHostAddress();
        } catch (UnknownHostException e) {
            return "?unknown?";
        }
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
