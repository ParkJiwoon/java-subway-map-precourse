package subway;

import subway.api.main.MainView;
import subway.domain.line.Line;
import subway.domain.line.LineRepository;
import subway.domain.station.Station;
import subway.domain.station.StationRepository;

import java.util.stream.Stream;

public class Application {
    public static void main(String[] args) {
        initData();
        MainView.run();
    }

    private static void initData() {
        initStations();
        initLines();
    }

    private static void initStations() {
        Stream.of("교대역", "강남역", "역삼역", "남부터미널역", "양재역", "양재시민의숲역", "매봉역")
                .map(Station::new)
                .forEach(StationRepository::save);
    }

    private static void initLines() {
        Line line1 = new Line("2호선", StationRepository.findByName("교대역"), StationRepository.findByName("역삼역"));
        line1.registerStation(StationRepository.findByName("강남역"), 2);
        LineRepository.save(line1);

        Line line2 = new Line("3호선", StationRepository.findByName("교대역"), StationRepository.findByName("매봉역"));
        line2.registerStation(StationRepository.findByName("남부터미널역"), 2);
        line2.registerStation(StationRepository.findByName("양재역"), 3);
        LineRepository.save(line2);

        Line line3 = new Line("신분당선", StationRepository.findByName("강남역"), StationRepository.findByName("양재시민의숲역"));
        line3.registerStation(StationRepository.findByName("양재역"), 2);
        LineRepository.save(line3);
    }
}
