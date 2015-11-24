package mindmap.selestar.com.mindmap.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mindmap.selestar.com.mindmap.Constants;
import mindmap.selestar.com.mindmap.models.IdeaElement;
import mindmap.selestar.com.mindmap.models.MapListElement;

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
        dbMindMapHelper = new DBMindMapHelper(context);
        cv = new ContentValues();
        db = dbMindMapHelper.getWritableDatabase();
    }

    public int getIdCount(String table)
    {
        c = db.query(table, null, null, null, null, null, null);
        return c.getCount();
    }

    public void addMap(MapListElement mapListElement)
    {
        cv.put(Constants.NAME_MINDMAP_COLUMN, mapListElement.name);
        cv.put(Constants.DESCRIPTION_MINDMAP_COLUMN, mapListElement.description);
        cv.put(Constants.IMG_MINDMAP_COLUMN, mapListElement.img);
        db.insert(Constants.TABLE_MINDMAP, null, cv);
    }

    public void addIdeaElement(IdeaElement ideaElement)
    {
        cv.put(Constants.NAME_IDEA_COLUMN, ideaElement.name);
        cv.put(Constants.TEXT_IDEA_COLUMN, ideaElement.text);
        cv.put(Constants.LEVEL_IDEA_COLUMN, ideaElement.level);
        cv.put(Constants.CONNECTIONS_IDEA_COLUMN, ideaElement.connections);
        cv.put(Constants.PARENT_IDEA_COLUMN, ideaElement.parent);
        cv.put(Constants.PICTURE_IDEA_COLUMN, ideaElement.picture);
        db.insert(Constants.TABLE_IDEA, null, cv);
    }

    public MapListElement getMap(int id)
    {
        MapListElement mapListElement;
        c = db.query(Constants.TABLE_MINDMAP, null, null, null, null, null, null);
        c.moveToPosition(id);


        int nameColIndex = c.getColumnIndex(Constants.NAME_MINDMAP_COLUMN);
        int descriptionColIndex = c.getColumnIndex(Constants.DESCRIPTION_MINDMAP_COLUMN);
        int imgColIndex = c.getColumnIndex(Constants.IMG_MINDMAP_COLUMN);

        mapListElement = new MapListElement(c.getString(nameColIndex), c.getString(descriptionColIndex),
                                            c.getInt(imgColIndex));

        c.close();
        return mapListElement;
    }

    public IdeaElement getIdea(int id)
    {
        IdeaElement ideaElement;
        c = db.query(Constants.TABLE_IDEA, null, null, null, null, null, null);
        c.moveToPosition(id);


        int nameColIndex = c.getColumnIndex(Constants.NAME_IDEA_COLUMN);
        int textColIndex = c.getColumnIndex(Constants.TEXT_IDEA_COLUMN);
        int levelColIndex = c.getColumnIndex(Constants.LEVEL_IDEA_COLUMN);
        int connectionsColIndex = c.getColumnIndex(Constants.CONNECTIONS_IDEA_COLUMN);
        int parentColIndex = c.getColumnIndex(Constants.PARENT_IDEA_COLUMN);
        int pictureColIndex = c.getColumnIndex(Constants.PICTURE_IDEA_COLUMN);

        ideaElement = new IdeaElement();

        ideaElement.name = c.getString(nameColIndex);
        ideaElement.text = c.getString(textColIndex);
        ideaElement.level = c.getInt(levelColIndex);
        ideaElement.connections = c.getInt(connectionsColIndex);
        ideaElement.parent = c.getInt(parentColIndex);
        ideaElement.picture = c.getString(pictureColIndex);

        c.close();
        return ideaElement;
    }

    class DBMindMapHelper extends SQLiteOpenHelper {

        public DBMindMapHelper(Context context) {

            super(context, Constants.DB_MINDMAP_NAME + PreferenceManager.getInstance().getString(Constants.NAME_KEY), null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {

            db.execSQL("create table "+ Constants.TABLE_MINDMAP + "("
                    + Constants.ID_COLUMN +" integer primary key autoincrement,"
                    + Constants.NAME_MINDMAP_COLUMN +" text,"
                    + Constants.DESCRIPTION_MINDMAP_COLUMN +" text,"
                    + Constants.IMG_MINDMAP_COLUMN +" integer" + ");");

            db.execSQL("create table "+ Constants.TABLE_IDEA + "("
                    + Constants.ID_COLUMN +" integer primary key autoincrement,"
                    + Constants.NAME_IDEA_COLUMN +" text,"
                    + Constants.TEXT_IDEA_COLUMN +" text,"
                    + Constants.LEVEL_IDEA_COLUMN +" integer,"
                    + Constants.CONNECTIONS_IDEA_COLUMN +" integer,"
                    + Constants.PARENT_IDEA_COLUMN +" integer,"
                    + Constants.PICTURE_IDEA_COLUMN +" integer" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {

        }
    }
}
