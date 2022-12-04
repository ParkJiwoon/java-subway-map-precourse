package subway.api.station;

import subway.api.common.InputView;
import subway.api.common.Menu;
import subway.api.common.OutputView;
import subway.api.common.View;

import java.util.List;

public class StationView extends View {
    private static final StationView INSTANCE = new StationView();
    private final StationController stationController;

    private StationView() {
        this.stationController = new StationController();
    }

    public static StationView getInstance() {
        return INSTANCE;
    }

    protected void show() {
        try {
            showMenus();
            String select = inputSelect();
            Menu menu = Menu.of(StationMenu.values(), select);
            menu.run();
            quit();
        } catch (IllegalArgumentException e) {
            OutputView.error(e.getMessage());
        }
    }

    private void showMenus() {
        System.out.println("## 역 관리 화면");
        System.out.println(Menu.listUp(StationMenu.values()));
        OutputView.emptyLine();
    }

    private String inputSelect() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return InputView.input();
    }

    /**
     * 역 등록 API
     */
    protected void registerStation() {
        String stationName = inputStationNameForRegister();
        stationController.addStation(stationName);
        printSuccessAddStation();
    }

    private String inputStationNameForRegister() {
        System.out.println("## 등록할 역 이름을 입력하세요.");
        return InputView.input();
    }

    private void printSuccessAddStation() {
        OutputView.info("지하철 역이 등록되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 역 삭제 API
     */
    protected void deleteStation() {
        String stationName = inputStationNameForDelete();
        stationController.deleteStation(stationName);
        printSuccessDeleteStation();
    }

    private String inputStationNameForDelete() {
        System.out.println("## 삭제할 역 이름을 입력하세요.");
        return InputView.input();
    }

    private void printSuccessDeleteStation() {
        OutputView.info("지하철 역이 삭제되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 모든 역 찾기 API
     */
    protected void findAllStations() {
        List<String> stationNames = stationController.findAllStationNames();
        printAllStations(stationNames);
    }

    private void printAllStations(List<String> stationNames) {
        System.out.println("## 역 목록");
        stationNames.forEach(OutputView::info);
        OutputView.emptyLine();
    }
}
