package subway.api.station;

import subway.domain.station.Station;
import subway.domain.station.StationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StationController {

    public void addStation(String stationName) {
        Station station = StationRepository.findByName(stationName);

        if (station != null) {
            throw new IllegalArgumentException("이미 등록된 역 이름입니다.");
        }

        Station newStation = new Station(stationName);
        StationRepository.save(newStation);
    }

    public void deleteStation(String stationName) {
        Station station = StationRepository.findByName(stationName);

        if (station == null) {
            throw new IllegalArgumentException("등록되지 않은 역입니다.");
        }

        if (station.hasLines()) {
            throw new IllegalArgumentException("노선에 등록된 역은 삭제할 수 없습니다.");
        }

        StationRepository.deleteByName(stationName);
    }

    public List<String> findAllStationNames() {
        return StationRepository.stations().stream()
                .map(Station::getName)
                .collect(Collectors.toList());
    }
}
