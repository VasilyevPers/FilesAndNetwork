import metro.Line;
import metro.Station;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class HTMLParsing {
    private String url;
    private List<Line> lines;
    private List<Station> stations;

    public HTMLParsing(String url){
        this.url = url;
        stations = new ArrayList<>();
        lines = new ArrayList<>();
    }
    private void  linesList(){
        try {
            Document urlCode = Jsoup.connect(url).get();
            Elements elements = urlCode.select("span.js-metro-line");
            for (Element el : elements){
                String name = el.text();
                String number = el.attr("data-line");
                lines.add(new Line(number, name));
            }
        } catch (Exception ex){
            ex.getMessage();
        }

    }

    public List<Line> getLines() {
        linesList();
        return lines;
    }

    private void stationsList(){
        try {
            String nameStation;
            String numberLine;

            Document urlCode = Jsoup.connect(url).get();
            Elements elementStan = urlCode.select("div.js-metro-stations");
            for (Element lineNum: elementStan){
                numberLine = lineNum.attr("data-line");
                for (Element nameStan : lineNum.select("p.single-station")){
                    boolean connection = true;
                    nameStation = nameStan.select("span.name").text();
                    Elements connect = nameStan.select("span.t-icon-metroln");
                    if (connect.size() == 0){
                        connection = false;
                    }
                        stations.add(new Station(numberLine, nameStation, connection));
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public List<Station> getStations() {
        stationsList();
        return stations;
    }
}
