package mindmap.selestar.com.mindmap.models;

/**
 * Created by ASTER-NOTUS on 12.11.2015.
 */
public class MindMap {
    public String id;
    public String name;
    public String description;

    public MindMap()
    {

    }

    public MindMap(String id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}