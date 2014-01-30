package client;

import game.Board;
import game.FieldType;
import game.Game;

import java.util.Iterator;
import java.util.List;

public class BasicAI extends Player{

	private String name;
	private FieldType myColour;
	private Game game;
	
	public BasicAI(String name, Client cl){
		super(name, cl);
	}
	
	@Override
	public int getMove(Board board) {
		int bestMove = -1;
		int bestGain = 0;
		Board gameBoard = board;
		List <Integer> validList = gameBoard.getValidList(myColour);
		System.out.println(validList.toString());
		Iterator<Integer> moveIterator = validList.iterator();
		while (moveIterator.hasNext()){
			int tryoutIndex = moveIterator.next();
			Board tryoutBoard = game.getBoard();
			int gain = tryBeat(tryoutBoard, tryoutIndex);
			while (gain == 1 && moveIterator.hasNext()){
				tryoutIndex = moveIterator.next();
				gain = tryBeat(tryoutBoard, tryoutIndex);
			}
			if (gain > bestGain){
				bestGain = gain;
				bestMove = tryoutIndex;
			}
		}
		System.out.println(myColour + " chooses index " + bestMove);
		return bestMove;
	}

	public int tryBeat(Board board, int fieldIndex) {
		return board.beat(fieldIndex, myColour, false);
		
	}



}
