import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;


/**
 * Created by User on 25.05.2017.
 */
public class PostgreSQLJDBC {
    public void insert(List<Page> pageList) {
        Connection c = null;
        Statement stmt = null;
        int session_id;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres",
                            "postgres", "netcracker");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT nextval('session_id');" );
            rs.next();
            session_id = rs.getInt("nextval");
            rs.close();
            //stmt = c.createStatement();
            String sql =null;
            for(Page p : pageList) {
                sql = "INSERT INTO pages "
                        + "VALUES ("+session_id+","+p.getId()+","+p.getParent()+","+p.getLevel()+",'"+p.getUrl()
                        +"','"+p.getName()+"','"+p.getStatus()+"',"+p.getTime()+",'"+p.getJson()+"',"+p.getHomeDomen()+");";
                stmt.executeUpdate(sql);
            }

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
