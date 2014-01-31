package Server;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.IOException;
import java.net.*;
/**
 * Build up the rolit-server, start with the GUI.
 * @author Casper
 *
 */
public class ServerGUI extends JFrame implements ActionListener {

    private JButton     bConnect;
    private JTextField  tfPort;
    private JTextArea   taMessages;
    private JTextArea   taPlayers;
    private Server      server;

    /** Construeert een ServerGUI object. */
    public ServerGUI() {
        super("ServerGUI");

        buildGUI();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    e.getWindow().dispose();
                    server.shutdown();
                }
                public void windowClosed(WindowEvent e) {
                    System.exit(0);
                }
            }
        );
    }

    /** Bouwt de daadwerkelijke GUI. */
    public void buildGUI() {
        setSize(540, 400);
        setResizable(false);

        // Panel p1 - Listen

        JPanel p1 = new JPanel(new FlowLayout());
        JPanel pp = new JPanel(new GridLayout(2,2));

        JLabel lbAddress = new JLabel("Address: ");
        JTextField tfAddress = new JTextField(getHostAddress(), 12);
        tfAddress.setEditable(false);

        JLabel lbPort = new JLabel("Port:");
        tfPort        = new JTextField("2727", 5);
        tfPort.addKeyListener(
        	new KeyAdapter() {
    	    	public void keyReleased (KeyEvent ev) {
    	        	if (!tfPort.getText().equals("")) {
    	        		bConnect.setEnabled(true);
    	        		
    					if (ev.getKeyCode() == KeyEvent.VK_ENTER) {
    						actionPerformed(new ActionEvent(bConnect, 0, ""));
    					}
    	        	} else {
    	        		bConnect.setEnabled(false);
    	        	}
    	    	}	
    		}
        );

        pp.add(lbAddress);
        pp.add(tfAddress);
        pp.add(lbPort);
        pp.add(tfPort);

        bConnect = new JButton("Launch");
        bConnect.addActionListener(this);

        p1.add(pp, BorderLayout.WEST);
        p1.add(bConnect, BorderLayout.EAST);

        // Panel p2 - Messages

        JPanel p2 = new JPanel();
        p2.setLayout(new BorderLayout());
        
        //-- Add messagearea --//
        JLabel lbMessages = new JLabel("Messages:");
        taMessages = new JTextArea("", 15, 34);
        taMessages.setEditable(false);
        taMessages.setLineWrap(true);

        JScrollPane spMessages = new JScrollPane(taMessages);
        spMessages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        p2.add(lbMessages);
        p2.add(spMessages, BorderLayout.SOUTH);
        
        // Panel p3 - Players
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BorderLayout());
        
        //-- Add playerlist --//
        JLabel lbPlayers = new JLabel("Players:");
        taPlayers = new JTextArea("", 15, 10);
        taPlayers.setEditable(false);
        taPlayers.setLineWrap(true);

        JScrollPane spPlayers = new JScrollPane(taPlayers);
        spPlayers.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        p3.add(lbPlayers);
        p3.add(spPlayers, BorderLayout.SOUTH);
        
        //-- Combine everything --//
        Container cc = getContentPane();
        cc.setLayout(new FlowLayout());
        cc.add(p1);
        cc.add(p2);
        cc.add(p3);
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

    /**
     * Als de "Start Listening" knop wordt ingedrukt wordt de 
     * methode startListening() aangeroepen.
     */
    public void actionPerformed(ActionEvent ev) {
        Object src = ev.getSource();
        if (src == bConnect) {
        	bConnect.setEnabled(false);
            startListening();
        }
    }

    /**
     * Als de port-veld van de GUI geldig is, zal deze methode
     * een Server-object construeren, die daadwerkelijk op de 
     * gespecificeerde port zal gaan wachten op Clients.
     * Dit gebeurt in een aparte thread van de Server.
     * Het port TextField en de "Start Listening" knop worden
     * niet selecteerbaar gemaakt.
     */
    private void startListening() {
        int port = 0;
        int max  = 0;

        try {
            port = Integer.parseInt(tfPort.getText());
        } catch (NumberFormatException e) {
            addMessage("ERROR: geen geldig portnummer!");
            return;
        }

        tfPort.setEditable(false);
        bConnect.setEnabled(false);

        try {
			server = new Server(port, this);			//Construct server
		} catch (IOException e) {
			System.out.print("Some IOException occured: "+e);
		}
        server.run();

        addMessage("Connectie op poort: " + port);
    }

    /** Voegt een bericht toe aan de TextArea op het frame. */
    public void addMessage(String msg) {
        taMessages.append(msg + "\n");
    }
    
    public void addPlayer(String msg) {
        taMessages.append(msg + "\n");
    }
    
    /**
     * Put incoming message on the screen.
     * @param msg 
     */
    public void update(String msg){
    	addMessage(msg);
    }

    /** Start een ServerGUI applicatie op. */
    public static void main(String[] args) {
        ServerGUI gui = new ServerGUI();
    }

}
