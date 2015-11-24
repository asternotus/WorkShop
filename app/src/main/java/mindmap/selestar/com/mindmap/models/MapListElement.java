package mindmap.selestar.com.mindmap.models;

/**
 * Created by ASTER-NOTUS on 12.11.2015.
 */
public class MapListElement {
    public String name;
    public String description;
    public int img;

    public MapListElement(String name, String description, int img) {
        this.name = name;
        this.description = description;
        this.img = img;
    }
}