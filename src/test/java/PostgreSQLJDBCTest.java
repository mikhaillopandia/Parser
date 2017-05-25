import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.05.2017.
 */
public class PostgreSQLJDBCTest {
    public static void main(String[] args) {
        selectSession s = new selectSession();
        List<Page> pageList = new ArrayList<Page>();
        pageList = s.selectByMaxSession();

        for (Page page: pageList) {
            System.out.println(page.getJson());
        }
    }

}
