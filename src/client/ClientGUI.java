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


public class ClientGUI extends JFrame implements ActionListener, Observer{
	private static final int DIM = 8;
	private Container c = getContentPane();
	private JPanel boardPanel = new JPanel();
	private JButton[] buttonArray = new JButton[DIM*DIM];
	private JLabel turnInfo = new JLabel("Playername's turn");
	private int boardSize;
	
	public ClientGUI(int size){
		super("Rolit Client");
		boardSize = size;
		init();
	}

	public void init(){
		for (int i = 0; i < DIM*DIM; i++){
			Border boardLine = BorderFactory.createLineBorder(Color.black);
			
			JButton fieldButton = new JButton();
			buttonArray[i] = fieldButton;
			boardPanel.add(buttonArray[i]);
			boardPanel.setLayout(new GridLayout(DIM, DIM));
			boardPanel.setBorder(boardLine);
			boardPanel.setPreferredSize(new Dimension(boardSize,boardSize));
			c.add(boardPanel);
		}
		setLayout(new FlowLayout());
		setSize(boardSize*2,boardSize*2);
		c.setMinimumSize(new Dimension(boardSize,boardSize));
		c.add(turnInfo);
		setVisible(true);
	}	

	
	public static void main(String[] args){
		ClientGUI test1 = new ClientGUI(500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}