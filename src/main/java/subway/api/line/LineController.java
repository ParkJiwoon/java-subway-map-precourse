package subway.api.line;

import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class LineController {

    public void addLine(String lineName, String firstStationName, String lastStationName) {
        Line line = LineRepository.findByName(lineName);

        if (line != null) {
            throw new IllegalArgumentException("이미 등록된 노선입니다.");
        }

        Station firstStation = StationRepository.findByName(firstStationName);
        Station lastStation = StationRepository.findByName(lastStationName);
        Line newLine = new Line(lineName, firstStation, lastStation);
        LineRepository.save(newLine);
    }

    public void deleteLine(String lineName) {
        Line line = LineRepository.findByName(lineName);

        if (line == null) {
            throw new IllegalArgumentException("등록되지 않은 노선입니다.");
        }

        LineRepository.deleteByName(lineName);
    }

    public List<String> findAllLineNames() {
        return LineRepository.lines().stream()
                .map(Line::getName)
                .collect(Collectors.toList());
    }
}
