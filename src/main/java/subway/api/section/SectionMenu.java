package subway.api.section;

import subway.api.common.Menu;

public enum SectionMenu implements Menu {
    REGISTER("1", "구간 등록", SectionView::registerSection),
    DELETE("2", "구간 삭제", SectionView::deleteSection),
    BACK("B", "돌아가기", SectionView::quit),
    ;

    private final String index;
    private final String name;
    private final Runnable runnable;

    SectionMenu(String index, String name, Runnable runnable) {
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
