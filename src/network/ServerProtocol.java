package network;


import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import game.Board;
import network.Errors;
import Server.ClientHandler;
import game.Game;

public class ServerProtocol {
        
        // Dit zijn alle commando's die de server kan ONTVANGEN van de client,
        // dus, waarmee de server dingen moet kunnen doen.

        /**
        * Constante voor het handshake-commando
        * hello [player_name] [supports] [version]
        */
        public static final String HANDSHAKE = "hello";

        /**
        * Constante voor het auth-commando
        * auth [signed_nonce]
        */
        public static final String AUTH = "auth";

        /**
        * Constante voor het create-gamecommando
        * creategame
        */
        public static final String CREATE_GAME = "creategame";

        /**
        * Constante voor het join-gamecommando
        * joingame [creator]
        */
        public static final String JOIN_GAME = "joingame";

        /**
        * Constante voor het start-gamecommando
        * startgame
        */
        public static final String START_GAME = "startgame";

        /**
        * Constante voor het movecommando
        * move [x] [y]
        */
        public static final String MOVE = "move";

        /**
        * Constante voor het messagecommando
        * message [body]
        */
        public static final String MESSAGE = "message";

        /**
        * Constante voor het challengecommando
        * challenge [other1] (other2 (other3))
        */
        public static final String CHALLENGE = "challenge";

        /**
        * Constante voor het challenge-responsecommando
        * challengeresponse [accept]
        */
        public static final String CHALLENGE_RESPONSE = "challengeresponse";

        /**
        * Constante voor het highscorecommando
        * highscore [arg]
        */
        public static final String HIGHSCORE = "highscore";
        
        /**
         * Constante voor het errorcommando
         * error [errorcode]
         */
        public static final String ERROR = "error";
        
        // Custom commands constants
        /**
         * Constant for the list-games command
         * games
         */
        public static final String CUSTOM_LIST_GAMES = "games";
        
        public static final int SERVER_SUPPORTS_BAREBONE = 0;
        public static final int SERVER_SUPPORTS_CHAT = 1;
        public static final int SERVER_SUPPORTS_CHALLENGE = 2;
        public static final int SERVER_SUPPORTS_CHATCHALLENGE = 3;
                
        private ClientHandler handler = null;

        /**
        * Handshake voor de server. Moet altijd het eerste verzonden pakket zijn, met uitzondering van de errors.
        * @requires Dat de handshake nog niet is ontvangen
        * @requires Dat dit het eerste pakket op de communicatelijn is.
        * @param clientName Naam van de client.
        * @param supports Wat de client ondersteunt.
        * @param version Een beschrijving van wat de client kan.
        */
        public void hello(String clientName, int supports, String version) {
                handler.server.serverUpdate(clientName + " wants to 'hello'. Supports: " + supports + " with version " + version);
                if(!!this.handler.clientInfo.handshake && !this.handler.server.hasClient(clientName)){
                        this.setNickname(clientName);
                        this.setSupports(supports);
                        this.setVersion(version);
                        this.handler.sendCommand(ClientProtocol.HANDSHAKE, new String[]{String.valueOf(this.getServerSupports()), this.getServerVersion()});
                                if(this.getSupports() == ClientProtocol.CLIENT_SUPPORTS_CHAT || this.getSupports() == ClientProtocol.CLIENT_SUPPORTS_CHATCHALLENGE){
                                        this.handler.sendCommand(ClientProtocol.ONLINE, new String[]{this.getNick(), String.valueOf(true)});
                                }
                        this.setHandshakeDone(true);
                }else if(this.isHandshakeDone()){
                        this.sendGenericError();
                }else if(this.handler.server.hasClient(clientName)){
                        this.sendGenericError();
                }
        }
        
