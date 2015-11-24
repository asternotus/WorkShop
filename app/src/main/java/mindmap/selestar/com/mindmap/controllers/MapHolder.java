package mindmap.selestar.com.mindmap.controllers;

import java.util.ArrayList;
import java.util.List;

import mindmap.selestar.com.mindmap.Constants;
import mindmap.selestar.com.mindmap.models.MapListElement;

/**
 * Created by ASTER-NOTUS on 13.11.2015.
 */
public class MapHolder
{
    List<MapListElement> mapList;

    public MapHolder()
    {
        mapList = new ArrayList<MapListElement>();
    }

    public void saveMaps(MapListElement mapListElement)
    {
        DBManager.getInstance().addMap(mapListElement);
    }

    public List<MapListElement> getMaps()
    {
        for (int i = 0; i < DBManager.getInstance().getIdCount(Constants.TABLE_MINDMAP); i++)
        {
            mapList.add(DBManager.getInstance().getMap(i));
        }

        return mapList;
    }

}
