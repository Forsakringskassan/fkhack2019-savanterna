package se.fk.behorighetsportalen.server;

import se.fk.behorighetsportalen.server.ansokan.rest.Ansokan;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Locale;
import java.util.Random;

public class CypherUtil {

    private static final int RANDOM_STRING_LENGTH = 21;
    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase(Locale.ROOT);
    private static final String digits = "0123456789";
    private static final String alphanum = upper + lower + digits;

    public static String generateId() {
        return Base64.getEncoder().encodeToString(generateRandomString(RANDOM_STRING_LENGTH, new SecureRandom(), alphanum).getBytes());
    }

    private static String generateRandomString(int length, Random random, String symbols) {
        char[] buf = new char[length];
        char[] symbolChar = symbols.toCharArray();
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbolChar[random.nextInt(symbolChar.length)];
        }
        return new String(buf);
    }

    public static Ansokan.Status convertToStatus(int status) {
        switch (status) {
            case 0:
                return Ansokan.Status.PÅBÖRJAD;
            case 1:
                return Ansokan.Status.BEVILJAD;
            case 2:
                return Ansokan.Status.NEKAD;
            default:
                return Ansokan.Status.PÅBÖRJAD;
        }
    }
}