        /**
         * Error voor de client.
         * @param errorcode Errorcode (-1 voor generic error)
         */
        public void error(int errorCode){
        		handler.server.serverUpdate("The following errorcode has occured: " + errorCode);
        }

       
        /**
        * Maak een spel
        * @requires Dat de handshake is gedaan.
        * @requires Dat de client nog geen spel is begonnen
        */
        public void createGame() {
                handler.server.serverUpdate(ServerProtocol.CREATE_GAME);
                if(this.isHandshakeDone() && !this.hasGame()){
                        this.setNetworkGame(new ServerGame(), true);
                        this.setOwnsGame(true);
                        this.getNetworkGame().addPlayer(this.getNick());
                        this.broadcastGame(this.getNetworkGame());
                }else if(!this.isHandshakeDone()){
                        this.sendError(ClientProtocol.ERROR_HANDSHAKE_MISSING);
                }else if(this.hasGame()){
                        this.sendError(ClientProtocol.ERROR_USER_ALREADY_HAS_GAME);
                }else{
                        this.sendGenericError();
                }
        }

        /**
        * Join een spel
        * @requires Dat de handshake is gedaan.
        * @requires Dat de speler nog niet in een spel zit.
        * @requires Dat het spel nog niet vol zit.
        * @param creator De maker van het spel
        */
        public void joinGame(ClientHandler creator) {
        	//TODO: join game
        }

        /**
        * @requires Dat de handshake is gedaan.
        * @requires Dat de speler in een spel zit.
        * @requires Dat de speler de creator van het spel is.
        * @requires Dat het spel nog niet is gestart.
        * Start het spel waarvan de gebruiker de creator is.
        */
        public void startGame(int gameID) {
        	//TODO: start game
        }

        /**
        * Doe een zet
        * @requires Dat de handshake is gedaan.
        * @requires Dat de speler in een spel zit
        * @requires Dat de speler een move-commando heeft ontvangen.
        * @param x X-coördinaat
        * @param y Y-coördinaat
        */
        public void move(int x, int y) {
                Logger.log(Logger.COMMAND, ServerProtocol.MOVE+" "+x+" "+y);
                try{
                        if(this.isHandshakeDone() && this.hasGame() && this.getNetworkGame().currentPlayer.getName() == this.getNick()){
                                this.getNetworkGame().chooseMove(this.getNick(), x, y);
                        }else if(!this.isHandshakeDone()){
                                this.sendError(ClientProtocol.ERROR_HANDSHAKE_MISSING);
                        }else if(!this.hasGame()){
                                this.sendError(ClientProtocol.ERROR_NO_SUCH_GAME);
                        }else if(this.getNetworkGame().currentPlayer.getName() != this.getNick()){
                                this.sendError(ClientProtocol.ERROR_INVALID_MOVE);
                        }else{
                                this.sendGenericError();
                        }
                }catch(NullPointerException npe){
                        Logger.log(Logger.ERROR, "Client is not the current player.");
                        this.sendError(ClientProtocol.ERROR_INVALID_MOVE);
                }
        }

        /**
        * @requires Dat de handshake is gedaan.
        * @requires Dat de server chat-berichten ondersteunt.
        * Stuur een bericht naar iedereen in de lobby of iedereen in het spel
        * @param body Het bericht
        */
        public void message(String body) {
                Logger.log(Logger.COMMAND, ServerProtocol.MESSAGE+" "+body);
                int s = this.handler.server.serverInfo.supports;
                if(this.isHandshakeDone() && (s == ServerProtocol.SERVER_SUPPORTS_CHAT || s == ServerProtocol.SERVER_SUPPORTS_CHATCHALLENGE)){
                        String origin = this.getNick();
                        String message = body;
                        this.handler.sendCommand(ClientProtocol.MESSAGE, new String[]{origin, message});        
                }else if(!this.isHandshakeDone()){
                        this.sendError(ClientProtocol.ERROR_HANDSHAKE_MISSING);
                }else if(s != ServerProtocol.SERVER_SUPPORTS_CHAT && s != ServerProtocol.SERVER_SUPPORTS_CHATCHALLENGE){
                        // Server does not support chat. Do nothing.
                }else{
                        this.sendGenericError();
                }
        }
        
