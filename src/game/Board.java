package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.FieldType;

/**
 * Klasse die het speelbord beschrijft van Rolit.
 * @author Casper & Twan
 * @version $Revision: 0.9 $
 */
public class Board {
	
	//---Constants--------------------------------------------------------------------//
	public static final int DIM = 8;
	private static final String[] INDEXING = {"+----+----+----+----+----+----+----+----+" , "| 00 | 01 | 02 | 03 | 04 | 05 | 06 | 07 |" , "+----+----+----+----+----+----+----+----+" , "| 08 | 09 | 10 | 11 | 12 | 13 | 14 | 15 |" , "+----+----+----+----+----+----+----+----+" , "| 16 | 17 | 18 | 19 | 20 | 21 | 22 | 23 |" , "+----+----+----+----+----+----+----+----+" , "| 24 | 25 | 26 | 27 | 28 | 29 | 30 | 31 |" , "+----+----+----+----+----+----+----+----+" , "| 32 | 33 | 34 | 35 | 36 | 37 | 38 | 39 |" , "+----+----+----+----+----+----+----+----+" , "| 40 | 41 | 42 | 43 | 44 | 45 | 46 | 47 |" , "+----+----+----+----+----+----+----+----+" , "| 48 | 49 | 50 | 51 | 52 | 53 | 54 | 55 |" , "+----+----+----+----+----+----+----+----+" , "| 56 | 57 | 58 | 59 | 60 | 61 | 62 | 63 |" , "+----+----+----+----+----+----+----+----+"};

    private static final String SEPERATOR = INDEXING[0];
    private static final String WHITESPACE = "    ";
    
