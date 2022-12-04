package subway.domain.line;

import subway.domain.station.Station;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Line {
    private static final int NAME_MIN_SIZE = 2;
    private static final int MIN_ORDER = 1;

    private final String name;
    private final List<Station> stations;

    public Line(String name, Station firstStation, Station lastStation) {
        validateName(name);
        this.name = name;

        validationStations(firstStation, lastStation);
        this.stations = new ArrayList<>();
        registerStation(firstStation, 1);
        registerStation(lastStation, 2);
    }

    private void validateName(String name) {
        validateNameNull(name);
        validateNameSize(name);
    }

    private void validateNameNull(String name) {
        if (name == null) {
            throw new IllegalArgumentException("노선 이름을 입력해야 합니다.");
        }
    }

    private void validateNameSize(String name) {
        if (name.length() < NAME_MIN_SIZE) {
            throw new IllegalArgumentException("노선 이름은 2글자 이상이어야 합니다.");
        }
    }

    private void validationStations(Station firstStation, Station lastStation) {
        validateFirstStation(firstStation);
        validateLastStation(lastStation);
    }

    private void validateFirstStation(Station firstStation) {
        if (firstStation == null) {
            throw new IllegalArgumentException("상행 종점역이 존재하지 않습니다.");
        }
    }

    private void validateLastStation(Station lastStation) {
        if (lastStation == null) {
            throw new IllegalArgumentException("하행 종점역이 존재하지 않습니다.");
        }
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return Collections.unmodifiableList(stations);
    }

    public void registerStation(Station station, int order) {
        validateOrderRange(order);

        if (stations.contains(station)) {
            throw new IllegalArgumentException("해당 노선에 이미 등록된 역입니다.");
        }

        stations.add(order - 1, station);
        station.incrementLineCount();
    }

    private void validateOrderRange(int order) {
        if (order < MIN_ORDER || order > stations.size() + 1) {
            throw new IllegalArgumentException("유효하지 않은 순서입니다.");
        }
    }

    public void deleteStation(Station station) {
        if (!stations.remove(station)) {
            throw new IllegalArgumentException("해당 노선에 등록되지 않은 역입니다.");
        }

        station.decrementLineCount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(name, line.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
