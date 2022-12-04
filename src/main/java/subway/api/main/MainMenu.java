package subway.api.main;

import subway.api.common.Menu;
import subway.api.line.LineView;
import subway.api.section.SectionView;
import subway.api.station.StationView;

public enum MainMenu implements Menu {
    MANAGE_STATION("1", "역 관리", () -> StationView.getInstance().run()),
    MANAGE_LINE("2", "노선 관리", () -> LineView.getInstance().run()),
    MANAGE_SECTION("3", "구간 관리", () -> SectionView.getInstance().run()),
    SHOW_SUBWAY_MAP("4", "지하철 노선도 출력", () -> MainView.getInstance().showSubwayMap()),
    QUIT("Q", "종료", () -> MainView.getInstance().quit())
    ;

    private final String index;
    private final String name;
    private final Runnable runnable;

    MainMenu(String index, String name, Runnable runnable) {
        this.index = index;
        this.name = name;
        this.runnable = runnable;
    }

    @Override
    public String getIndex() {
        return index;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        runnable.run();
    }

    @Override
    public String getString() {
        return index + ". " + name;
    }
}
