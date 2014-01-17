package src;
/**
 * Klasse die het speelbord beschrijft van Rolit.
 * @author Casper
 * @version $Revision: 0.1 $
 */
public class Board {
	
	//---Constants--------------------------------------------------------------------//
	public static final int DIM = 8;
	private static final String[] INDEXING = {"+----+----+----+----+----+----+----+----+" , "| 00 | 01 | 02 | 03 | 04 | 05 | 06 | 07 |" , "+----+----+----+----+----+----+----+----+" , "| 08 | 09 | 10 | 11 | 12 | 13 | 14 | 15 |" , "+----+----+----+----+----+----+----+----+" , "| 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |" , "+----+----+----+----+----+----+----+----+" , "| 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 |" , "+----+----+----+----+----+----+----+----+" , "| 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 |" , "+----+----+----+----+----+----+----+----+" , "| 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 |" , "+----+----+----+----+----+----+----+----+" , "| 48 | 49 | 50 | 51 | 52 | 53 | 54 | 55 |" , "+----+----+----+----+----+----+----+----+" , "| 56 | 57 | 58 | 59 | 60 | 61 | 62 | 63 |" , "+----+----+----+----+----+----+----+----+"};

    private static final String SEPERATOR = INDEXING[0];
    private static final String WHITESPACE = "    ";

	/**
	 * FType describes the state of a field, which can be the colours red, green, yellow, blue or empty.
	 */
	enum FType {EMPTY, RED, YELLOW, GREEN, BLUE};
	
	// -- Instance variables ---------------------------------------------------------//
	/**
	 * The board is made out of fields, as described below.
	 */
	private FType[][] fields;
	private FType[] index;
	
	//---Constructor -----------------------------------------------------------------//
	/**
	 * Create a board and set the 4 default fields. 
	 */
	public Board(){
		fields = new FType[ DIM ][ DIM ];
		index = new FType[ DIM * DIM ];
		setField( 3 , 3 , FType.RED);
		setField( 4 , 3 , FType.YELLOW);
		setField( 4 , 4 , FType.GREEN);
		setField( 3 , 4 , FType.BLUE);
	}
	
	/**
	 * Copy the exact current state of the board.
	 * @return newboard
	 */
	public Board copyBoard(){
		Board newboard = new Board();
		for (int i = 0; i<64; i++){
			newboard.setField(i, index[i]);
		}
		return newboard;
	}
	
	/**
	 * Standard method for in-game moves. Checks whether the move is valid, then proceeds with game.
	 * @param x
	 * @param y
	 * @param activeplayer
	 * @param index
	 */
	public void move(int x, int y, FType activeplayer){		
		if (isValid(x, y, activeplayer)){
			setField(x, y, activeplayer);
		};
		// TO-DO notifyAll() ofzo?? Of iets anders van nextPlayer()?
	} 							//1: isValid(), 2: setField() (,3: nextPlayer());
	public void move(int index, FType activeplayer){
		if (isValid(index, activeplayer)){
			setField(index, activeplayer);
		}
	}
	
	/**
	 * Check whether the specified move is legal or not.
	 * @param x
	 * @param y
	 * @return boolean Whether the move is legal (true) or not (false).
	 */
	public boolean isValid(int x, int y, FType player){
		boolean result = false;
		if( !isAdjacent(x, y)){
			return false;
		}
		else if (isHorBeat(x, y, player) || isVerBeat(x, y, player) || isDiagBeat(toIndex(x,y), player)){ //hor, vert or diag same FType with different FType(s) between with no FType.EMPTY
			setXFields(x, y, player);
			setYFields(x, y, player);
			setDiagFields(x, y, player);
		}
		else {
			return isAdjacent(x, y);
		}
		
		
		return result;
	} //1: isAdjacent() 2: FType;
	public boolean isValid(int i, FType player){
		return isValid(toXCoord(i), toYCoord(i), player);
	}
	
	//--- ------------------------------------------------------------------------//
	
	public void setField(int x, int y, FType color){
		fields[x][y] = color;
		setField(toIndex(x, y), color);
	} //just set it, do not check for legal;
	public void setField(int i, FType color){
		index[i] = color;
		setField(toXCoord(i), toYCoord(i), color);
	}
		
	public FType getField(int x, int y){return fields[x][y];} //get tha colours
	public FType getField(int i){return index[i];}


	public boolean isEmpty(int x, int y){return getField(x, y)==FType.EMPTY;}
	public boolean isEmpty(int i){return getField(i)==FType.EMPTY;}
	
	// --- Game Logic ----------------------------------------------------------------------------//
	
	/**
	 * Check whether the given coördinates are adjacent to an non-empty place.
	 * @param x
	 * @param y
	 * @param index
	 * @return boolean True if adjacent, False if not :-)
	 */
	public boolean isAdjacent(int x, int y){
		return isAdjacent(toIndex(x, y));
	}
	public boolean isAdjacent(int i){
		if(	   !isEmpty(i-9) || !isEmpty(i-8) || !isEmpty(i-7) 
			|| !isEmpty(i-1) 				  || !isEmpty(i+1) 
			|| !isEmpty(i+7) || !isEmpty(i+8) || !isEmpty(i+9)
			){
			return true;
		}
		return false;
	}
	
	/**
	 * Check if there is another field containing the same FType.
	 * Requires that isEmpty(x,y)==true.
	 */
	public boolean isHorBeat(int x, int y, FType player){
		if (x<7 && getField(x+1)!=player){ //IK ZIE ZE RECHTS
			for (int i=x+1; i<8; i++){
				if (getField(i,y)==player){
					return true;
				}
			}
		}
		if (getField(x-1)!=player){ //IK ZIE ZE LINKS
			for (int i=x-1; i>0; i--){
			}
		}
	}
	public boolean isVerBeat(int x, int y, FType player){
		
	}
	public boolean isDiagBeat(int i, FType player){
		
	}
	
	public void setXFields(int x, int y, FType player){}
	public void setYFields(int x, int y, FType player){}
	public void setDiagFields(int x, int y, FType player){}
	
	
	// --- Helping methods --------------------------------------------------------------------//
	public int[] getEmptyFields(){} //loop through 'index', 1: if(isEmpty());
	public int[] getValidList(FType activeplayer){} //for all getEmptyIndices(), 1:isEmpty(), 2:(x, y or diagonal must be one ball (adjacent) with same activeplayer) !iff (must be adjacent);
	
	public boolean isFull(){}

	
	//--- Omrekenen -------------------------------------------------------//
	//---------------------------------------------------------------------//
	public int toIndex(int x, int y){
		int i = 0;
		i += (y* DIM );
		i += x;
		return i;
		//TO-DO opvangen als -1 < x*y > 63;
		//TO-DO opvangen als -1 < x > 8 || -1 < y > 8;
		
	}
	public int toXCoord(int index){
		int x = 0;
		x = index % 8;
		return x; 
		
		//TO-DO opvangen als -1 < index > 63;
	}
	public int toYCoord(int index){
		int y = 0;
		y = index / 8;
		return y; 
		
		//TO-DO opvangen als -1 < index > 63;
	}
	//---------------------------------------------------------------------//
	
	
	
	
	/**
	 * Check whether the board is full or not.
	 * @return
	 */
	public boolean gameOver(){
		return isFull();
	}
	
	//toString
	public String toString(){
		String s = "";
		for (int i = 0; i<DIM; i++){ // ga elke Y af
			String row = "";
			for (int j = 0; j<DIM; j++){ //ga elke X af
				
			}
		}
		
		return s;
	}
	
}
