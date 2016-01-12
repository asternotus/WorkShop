package mindmap.selestar.com.mindmap.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;

import java.util.ArrayList;

import mindmap.selestar.com.mindmap.Constants;
import mindmap.selestar.com.mindmap.models.MindMap;
import mindmap.selestar.com.mindmap.views.IdeaView;

/**
 * Created by ASTER-NOTUS on 07.11.2015.
 */
public class DBManager
{
    private static DBManager instance;
    private DBMindMapHelper dbMindMapHelper;
    private ContentValues cv;
    private SQLiteDatabase db;

    private Cursor c;

    private Context context;

    private DBManager()
    {

    }

    public static DBManager getInstance()
    {
        if(instance == null)
        {
            instance = new DBManager();
        }
        return instance;
    }

    public void init(Context context)
    {
        cv = new ContentValues();

        this.context = context;

        dbMindMapHelper = new DBMindMapHelper(context);
        db = dbMindMapHelper.getWritableDatabase();
    }

    public void addMap(MindMap mindMapElement)
    {
        cv = new ContentValues();
        cv.put(Constants.ID_MINDMAP_COLUMN, mindMapElement.id);
        cv.put(Constants.NAME_MINDMAP_COLUMN, mindMapElement.name);
        cv.put(Constants.DESCRIPTION_MINDMAP_COLUMN, mindMapElement.description);
        db.insert(Constants.TABLE_MINDMAP, null, cv);
    }

    public void updateMap(MindMap mindMapElement)
    {
        cv = new ContentValues();
        cv.put(Constants.ID_MINDMAP_COLUMN, mindMapElement.id);
        cv.put(Constants.NAME_MINDMAP_COLUMN, mindMapElement.name);
        cv.put(Constants.DESCRIPTION_MINDMAP_COLUMN, mindMapElement.description);

        db.update(Constants.TABLE_MINDMAP, cv, Constants.ID_MINDMAP_COLUMN + " = ?",
                new String[]{mindMapElement.id});
    }

    public void deleteMap(MindMap mindMapElement)
    {

        db.delete(Constants.TABLE_MINDMAP, Constants.ID_MINDMAP_COLUMN + " = ?",
                new String[]{mindMapElement.id});
    }

    public void addIdeaView(IdeaView ideaView, String mapID, Point size)
    {
        ArrayList<IdeaView> ideaViews = DBManager.getInstance().getIdeas(context, mapID, size);

        boolean canAdd = true;
        for (int i = 0; i < ideaViews.size(); i++)
        {
            if(ideaViews.get(i).id.equals((ideaView.id)))
            {
                canAdd = false;
                break;
            }
        }

        if(canAdd)
        {
            cv = new ContentValues();
            cv.put(Constants.ID_IDEA_COLUMN, ideaView.id);
            cv.put(Constants.NAME_IDEA_COLUMN, ideaView.name);
            cv.put(Constants.X_IDEA_COLUMN, ideaView.getX());
            cv.put(Constants.Y_IDEA_COLUMN, ideaView.getY());
            cv.put(Constants.MAP_IDEA_COLUMN, ideaView.mapID);
            cv.put(Constants.PARENT_IDEA_COLUMN, ideaView.parentID);
            db.insert(Constants.TABLE_IDEA, null, cv);
        }
    }

    public MindMap getMapByPosition(int position)
    {
        c = db.query(Constants.TABLE_MINDMAP, null, null, null, null, null, null);
        c.moveToPosition(position);

        MindMap mindMapElement = new MindMap();

        mindMapElement.id = c.getString(c.getColumnIndex(Constants.ID_MINDMAP_COLUMN));
        mindMapElement.name = c.getString(c.getColumnIndex(Constants.NAME_MINDMAP_COLUMN));
        mindMapElement.description = c.getString(c.getColumnIndex(Constants.DESCRIPTION_MINDMAP_COLUMN));

        c.close();
        return mindMapElement;
    }

    public ArrayList<MindMap> getMaps()
    {
        c = db.query(Constants.TABLE_MINDMAP, null, null, null, null, null, null);

        ArrayList<MindMap> mapList = new ArrayList<>();

        if(c.moveToFirst()) {
            do {
                MindMap mindMapElement = new MindMap();

                mindMapElement.id = c.getString(c.getColumnIndex(Constants.ID_MINDMAP_COLUMN));
                mindMapElement.name = c.getString(c.getColumnIndex(Constants.NAME_MINDMAP_COLUMN));
                mindMapElement.description = c.getString(c.getColumnIndex(Constants.DESCRIPTION_MINDMAP_COLUMN));

                mapList.add(mindMapElement);
            }
            while (c.moveToNext());
        }
        else
        {
            c.close();
        }
        return mapList;
    }

    public ArrayList<IdeaView> getIdeas(Context context, String mapID, Point size)
    {
        c = db.query(Constants.TABLE_IDEA, null, null, null, null, null, null);

        ArrayList<IdeaView> ideaList = new ArrayList<>();

        if(c.moveToFirst()) {
            do
            {
                IdeaView ideaView = new IdeaView(context);

                ideaView.id = c.getString(c.getColumnIndex(Constants.ID_IDEA_COLUMN));

                ideaView.parentID = c.getString(c.getColumnIndex(Constants.PARENT_IDEA_COLUMN));
                ideaView.mapID = c.getString(c.getColumnIndex(Constants.MAP_IDEA_COLUMN));
                ideaView.setX(c.getFloat(c.getColumnIndex(Constants.X_IDEA_COLUMN)));
                ideaView.setY(c.getFloat(c.getColumnIndex(Constants.Y_IDEA_COLUMN)));

                if(c.getPosition() == 0)
                {
                    ideaView.setTextSize(70f / Constants.MAIN_IDEA_SIZE);
                }
                else
                {
                    ideaView.setTextSize(70f / Constants.CHILD_IDEA_SIZE);
                }

                ideaView.setText(c.getString(c.getColumnIndex(Constants.NAME_IDEA_COLUMN)));
                ideaView.setColor(Constants.ACCENT_COLOR);

                if(ideaView.mapID.equals(mapID))
                {
                    ideaList.add(ideaView);
                }
            }
            while (c.moveToNext());
        }
        else
        {
            c.close();
        }
        return ideaList;
    }


}
