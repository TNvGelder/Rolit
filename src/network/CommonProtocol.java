package network;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;

/**
 * @author Pieter Bos
 * @author Martijn de Bijl
 *
 *
 */
public class CommonProtocol {
    /**
     * Constante om aan te geven dat de client of server alleen de basisdingen kan doen.
     */
    public static final int SUPPORTS_BAREBONE = 0;

    /**
     * Constante om aan te geven dat de client of server ook kan chatten.
     */
    public static final int SUPPORTS_CHAT = 1;

    /**
     * Constante om aan te geven dat de client of server ook kan uitdagen / uitgedaagd worden.
     */
    public static final int SUPPORTS_CHALLENGE = 2;

    /**
     * SUPPORTS_CHAT | SUPPORTS_CHALLENGE
     */
    public static final int SUPPORTS_CHAT_CHALLENGE = 3;

    /**
     * String-waarde voor true in het protocol
     */
    public static final String T_BOOLEAN_TRUE = "true";

    /**
     * String-waarde voor false in het protocol
     */
    public static final String T_BOOLEAN_FALSE = "false";

    /**
     * Versie in de handshake voor een standaardimplementatie
     */
    public static final String VERSION_NONE = "Standaard";

    /**
     * Einde van regels
     */
    public static final String LINE_ENDING = "\r\n";

    /**
     * Delimiter van commando's
     */
    public static final String COMMAND_DELIMITER = " ";
}