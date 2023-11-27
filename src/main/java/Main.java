import java.io.FileWriter;
import java.io.PrintWriter;
public class Main {
    public static void main(String[] args) {
        String url = "https://skillbox-java.github.io";
        String path = "src/main/resources/data";
        WritingFile test = new WritingFile(url, path);
        String pathJson = "src/main/resources/stationLine.json";
        String pathJson2 = "src/main/resources/stations.json";
        try (PrintWriter out = new PrintWriter(new FileWriter(pathJson))) {
            out.write(test.metroJson().toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(pathJson2))) {
            out.write(test.stationsJson().toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
