package client;

import game.Game;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class ClientGUI extends JFrame implements Observer{

	private Container c = getContentPane();
	private JButton[] buttonArray = new JButton[10];
	private JLabel turnInfo = new JLabel();
	private TTTController contr;
	private Game game;
	
	public ClientGUI(Game g){
		this.game=game;
		init();
		contr = new TTTController(g);
		
	}
	
	public void init(){
		int dim = game.getBoard().DIM;
		for (int i = 0; i < dim; i++){
			buttonArray[i] = new JButton("Empty");
		}
		buttonArray[dim] = new JButton("Another Game?");
		setLayout(new FlowLayout());
		for (int i = 0; i < 10; i++){
			c.add(buttonArray[i]);
		}
		c.add(turnInfo);
		setSize(300,300);
		setVisible(true);
		
	}
	
	public static void main(String[] args){
		Game obsGame = new Game();
		ClientGUI test1 = new ClientGUI(obsGame);
		obsGame.addObserver(test1);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
	}
	
	public class TTTController implements ActionListener{
		private Game game;
		
		public TTTController(Game g){
			game = g;
			for (int i = 0; i < 10; i++){
				buttonArray[i].addActionListener(this);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			
		}
		
	}
	
}
