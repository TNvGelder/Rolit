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

public class ServerProtocol implements Runnable {

	// Dit zijn alle commando's die de server kan ONTVANGEN van de client,
	// dus, waarmee de server dingen moet kunnen doen.

	/**
	 * Constante voor het handshake-commando hello [player_name] [supports]
	 * [version]
	 */
	public static final String HANDSHAKE = "hello";

	/**
	 * Constante voor het auth-commando auth [signed_nonce]
	 */
	public static final String AUTH = "auth";

	/**
	 * Constante voor het create-gamecommando creategame
	 */
	public static final String CREATE_GAME = "creategame";

	/**
	 * Constante voor het join-gamecommando joingame [creator]
	 */
	public static final String JOIN_GAME = "joingame";

	/**
	 * Constante voor het start-gamecommando startgame
	 */
	public static final String START_GAME = "startgame";

	/**
	 * Constante voor het movecommando move [x] [y]
	 */
	public static final String MOVE = "move";

	/**
	 * Constante voor het messagecommando message [body]
	 */
	public static final String MESSAGE = "message";

	/**
	 * Constante voor het challengecommando challenge [other1] (other2 (other3))
	 */
	public static final String CHALLENGE = "challenge";

	/**
	 * Constante voor het challenge-responsecommando challengeresponse [accept]
	 */
	public static final String CHALLENGE_RESPONSE = "challengeresponse";

	/**
	 * Constante voor het highscorecommando highscore [arg]
	 */
	public static final String HIGHSCORE = "highscore";

	/**
	 * Constante voor het errorcommando error [errorcode]
	 */
	public static final String ERROR = "error";

	// Custom commands constants
	/**
	 * Constant for the list-games command games
	 */
	public static final String CUSTOM_LIST_GAMES = "games";

	public static final int SERVER_SUPPORTS_BAREBONE = 0;
	public static final int SERVER_SUPPORTS_CHAT = 1;
	public static final int SERVER_SUPPORTS_CHALLENGE = 2;
	public static final int SERVER_SUPPORTS_CHATCHALLENGE = 3;

	private ClientHandler handler = null;

	/**
	 * Handshake voor de server. Moet altijd het eerste verzonden pakket zijn,
	 * met uitzondering van de errors.
	 * 
	 * @requires Dat de handshake nog niet is ontvangen
	 * @requires Dat dit het eerste pakket op de communicatelijn is.
	 * @param clientName
	 *            Naam van de client.
	 * @param supports
	 *            Wat de client ondersteunt.
	 * @param version
	 *            Een beschrijving van wat de client kan.
	 */
	public void hello(String clientName, int supports, String version) {
		handler.server.serverUpdate(clientName
				+ " wants to 'hello'. Supports: " + supports + " with version "
				+ version);
		if (!!this.handler.clientInfo.handshake
				&& !this.handler.server.hasClient(clientName)) {
			this.setName(clientName);
			this.setSupports(supports);
			this.setVersion(version);
			this.handler.sendCommand(ClientProtocol.HANDSHAKE,
					new String[] { String.valueOf(this.getServerSupports()),
							this.getServerVersion() });
			if (this.getSupports() == ClientProtocol.CLIENT_SUPPORTS_CHAT
					|| this.getSupports() == ClientProtocol.CLIENT_SUPPORTS_CHATCHALLENGE) {
				this.handler.sendCommand(ClientProtocol.ONLINE, new String[] {
						this.getNick(), String.valueOf(true) });
			}
			this.setHandshake(true);
		} else if (this.isHandshakeDone()) {
			this.sendGenericError();
		} else if (this.handler.server.hasClient(clientName)) {
			this.sendGenericError();
		}
	}

	/**
	 * Error voor de client.
	 * 
	 * @param errorcode
	 *            Errorcode (-1 voor generic error)
	 */
	public void error(int errorCode) {
		handler.server.serverUpdate("The following errorcode has occured: "
				+ errorCode);
	}

	/**
	 * Maak een spel
	 * 
	 * @requires Dat de handshake is gedaan.
	 * @requires Dat de client nog geen spel is begonnen
	 */
	public void createGame() {
		// TODO: create game
	}

	/**
	 * Join een spel
	 * 
	 * @requires Dat de handshake is gedaan.
	 * @requires Dat de speler nog niet in een spel zit.
	 * @requires Dat het spel nog niet vol zit.
	 * @param creator
	 *            De maker van het spel
	 */
	public void joinGame(String creator) {
		// TODO: join game
	}

	/**
	 * @requires Dat de handshake is gedaan.
	 * @requires Dat de speler in een spel zit.
	 * @requires Dat de speler de creator van het spel is.
	 * @requires Dat het spel nog niet is gestart. Start het spel waarvan de
	 *           gebruiker de creator is.
	 */
	public void startGame() {
		// TODO: start game
	}

