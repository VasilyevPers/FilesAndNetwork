package metro;

public class Station {
    private String name;
    private String numberLine;
    private boolean hasConnection;

    public Station(String numberLine, String name, boolean hasConnection){
        this.name = name;
        this.numberLine = numberLine;
        this.hasConnection = hasConnection;
    }

    public String getName() {
        return name;
    }

    public String getNumberLine() {
        return numberLine;
    }

    public boolean getHasConnection() {
        return hasConnection;
    }

    @Override
    public String toString() {
        return numberLine + " " + name;
    }
}