        /**
        * Daag één ander uit voor een spel
        * @requires Dat de handshake is gedaan.
        * @requires Dat de speler niet in een spel zit.
        * @requires Dat de speler niet een uitdager is.
        * @requires Dat de speler niet al is uitgedaagd.
        * @requires Dat de uitgedaagde(n) kunnen worden uitgedaagd.
        * @param other De ander
        */
        public void challenge(String[] others){
                ClientHandler[] os = new ClientHandler[others.length];
                String[] commandArgs = new String[others.length+1];
                commandArgs[0] = this.getNick();
                String log = ServerProtocol.CHALLENGE;
                
                for(int i = 0; i < others.length; i++){
                        os[i] = this.handler.server.getClient(others[i]);
                        commandArgs[i+1] = others[i];
                        log = log+" "+others[i];
                }
                
                Logger.log(Logger.COMMAND, log);
                int s = this.handler.server.serverInfo.supports;
                if(this.isHandshakeDone() && !this.hasGame() && this.isChallenger() && this.isChallenged() && this.canBeChallenged(os) && (s == ServerProtocol.SERVER_SUPPORTS_CHALLENGE || s == ServerProtocol.SERVER_SUPPORTS_CHATCHALLENGE)){
                        for(ClientHandler client : os){
                                client.sendCommand(ClientProtocol.CHALLENGE, commandArgs);
                        }
                }else if(!this.isHandshakeDone()){
                        this.sendError(ClientProtocol.ERROR_HANDSHAKE_MISSING);
                }else if(this.hasGame()){
                        this.sendError(ClientProtocol.ERROR_USER_ALREADY_HAS_GAME);
                }else if(this.isChallenger()){
                        this.sendGenericError();
                }else if(this.isChallenged()){
                        this.sendGenericError();
                }else if(!this.canBeChallenged(os)){
                        this.sendGenericError();
                }else if(s != ServerProtocol.SERVER_SUPPORTS_CHALLENGE && s != ServerProtocol.SERVER_SUPPORTS_CHATCHALLENGE){
                        // Server does not support challenge. Do nothing.
                }else{
                        this.sendGenericError();
                }
        }

        /**
        * Reageer op een uitdaging
        * @requires Dat de handshake is gedaan.
        * @requires Dat de speler is uitgedaagd.
        * @param accept Of de client accepteert
        */
        public void challengeResponse(boolean accept) {
                Logger.log(Logger.COMMAND, ServerProtocol.CHALLENGE_RESPONSE+" "+String.valueOf(accept));
                if(this.isHandshakeDone() && this.isChallenged()){
                        String challenger = this.getChallenger();
                        String[] args = new String[]{challenger, String.valueOf(accept)};
                        this.handler.server.getClient(challenger).sendCommand(ClientProtocol.CHALLENGE_RESPONSE, args);
                }else if(!this.isHandshakeDone()){
                        this.sendError(ClientProtocol.ERROR_HANDSHAKE_MISSING);
                }else if(!this.isChallenged()){
                        this.sendGenericError();
                }else{
                        this.sendGenericError();
                }
        }

        /**
        * Vraag highscores op bij de server
        * @requires Dat de handshake is gedaan
        * @requires Dat wanneer het type één van "date" of "player" is, arg respectievelijk van het volgende formaat is:
        * * yyyy-[m]m-[d]d
        * * SpelerNaamZonderSpaties
        * @param type Type highscore (bijv. date, player)
        * @param arg Een argument (bijv. 2014-01-01)
        */
        public void highscore(String type, String arg) {
                Logger.log(Logger.COMMAND, ServerProtocol.HIGHSCORE+" "+type+" "+arg);
                if(this.isHandshakeDone()){
                        // TODO: Implement leaderboard.
                        //this.handler.server.getLeaderboard().getScore(type, arg);        
                }else if(!this.isHandshakeDone()){
                        this.sendError(ClientProtocol.ERROR_HANDSHAKE_MISSING);
                }else{
                        this.sendGenericError();
                }
        }
        
