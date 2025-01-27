package subway.api.line;

import subway.api.common.Menu;

public enum LineMenu implements Menu {
    REGISTER("1", "노선 등록", () -> LineView.getInstance().registerLine()),
    DELETE("2", "노선 삭제", () -> LineView.getInstance().deleteLine()),
    SHOW("3", "노선 조회", () -> LineView.getInstance().findAllLines()),
    BACK("B", "돌아가기", () -> LineView.getInstance().quit()),
    ;

    private final String index;
    private final String name;
    private final Runnable runnable;

    LineMenu(String index, String name, Runnable runnable) {
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
