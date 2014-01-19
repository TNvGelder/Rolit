package game;

import java.util.ArrayList;
import java.util.List;

import game.FieldType;

/**
 * Klasse die het speelbord beschrijft van Rolit.
 * @author Casper
 * @version $Revision: 0.9 $
 */
public class Board {
	
	//---Constants--------------------------------------------------------------------//
	public static final int DIM = 8;
	private static final String[] INDEXING = {"+----+----+----+----+----+----+----+----+" , "| 00 | 01 | 02 | 03 | 04 | 05 | 06 | 07 |" , "+----+----+----+----+----+----+----+----+" , "| 08 | 09 | 10 | 11 | 12 | 13 | 14 | 15 |" , "+----+----+----+----+----+----+----+----+" , "| 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |" , "+----+----+----+----+----+----+----+----+" , "| 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 |" , "+----+----+----+----+----+----+----+----+" , "| 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 |" , "+----+----+----+----+----+----+----+----+" , "| 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 |" , "+----+----+----+----+----+----+----+----+" , "| 48 | 49 | 50 | 51 | 52 | 53 | 54 | 55 |" , "+----+----+----+----+----+----+----+----+" , "| 56 | 57 | 58 | 59 | 60 | 61 | 62 | 63 |" , "+----+----+----+----+----+----+----+----+"};

    private static final String SEPERATOR = INDEXING[0];
    private static final String WHITESPACE = "    ";

	
	// -- Instance variables ---------------------------------------------------------//
	/**
	 * The board is made out of fields, as described below.
	 */
	private FieldType[][] fields;
	private FieldType[] index;
	
	//---Constructor -----------------------------------------------------------------//
	/**
	 * Create a board and set the 4 default fields. 
	 */
	public Board(){
		fields = new FieldType[ DIM ][ DIM ];
		index = new FieldType[ DIM * DIM ];
		setField( 3 , 3 , FieldType.RED);
		setField( 4 , 3 , FieldType.YELLOW);
		setField( 4 , 4 , FieldType.GREEN);
		setField( 3 , 4 , FieldType.BLUE);
	}
	
	/**
	 * Copy the exact current state of the board in a new instance of it.
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
	public void move(int x, int y, FieldType activeplayer){		
		if (isValid(x, y, activeplayer)){
			setField(x, y, activeplayer);
		};
		// TO-DO notifyAll() ofzo?? Of iets anders van nextPlayer()?
	} 							//1: isValid(), 2: setField() (,3: nextPlayer());
	public void move(int index, FieldType activeplayer){
		if (isValid(index, activeplayer)){
			setField(index, activeplayer);
		}
	}
	
	/**
	 * Check whether the specified move is legal or not.
	 * @param x
	 * @param y
	 * @param i		(converts to coordinates, then excecutes with coordinates)
	 * @return boolean Whether the move is legal (true) or not (false).
	 */
	public boolean isValid(int x, int y, FieldType player){
		boolean result = false;
		if( !isAdjacent(x, y)){
			return false;
		}
		else if (isBeat(x, y, player)){
			result = true;
		}
		else {
			result =  isAdjacent(x, y);
		}
		return result;
	} //1: isAdjacent() 2: FieldType;
	public boolean isValid(int i, FieldType player){
		return isValid(toXCoord(i), toYCoord(i), player);
	}
	
	//--- ------------------------------------------------------------------------//

	
	/**
	 * Do not use setField() outside of Board. Use move() instead. 
	 * setField exists so that the constructor doesn't have to to 
	 * strange stuff and copies of the board are easily made.
	 * @param x
	 * @param y
	 * @param color
	 */
	public void setField(int x, int y, FieldType color){
		fields[x][y] = color;
		if (getField(x,y)!=color){
			setField(toIndex(x, y), color);
		}
	}
	public void setField(int i, FieldType color){
		index[i] = color;
		if (getField(i)!=color){
			setField(toXCoord(i), toYCoord(i), color);
		}
	}
	
	/**
	 * Returns whatever FieldType is in the field.
	 * @param x
	 * @param y
	 * @return FieldType The color of the field in the given position.
	 */
	public FieldType getField(int x, int y){return fields[x][y];}
	public FieldType getField(int i){return index[i];}

	/**
	 * Returns true if the field is empty.
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEmpty(int x, int y){return getField(x, y)==FieldType.EMPTY;}
	public boolean isEmpty(int i){return getField(i)==FieldType.EMPTY;}

	
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
	 * Check if there is another field containing the same FieldType.
	 * Requires that isEmpty(x,y)==true.
	 */
	public boolean isBeat(int x, int y, FieldType player){
		//---------- Horizontal --------------------------------------------------//
		if (x<6 && getField(x+1 ,y)!=FieldType.EMPTY && getField(x+1 ,y)!=player){
			for (int i=x+2; i<8; i++){
				if (getField(i,y)==player){
					return true;
				}
			}
		}
		if (x>1 && getField(x-1 ,y)!=FieldType.EMPTY && getField(x-1 ,y)!=player){
			for (int i=x-2; i>0; i--){
				if (getField(i,y)==player){
					return true;
				}
			}
		}
		//---------- Vertical ----------------------------------------------------//
		if (y<6 && getField(x, y+1)!=FieldType.EMPTY && getField(x, y+1)!=player){
			for (int i=y+2; i<8; i++){
				if (getField(x,i)==player){
					return true;
				}
			}
		}
		if (y>1 && getField(x, y-1)!=FieldType.EMPTY && getField(x, y-1)!=player){
			for (int i=y-2; i>0; i--){
				if (getField(x,i)==player){
					return true;
				}
			}
		}
		//---------- Diagonal ----------------------------------------------------//
		int z = toIndex(x,y);
		
			// --- Links-Boven ---//
		if (x>1 && y>1 && getField(x-1, y-1)!=FieldType.EMPTY && getField(x-1,y-1)!=player){	
			for (int j = z-18; j>0; j=j-9){ if (getField(j)==player){return true;}}
		}
			// --- Rechts-Boven ---//
		if (x<6 && y>1 && getField(x+1, y-1) != FieldType.EMPTY && getField(x+1, y-1) != player){
			for (int j = z-14; j>0; j=j-7){ if (getField(j)==player){return true;}}
		}
			// --- Links-Onder ---//
		if (x>1 && y<6 && getField(x-1, y+1) != FieldType.EMPTY && getField(x-1, y+1) != player){
			for (int j = z+14; j>0; j=j+7){ if (getField(j)==player){return true;}}
		}
			// --- Rechts-Onder ---//
		if (x<6 && y<6 && getField(x+1, y+1) != FieldType.EMPTY && getField(x+1, y+1) != player){
			for (int j = z+18; j>0; j=j+9){ if (getField(j)==player){return true;}}
		}
		//------------------------------------------------------------------------//
		return false;
		
	}

