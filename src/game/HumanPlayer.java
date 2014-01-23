package game;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {
	
	public HumanPlayer(String name, FieldType color) {
		super(name, color);
	}

	@Override
	public int getMove(Board board) {
		boolean valid = false;
		int result = 0;
        
        System.out.print("" + getName() + ", please do a move.");
		
		do {
            String line = (new Scanner(System.in)).nextLine();
            Scanner lineScanner = new Scanner(line);
            if (lineScanner.hasNextInt()) {
                if (isValidMove(lineScanner.nextInt(), board)){
                	result = lineScanner.nextInt();
                	valid = true;
                }
            }
        } while (!valid);
		
		return result;
	}
	
	
	/**.
     * checks whether move is valid
     * @param choice - move that the player wants to do
     * @param b - board
     * @return boolean - whether move is valid or not
     */
    public boolean isValidMove(int choice, Board b) {
            List<Integer> fields = b.getValidList(this.getColor());
            return fields.contains(choice);
    }
    
    
}
