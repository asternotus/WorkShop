package mindmap.selestar.com.mindmap.controllers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ASTER-NOTUS on 27.10.2015.
 */
public class PreferenceManager
{
    private static PreferenceManager instance;
    private SharedPreferences sPref;
    private SharedPreferences.Editor editor;

    private PreferenceManager()
    {
    }

    public static PreferenceManager getInstance()
    {
        if(instance == null)
        {
            instance = new PreferenceManager();
        }

        return instance;
    }

    public void init(Context context, String name)
    {
        sPref = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = sPref.edit();
    }

    public void addString(String key, String value)
    {
        editor.putString(key, value);
        editor.commit();
    }

    public void addInt(String key, int value)
    {
        editor.putInt(key, value);
        editor.commit();
    }

    public void addBoolean(String key, boolean value)
    {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public String getString(String key)
    {
        return sPref.getString(key,"\\$");
    }

    public int getInt(String key)
    {
        return sPref.getInt(key, 0);
    }

    public boolean getBoolean(String key)
    {
        return sPref.getBoolean(key, false);
    }
}
