
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
        int Tcount = 2;
        int i = 0;
        int j = 0;
        int count = 0 ;
        for (Page p : pageList) {
            ThreadList.add(new ThreadProcess(p));
        }

        while (j < ThreadList.size()) {
            {
                count = 0 ;
               /* for(Thread T : ThreadList)
                {
                    if(T.getState() == Thread.State.TERMINATED)
                        count++;
                }*/
                for(int t =i; t< ThreadList.size(); t++)
                {
                    if(ThreadList.get(t).getState() == Thread.State.TERMINATED)
                        count++;
                }
if(count == 0)
    count = Tcount;
                for(int q =0; q<Tcount && q< count && j<ThreadList.size();q++)
                //for(int q =0; q<=Tcount && q<= count;q++)
                {
                    ThreadList.get(j).start();

                    if(j>ThreadList.size()-Tcount-2)
                    {
                        try {
                        ThreadList.get(j).join();
                    } catch (InterruptedException e) {
                        System.out.println("Главный поток прерван");
                    }
                    }
                    j++;
                    if (q == 0)
                        i = j;
                }

                /*try {
                    ThreadList.get(j).join();
                } catch (InterruptedException e) {
                    System.out.println("Главный поток прерван");
                }*/
                //i++;
                //j++;
            }
        }
    }
}