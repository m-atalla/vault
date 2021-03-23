package vault;

import java.util.Scanner;

public class Util {
    /**
     * Shell colors.
     */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void success(String message) {
        System.out.println(Util.ANSI_GREEN + ">> " + message + Util.ANSI_RESET);
    }

    public static void info(String message) {
        System.out.println(Util.ANSI_BLUE + ">> " + message + Util.ANSI_RESET);
    }

    public static void error(String message) {
        System.out.println(Util.ANSI_RED + ">> " + message + Util.ANSI_RESET);
    }

    public static String getPath(String root, String domain) {
        return root + "/" + domain;
    }

    /**
     * Standard app input buffer
     * @return String
     */
    public static String getInput() {
        Scanner scan = new Scanner(System.in);
        String inputFormat = "\uD83D\uDD12> ";
        System.out.print(inputFormat);
        return scan.next().trim();
    }

    public static void helpPrompt() {
        Util.info("Type 'help' to see available commands.");
    }
}