        /**
        * Vraag lijst met spellen op bij de server
        * @requires Dat de handshake is gedaan
        */
        public void customListGames(){
                Logger.log(Logger.CUSTOMCMD, ServerProtocol.CUSTOM_LIST_GAMES);
                if(this.isHandshakeDone()){
                        ArrayList<ServerGame> games = this.handler.server.getGames();
                        
                        String[] cmdString = new String[games.size()];
                        
                        for(int i = 0; i < games.size(); i++){
                                ServerGame game = games.get(i);
                                String owner = game.ownerClient.getNick();
                                String started = String.valueOf(game.status);
                                String players = String.valueOf(game.participants.size());
                                cmdString[i] = owner+";"+started+";"+players;
                        }
                        
                        this.handler.sendCommand(ClientProtocol.CUSTOM_LIST, cmdString);
                        
                }else if(!this.isHandshakeDone()){
                        this.sendError(ClientProtocol.ERROR_HANDSHAKE_MISSING);
                }else{
                        this.sendGenericError();
                }
        }
        
        public HashMap<String,Integer[]> serverCommands = new HashMap<String,Integer[]>();


        public boolean isValidCommand(String msg) {
                
                Logger.log(Logger.DEBUG, "Checking command "+msg+" for validity");
                
                String[] splitCommand = msg.split(" ");
                String command = splitCommand[0].toLowerCase();
                int numArgs = splitCommand.length-1;
                
                if(serverCommands.containsKey(command)){
                        for(int possibleArgs : serverCommands.get(command)){
                                Logger.log(Logger.DEBUG, "Command has "+numArgs+" arguments and can have "+possibleArgs);
                                if(numArgs == possibleArgs){
                                        return true;
                                }
                                if(possibleArgs == -1 && numArgs > 0 && command.equals(ServerProtocol.MESSAGE)){
                                        return true;
                                }
                        }
                }
                Logger.log(Logger.DEBUG, "Invalid command.");
                return false;
        }
        
        public void executeCommand(String msg) throws IllegalArgumentException, IOException{
                String[] splitCommand = msg.split(" ");
                String command = splitCommand[0].toLowerCase();
                int numArgs = splitCommand.length-1;
                String [] args = new String[numArgs];
                for(int i=1; i<=numArgs;i++){
                        args[i-1] = splitCommand[i];
                }
                switch(command){
                        case HANDSHAKE:
                                int supports = Integer.parseInt(args[1]);
                                hello(args[0], supports, args[2]);
                                break;
                        case ERROR:
                                int code = Integer.parseInt(args[0]);
                                error(code);
                                break;
                        case AUTH:
                                auth(args[0]);
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
                                for(String arg : args){
                                        if(mesg.equals("")){
                                                mesg = arg;
                                        }else{
                                                mesg = mesg+" "+arg;
                                        }
                                }
                                message(mesg);
                                break;
                        case CHALLENGE:
                                challenge(args);
                                break;
                        case CHALLENGE_RESPONSE:
                                challengeResponse(Boolean.parseBoolean(args[0]));
                                break;
                        case HIGHSCORE:
                                highscore(args[0], args[1]);
                                break;
                        // Custom commands
                        case CUSTOM_LIST_GAMES:
                                customListGames();
                                break;
                        default:
                                // Unknown command or non-sensical message
                                break;
                }
        }
        
        // Get client info methods
        public boolean isHandshakeDone(){
                return this.handler.clientInfo.handshake;
        }
        public String getNick(){
                return this.handler.clientInfo.name;
        }
        public int getSupports(){
                return this.handler.clientInfo.supports;
        }
        public String getVersion(){
                return this.handler.clientInfo.version;
        }
        public boolean hasGame(){
                return this.handler.clientInfo.hasGame;
        }
        public boolean ownsGame(){
                return this.handler.clientInfo.ownsGame;
        }
        
