package game;

import java.util.Iterator;
import java.util.List;

public class BasicAI implements Player{

	private String name;
	private FieldType myColour;
	private Game game;
	
	public BasicAI(String name){
		this.name = name;
	}
	
	@Override
	public void setGame(Game g) {
		this.game = g;
		
	}

	@Override
	public int giveMove() {
		int bestMove = -1;
		int bestGain = 0;
		Board gameBoard = game.getBoard();
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

	@Override
	public void setColour(FieldType newColour) {
		myColour = newColour;
		
	}

	public int tryBeat(Board board, int fieldIndex) {
		return board.beat(fieldIndex, myColour, false);
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public FieldType getColor() {
		return myColour;
	}


}
