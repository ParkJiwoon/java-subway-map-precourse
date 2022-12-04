package subway.api.section;

import subway.api.common.InputView;
import subway.api.common.Menu;
import subway.api.common.OutputView;

public class SectionView {
    private static final SectionController sectionController = new SectionController();
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
            Menu menu = Menu.of(SectionMenu.values(), select);
            menu.run();
            quit();
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());
        }
    }

    private static void showMenus() {
        System.out.println("## 구간 관리 화면");
        System.out.println(Menu.listUp(SectionMenu.values()));
        OutputView.emptyLine();
    }

    private static String inputSelect() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return InputView.input();
    }

    /**
     * 구간 등록 API
     */
    protected static void registerSection() {
        String lineName = inputLineNameForRegister();
        String stationName = inputStationNameForRegister();
        Integer order = inputOrder();
        sectionController.addSection(lineName, stationName, order);
        printSuccessRegisterSection();
    }

    private static String inputLineNameForRegister() {
        System.out.println("## 노선을 입력하세요.");
        return InputView.input();
    }

    private static String inputStationNameForRegister() {
        System.out.println("## 역이름을 입력하세요.");
        return InputView.input();
    }

    private static Integer inputOrder() {
        System.out.println("## 순서를 입력하세요.");
        String input = InputView.input();
        return Integer.parseInt(input);
    }

    private static void printSuccessRegisterSection() {
        OutputView.info("구간이 등록되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 구간 삭제 API
     */
    protected static void deleteSection() {
        String lineName = inputLineNameForDelete();
        String stationName = inputStationNameForDelete();
        sectionController.deleteSection(lineName, stationName);
        printSuccessDeleteSection();
    }

    private static String inputLineNameForDelete() {
        System.out.println("## 삭제할 구간의 노선을 입력하세요.");
        return InputView.input();
    }

    private static String inputStationNameForDelete() {
        System.out.println("## 삭제할 구간의 역을 입력하세요.");
        return InputView.input();
    }

    private static void printSuccessDeleteSection() {
        OutputView.info("구간이 삭제되었습니다.");
        OutputView.emptyLine();
    }
}