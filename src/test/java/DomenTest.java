/**
 * Created by milo0116 on 13.05.2017.
 */
public class DomenTest {
    public static void main(String[] args) {
        Domen D = new Domen();
        String s;
        int res;
        String url = "https://vk.com/im?peers=35606242_29205922";
        s = D.findDomen(url);

        res = D.match(s, "https://api.vk.com/im?p4565");
        System.out.println(s);
        System.out.println(res);
    }
}