        // Set ClientInformation
        public void setHandshake(boolean handshake){
                this.handler.clientInfo.handshake = handshake;
        }
        public void setName(String name){
                this.handler.clientInfo.name = name;
        }
        public void setSupports(int supports){
                this.handler.clientInfo.supports = supports;
        }
        public void setVersion(String version){
                this.handler.clientInfo.version = version;
        }
        	//
        	//Set game & remove game?
        	//
        public void setOwnsGame(boolean val){
                this.handler.clientInfo.ownsGame = val;
        }
        public void setIsChallenged(boolean val, String challenger){
                this.handler.clientInfo.isChallenged = val;
                this.handler.clientInfo.challenger = challenger;
        }
        public void setIsChallenger(boolean val, String[] challengees){
                this.handler.clientInfo.isChallenger = val;
                this.handler.clientInfo.challengees = challengees;
        }
        
        // Get server info methods
        public int getServerSupports(){
                return this.handler.server.serverInfo.supports;
        }
        public String getServerVersion(){
                return this.handler.server.serverInfo.version;
        }
        
        private String generateNonce() {
                return UUID.randomUUID().toString();
        }
        
        // Command helper functions
        public void sendGenericError(){
                Logger.log(Logger.ERROR, "Sending generic error to client.");
                this.handler.sendCommand(ClientProtocol.ERROR, new String[]{String.valueOf(ClientProtocol.ERROR_GENERIC)});
        }
        
        public void sendError(int errorcode){
                Logger.log(Logger.ERROR, "Sending error to client. ["+Errors.getError(1, errorcode)+"]");
                this.handler.sendCommand(ClientProtocol.ERROR, new String[]{String.valueOf(errorcode)});
        }
        
        public boolean checkAuth(String signature){
                String nonce = this.getNonce();
                PublicKey pKey = this.getPublicKey();
                byte[] sign = Security.base64Decode(signature);
                
                return Security.verify(sign, nonce.getBytes(), pKey);
        }
        
        public ServerGame getGameForClient(String name){
                ClientHandler client = this.handler.server.getClient(name);
                if(client != null){
                        return client.clientInfo.networkGame;
                }
                return null;
        }
        
        public void broadcastGame(ServerGame game){
                String creator = game.ownerClient.getNick();
                String status = String.valueOf(game.status);
                String numPlayers = String.valueOf(game.participants.size());
                this.handler.sendCommand(ClientProtocol.GAME, new String[]{creator, status, numPlayers});
        }
        
        
        public ServerProtocol(ClientHandler handler){
                // Add commands to serverCommands Map
                // to help with command detection and
                // to prevent the sending of random junk
                // from generating errors server-side.
                serverCommands.put(ServerProtocol.HANDSHAKE, new Integer[]{3});
                serverCommands.put(ServerProtocol.ERROR, new Integer[]{1});
                serverCommands.put(ServerProtocol.AUTH, new Integer[]{1});
                serverCommands.put(ServerProtocol.CREATE_GAME, new Integer[]{0});
                serverCommands.put(ServerProtocol.JOIN_GAME, new Integer[]{1});
                serverCommands.put(ServerProtocol.START_GAME, new Integer[]{0});
                serverCommands.put(ServerProtocol.MOVE, new Integer[]{2});
                serverCommands.put(ServerProtocol.MESSAGE, new Integer[]{-1});
                serverCommands.put(ServerProtocol.CHALLENGE, new Integer[]{1,2,3});
                serverCommands.put(ServerProtocol.CHALLENGE_RESPONSE, new Integer[]{1});
                serverCommands.put(ServerProtocol.HIGHSCORE, new Integer[]{2});
                // Custom commands
                serverCommands.put(ServerProtocol.CUSTOM_LIST_GAMES, new Integer[]{0});
                if(handler != null){
                        this.handler = handler;
                }
        }

		public void doCommand(String msg) {
			// TODO Auto-generated method stub
			
		}

}