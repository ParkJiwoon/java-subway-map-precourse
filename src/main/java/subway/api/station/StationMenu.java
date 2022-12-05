package subway.api.station;

import subway.api.common.Menu;

public enum StationMenu implements Menu {
    REGISTER("1", "역 등록", () -> StationView.getInstance().registerStation()),
    DELETE("2", "역 삭제", () -> StationView.getInstance().deleteStation()),
    SHOW("3", "역 조회", () -> StationView.getInstance().findAllStations()),
    BACK("B", "돌아가기", () -> StationView.getInstance().quit()),
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
    public void run() {
        runnable.run();
    }

    @Override
    public boolean equalIndex(String select) {
        return index.equals(select);
    }

    @Override
    public String getString() {
        return index + ". " + name;
    }
}