	/**
	 * Doe een zet
	 * 
	 * @requires Dat de handshake is gedaan.
	 * @requires Dat de speler in een spel zit
	 * @requires Dat de speler een move-commando heeft ontvangen.
	 * @param x
	 *            X-coördinaat
	 * @param y
	 *            Y-coördinaat
	 */
	public void move(int x, int y) {
		handler.move(Board.toIndex(x, y));
		//handler.server.games[handler.getGameNumber()].getBoard().toIndex(x, y);
	}

	/**
	 * @requires Dat de handshake is gedaan.
	 * @requires Dat de server chat-berichten ondersteunt. Stuur een bericht
	 *           naar iedereen in de lobby of iedereen in het spel
	 * @param body
	 *            Het bericht
	 */
	public void message(String body) {
		// TODO: message
	}

	public HashMap<String, Integer[]> serverCommands = new HashMap<String, Integer[]>();

	private Thread thread;

	public boolean isValidCommand(String msg) {

		String[] splitCommand = msg.split(" ");
		String command = splitCommand[0].toLowerCase();
		int numArgs = splitCommand.length - 1;

		if (serverCommands.containsKey(command)) {
			for (int possibleArgs : serverCommands.get(command)) {
				if (numArgs == possibleArgs) {
					return true;
				}
				if (possibleArgs == -1 && numArgs > 0
						&& command.equals(ServerProtocol.MESSAGE)) {
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

	// Get client info methods
	public boolean isHandshakeDone() {
		return this.handler.clientInfo.handshake;
	}

	public String getNick() {
		return this.handler.clientInfo.name;
	}

	public int getSupports() {
		return this.handler.clientInfo.supports;
	}

	public String getVersion() {
		return this.handler.clientInfo.version;
	}

	public boolean hasGame() {
		return this.handler.clientInfo.hasGame;
	}

	public boolean ownsGame() {
		return this.handler.clientInfo.ownsGame;
	}

	// Set ClientInformation
	public void setHandshake(boolean handshake) {
		this.handler.clientInfo.handshake = handshake;
	}

	public void setName(String name) {
		this.handler.clientInfo.name = name;
	}

	public void setSupports(int supports) {
		this.handler.clientInfo.supports = supports;
	}

	public void setVersion(String version) {
		this.handler.clientInfo.version = version;
	}

	//
	// Set game & remove game?
	//
	public void setOwnsGame(boolean val) {
		this.handler.clientInfo.ownsGame = val;
	}

	// Get server info methods
	public int getServerSupports() {
		return this.handler.server.serverInfo.supports;
	}

	public String getServerVersion() {
		return this.handler.server.serverInfo.version;
	}

	// Command helper functions
	public void sendGenericError() {
		handler.server.serverUpdate("Sending generic error to client.");
		this.handler.sendCommand(ClientProtocol.ERROR,
				new String[] { String.valueOf(ClientProtocol.ERROR_GENERIC) });
	}

	public void sendError(int errorcode) {
		handler.server.serverUpdate("Sending error to client. ["
				+ Errors.getError(1, errorcode) + "]");
		this.handler.sendCommand(ClientProtocol.ERROR,
				new String[] { String.valueOf(errorcode) });
	}

	public Game getGameForClient(String name) {
		ClientHandler client = this.handler.server.getClient(name);
		if (client != null) {
			return client.clientInfo.Game;
		}
		return null;
	}

	public void broadcastGame(Game game) {
		String creator = game.ownerClient.getName();
		String status = String.valueOf(game.status);
		String numPlayers = String.valueOf(game.getPlayers());
		this.handler.sendCommand(ClientProtocol.GAME, new String[] { creator,
				status, numPlayers });
	}

	public void run() {
		serverCommands.put(ServerProtocol.HANDSHAKE.toLowerCase(),
				new Integer[] { 3 });
		serverCommands.put(ServerProtocol.ERROR.toLowerCase(),
				new Integer[] { 1 });
		serverCommands.put(ServerProtocol.AUTH.toLowerCase(),
				new Integer[] { 1 });
		serverCommands.put(ServerProtocol.CREATE_GAME.toLowerCase(),
				new Integer[] { 0 });
		serverCommands.put(ServerProtocol.JOIN_GAME.toLowerCase(),
				new Integer[] { 1 });
		serverCommands.put(ServerProtocol.START_GAME.toLowerCase(),
				new Integer[] { 0 });
		serverCommands.put(ServerProtocol.MOVE.toLowerCase(),
				new Integer[] { 2 });
		serverCommands.put(ServerProtocol.MESSAGE.toLowerCase(),
				new Integer[] { -1 });
		serverCommands.put(ServerProtocol.CHALLENGE.toLowerCase(),
				new Integer[] { 1, 2, 3 });
		serverCommands.put(ServerProtocol.CHALLENGE_RESPONSE.toLowerCase(),
				new Integer[] { 1 });
		serverCommands.put(ServerProtocol.HIGHSCORE.toLowerCase(),
				new Integer[] { 2 });
	}

	public ServerProtocol(ClientHandler handler) {
		if (handler != null) {
			this.handler = handler;
		}
		this.thread = new Thread(this);
		this.thread.start();
	}

}