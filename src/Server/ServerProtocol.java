package rolit.model.networking.server;

import rolit.model.networking.common.CommonProtocol;

import java.io.IOException;

/**
 * @author Pieter Bos
 * @author Martijn de Bijl
 *
 * Abstract class met alle constanten en methodes die gebruikt kunnen worden
 */
public abstract class ServerProtocol extends CommonProtocol {
    /**
     * Constante voor het handshake-commando
     */
    public static final String HANDSHAKE = "hello";

    /**
     * Constante voor het authOk-commando
     */
    public static final String AUTH_OK = "authOk";

    /**
     * Constante voor het error-commando
     */
    public static final String ERROR = "error";

    /**
     * Constante voor het game-commando
     */
    public static final String GAME = "game";

    /**
     * Constante voor het start-commando
     */
    public static final String START = "start";

    /**
     * Constante voor het move-commando
     */
    public static final String MOVE = "move";

    /**
     * Constante voor het move-done-commando
     */
    public static final String MOVE_DONE = "moveDone";

    /**
     * Constante voor het game-over-command
     */
    public static final String GAME_OVER = "gameOver";

    /**
     * Constante voor het message-commando
     */
    public static final String MESSAGE = "message";

    /**
     * Constante voor het challenge-commando
     */
    public static final String CHALLENGE = "challenge";

    /**
     * Constante voor het challenge-response-commando
     */
    public static final String CHALLENGE_RESPONSE = "challengeResponse";

    /**
     * Constante voor het can-be-challenged-commando
     */
    public static final String CAN_BE_CHALLENGED = "canBeChallenged";

    /**
     *
     */
    public static final String ONLINE = "online";

    /**
     * Constante voor highscore
     */
    public static final String HIGHSCORE = "highscore";

    public static final int ERROR_GENERIC = -1;
    public static final int ERROR_INVALID_LOGIN = 1;
    public static final int ERROR_GAME_FULL = 2;
    public static final int ERROR_TOO_LITTLE_PLAYERS = 3;
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

    /**
     * Antwoord op de handshake van de client. Moet altijd het eerst verzonden commando zijn, met uitzondering van
     * errors.
     * @requires Dat de handshake van de client is verzonden.
     * @requires Dat de handshake van de client niet een al ingelodge naam kiest.
     * @requires Dat de clientName niet begint met "player_"
     * @param supports Wat de server ondersteunt.
     * @param version Een beschrijving van wat de server kan
     */
    public abstract void handshake(int supports, String version) throws IOException;

    /**
     * Antwoord op de handshake van de client. Moet altijd het eerst verzonden commando zijn, met uitzondering van
     * errors.
     * @requires Dat de requirements van de eerste overload zijn voldaan.
     * @requires Dat de clientName juist wel begint met "player_"
     * @param supports
     * @param version
     * @param nonce
     * @throws IOException
     */
    public abstract void handshake(int supports, String version, String nonce) throws IOException;

    /**
     * Antwoord op het auth-pakket van de client.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de client een juist gesignde nonce heeft gestuurd.
     * @requires Dat de nonce gesigned is met de juiste public-key volgens ss-security.student.utwente.nl
     * @throws IOException
     */
    public abstract void authOk() throws IOException;

    /**
     * Commando om de client te laten weten dat hij iets fout heeft gedaan, waardoor de verbinding moet worden
     * verbroken.
     * @requires Dat de client iets fout heeft gedaan...
     * @requires Dat dit het enige en eerste pakket na de fout is.
     * @param errorCode De error-code, op te zoeken in de errorCode-tabel.
     */
    public abstract void error(int errorCode) throws IOException;

    /**
     * Commando om de client te laten weten dat er óf een nieuw spel is, óf dat er een spel is veranderd in status.
     * Clients krijgen een serie van deze commando's na de handshake om zo een lijst van alle spellen op te bouwen. Als
     * er daarna iets verandert aan het aantal spelers of dat het spel is begonnen moet de server weer een update
     * sturen.
     * @requires Dat de handshake is gedaan.
     * @requires Dat óf het spel in hasStarted-status is veranderd, óf in aantal spelers is veranderd, óf dat de client
     * nog niet de volledige lijst met spellen heeft ontvangen direct na de handshake.
     * @param creator De maker van het spel.
     * @param hasStarted De status van het spel.
     * @param noPlayers Het aantal spelers in het spel.
     */
    public abstract void game(String creator, int status, int noPlayers) throws IOException;

    /**
     * Commando om een spel te starten met twee spelers, die in die volgorde een zet moeten doen.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler in een spel zit.
     * @requires Dat de creator van het spel het spel heeft gestart.
     * @requires Dat dit bericht nog niet is gestuurd voor dit spel.
     * @param playerOne De eerste speler
     * @param playerTwo De tweede speler
     */
    public abstract void start(String playerOne, String playerTwo) throws IOException;

