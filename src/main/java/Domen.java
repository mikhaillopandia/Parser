import java.io.*;
/**
 * Created by milo0116 on 13.05.2017.
 */
public class Domen {
    public String findDomen(String url) {
        String result;
        int start = 0;
        int end = 0;
        start = url.indexOf("//");
        end = url.indexOf('/', start+2);
        result = url.substring(start+2, end);
        return result;
    }

    public int match(String domen, String url) {
        int start = 0;
        int end = 0;
        start = url.indexOf("//");
        end = url.indexOf('/', start+2);

        if((url.substring(0, end).indexOf(domen) > 0) )
        {
            return 1;
        }
        else
            return 0;
    }
}
