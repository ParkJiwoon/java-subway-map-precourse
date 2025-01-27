package subway.api.section;

import subway.api.common.InputView;
import subway.api.common.Menu;
import subway.api.common.OutputView;
import subway.api.common.View;

public class SectionView extends View {
    private static final SectionView INSTANCE = new SectionView();
    private final SectionController sectionController;

    private SectionView() {
        this.sectionController = new SectionController();
    }

    public static SectionView getInstance() {
        return INSTANCE;
    }

    @Override
    protected void showMenus() {
        System.out.println("## 구간 관리 화면");
        System.out.println(Menu.listUp(SectionMenu.values()));
        OutputView.emptyLine();
    }

    @Override
    protected void runSelectMenu(String select) {
        Menu menu = Menu.of(SectionMenu.values(), select);
        menu.run();
        quit();
    }

    /**
     * 구간 등록 API
     */
    protected void registerSection() {
        String lineName = inputLineNameForRegister();
        String stationName = inputStationNameForRegister();
        Integer order = inputOrder();
        sectionController.addSection(lineName, stationName, order);
        printSuccessRegisterSection();
    }

    private String inputLineNameForRegister() {
        System.out.println("## 노선을 입력하세요.");
        return InputView.string();
    }

    private String inputStationNameForRegister() {
        System.out.println("## 역이름을 입력하세요.");
        return InputView.string();
    }

    private Integer inputOrder() {
        System.out.println("## 순서를 입력하세요.");
        return InputView.integer();
    }

    private void printSuccessRegisterSection() {
        OutputView.info("구간이 등록되었습니다.");
        OutputView.emptyLine();
    }

    /**
     * 구간 삭제 API
     */
    protected void deleteSection() {
        String lineName = inputLineNameForDelete();
        String stationName = inputStationNameForDelete();
        sectionController.deleteSection(lineName, stationName);
        printSuccessDeleteSection();
    }

    private String inputLineNameForDelete() {
        System.out.println("## 삭제할 구간의 노선을 입력하세요.");
        return InputView.string();
    }

    private String inputStationNameForDelete() {
        System.out.println("## 삭제할 구간의 역을 입력하세요.");
        return InputView.string();
    }

    private void printSuccessDeleteSection() {
        OutputView.info("구간이 삭제되었습니다.");
        OutputView.emptyLine();
    }
}
