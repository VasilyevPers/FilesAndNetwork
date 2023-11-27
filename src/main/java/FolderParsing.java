import folderInfo.FolderInfo;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
public class FolderParsing {
    private String paths;
    private List<FolderInfo> folderInfo;
    private List<String> listFile;

    public FolderParsing(String path){
        this.paths = path;
        listFile = new ArrayList<>();
        folderInfo = new ArrayList<>();
    }

    private void info (){
       List<String> file = listFile(new File(paths));
       for (String s : file){
           if (new File(s).getName().matches(".+\\.json") ||
                   new File(s).getName().matches(".+\\.csv")){
               String name = new File(s).getName();
               String size = String.valueOf(new File(s).length());
               String path = s;
               folderInfo.add(new FolderInfo(name, getFileSize(size), path));
           }
       }
    }

    public List<FolderInfo> getFolderInfo() {
        info();
        return folderInfo;
    }

    private String getFileSize(String size) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        long sizeFiles = Integer.parseInt(size);
        if (sizeFiles >= 1024 && sizeFiles < Math.pow(1024, 2)){
            double sizeFile = sizeFiles / 1024;
            return decimalFormat.format(sizeFile) + " Kb";
        }else if (sizeFiles >= Math.pow(1024, 2) && sizeFiles < Math.pow(1024, 3)){
            double sizeFile = sizeFiles / Math.pow(1024, 2);
            return decimalFormat.format(sizeFile) + " Mb";
        }else if (sizeFiles >= Math.pow(1024, 3) && sizeFiles < Math.pow(1024, 4)){
            double sizeFile = sizeFiles / Math.pow(1024, 3);
            return decimalFormat.format(sizeFile) + " Gb";
        }
        return sizeFiles + " b";
    }

    private List<String> listFile (File file) {
        if (file.isFile()) {
            listFile.add(file.getAbsolutePath());
        }
        if (file.isDirectory()){
            File[] f = file.listFiles();
            for (File file1 : f){
                listFile(file1);
            }
        }
        return listFile;
    }
}
