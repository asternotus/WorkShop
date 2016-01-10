package mindmap.selestar.com.mindmap.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mindmap.selestar.com.mindmap.Constants;

/**
 * Created by ASTER-NOTUS on 23.12.2015.
 */
class DBMindMapHelper extends SQLiteOpenHelper {

    public DBMindMapHelper(Context context)
    {
        super(context, Constants.DB_MINDMAP_NAME + PreferenceManager.getInstance().getString(Constants.NAME_KEY), null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table "+ Constants.TABLE_MINDMAP + "("
                + Constants.ID_MINDMAP_COLUMN +" text,"
                + Constants.NAME_MINDMAP_COLUMN + " text,"
                + Constants.DESCRIPTION_MINDMAP_COLUMN + " text" + ");");

        db.execSQL("create table "+ Constants.TABLE_IDEA + "("
                + Constants.ID_IDEA_COLUMN +" text,"
                + Constants.NAME_IDEA_COLUMN +" text,"
                + Constants.X_IDEA_COLUMN +" real,"
                + Constants.Y_IDEA_COLUMN +" real,"
                + Constants.MAP_IDEA_COLUMN +" text,"
                + Constants.PARENT_IDEA_COLUMN +" text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}