package filesJsonCsv;

import java.util.Objects;

public class JsonFile {
    public String name;
    public String depth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonFile jsonFile = (JsonFile) o;
        return Objects.equals(name, jsonFile.name) && Objects.equals(depth, jsonFile.depth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, depth);
    }

    public JsonFile(String name, String depth) {
        this.name = name;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public String getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "\n" + name + " " + depth;
    }
}
