import filesJsonCsv.CsvFile;
import filesJsonCsv.JsonFile;
import metro.Line;
import metro.Station;
import metro.StationInfo;
import org.json.simple.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class WritingFile {
    private final String htmlPath;
    private final String folderPath;
    private HTMLParsing parsingMetro;

    public WritingFile(String htmlPath, String folderPath) {
        this.htmlPath = htmlPath;
        this.folderPath = folderPath;
        this.parsingMetro = new HTMLParsing(htmlPath);

    }

    public JSONObject metroJson() {                          //Создает JSON объект содержащий станции и линии
        Map<String, Object> jsonFile = new LinkedHashMap<>();
        jsonFile.put("Stations", stationsInLines());
        jsonFile.put("Lines", linesInfo());

        JSONObject lineStations = new JSONObject(jsonFile);

        return lineStations;
    }

    public JSONObject stationsJson() {                       //Создает JSON объект содержащий информацию о станциях
        Map<String, Object> jsonFile = new HashMap<>();
        jsonFile.put("Stations", sortedStationsInfo());

        JSONObject lineStations = new JSONObject(jsonFile);

        return lineStations;
    }

    private Map<String, List<String>> stationsInLines() {         //Создается список станций, пренадлежащих к каждой линии
        Map<String, List<String>> stationInLine = new LinkedHashMap<>();
        List<Station> stations = parsingMetro.getStations();
        for (Line l : parsingMetro.getLines()) {
            List<String> station = new ArrayList<>();
            for (Station s : stations) {
                if (s.getNumberLine().equals(l.getNumber())) {
                    station.add(s.getName());
                }
            }
            stationInLine.put(l.getNumber(), station);
        }
        return stationInLine;
    }

    private List<List<String>> linesInfo() {                 //Создается список списков линий и их номеров
        List<List<String>> lineInfo = new ArrayList<>();
        for (Line f : parsingMetro.getLines()) {
            List<String> s = new ArrayList<>();
            s.add("Name lines: " + f.getName());
            s.add("Number line: " + f.getNumber());
            lineInfo.add(s);
        }
        return lineInfo;
    }

    private List<StationInfo> stationsInfo() {          //Создает список станций с информацией
        List<StationInfo> stationInfo = new ArrayList<>();
        StationInfo testStation;
        for (Station station : parsingMetro.getStations()) {
            for (CsvFile openDate : csvList()) {
                if (openDate.getName().equals(station.getName())) {
                    for (JsonFile depth : jsonList()) {
                        if (depth.getName().equals(openDate.getName())) {
                            boolean adds = true;
                            testStation = new StationInfo(station.getName(),
                                    station.getNumberLine(),
                                    openDate.getOpenDate(),
                                    Double.parseDouble(depth.getDepth()),
                                    station.getHasConnection());
                            if (stationInfo.isEmpty()){
                                stationInfo.add(testStation);
                            }
                            for (StationInfo f : stationInfo){
                                if (!f.getNameStation().equals(testStation.getNameStation())){
                                   continue;
                                }
                                boolean onLine = f.getNameStation().equals(testStation.getNameStation()) &&
                                                 f.getNumberLine().equals(testStation.getNumberLine());

                                boolean ofLine = f.getNameStation().equals(testStation.getNameStation())&&
                                        (f.getOpenDate().equals(testStation.getOpenDate()) ||
                                        f.getPlacementDepth().equals(testStation.getPlacementDepth())) &&
                                        !f.getNumberLine().equals(testStation.getNumberLine());
                                if (onLine || ofLine){
                                    adds = false;
                                }
                            }
                            if (adds) stationInfo.add(testStation);
                        }
                    }
                }
            }
        }
        return stationInfo;
    }

    private List<JsonFile> jsonList() {                      //Создает список глубин размещения станций со всех json файлов
        FolderParsing json = new FolderParsing(folderPath);
        JsonParsing jsonParsing;
        List<String> paths = new ArrayList<>();
        List<JsonFile> jsonList = new ArrayList<>();
        json.getFolderInfo().stream()
                .filter(e -> e.getName().matches(".+\\.json"))
                .forEach(e -> paths.add(e.getPath()));

        for (String f : paths) {
            jsonParsing = new JsonParsing(f);
            jsonList.addAll(jsonParsing.getJsonFiles());
        }
        jsonList = jsonList.stream().distinct().collect(Collectors.toList()); //Удаляет из списка повторяющиеся элементы
        return jsonList;
    }

    private List<CsvFile> csvList() {                      //Создает список дат открытия всех станций со всех csv файлов
        FolderParsing csv = new FolderParsing(folderPath);
        CsvParsing csvParsing;
        List<String> paths = new ArrayList<>();
        List<CsvFile> csvList = new ArrayList<>();
        csv.getFolderInfo().stream()
                .filter(e -> e.getName().matches(".+\\.csv"))
                .forEach(e -> paths.add(e.getPath()));

        for (String f : paths) {
            csvParsing = new CsvParsing(f);
            csvList.addAll(csvParsing.getCsvFiles());
        }
        csvList = csvList.stream().distinct().collect(Collectors.toList());  //Удаляет из списка повторяющиеся элементы
        return csvList;

    }

    private List<List<String>> sortedStationsInfo() {         //Данный метод должет (по задумке) удалять из списка одинаковые станции
                                                            //(но я не уверен)
        List<StationInfo> sortedStations = stationsInfo();
        sortedStations = sortedStations.stream()
                .sorted(Comparator.comparing(StationInfo::getNameStation)
                        .thenComparing(StationInfo::getPlacementDepth))
                .collect(Collectors.toList());


        List<List<String>> stations = new ArrayList<>();
        for (StationInfo s : sortedStations){                       //данный цикл переписывает объекты отсортированного листа станций
            List<String> info = new ArrayList<>();                  // в List<String> с учетом отсутствующих параметров
            if (s.getPlacementDepth() == -555.0){
                info.add("Name station: " + s.getNameStation());
                info.add("Number line: " + s.getNumberLine());
                info.add("Date open: " + s.getOpenDate());
                info.add("Has connection: " + s.getHasConnection());
                stations.add(info);
            } else {
                info.add("Name station: " + s.getNameStation());
                info.add("Number line: " + s.getNumberLine());
                info.add("Date open: " + s.getOpenDate());
                info.add("Placement depth:" + s.getPlacementDepth());
                info.add("Has connection: " + s.getHasConnection());
                stations.add(info);
            }
        }

        return stations;
    }

}
