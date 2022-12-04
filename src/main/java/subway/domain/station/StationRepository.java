package subway.domain.station;

import java.util.*;

public class StationRepository {
    private static final Map<String, Station> stations = new LinkedHashMap<>();

    public static Collection<Station> stations() {
        return Collections.unmodifiableCollection(stations.values());
    }

    public static Station findByName(String name) {
        return stations.get(name);
    }

    public static void save(Station station) {
        stations.putIfAbsent(station.getName(), station);
    }

    public static void deleteByName(String name) {
        stations.remove(name);
    }
}
