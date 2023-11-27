package metro;

import java.util.Objects;

public class StationInfo {
    private String nameStation;
    private String numberLine;
    private String openDate;
    private Double placementDepth;
    private boolean hasConnection;

    public StationInfo(String nameStation, String numberLine, String openDate, Double placementDepth, boolean hasConnection) {
        this.nameStation = nameStation;
        this.numberLine = numberLine;
        this.openDate = openDate;
        this.placementDepth = placementDepth;
        this.hasConnection = hasConnection;
    }

    public String getNameStation() {
        return nameStation;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public String getOpenDate() {
        return openDate;
    }

    public Double getPlacementDepth() {
        return placementDepth;
    }

    public boolean getHasConnection() {
        return hasConnection;
    }

//    @Override
//    public String toString() {                               //Метод создавался исключительно для проверки кода
//        return "nameStation: " + nameStation + " " +
//                "numberLine: " + numberLine + " " +
//                "openDate: " + openDate + " " +
//                "placementDepth: " + placementDepth + " " +
//                "hasConnection: " + hasConnection;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        StationInfo that = (StationInfo) o;
//        return Objects.equals(nameStation, that.nameStation) &&
//                Objects.equals(numberLine, that.numberLine) &&
//                Objects.equals(placementDepth, that.placementDepth) &&
//                Objects.equals(openDate, that.openDate);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(nameStation, numberLine, placementDepth, openDate);
//    }
}
