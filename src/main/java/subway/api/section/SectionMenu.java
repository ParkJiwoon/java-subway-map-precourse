package subway.api.section;

import subway.api.common.Menu;

public enum SectionMenu implements Menu {
    REGISTER("1", "구간 등록", () -> SectionView.getInstance().registerSection()),
    DELETE("2", "구간 삭제", () -> SectionView.getInstance().deleteSection()),
    BACK("B", "돌아가기", () -> SectionView.getInstance().quit()),
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
