package game;

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
		List <Integer> adjList = gameBoard.getAdjacentList();
		int i = 0;
		while (i < adjList.size()){
			Board tryoutBoard = game.getBoard();
			int gain = tryBeat(tryoutBoard, i);
			while (gain == 1){
				i++;
				gain = tryBeat(tryoutBoard, i);
			}
			if (gain > bestGain){
				bestGain = gain;
				bestMove = i;
			}
		}
		return bestMove;
	}

	@Override
	public void setColour(FieldType newColour) {
		myColour = newColour;
		
	}

	public int tryBeat(Board board, int fieldIndex) {
		return board.beat(fieldIndex, myColour);
		
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
