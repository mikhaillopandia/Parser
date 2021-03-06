import javafx.geometry.Pos;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo0116 on 30.04.2017.
 */
public class Parser {
    public void ParserProcess(String url, int maxL, int Threads, int ConnectTimeout, int ReadTimeout, boolean Home) throws IOException {
        List<Page> pageList = new ArrayList<Page>();
        List<Page> selectList = new ArrayList<Page>();

        int object_id = 2; //because of Root object is 1
        int parent_id = 0;
        int maxLevel = maxL;//MAX level of parcing
        int currLevel = 1;//current level of parcing
        //prepare Root information
        String rootUrl = url;
        String domen;
        Domen d = new Domen();
        domen = d.findDomen(rootUrl);


        Page A = new Page(rootUrl, "Root");
        A.setId(1);
        A.setParent(0);
        A.setLevel(1);
        pageList.add(A);
        //System.out.println(A.toString());

        while (currLevel <= maxLevel) {
        for (Page p : pageList) {
            if (p.getLevel() == currLevel-1) {
                selectList.add(p);
            }
        }
        for (Page p : selectList) {
            parent_id = p.getId();

            Document doc = Jsoup.connect(p.getUrl()).get();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                Page loc= new Page(link.attr("abs:href"), link.text());
                loc.setLevel(currLevel);
                loc.setId(object_id);
                loc.setParent(parent_id);
                pageList.add(loc);
                object_id++;
            }
        }
            currLevel++;
             selectList.clear();
        }

        for (Page page: pageList) {
            if(d.match(domen, page.getUrl()) == 0)
                page.setHomeDomen(0);
            page.toJson();
        }
        //processing
        Process p = new Process();
        p.Process(pageList, Threads, ConnectTimeout, ReadTimeout, Home);

        Json j = new Json();
        String Json = j.toJson(pageList);
           for (Page page: pageList) {
           System.out.println(page.toString());
        }
        System.out.println(Json);

        PostgreSQLJDBC Postgre = new PostgreSQLJDBC();
        Postgre.insert(pageList);
    }
}
