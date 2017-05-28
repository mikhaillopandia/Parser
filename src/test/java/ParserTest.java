import java.io.IOException;

/**
 * Created by milo0116 on 16.05.2017.
 */
public class ParserTest {
    public static void main(String[] args) {
        Parser P = new Parser();
        try {
            P.ParserProcess("https://zaxidfest.com/video", 3,2, 500, 3000, false);
        }catch(IOException e)
        { e.printStackTrace();}

    }
}
