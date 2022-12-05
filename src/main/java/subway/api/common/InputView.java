package subway.api.common;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String string() {
        String input = scanner.nextLine();
        OutputView.emptyLine();
        return input;
    }
}
