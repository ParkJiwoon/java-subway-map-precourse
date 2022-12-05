package subway.api.common;

import java.util.Arrays;
import java.util.stream.Collectors;

public interface Menu {
    void run();
    boolean equalIndex(String select);
    String getString();

    static Menu of(Menu[] menus, String select) {
        return Arrays.stream(menus)
                .filter(menu -> menu.equalIndex(select))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("선택할 수 없는 기능입니다."));
    }

    static String listUp(Menu[] menus) {
        return Arrays.stream(menus)
                .map(Menu::getString)
                .collect(Collectors.joining("\n"));
    }
}
