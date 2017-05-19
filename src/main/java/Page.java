/**
 * Created by milo0116 on 30.04.2017.
 */
public class Page {
    private int id;
    private int parent;
    private int level;
    private String url;
    private String name;
    private String status;
    private long time;
    private String json;
    private int homeDomen;

    public Page(String url, String name) {
        this.url = url;
        this.name = name;
        this.homeDomen = 1;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public int getHomeDomen() {
        return homeDomen;
    }

    public void setHomeDomen(int homeDomen) {
        this.homeDomen = homeDomen;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", parent=" + parent +
                ", level=" + level +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", time=" + time +
                //", json='" + json + '\'' +
                ", homeDomen=" + homeDomen +
                '}';
    }


    public void toJson() {
        this.setJson("{ \"id\" : " + this.getId() + "\n" +
                "\"parent_id\" : " + this.getParent() + "\n" +
                "\"level\" : " + this.getLevel() + "\n" +
                "\"url\" : " + "\"" + this.getUrl() + "\"" + "\n" +
                "\"name\" : " + "\"" + this.getName() + "\"" + "\n" +
                "\"status\" : " + "\"" + this.getStatus() + "\"" + "\n" +
                "\"time\" : " + "\"" + this.getTime() + "\"" + "}");
    }
}