    int redFields = 0;
    int yellowFields = 0;
    int blueFields = 0;
    int greenFields = 0;

	
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
		for (int y = 0; y < DIM; y++) { //EERST DE Y
			for (int x = 0; x < DIM; x++) { //DAN DE X
				setField(x, y, FieldType.EMPTY);
			}
		}
		setField( 3 , 3 , FieldType.RED);
		setField( 4 , 3 , FieldType.YELLOW);
		setField( 4 , 4 , FieldType.GREEN);
		setField( 3 , 4 , FieldType.BLUE);
	}
	
	/**
	 * @returns the ammount of fields with FieldType colour
	 */
	public int getClrCount(FieldType colour){
		if (colour == FieldType.RED){
			return redFields;
		}
		if (colour == FieldType.YELLOW){
			return yellowFields;
		}
		if (colour == FieldType.GREEN){
			return greenFields;
		}
		if (colour == FieldType.BLUE){
			return blueFields;
		}
		else{
			return (DIM*DIM - redFields - yellowFields - greenFields - blueFields);
		}
	}
	
	/**
	 * adds number to redFields, yellowFields, greenFields or blueFields.
	 */
	public void changeClrCount(int number, FieldType colour){
		if (colour == FieldType.EMPTY){
			return;
		}
		if (colour == FieldType.RED){
			redFields = redFields + number;
		}
		if (colour == FieldType.YELLOW){
			yellowFields = yellowFields + number;
		}
		if (colour == FieldType.GREEN){
			greenFields = greenFields + number;
		}
		if (colour == FieldType.BLUE){
			blueFields = blueFields + number;
		}
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
	public void move(int x, int y, FieldType player){		
		if (isValid(x, y, player)){
			setField(x, y, player);
			beat(x,y,player,true);
			//Als valid move is, zijn ook de stenen al gezet.
		}
	}
	public void move(int i, FieldType player){
		move(toXCoord(i), toYCoord(i), player);
	}
	
	/**
	 * Check whether the specified move is legal or not.
	 * @param x
	 * @param y
	 * @param i		(converts to coordinates, then excecutes with coordinates)
	 * @return boolean Whether the move is legal (true) or not (false).
	 */
	public boolean isValid(int x, int y, FieldType playerColour){
		return isValid(toIndex(x,y), playerColour);
	}
	public boolean isValid(int i, FieldType playerColour){
		return (getValidList(playerColour).contains(i));
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
		FieldType oldColour = getField(x,y);
		changeClrCount(-1, oldColour);
		changeClrCount(1, color);
		fields[x][y] = color;
		index[toIndex(x,y)] = color;
	}
	public void setField(int i, FieldType color){
		setField(toXCoord(i), toYCoord(i), color);
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
		boolean result = false;
		for(int i = x-1; i<=x+1; i++){
			for(int j = y-1; j<=y+1; j++){
				if(i>=0 && i<DIM && j>=0 && j<DIM && !(i==x && j==y)){
					result |= !isEmpty(i,j);
				}
			}
		}
		return result;
	}
	public boolean isAdjacent(int i){
		return isAdjacent(toXCoord(i), toYCoord(i));
	}
	
	/**
	 * Beats all possible enemy colours near (x,y).
	 * Requires that isEmpty(x,y)==true.
	 * returns the gain of the move. If gain == 1 the beat is not valid and the board won't change.
	 * @return (getClrCount(playerColour) - startCount + 1);
	 */

	public int beat(int i, FieldType playerColour, boolean allowSetField){
		return beat(toXCoord(i), toYCoord(i), playerColour, allowSetField);
	}
	public int beat(int x, int y, FieldType playerColour,boolean allowSetField){
		int gainCount = 1;
		//---------- Horizontal --------------------------------------------------//
		if (x<6 && getField(x+1 ,y)!=playerColour){
			for (int i=x+1; i<8; i++){
				if (getField(i,y) == FieldType.EMPTY){
					i = 8;
				}
				if (i < 8 && getField(i,y)==playerColour){
					for (int i2 = x; i2 < i; i2++ ){
						if (allowSetField){
							setField(i2, y, playerColour);	
						}
						gainCount++;
					}
					i = 8;
				}
			}
		}
		if (x>1 && getField(x-1 ,y)!=playerColour){
			for (int i=x-1; i>=0; i--){
				if (getField(i,y) == FieldType.EMPTY){
					i = -1;
				}
				if (i > -1 && getField(i,y) == playerColour){
					for (int i2 = x; i2 > i; i2--){
						if (allowSetField){
							setField(i2, y, playerColour);	
						}
						gainCount++;
					}
					i = -1;
				}
			}
		}
		//---------- Vertical ----------------------------------------------------//
		if (y<6 && getField(x ,y+1)!=playerColour){
			for (int i=y+1; i<8; i++){
				if (getField(x,i) == FieldType.EMPTY){
					i = 8;
				}
				if (i < 8 && getField(x, i)==playerColour){
					for (int i2 = y; i2 < i; i2++ ){
						if (allowSetField){
							setField(x, i2, playerColour);	
						}
						gainCount++;
					}
					i = 8;
				}
			}
		}

		if (y>1 && getField(x ,y-1)!=playerColour){
			for (int i=y-1; i>=0; i--){
				if (getField(x,i) == FieldType.EMPTY){
					i = -1;
				}
				if (i > -1 && getField(x,i) == playerColour){
					for (int i2 = y; i2 > i; i2--){
						if (allowSetField){
							setField(x, i2, playerColour);	
						}
						gainCount++;
					}
					i = -1;
				}
			}
		}

		//---------- Diagonal ----------------------------------------------------//
		int z = toIndex(x,y);
		
			// --- Links-Boven ---//
		if (x>1 && y>1 && getField(x-1,y-1)!=playerColour){	
			for (int j = z-9; j>=0; j=j-9){
				if (getField(j) == FieldType.EMPTY){
					j = -1;
				}
				if (j >= 0 && getField(j)==playerColour){
					for (int j2 = z; j2 > j; j2 = j2 - 9){
						if (allowSetField){
							setField(j2, playerColour);	
						}
						gainCount++;
					}
					j = -1;
				}
			}
		}
			// --- Rechts-Boven ---//
		if (x<6 && y>1 && getField(x+1, y-1) != playerColour){
			for (int j = z-7; j>=0; j=j-7){
				if (getField(j) == FieldType.EMPTY){
					j = -1;
				}
				if (j >= 0 && getField(j)==playerColour){
					for(int j2 = z; j2 > j; j2 = j2 - 7){
						if (allowSetField){
							setField(j2, playerColour);	
						}
						gainCount++;
					}
					j = -1;
				}
			}
		}
			// --- Links-Onder ---//
		if (x>1 && y<6 && getField(x-1, y+1) != playerColour){
			for (int j = z+7; j<=63; j=j+7){
				if (getField(j) == FieldType.EMPTY){
					j = 64;
				}
				if (j <= 63 && getField(j)==playerColour){
					for(int j2 = z; j2 < j; j2 = j2+7){
						if (allowSetField){
							setField(j2, playerColour);	
						}
						gainCount++;
					}
					j = 64;
				}
			}
		}

			// --- Rechts-Onder ---//
		if (x<6 && y<6 && getField(x+1, y+1) != playerColour){
			for (int j = z+9; j<= 63; j=j+9){
				if (getField(j) == FieldType.EMPTY){
					j = 64;
				}
				if (j <= 63 && getField(j)==playerColour){
					for(int j2 = z; j2 < j; j2 = j2+9){
						if (allowSetField){
							setField(j2, playerColour);	
						}
						gainCount++;
					}
					j = 64;
				}
			}
		}
		//------------------------------------------------------------------------//
		return gainCount;
		
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
	 * Use the list of indices of empty fields and check for every index whether it's adjacent or not.
	 * @return
	 */
	public List<Integer> getValidList(FieldType colour){
		List<Integer> list = getEmptyFields();
		List<Integer> validList = new ArrayList<Integer>();
		Iterator<Integer> emptyIterate = list.iterator();
		while (emptyIterate.hasNext()){
			int move = emptyIterate.next();
			if(isAdjacent(move) && (beat(move,colour,false) != 1)){
				validList.add(move);
			}
		}
		if (validList.size() == 0){
			emptyIterate = list.iterator();
			while (emptyIterate.hasNext()){
				int move = emptyIterate.next();
				if (isAdjacent(move)){
					validList.add(move);
				}
			}
		}
		return validList;
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
		return index % 8;
		
		//TO-DO opvangen als -1 < index > 63;
	}
	public int toYCoord(int index){
		return index / 8; 
		
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
		for (int y = 0; y < DIM; y++){ // ga elke Y af
			String row = "|";
            for (int x = 0; x < DIM; x++) { // ga elke X af
            	if (getField(x,y)== FieldType.EMPTY ){
            		String number = "";
            		if (toIndex(x,y)<10){
            			number = "0" + toIndex(x,y);
            		}
            		else {
            			number = "" + toIndex(x,y);
            		}
            		row = row + " " + number + " |";
            	}
            	else {
            		String field = "    ";
            		if (getField(x,y)==FieldType.RED){field = "RED ";}
            		else if (getField(x,y)==FieldType.YELLOW){field = "YELL";}
            		else if (getField(x,y)==FieldType.GREEN){field = "GREE";}
            		else if (getField(x,y)==FieldType.BLUE){field = "BLUE";}
            		row = row + field + "|";
            	}
            }
            s = s+ "\n" + SEPERATOR + "\n" + row;
            
        }
		s = s + "\n" + SEPERATOR;
        return s;
     }
	public static void main(String args[]){
		Board board = new Board();
		System.out.println(board.toString());
		board.move(26, FieldType.RED);// mag volgens de regels niet
		board.move(31, FieldType.RED);// mag volgens de regels niet
		board.move(29, FieldType.RED);
		board.move(44, FieldType.YELLOW);
		System.out.println(board.toString());
	}
	
}