    /**
     * Commando om een spel te starten met drie spelers, die in die volgorde een zet moeten doen.
     * @requires Dat de requirements bij de eerste overload zijn voldaan.
     * @param playerOne De eerste speler
     * @param playerTwo De tweede speler
     * @param playerThree De derde speler
     */
    public abstract void start(String playerOne, String playerTwo, String playerThree) throws IOException;

    /**
     * Commando om een spel te starten met vier spelers, die in die volgorde een zet moeten doen.
     * @requires Dat de requirements bij de eerste overload zijn voldaan.
     * @param playerOne De eerste speler
     * @param playerTwo De tweede speler
     * @param playerThree De derde speler
     * @param playerFour De vierde speler
     */
    public abstract void start(String playerOne, String playerTwo, String playerThree, String playerFour) throws IOException;

    /**
     * Commando om de client te vertellen dat hij een zet moet gaan doen.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler in een spel zit.
     * @requires Dat het spel is gestart.
     * @requires Dat de speler ook echt aan de beurt is.
     */
    public abstract void move() throws IOException;

    /**
     * Commando om de client te laten weten dat iemand een zet heeft gedaan in het huidige spel.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler in een spel zit.
     * @requires Dat het spel is gestart.
     * @requires Dat de speler die zet heeft gedaan
     * @param name Naam van de speler die de zet heeft gedan.
     * @param x X-coördinaat, waarbij de linkerkant 0 is en de rechterkant 7.
     * @param y Y-coördinaat, waarbij de bovenkant 0 is en de onderkant 7.
     */
    public abstract void moveDone(String name, int x, int y) throws IOException;

    /**
     * Commando om de client te laten weten dat het spel is afgelopen, om welke reden dan ook. Eventueel zijn er
     * winnaars als het spel helemaal is voltooid. De server mag bepalen wat er gebeurt als er meerdere mensen dezelfde
     * score hebben.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler in een spel zit.
     * @requires Dat het spel is gestart.
     * @requires Dat:
     *           * Als het spel is gestart: ofwel het spel is afgelopen volgens de regels van de server ofwel één van de
     *                 mensen is weggegaan
     * @param score De hoogste score
     * @param winners De mensen met die score
     */
    public abstract void gameOver(int score, String[] winners) throws IOException;

    /**
     * Commando om de client op te hoogte te stellen van een chatbericht
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler dit bericht heeft verzonden.
     * @param name Afzender van het chatbericht
     * @param body Tekst van het chatbericht
     */
    public abstract void message(String name, String body) throws IOException;

    /**
     * Commando om de client op te hoogte te stellen van een uitdaging met twee mensen.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler niet is uitgedaagd.
     * @requires Dat de speler niet in een spel zit.
     * @requires Dat de speler uitdaagbaar is.
     * @param challenger De uitdager
     */
    public abstract void challenge(String challenger, String other1) throws IOException;

    /**
     * Commando om de client op de hoogte te stellen van een uitdaging met drie mensen
     * @requires Dat de requirements bij de eerste overload zijn voldaan.
     * @param challenger De uitdager
     * @param other1 Andere gebruiker
     */
    public abstract void challenge(String challenger, String other1, String other2) throws IOException;

    /**
     * Commando om de client op de hoogte te stellen van een uitdaging met vier mensen
     * @requires Dat de requirements bij de eerste overload zijn voldaan.
     * @param challenger De uitdager
     * @param other1 Andere gebruiker 1
     * @param other2 Andere gebruiker 2
     */
    public abstract void challenge(String challenger, String other1, String other2, String other3) throws IOException;

    /**
     * Commando om mensen die in een uitdaging zitten op de hoogte te stellen van de status van de uitgedaagden.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de client is uitgedaagd.
     * @param name Naam van de uitgedaagde.
     * @param accept Of deze persoon accepteert.
     */
    public abstract void challengeResponse(String name, boolean accept) throws IOException;

    /**
     * Commando om de client op de hoogte te stellen van het veranderen van de status van iemand.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de flag true is als de speler is uitgedaagd of een uitdager is.
     * @param name Naam van de uitgedaagde
     * @param flag Of hij kan worden uitgedaagd.
     */
    public abstract void canBeChallenged(String name, boolean flag) throws IOException;

    /**
     * Commando om de client op de hoogte te stellen van de gevraagde highscores.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de client heeft gevraagd om highscores.
     * @param args Argumenten
     */
    public abstract void highscore(String[] args) throws IOException;

    /**
     * Commando om de client op de hoogte te stellen van een gebruiker die inlogt of weggaat
     * @requires Dat de handshake is gedaan
     * @requires Dat ófwel er een client bijkomt, ófwel er een client weggaat, ófwel dat de client de lijst met mensen
     * nog niet in zijn geheel heeft ontvangen na de lijst van spellen. Dit laatste is geen requirement voor servers
     * zonder chat.
     */
    public abstract void online(String name, boolean isOnline) throws IOException;
}