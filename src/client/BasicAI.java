package client;

import game.Board;
import game.FieldType;
import game.Game;

import java.util.Iterator;
import java.util.List;

public class BasicAI{

	private String name;
	private FieldType myColour;
	private Client client;
	
	public BasicAI(String name, Client cl){
		this.name = name;
		client = cl;
	}
	
	public void makeMove(){
		client.sendMove(client.getHint());
	}

	public int tryBeat(Board board, int fieldIndex) {
		return board.beat(fieldIndex, myColour, false);
		
	}



}
