package network;

import java.util.HashMap;

public class Errors {
        
        public static final int SERVER = 1;
        public static final int CLIENT = 2;
        
        public HashMap<Integer, String> serverErrors = new HashMap<Integer, String>();
        public HashMap<Integer, String> clientErrors = new HashMap<Integer, String>();
        
        /**
         * Server error codes
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

        public static String getError(int type, int errorCode) {
                String error = "Unknown error";

                if(type == SERVER){
                        switch(errorCode){
                                case ERROR_GENERIC:
                                        error = "An error occurred";
                                        break;
                                case ERROR_INVALID_LOGIN:
                                        error = "Invalid login";
                                        break;
                                case ERROR_GAME_FULL:
                                        error = "Game is full";
                                        break;
                                case ERROR_TOO_FEW_PLAYERS:
                                        error = "Too few players";
                                        break;
                                case ERROR_INVALID_MOVE:
                                        error = "Invalid move";
                                        break;
                                case ERROR_NO_SUCH_GAME:
                                        error = "No such game";
                                        break;
                                case ERROR_USER_HAS_NO_GAME:
                                        error = "User has no game";
                                        break;
                                case ERROR_HANDSHAKE_MISSING:
                                        error = "Handshake missing";
                                        break;
                                case ERROR_USER_ALREADY_HAS_GAME:
                                        error = "User already has game";
                                        break;
                        }
                }
                if(type == CLIENT){
                        if(errorCode == ERROR_GENERIC){
                                error = "An error occurred.";        
                        }
                }
                
                return error;
        }

}