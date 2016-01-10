package mindmap.selestar.com.mindmap.views;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;

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
    public int color;
    public LinearLayout.LayoutParams layoutParams;

    public IdeaView(Context context, String id, String name, String parentID, String mapID, int color)
    {
        super(context);
        this.id = id;
        this.name = name;
        this.parentID = parentID;
        this.mapID = mapID;
        this.color = color;

        ideas = new ArrayList<>();

        setText(name);
        setTextSize(10f);
        setPadding(16, 16, 16, 16);
        setBackgroundColor(color);
    }

    public IdeaView(Context context)
    {
        super(context);

        ideas = new ArrayList<>();

        setTextSize(10f);
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
