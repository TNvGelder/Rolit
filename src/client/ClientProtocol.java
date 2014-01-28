package rolit.model.networking.client;

import rolit.model.networking.common.CommonProtocol;

/**
 * @author Pieter Bos
 * @author Martijn de Bijl
 *
 * Abstract class met alle constanten en methodes die gebruikt kunnen worden
 */
public abstract class ClientProtocol extends CommonProtocol {
    /**
     * Constante voor het handshake-commando
     */
    public static final String HANDSHAKE = "hello";

    /**
     * Constante voor het auth-commando
     */
    public static final String AUTH = "auth";

    /**
     * Constante voor het create-gamecommando
     */
    public static final String CREATE_GAME = "createGame";

    /**
     * Constante voor het join-gamecommando
     */
    public static final String JOIN_GAME = "joinGame";

    /**
     * Constante voor het start-gamecommando
     */
    public static final String START_GAME = "startGame";

    /**
     * Constante voor het movecommando
     */
    public static final String MOVE = "move";

    /**
     * Constante voor het messagecommando
     */
    public static final String MESSAGE = "message";

    /**
     * Constante voor het challengecommando
     */
    public static final String CHALLENGE = "challenge";

    /**
     * Constante voor het challenge-responsecommando
     */
    public static final String CHALLENGE_RESPONSE = "challengeResponse";

    /**
     * Constante voor het highscorecommando
     */
    public static final String HIGHSCORE = "highscore";

    /**
     * Handshake voor de server. Moet altijd het eerste verzonden pakket zijn, met uitzondering van de errors.
     * @requires Dat de handshake nog niet is ontvangen
     * @requires Dat dit het eerste pakket op de communicatelijn is.
     * @param clientName Naam van de client.
     * @param supports Wat de client ondersteunt.
     * @param version Een beschrijving van wat de client kan.
     */
    public abstract void hello(String clientName, int supports, String version);

    /**
     * Authenticatie van de client.
     * @requires Dat de handshake is gedaan.
     * @requires Dat de client de private key heeft opgehaald bij ss-security.student.utwente.nl met de verstuurde
     * private key en bijbehorend wachtwoord.
     * @requires Dat de nonce die door de server is gestuurd is gesigned met de private key.
     * @param signature De gesignde nonce
     */
    public abstract void auth(String signature);

    /**
     * Maak een spel
     * @requires Dat de handshake is gedaan.
     * @requires Dat de client nog geen spel is begonnen
     */
    public abstract void createGame();

    /**
     * Join een spel
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler nog niet in een spel zit.
     * @requires Dat het spel nog niet vol zit.
     * @param creator De maker van het spel
     */
    public abstract void joinGame(String creator);

    /**
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler in een spel zit.
     * @requires Dat de speler de creator van het spel is.
     * @requires Dat het spel nog niet is gestart.
     * Start het spel waarvan de gebruiker de creator is.
     */
    public abstract void startGame();

    /**
     * Doe een zet
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler in een spel zit
     * @requires Dat de speler een move-commando heeft ontvangen.
     * @param x X-coördinaat
     * @param y Y-coördinaat
     */
    public abstract void move(int x, int y);

    /**
     * @requires Dat de handshake is gedaan.
     * @requires Dat de server chat-berichten ondersteunt.
     * Stuur een bericht naar iedereen in de lobby of iedereen in het spel
     * @param body Het bericht
     */
    public abstract void message(String body);

    /**
     * Daag één ander uit voor een spel
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler niet in een spel zit.
     * @requires Dat de speler niet een uitdager is.
     * @requires Dat de speler niet al is uitgedaagd.
     * @requires Dat de uitgedaagde(n) kunnen worden uitgedaagd.
     * @param other De ander
     */
    public abstract void challenge(String other);

    /**
     * Daag twee anderen uit voor een spel
     * @requires Dat de requirements bij de andere overload worden voldaan.
     * @param other1 De een
     * @param other2 De ander
     */
    public abstract void challenge(String other1, String other2);

    /**
     * Daag drie anderen uit voor een spel
     * @requires Dat de requirements bij de andere overload worden voldaan.
     * @param other1 De eerste andere
     * @param other2 De tweede andere
     * @param other3 De derder andere
     */
    public abstract void challenge(String other1, String other2, String other3);

    /**
     * Reageer op een uitdaging
     * @requires Dat de handshake is gedaan.
     * @requires Dat de speler is uitgedaagd.
     * @param accept Of de client accepteert
     */
    public abstract void challengeResponse(boolean accept);

    /**
     * Vraag highscores op bij de server
     * @requires Dat de handshake is gedaan
     * @requires Dat wanneer het type één van "date" of "player" is, arg respectievelijk van het volgende formaat is:
     *           * yyyy-[m]m-[d]d
     *           * SpelerNaamZonderSpaties
     * @param type Type highscore (bijv. date, player)
     * @param arg Een argument (bijv. 2014-01-01)
     */
    public abstract void highscore(String type, String arg);
}