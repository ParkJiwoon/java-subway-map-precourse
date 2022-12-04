package subway.api.common;

public class OutputView {
    private static final String INFO_PREFIX = "[INFO] ";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void emptyLine() {
        System.out.println();
    }

    public static void error(String message) {
        System.out.println(ERROR_PREFIX + message + "\n");
    }

    public static void info(String message) {
        System.out.println(INFO_PREFIX + message);
    }
}
