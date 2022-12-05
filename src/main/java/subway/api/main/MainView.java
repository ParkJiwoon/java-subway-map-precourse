package subway.api.main;

import subway.api.common.InputView;
import subway.api.common.Menu;
import subway.api.common.OutputView;
import subway.api.common.View;

import java.util.List;
import java.util.Map;

public class MainView extends View {
    private static final MainView INSTANCE = new MainView();
    private final MainController mainController;

    private MainView() {
        this.mainController = new MainController();
    }

    public static MainView getInstance() {
        return INSTANCE;
    }

    protected void show() {
        try {
            showMenus();
            String select = inputSelect();
            Menu menu = Menu.of(MainMenu.values(), select);
            menu.run();
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());
        }
    }

    private void showMenus() {
        System.out.println("## 메인 화면");
        System.out.println(Menu.listUp(MainMenu.values()));
        OutputView.emptyLine();
    }

    private String inputSelect() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return InputView.string();
    }

    /**
     * 지하철 노선도 출력
     */
    public void showSubwayMap() {
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
