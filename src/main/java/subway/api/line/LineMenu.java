package subway.api.line;

import subway.api.common.Menu;

public enum LineMenu implements Menu {
    REGISTER("1", "노선 등록", LineView::registerLine),
    DELETE("2", "노선 삭제", LineView::deleteLine),
    SHOW("3", "노선 조회", LineView::findAllLines),
    BACK("B", "돌아가기", LineView::quit),
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
