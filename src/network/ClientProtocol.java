package network;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

import client.ServerHandler;
//Deze hoort hier niet?
public class ClientProtocol {

        /**
         * Constante voor het handshake-commando
         * hello [supports] [version] (nonce)
         * 
         */
        public static final String HANDSHAKE = "hello";
        /**
         * Constante voor het authOk-commando
         * authok
         */
        public static final String AUTH_OK = "authOk";
        /**
         * Constante voor het error-commando
         * error [errorcode]
         */
        public static final String ERROR = "error";
        /**
         * Constante voor het game-commando
         * game [creator] [status] [numPlayers]
         */
        public static final String GAME = "game";
        /**
         * Constante voor het start-commando
         * start [player1] [player2] (player3 (player4))
         */
        public static final String START = "start";
        /**
         * Constante voor het move-commando
         * move
         */
        public static final String MOVE = "move";
        /**
         * Constante voor het move-done-commando
         * moveDone [player] [x] [y]
         */
        public static final String MOVE_DONE = "moveDone";
        /**
         * Constante voor het game-over-command
         * gameOver (highSquareCount (winner1 (winner2 (winner3 (winner4)))))
         */
        public static final String GAME_OVER = "gameOver";
        /**
         * Constante voor het message-commando
         * message [afzender] [message]
         */
        public static final String MESSAGE = "message";
        /**
         * Constante voor het challenge-commando
         * challenge [challenger] [other1] (other2 (other3))
         */
        public static final String CHALLENGE = "challenge";
        /**
         * Constante voor het challenge-response-commando
         * challengeResponse [challengee] [accept]
         */
        public static final String CHALLENGE_RESPONSE = "challengeResponse";
        /**
         * Constante voor het can-be-challenged-commando
         * canBeChallenged [player] [flag]
         */
        public static final String CAN_BE_CHALLENGED = "canBeChallenged";
        /**
         * Constante voor het online commando
         * online [naam] [isOnline]
         */
        public static final String ONLINE = "online";
        /**
         * Constante voor highscore
         * highscore [results]
         */
        public static final String HIGHSCORE = "highscore";
        
        // Custom commands
        /**
         * Constant for custom game list command
         * list owner1;started1;numplayers1 owner2;started2;numplayers2 ...
         */
        public static final String CUSTOM_LIST = "list";

        /**
         * Generic error codes
         */
        public static final int ERROR_GENERIC = -1;
        public static final int ERROR_INVALID_LOGIN = 1;
        public static final int ERROR_GAME_FULL = 2;
        public static final int ERROR_TOO_FEW_PLAYERS = 3;
        public static final int ERROR_INVALID_MOVE = 4;
        public static final int ERROR_NO_SUCH_GAME = 5;
        public static final int ERROR_USER_HAS_NO_GAME = 6;
        public static final int ERROR_HANDSHAKE_MISSING = 7;
        public static final int ERROR_USER_ALREADY_HAS_GAME = 8;
        public static final int HIGHSCORE_UNAVAILABLE = -1;

        /**
         * Status om aan te geven dat de creator voortijdig is weggegaan
         */
        public static final int STATUS_PREMATURE_LEAVE = -1;

        /**
         * Status om aan te geven dat het spel nog niet is gestart.
         */
        public static final int STATUS_NOT_STARTED = 0;

        /**
         * Status om aan te geven dat het spel is gestart.
         */
        public static final int STATUS_STARTED = 1;
        
        public static final int CLIENT_SUPPORTS_BAREBONE = 0;
        public static final int CLIENT_SUPPORTS_CHAT = 1;
        public static final int CLIENT_SUPPORTS_CHALLENGE = 2;
        public static final int CLIENT_SUPPORTS_CHATCHALLENGE = 3;
        
        private ServerHandler parentHandler = null;
        
        public HashMap<String,Integer[]> clientCommands = new HashMap<String,Integer[]>();
        
    	public boolean isValidCommand(String msg) {

    		String[] splitCommand = msg.split(" ");
    		String command = splitCommand[0].toLowerCase();
    		int numArgs = splitCommand.length - 1;

    		if (clientCommands.containsKey(command)) {
    			for (int possibleArgs : clientCommands.get(command)) {
    				if (numArgs == possibleArgs) {
    					return true;
    				}
    				if (possibleArgs == -1 && numArgs > 0
    						&& command.equals(ClientProtocol.MESSAGE)) {
    					return true;
    				}
    			}
    		}
    		return false;
    	}
    	public void doCommand(String msg) throws IllegalArgumentException,
		IOException {
			String[] splitCommand = msg.split(" ");
			String command = splitCommand[0].toLowerCase();
			int numArgs = splitCommand.length - 1;
			String[] args = new String[numArgs];
			for (int i = 1; i <= numArgs; i++) {
				args[i - 1] = splitCommand[i];
			}
			switch (command) {
			case HANDSHAKE:
				int supports = Integer.parseInt(args[1]);
				hello(args[0], supports, args[2]);
				break;
			case ERROR:
				int code = Integer.parseInt(args[0]);
				error(code);
				break;
			case CREATE_GAME:
				createGame();
				break;
			case JOIN_GAME:
				joinGame(args[0]);
				break;
			case START_GAME:
				startGame();
				break;
			case MOVE:
				int x = Integer.parseInt(args[0]);
				int y = Integer.parseInt(args[1]);
				move(x, y);
				break;
			case MESSAGE:
				String mesg = "";
				for (String arg : args) {
					if (mesg.equals("")) {
						mesg = arg;
					} else {
						mesg = mesg + " " + arg;
					}
				}
				message(mesg);
				break;
			default:
				// Unknown command or non-sensical message
				break;
			}
}
    	

}