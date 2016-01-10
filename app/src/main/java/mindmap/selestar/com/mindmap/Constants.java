package mindmap.selestar.com.mindmap;

import android.support.v4.content.ContextCompat;

import mindmap.selestar.com.mindmap.activities.MainActivity;

/**
 * Created by ASTER-NOTUS on 27.10.2015.
 */
public class Constants
{
    public static final String LOG_TAG = "TESTSIZE";

    public static final String NAME_KEY = "user_name";
    public static final String PASSWORD_KEY = "user_password";

    public static final String DB_MINDMAP_NAME = "database";

    // database mindmap constants
    public static final String TABLE_MINDMAP = "table_mindmap";
    public static final String ID_MINDMAP_COLUMN = "id_mindmap";
    public static final String NAME_MINDMAP_COLUMN = "name_mindmap";
    public static final String DESCRIPTION_MINDMAP_COLUMN = "description_mindmap";

    // database idea constants
    public static final String TABLE_IDEA = "table_idea";
    public static final String ID_IDEA_COLUMN = "id_idea";
    public static final String NAME_IDEA_COLUMN = "name_idea";
    public static final String X_IDEA_COLUMN = "x_idea";
    public static final String Y_IDEA_COLUMN = "y_idea";
    public static final String PARENT_IDEA_COLUMN = "parent_idea";
    public static final String MAP_IDEA_COLUMN = "map_idea";

    // Colors
    public static final int LAYOUT_BACKGROUND = ContextCompat.getColor(MainActivity.context, R.color.layout_background);
    public static final int ACCENT_COLOR = ContextCompat.getColor(MainActivity.context, R.color.accent_color);
}
