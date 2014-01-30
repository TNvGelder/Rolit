package client;
import game.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import game.Board;

public class ClientGUI extends JFrame implements Observer{
	private static final int DIM = 8;
	private Container c = getContentPane();
	private JPanel boardPanel = new JPanel();
	private JButton[] buttonArray = new JButton[DIM*DIM];
	private JLabel turnInfo = new JLabel("Playername's turn");
	private int boardSize;
	private ServerHandler serverHandler;
	
	public ClientGUI(int size, ServerHandler servHandler){
		super("Rolit Client");
		boardSize = size;
		init();
		serverHandler = servHandler;
		RolitController testctrl1 = this.new RolitController(serverHandler);
	}

	public void init(){
		Border boardLine = BorderFactory.createLineBorder(Color.black);
		for (int i = 0; i < DIM*DIM; i++){
			
			JButton fieldButton = new JButton();
			buttonArray[i] = fieldButton;
			boardPanel.add(buttonArray[i]);
		}
		boardPanel.setLayout(new GridLayout(DIM, DIM));
		boardPanel.setBorder(boardLine);
		boardPanel.setPreferredSize(new Dimension(boardSize,boardSize));
		c.add(boardPanel);
		setLayout(new FlowLayout());
		setSize(boardSize*2,boardSize*2);
		c.setMinimumSize(new Dimension(boardSize,boardSize));
		c.add(turnInfo);
		setVisible(true);
	}	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public class RolitController implements ActionListener{
		private ServerHandler serverHandler;
		public RolitController(ServerHandler servHandler){
			serverHandler = servHandler;
			for (int i = 0; i < 64; i++){
				buttonArray[i].addActionListener(this);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			for (int i = 0; i < 64; i++){
				if (buttonArray[i].equals(b)){
					serverHandler.sendMessage("move " + Board.toXCoord(i) + " " + Board.toYCoord(i));
				}
			}
			
		}
		
	}
}