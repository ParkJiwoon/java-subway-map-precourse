package subway.api.section;

import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;

public class SectionController {

    public void addSection(String lineName, String stationName, int order) {
        Line line = LineRepository.findByName(lineName);
        Station station = StationRepository.findByName(stationName);
        line.registerStation(station, order);
    }

    public void deleteSection(String lineName, String stationName) {
        Line line = LineRepository.findByName(lineName);
        Station station = StationRepository.findByName(stationName);
        line.deleteStation(station);
    }
}
