package mindmap.selestar.com.mindmap.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.activities.MindMapFragment;
import mindmap.selestar.com.mindmap.models.MapListElement;

/**
 * Created by ASTER-NOTUS on 12.11.2015.
 */
public class MapListAdapter extends RecyclerView.Adapter<MapListAdapter.MapViewHolder>{

    private List<MapListElement> mapListElements;
    private Context context;
    private Intent intent;
    private MapViewHolder mvh;

    public MapListAdapter(List<MapListElement> mapListElements, Context context){
        this.mapListElements = mapListElements;
        this.context = context;
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.element_item, viewGroup, false);
        mvh = new MapViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentController.getInstance().replace();
            }
        });
        return mvh;
    }

    @Override
    public void onBindViewHolder(MapViewHolder mapViewHolder, int i) {
      mapViewHolder.item_tv_name.setText(mapListElements.get(i).name);
      mapViewHolder.item_tv_description.setText(mapListElements.get(i).description);
      mapViewHolder.item_iv_img.setImageResource(mapListElements.get(i).img);
    }

    @Override
    public int getItemCount() {
        return mapListElements.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MapViewHolder extends RecyclerView.ViewHolder {
        public CardView item_cv;
        public TextView item_tv_name;
        public TextView item_tv_description;
        public ImageView item_iv_img;

        public MapViewHolder(View itemView) {
            super(itemView);
            item_cv = (CardView)itemView.findViewById(R.id.item_cv);
            item_tv_name = (TextView)itemView.findViewById(R.id.item_tv_name);
            item_tv_description = (TextView)itemView.findViewById(R.id.item_tv_description);
            item_iv_img = (ImageView)itemView.findViewById(R.id.item_iv_img);
        }
    }
}
