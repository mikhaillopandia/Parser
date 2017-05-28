import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by milo0116 on 24.05.2017.
 */
public class ThreadProcess extends Thread{
    private Page P;
    int CTimeout;
    int RTimeout;

    public ThreadProcess(Page Page, int ConnectTimeout, int ReadTimeout)
    {
        this.P = Page;
        this.CTimeout = ConnectTimeout;
        this.RTimeout = ReadTimeout;
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
                connection.setConnectTimeout(CTimeout);
                connection.setReadTimeout(RTimeout);

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
