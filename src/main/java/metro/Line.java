package metro;

public class Line {
    private String name;
    private String number;

    public Line(String number, String name){
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return number + " " + name;
    }
}
