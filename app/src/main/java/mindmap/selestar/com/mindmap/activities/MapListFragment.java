package mindmap.selestar.com.mindmap.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import mindmap.selestar.com.mindmap.NewElementListener;
import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.controllers.DBManager;
import mindmap.selestar.com.mindmap.controllers.MapHolder;
import mindmap.selestar.com.mindmap.controllers.MapListAdapter;
import mindmap.selestar.com.mindmap.fragments.DialogNewElement;
import mindmap.selestar.com.mindmap.models.MapListElement;

/**
 * Created by ASTER-NOTUS on 11.11.2015.
 */
public class MapListFragment extends Fragment implements NewElementListener
{
    private List<MapListElement> mapListElements;

    private RecyclerView maps_rv_maplist;
    private Button maps_btn_addnewmap;

    private LinearLayoutManager llm;

    private MapListAdapter adapter;
    private MapHolder mapHolder;

    private DialogNewElement dialogNewElement;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_maps, null);

        DBManager.getInstance().init(getActivity());

        dialogNewElement = new DialogNewElement();

        mapHolder = new MapHolder();

        maps_rv_maplist = (RecyclerView) v.findViewById(R.id.maps_rv_maplist);
        maps_btn_addnewmap = (Button) v.findViewById(R.id.maps_btn_addnewmap);

        maps_btn_addnewmap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialogNewElement.show(getFragmentManager(), "dialogNewElement");
            }
        });

        llm = new LinearLayoutManager(getActivity());
        maps_rv_maplist.setLayoutManager(llm);

        mapListElements = mapHolder.getMaps();

        adapter = new MapListAdapter(mapListElements, getActivity());
        maps_rv_maplist.setAdapter(adapter);

        return v;
    }

    @Override
    public void addNewElement(MapListElement mapListElement)
    {
        mapListElements.add(mapListElement);
        mapHolder.saveMaps(mapListElement);
        adapter.notifyDataSetChanged();
    }
}
