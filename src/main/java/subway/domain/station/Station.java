package subway.domain.station;

import java.util.Objects;

public class Station {
    private static final int NAME_MIN_SIZE      = 2;
    private static final int DEFAULT_LINE_COUNT = 0;

    private final String name;
    private int lineCount;

    public Station(String name) {
        validateName(name);
        this.name = name;
        this.lineCount = DEFAULT_LINE_COUNT;
    }

    private void validateName(String name) {
        validateNameNull(name);
        validateNameSize(name);
    }

    private void validateNameNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException("역 이름을 입력해야 합니다.");
        }
    }

    private void validateNameSize(String name) {
        if (name.length() < NAME_MIN_SIZE) {
            throw new IllegalArgumentException("역 이름은 2글자 이상이어야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public void incrementLineCount() {
        lineCount += 1;
    }

    public void decrementLineCount() {
        lineCount -= 1;
    }

    public boolean hasLines() {
        return lineCount > DEFAULT_LINE_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(name, station.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
