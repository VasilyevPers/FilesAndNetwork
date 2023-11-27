package filesJsonCsv;

import java.util.Objects;

public class CsvFile {
    private String name;
    private String openDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CsvFile csvFile = (CsvFile) o;
        return Objects.equals(name, csvFile.name) && Objects.equals(openDate, csvFile.openDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, openDate);
    }

    public CsvFile(String name, String openDate) {
        this.name = name;
        this.openDate = openDate;
    }

    public String getName() {
        return name;
    }

    public String getOpenDate() {
        return openDate;
    }

    @Override
    public String toString() {
        return "\n" + name + " " + openDate;
    }
}
