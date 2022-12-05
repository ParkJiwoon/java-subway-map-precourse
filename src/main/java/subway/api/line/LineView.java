package subway.api.line;

import subway.api.common.InputView;
import subway.api.common.Menu;
import subway.api.common.OutputView;
import subway.api.common.View;

import java.util.List;

public class LineView extends View {
    private static final LineView INSTANCE = new LineView();
    private final LineController lineController;

    private LineView() {
        this.lineController = new LineController();
    }

    public static LineView getInstance() {
        return INSTANCE;
    }

    protected void show() {
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

    private void showMenus() {
        System.out.println("## 노선 관리 화면");
        System.out.println(Menu.listUp(LineMenu.values()));
        OutputView.emptyLine();
    }

    private String inputSelect() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return InputView.string();
    }

    /**
     * 노선 등록 API
     */
    protected void registerLine() {
        String lineName = inputLineNameForRegister();
        String firstStationName = inputFirstStationForLine();
        String lastStationName = inputLastStationForLine();
        lineController.addLine(lineName, firstStationName, lastStationName);
        printSuccessAddLine();
    }

    private String inputLineNameForRegister() {
        System.out.println("## 등록할 노선 이름을 입력하세요.");
        return InputView.string();
    }

    private String inputFirstStationForLine() {
        System.out.println("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
        return InputView.string();
    }

    private String inputLastStationForLine() {
        System.out.println("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
        return InputView.string();
    }

    private void printSuccessAddLine() {
        OutputView.info("지하철 노선이 등록되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 노선 삭제 API
     */
    protected void deleteLine() {
        String lineName = inputLineNameForDelete();
        lineController.deleteLine(lineName);
        printSuccessDeleteLine();
    }

    private String inputLineNameForDelete() {
        System.out.println("## 삭제할 노선 이름을 입력하세요.");
        return InputView.string();
    }

    private void printSuccessDeleteLine() {
        OutputView.info("지하철 노선이 삭제되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 노선 조회 API
     */
    protected void findAllLines() {
        List<String> lineNames = lineController.findAllLineNames();
        printAllLines(lineNames);
    }

    private void printAllLines(List<String> linesNames) {
        System.out.println("## 노선 목록");
        linesNames.forEach(OutputView::info);
        OutputView.emptyLine();
    }
}
