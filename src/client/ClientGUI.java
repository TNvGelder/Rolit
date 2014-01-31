package client;
import game.FieldType;
import game.Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import game.Board;

public class ClientGUI extends JFrame{
	private static final int DIM = 8;
	private Container c = getContentPane();
	private JPanel boardPanel = new JPanel();
	private JButton[] buttonArray = new JButton[DIM*DIM];
	private JLabel turnInfo = new JLabel("Playername's turn");
	private int boardSize;
	private Client client;
	private List<Integer> validList;
	
	public ClientGUI(int size, Client cl){
		super("Rolit Client");
		client = cl;
		boardSize = size;
		init();
		RolitController testctrl1 = this.new RolitController(cl);
		addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 e.getWindow().dispose();
             }
             public void windowClosed(WindowEvent e) {
                 System.exit(0);
             }
		 });
	}
	
	public void makeMove(){
		validList =  client.getValidMoves();
		Iterator<Integer> validIterator = validList.iterator();
		while(validIterator.hasNext()){
			JButton validButton = buttonArray[validIterator.next()];
			validButton.setEnabled(true);
			validButton.setBackground(new Color(100,100,100));
		}
	}
	
	public void setField(int i, FieldType color) {
		if (color == FieldType.RED){
			buttonArray[i].setBackground(new Color(255,0,0));	
		}
		if (color == FieldType.YELLOW){
			buttonArray[i].setBackground(new Color(255,255,0));	
		}
		if (color == FieldType.GREEN){
			buttonArray[i].setBackground(new Color(0,255,0));	
		}
		if (color == FieldType.BLUE){
			buttonArray[i].setBackground(new Color(0,0,255));	
		}	
		if (color == FieldType.EMPTY){
			buttonArray[i].setBackground(new Color(0,0,0));	
		}
	}

	public void init(){
		Border boardLine = BorderFactory.createLineBorder(Color.black);
		for (int i = 0; i < DIM*DIM; i++){
			
			JButton fieldButton = new JButton();
			fieldButton.setBackground(new Color(0,0,0));
			fieldButton.setEnabled(false);
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

	
	public class RolitController implements ActionListener{
		private Client client;
		public RolitController(Client cl){
			client = cl;
			for (int i = 0; i < 64; i++){
				buttonArray[i].addActionListener(this);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			for (int i = 0; i < 64; i++){
				JButton button = buttonArray[i];
				if (button.isEnabled()){
					button.setEnabled(false);
					button.setBackground(new Color(0,0,0));
				}
				if (buttonArray[i].equals(b)){
					client.sendMove(i);
				}
			}
			
		}
		
	}

}