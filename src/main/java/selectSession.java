import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.05.2017.
 */
public class selectSession {
    int session_id = 0;

    public selectSession(int session_id) {
        this.session_id = session_id;
    }
    public selectSession() {
        this.session_id = 0;
    }

    public List<Page> selectByMaxSession() {
        Connection c = null;
        Statement stmt = null;

        Page p;
        List<Page> pageList = new ArrayList<Page>();
        try {
            Class.forName("org.postgresql.Driver");
            ResultSet rs;
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "netcracker");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            if(this.session_id == 0) {
                rs = stmt.executeQuery("SELECT * FROM pages where session_id = (select max(session_id) from pages);");
            }
            else
            {
                rs = stmt.executeQuery("SELECT * FROM pages where session_id = "+session_id+";");
            }
            while ( rs.next() ) {
                p = new Page(rs.getString("url"), rs.getString("name"));
                p.setId(rs.getInt("id"));
                p.setParent(rs.getInt("parent"));
                p.setLevel(rs.getInt("level"));
                p.setStatus(rs.getString("status"));
                p.setTime(rs.getInt("time"));
                p.setJson(rs.getString("json"));
                p.setHomeDomen(rs.getInt("homeDomen"));
                pageList.add(p);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Selected successfully");
        return pageList;
    }

}
