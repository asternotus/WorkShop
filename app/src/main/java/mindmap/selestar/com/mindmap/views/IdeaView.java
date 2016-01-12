package mindmap.selestar.com.mindmap.views;

import android.content.Context;
import android.view.View;
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
    public int ideaWidth, ideaHeight;

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
    }

    public IdeaView(Context context)
    {
        super(context);

        ideas = new ArrayList<>();

        setTextSize(textSize);
        setPadding(16, 16, 16, 16);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        //Get the width measurement
        int widthSize = View.resolveSize(ideaWidth, widthMeasureSpec);

        //Get the height measurement
        int heightSize = View.resolveSize(ideaHeight, heightMeasureSpec);

        //MUST call this to store the measurements
        setMeasuredDimension(widthSize, heightSize);
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
