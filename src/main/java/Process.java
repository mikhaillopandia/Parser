
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo0116 on 30.04.2017.
 */
public class Process {
    public void Process (List<Page> pageList) {
        long startTime;
        long elapsedTime;
        HttpURLConnection connection = null;
        for (Page p : pageList) {
            startTime = System.currentTimeMillis();
            try {
                connection = (HttpURLConnection) new URL(p.getUrl()).openConnection();
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                //connection.setConnectTimeout(250);
                //connection.setReadTimeout(500);

                connection.connect();
                p.setStatus(connection.getResponseCode()+" "+connection.getResponseMessage());
            } catch (Throwable cause) {
                cause.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            //System.out.println(elapsedTime);
            p.setTime(elapsedTime);
        }
    }
}