	/**
	 * Turn the fields while doing a valid move.
	 * @param i
	 * @param player
	 */
	public void turnFields(int i, FieldType player){
		turnFields(toXCoord(i), toYCoord(i), player);
	}
	
	public void turnFields(int x, int y, FieldType player){
	//---------- Horizontal --------------------------------------------------//
		if (x<6 && getField(x+1 ,y)!=FieldType.EMPTY && getField(x+1 ,y)!=player){
			for (int i=x+2; i<8; i++){
				if (getField(i,y)!=player){
					setField(i,y, player);
				}
			}
		}
		if (x>1 && getField(x-1 ,y)!=FieldType.EMPTY && getField(x-1 ,y)!=player){
			for (int i=x-2; i>0; i--){
				if (getField(i,y)!=player){
					setField(i,y, player);
				}
			}
		}
	//---------- Vertical ----------------------------------------------------//
		if (y<6 && getField(x, y+1)!=FieldType.EMPTY && getField(x, y+1)!=player){
			for (int i=y+2; i<8; i++){
				if (getField(x,i)!=player){
					setField(x,i, player);
				}
			}
		}
		if (y>1 && getField(x, y-1)!=FieldType.EMPTY && getField(x, y-1)!=player){
			for (int i=y-2; i>0; i--){
				if (getField(x,i)!=player){
					setField(x,i, player);
				}
			}
		}
	//---------- Diagonal ----------------------------------------------------//
		int z = toIndex(x,y);
		
			// --- Links-Boven ---//
		if (x>1 && y>1 && getField(x-1, y-1)!=FieldType.EMPTY && getField(x-1,y-1)!=player){	
			for (int j = z-18; j>0; j=j-9){ if (getField(j)!=player){setField(j, player);}}
		}
			// --- Rechts-Boven ---//
		if (x<6 && y>1 && getField(x+1, y-1) != FieldType.EMPTY && getField(x+1, y-1) != player){
			for (int j = z-14; j>0; j=j-7){ if (getField(j)!=player){setField(j, player);}}
		}
			// --- Links-Onder ---//
		if (x>1 && y<6 && getField(x-1, y+1) != FieldType.EMPTY && getField(x-1, y+1) != player){
			for (int j = z+14; j>0; j=j+7){ if (getField(j)!=player){setField(j, player);}}
		}
			// --- Rechts-Onder ---//
		if (x<6 && y<6 && getField(x+1, y+1) != FieldType.EMPTY && getField(x+1, y+1) != player){
			for (int j = z+18; j>0; j=j+9){ if (getField(j)!=player){setField(j, player);}}
		}
		//------------------------------------------------------------------------//
	}


	// --- Helping methods --------------------------------------------------------------------//
	/**
	 * Get an List of integers with index numbers of empty fields.
	 * @return
	 */
	public List<Integer> getEmptyFields(){
		List<Integer> efields = new ArrayList<Integer>();
		for (int i=0; i<64; i++){
			if (getField(i)==FieldType.EMPTY){
				efields.add(i);
			}
		}
		return efields;
	}
	
	/**
	 * Use the list of indices of empty fields and check for every index whether it's valid or not.
	 * @param player
	 * @return
	 */
	public List<Integer> getValidList(FieldType player){
		List<Integer> list = getEmptyFields();
		for (int i=0; i<list.size(); i++){
			if (isValid(list.get(i),player)){
				list.add(i);
			}
		}
		return list;
	}
	
	public boolean isFull(){
		boolean result = true;
		for (int i=0; i<64; i++){
			if (isEmpty(i)){
				result = false;
			}
		}
		return result;}
	
	
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
		return false; //isFull();
	}
	
	//toString
	public String toString(){
		String s = "";
		for (int i = 0; i<DIM; i++){ // ga elke Y af
			String row = "|";
            for (int j = 0; j < DIM; j++) { // ga elke X af
            	if (getField(i,j)== FieldType.EMPTY ){
            		String number = "";
            		if (toIndex(i,j)<10){
            			number = "0" + toIndex(i,j);
            		}
            		else {
            			number = "" + toIndex(i,j);
            		}
            		row = row + " " + number + " |";
            	}
            	else {
            		String field = "";
            		if (getField(i,j)==FieldType.RED){field = "RED ";}
            		else if (getField(i,j)==FieldType.YELLOW){field = "YELL";}
            		else if (getField(i,j)==FieldType.GREEN){field = "GREE";}
            		else if (getField(i,j)==FieldType.BLUE){field = "BLUE";}
            		row = row + field + "|";
            	}
            }
            
        }
        return s;
     }
	public static void main(String args[]){
		Board board = new Board();
		System.out.println(board.toString());
	}
	
}
