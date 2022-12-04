package subway.api.main;

import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.station.Station;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainController {
    public Map<String, List<String>> findSubwayMap() {
        Map<String, List<String>> result = new LinkedHashMap<>();

        for (Line line : LineRepository.lines()) {
            List<String> stationNames = line.getStations().stream()
                    .map(Station::getName)
                    .collect(Collectors.toList());

            result.put(line.getName(), stationNames);
        }

        return result;
    }
}
