package mindmap.selestar.com.mindmap.views;

import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by ASTER-NOTUS on 13.12.2015.
 */
public class IdeaView extends Button
{
    public String id;
    public String name;
    public ArrayList<IdeaView> ideas;
    public String parentID;
    public String mapID;
    public float textSize;
    public int color;

    public IdeaView(Context context, String id, String name, String parentID, String mapID, int color, float textSize)
    {
        super(context);

        this.id = id;
        this.name = name;
        this.parentID = parentID;
        this.mapID = mapID;
        this.color = color;
        this.textSize = textSize;

        ideas = new ArrayList<>();

        setText(name);
        setTextSize(textSize);
        setPadding(16, 16, 16, 16);
        setBackgroundColor(color);

        setMinHeight(5);
        setMinimumHeight(5);
        setMinWidth(5);
        setMinimumWidth(5);
    }

    public IdeaView(Context context)
    {
        super(context);

        setMinHeight(5);
        setMinimumHeight(5);
        setMinWidth(5);
        setMinimumWidth(5);

        ideas = new ArrayList<>();

        setTextSize(textSize);
        setPadding(16, 16, 16, 16);
    }



    public void setName(String name)
    {
        setText(name);
    }

    public void setColor(int color)
    {
        setBackgroundColor(color);
    }
}
