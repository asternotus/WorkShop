package mindmap.selestar.com.mindmap.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.UUID;

import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.controllers.MapListAdapter;
import mindmap.selestar.com.mindmap.data.DBManager;
import mindmap.selestar.com.mindmap.models.MindMap;

/**
 * Created by ASTER-NOTUS on 11.11.2015.
 */
public class MapListFragment extends Fragment
{
    public RecyclerView maps_rv_maplist;
    private Button maps_btn_addnewmap;

    private LinearLayoutManager llm;

    private ArrayList<MindMap> mapList;

    // dialog views
    private Button dialog_btn_submit;
    private Button dialog_btn_cancel;
    private EditText dialog_et_name;
    private TextInputLayout dialog_til_name;
    private EditText dialog_et_description;
    private TextInputLayout dialog_til_description;

    public MapListAdapter adapter;

    private AlertDialog addMapDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View v = inflater.inflate(R.layout.maps_fragment_layout, null);

        DBManager.getInstance().init(getActivity());

        maps_rv_maplist = (RecyclerView) v.findViewById(R.id.maps_rv_maplist);
        maps_btn_addnewmap = (Button) v.findViewById(R.id.maps_btn_addnewmap);

        llm = new LinearLayoutManager(getActivity());
        maps_rv_maplist.setLayoutManager(llm);

        mapList = DBManager.getInstance().getMaps();

        adapter = new MapListAdapter(mapList, getActivity());
        maps_rv_maplist.setAdapter(adapter);

        maps_btn_addnewmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapDialogBuilding();
            }
        });

        return v;
    }

    private void mapDialogBuilding()
    {
        addMapDialog = new AlertDialog.Builder(getActivity()).create();

        View view = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_new_element_layout, null);

        addMapDialog.setView(view);

        dialog_btn_submit = (Button) view.findViewById(R.id.dialog_btn_submit);
        dialog_btn_cancel = (Button) view.findViewById(R.id.dialog_btn_cancel);

        dialog_et_name = (EditText) view.findViewById(R.id.dialog_et_name);
        dialog_til_name = (TextInputLayout) view.findViewById(R.id.dialog_til_name);
        dialog_til_name.setHint(getString(R.string.name));

        dialog_et_description = (EditText) view.findViewById(R.id.dialog_et_decription);
        dialog_til_description = (TextInputLayout) view.findViewById(R.id.dialog_til_decription);
        dialog_til_description.setHint(getString(R.string.hint_description));

        dialog_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MindMap mindMap = new MindMap(UUID.randomUUID()+"", dialog_et_name.getText().toString(), dialog_et_description.getText().toString());
                mapList.add(mindMap);
                DBManager.getInstance().addMap(mindMap);
                adapter.notifyDataSetChanged();
                addMapDialog.dismiss();
            }
        });

        dialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                addMapDialog.dismiss();
            }
        });

        addMapDialog.show();
    }
}

