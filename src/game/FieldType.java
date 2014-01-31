package game;

public enum FieldType {
	
	/**
	 * FieldType describes the state of a field, which can be the colours red, green, yellow, blue or empty.
	 */
	
	EMPTY, RED, YELLOW, GREEN, BLUE;
	
	public FieldType nextFieldType(int playerCount){
		if (playerCount == 2 && this == RED){
			return GREEN;
		}
		if (playerCount == 2 && this == GREEN){
			return RED;
		}
		if (playerCount > 3){
			if (this == RED){
				return YELLOW;
			}
			if (this == YELLOW){
				return GREEN;
			}
		}
		if (playerCount >= 3){
			if (this == GREEN){
				return RED;
			}
		}
		if (playerCount == 4){
			if (this == GREEN){
				return BLUE;
			}
			if (this == BLUE){
				return RED;
			}
		}
		return null;

	}
}
