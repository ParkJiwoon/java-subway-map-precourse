package subway.domain.line;

import java.util.*;

public class LineRepository {
    private static final Map<String, Line> lines = new LinkedHashMap<>();

    public static Collection<Line> lines() {
        return Collections.unmodifiableCollection(lines.values());
    }

    public static Line findByName(String name) {
        return lines.get(name);
    }

    public static void save(Line line) {
        lines.putIfAbsent(line.getName(), line);
    }

    public static void deleteByName(String name) {
        lines.remove(name);
    }
}
