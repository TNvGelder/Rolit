package client;
import game.Game;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class ClientGUI extends JFrame implements Observer{
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
	
	public void getmove(){
		client.getValidList();
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
				if (buttonArray[i].equals(b)){
					client.sendMove(i);
				}
			}
			
		}
		
	}
}