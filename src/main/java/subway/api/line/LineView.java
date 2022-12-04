package subway.api.line;

import subway.api.common.InputView;
import subway.api.common.Menu;
import subway.api.common.OutputView;

import java.util.List;

public class LineView {

    private static final LineController lineController = new LineController();
    private static boolean showing = true;

    private static void startShowing() {
        showing = true;
    }

    protected static void quit() {
        showing = false;
    }

    public static void run() {
        startShowing();

        while (showing) {
            show();
        }
    }

    private static void show() {
        try {
            showMenus();
            String select = inputSelect();
            Menu menu = Menu.of(LineMenu.values(), select);
            menu.run();
            quit();
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());
        }
    }

    private static void showMenus() {
        System.out.println("## 노선 관리 화면");
        System.out.println(Menu.listUp(LineMenu.values()));
        OutputView.emptyLine();
    }

    private static String inputSelect() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return InputView.input();
    }

    /**
     * 노선 등록 API
     */
    protected static void registerLine() {
        String lineName = inputLineNameForRegister();
        String firstStationName = inputFirstStationForLine();
        String lastStationName = inputLastStationForLine();
        lineController.addLine(lineName, firstStationName, lastStationName);
        printSuccessAddLine();
    }

    private static String inputLineNameForRegister() {
        System.out.println("## 등록할 노선 이름을 입력하세요.");
        return InputView.input();
    }

    private static String inputFirstStationForLine() {
        System.out.println("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
        return InputView.input();
    }

    private static String inputLastStationForLine() {
        System.out.println("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
        return InputView.input();
    }

    private static void printSuccessAddLine() {
        OutputView.info("지하철 노선이 등록되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 노선 삭제 API
     */
    protected static void deleteLine() {
        String lineName = inputLineNameForDelete();
        lineController.deleteLine(lineName);
        printSuccessDeleteLine();
    }

    private static String inputLineNameForDelete() {
        System.out.println("## 삭제할 노선 이름을 입력하세요.");
        return InputView.input();
    }

    private static void printSuccessDeleteLine() {
        OutputView.info("지하철 노선이 삭제되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 노선 조회 API
     */
    protected static void findAllLines() {
        List<String> lineNames = lineController.findAllLineNames();
        printAllLines(lineNames);
    }

    private static void printAllLines(List<String> linesNames) {
        System.out.println("## 노선 목록");
        linesNames.forEach(OutputView::info);
        OutputView.emptyLine();
    }
}
