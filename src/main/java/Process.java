
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo0116 on 30.04.2017.
 */
public class Process {
    //multyThread
    public void Process(List<Page> pageList, int Threads, int ConnectTimeout, int ReadTimeout, boolean domenProcess) {
        List<ThreadProcess> ThreadList = new ArrayList<ThreadProcess>();
        int Tcount = 2;
        int i = 0;
        int j = 0;
        int count = Threads ;
        //int domenProcess = 1;
        for (Page p : pageList) {
            if(domenProcess == true && p.getHomeDomen() != 0)
            ThreadList.add(new ThreadProcess(p, ConnectTimeout, ReadTimeout));
            if(domenProcess == false)
                ThreadList.add(new ThreadProcess(p, ConnectTimeout, ReadTimeout));
        }

        while (j < ThreadList.size()) {
            {
                count = 0 ;
                for(int t =i; t< ThreadList.size(); t++)
                {
                    if(ThreadList.get(t).getState() == Thread.State.TERMINATED)
                        count++;
                }
if(count == 0)
    count = Tcount;
                for(int q =0; q<Tcount && q< count && j<ThreadList.size();q++)
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
            }
        }
    }
}