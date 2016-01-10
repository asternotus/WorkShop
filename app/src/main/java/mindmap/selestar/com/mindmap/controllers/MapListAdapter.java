package mindmap.selestar.com.mindmap.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mindmap.selestar.com.mindmap.ItemClickListener;
import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.fragments.MapListFragment;
import mindmap.selestar.com.mindmap.fragments.MindMapFragment;
import mindmap.selestar.com.mindmap.models.MindMap;

/**
 * Created by ASTER-NOTUS on 12.11.2015.
 */
public class MapListAdapter extends RecyclerView.Adapter<MapListAdapter.MapViewHolder>{

    private List<MindMap> mindMapElements;
    private Context context;
    private MapViewHolder mvh;
    private MindMapFragment mindMapFragment;

    public MapListAdapter(List<MindMap> mindMapElements, Context context){
        this.mindMapElements = mindMapElements;
        this.context = context;
        this.mindMapFragment = new MindMapFragment();
    }

    @Override
    public MapViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.map_item_layout, viewGroup, false);
        mvh = new MapViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(final MapViewHolder mapViewHolder, int i)
    {
        mapViewHolder.item_tv_name.setText(mindMapElements.get(i).name);
        mapViewHolder.item_tv_description.setText(mindMapElements.get(i).description);
        mapViewHolder.item_iv_img.setImageResource(R.drawable.brain_logo);
        mapViewHolder.id = mindMapElements.get(i).id;

        mapViewHolder.setClickListener(new ItemClickListener()
        {
            @Override
            public void onClick(View view, int position, boolean isLongClick)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("mapPosition", position);
                mindMapFragment.setArguments(bundle);
                FragmentController.getInstance().replace(R.id.main_fl_container, new MapListFragment(), mindMapFragment, true);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mindMapElements.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class MapViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public CardView item_cv;
        public TextView item_tv_name;
        public TextView item_tv_description;
        public ImageView item_iv_img;
        public String id;

        private ItemClickListener clickListener;

        public MapViewHolder(View itemView)
        {
            super(itemView);
            item_cv = (CardView)itemView.findViewById(R.id.map_item_cv);
            item_tv_name = (TextView)itemView.findViewById(R.id.map_item_tv_name);
            item_tv_description = (TextView)itemView.findViewById(R.id.map_item_tv_description);
            item_iv_img = (ImageView)itemView.findViewById(R.id.map_item_iv_img);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getPosition(), true);
            return false;
        }
    }
}
