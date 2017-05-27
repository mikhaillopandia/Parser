import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by milo0116 on 24.05.2017.
 */
public class ThreadProcess extends Thread{
    private Page P;

    public ThreadProcess(Page Page)
    {
        this.P = Page;
    }

    public void run()
    {
        long startTime;
        long elapsedTime;
        HttpURLConnection connection = null;

            startTime = System.currentTimeMillis();
            try {
                connection = (HttpURLConnection) new URL(P.getUrl()).openConnection();
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                //connection.setConnectTimeout(250);
                //connection.setReadTimeout(500);

                connection.connect();
                P.setStatus(connection.getResponseCode()+" "+connection.getResponseMessage());
            } catch (Throwable cause) {
                cause.printStackTrace();
            } finally {
                if (connection != null)
                    connection.disconnect();
            }
            elapsedTime = System.currentTimeMillis() - startTime;
            //System.out.println(elapsedTime);
            P.setTime(elapsedTime);


    }
}
