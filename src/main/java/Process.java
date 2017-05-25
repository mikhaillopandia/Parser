
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo0116 on 30.04.2017.
 */
public class Process {
    //one thread
    /*public void Process (List<Page> pageList) {
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
    }*/
    //multyThread
    public void Process(List<Page> pageList) {
        List<ThreadProcess> ThreadList = new ArrayList<ThreadProcess>();
        int i = 0;
        for (Page p : pageList) {
            ThreadList.add(new ThreadProcess(p));
        }
        for (Thread T : ThreadList) {
            {
                T.start();
                try {
                    T.join();
                } catch (InterruptedException e) {
                    System.out.println("Главный поток прерван");
                }
                i++;
            }
        }
    }
}