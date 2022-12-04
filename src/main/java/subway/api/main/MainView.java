package subway.api.main;

import subway.api.common.InputView;
import subway.api.common.Menu;
import subway.api.common.OutputView;

import java.util.List;
import java.util.Map;

public class MainView {
    private static final MainController mainController = new MainController();
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
            Menu menu = Menu.of(MainMenu.values(), select);
            menu.run();
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());
        }
    }

    private static void showMenus() {
        System.out.println("## 메인 화면");
        System.out.println(Menu.listUp(MainMenu.values()));
        OutputView.emptyLine();
    }

    private static String inputSelect() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return InputView.input();
    }

    /**
     * 지하철 노선도 출력
     */
    protected static void showSubwayMap() {
        Map<String, List<String>> subwayMap = mainController.findSubwayMap();
        printSubwayMap(subwayMap);
    }

    private static void printSubwayMap(Map<String, List<String>> subwayMap) {
        OutputView.emptyLine();
        System.out.println("## 지하철 노선도");
        subwayMap.forEach((lineName, stationNames) -> {
            OutputView.info(lineName);
            OutputView.info("---");
            stationNames.forEach(OutputView::info);
            OutputView.emptyLine();
        });
    }
}
