package folderInfo;

public class FolderInfo {
    private String path;
    private String name;
    private String size;

    public FolderInfo(String name, String size, String path){
        this.name = name;
        this.size = size;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "\n" + "Имя файла: " + name + "\n" +
                "Размер файла: " + size + "\n" +
                "Путь файла: " + path;
    }
}
