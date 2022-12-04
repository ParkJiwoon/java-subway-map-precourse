package subway.api.station;

import subway.api.common.Menu;

public enum StationMenu implements Menu {
    REGISTER("1", "역 등록", StationView::registerStation),
    DELETE("2", "역 삭제", StationView::deleteStation),
    SHOW("3", "역 조회", StationView::findAllStations),
    BACK("B", "돌아가기", StationView::quit),
    ;

    private final String index;
    private final String name;
    private final Runnable runnable;

    StationMenu(String index, String name, Runnable runnable) {
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
