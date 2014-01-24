package client;
import game.Game;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ClientGUI extends JFrame implements ActionListener{
	private Container c = getContentPane();
	private JButton[] buttonArray = new JButton[63];
	private JLabel turnInfo = new JLabel("Playername's turn");
	private int guiSize;
	
	public ClientGUI(int size){
		guiSize = size;
		init();
	}

	public void init(){
		for (int i = 0; i < 63; i++){
			JButton fieldButton = new JButton();
			fieldButton.setSize((guiSize - guiSize/10)/8,(guiSize - guiSize/10)/8);
			buttonArray[i] = fieldButton;
			c.add(buttonArray[i]);
			c.setLayout(new FlowLayout());
		}
		c.add(turnInfo);
		setLayout(new FlowLayout());
		for (int i = 0; i < 10; i++){
			c.add(buttonArray[i]);
		}
		c.add(turnInfo);
		c.setSize(100,100);
		setSize(guiSize,guiSize);
		setVisible(true);
	}	

	
	public static void main(String[] args){
		ClientGUI test1 = new ClientGUI(800);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}