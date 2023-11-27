import filesJsonCsv.CsvFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvParsing {
    private String path;
    private List<CsvFile> csvFiles;

    public CsvParsing(String path){
        this.path = path;
        csvFiles = new ArrayList<>();
    }

    private void csvParsing(){
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(el -> {
               String[] words = el.strip().split(",");
               String nameStation = words[0];
               String date = words[1];
               csvFiles.add(new CsvFile(nameStation, date));
            });
            csvFiles.remove(0);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<CsvFile> getCsvFiles() {
        csvParsing();
        return csvFiles;
    }
}
