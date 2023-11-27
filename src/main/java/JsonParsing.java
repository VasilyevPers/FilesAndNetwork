import filesJsonCsv.JsonFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
public class JsonParsing {
    private List<JsonFile> jsonFiles;
    private String path;

    public JsonParsing(String path){
        this.path = path;
        jsonFiles = new ArrayList<>();
    }

    private void listKey(){
        StringBuilder builder = new StringBuilder();
        JSONParser parser = new JSONParser();
        try {
            List<String> parseFile = Files.readAllLines(Path.of(path));
            parseFile.forEach(f -> builder.append(f));
            JSONArray jsonData = (JSONArray) parser.parse(String.valueOf(builder));
            jsonData.forEach(el -> {
                JSONObject stationsInfo = (JSONObject) el;
                String nameStation = (String) stationsInfo.get("station_name");
                String depth = "-555.0";
                if (!stationsInfo.get("depth").equals("?")){
                    depth = ((String) stationsInfo.get("depth"))
                            .replaceAll(",", ".");
                }
                jsonFiles.add(new JsonFile(nameStation, depth));
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<JsonFile> getJsonFiles() {
        listKey();
        return jsonFiles;
    }
}
