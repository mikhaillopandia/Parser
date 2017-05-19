import java.util.List;
/**
 * Created by milo0116 on 02.05.2017.
 */
public class Json {
    public String toJson(List<Page> pageList)
    {
        String res = null;
        res = "{ nodes:{\n";
        for (Page p: pageList) {
            //if(p.getId()!=1)
              //  res+= ',';
                    res+= p.getId() + ":{'label':'" + p.getName() +
                    "','url':'" + p.getUrl() +
                    "','parent':" + p.getParent() +
                    ",'status':'" + p.getStatus() +
                    "','time':'" + p.getTime() +
                    "','shape':'dot'" +
                    "},\n";
            //res+="}";
            if(p.getId() == pageList.size()) res += "}";
        }
        res += "edges:{\n";
        for (Page p: pageList) {
            if(p.getId()!=1)
              res+= ',';
            res += "\n"+ p.getParent() + ":{ " + p.getId() + ":{ directed: true, weight:3 } }";
        }   res += "}}";
        return res;
    }
}
