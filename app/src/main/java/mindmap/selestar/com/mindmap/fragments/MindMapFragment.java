package mindmap.selestar.com.mindmap.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.UUID;

import mindmap.selestar.com.mindmap.Constants;
import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.data.DBManager;
import mindmap.selestar.com.mindmap.models.MindMap;
import mindmap.selestar.com.mindmap.views.BoardView;
import mindmap.selestar.com.mindmap.views.IdeaView;
import mindmap.selestar.com.mindmap.views.ZoomLayout;

/**
 * Created by ASTER-NOTUS on 13.12.2015.
 */
public class MindMapFragment extends Fragment
{
    private BoardView boardView;
    private Point size;
    private ZoomLayout mind_map_zoom_layout;

    private MindMap mindMap;

    private IdeaView rootIdea;

    private boolean clickable = false;
    private int movementFlags = 0;

    public View.OnTouchListener onTouchListener;

    private AlertDialog configIdeaDialog;

    private ArrayList<IdeaView> ideaList;

    // dialog views
    private Button dialog_ic_btn_submit;
    private Button dialog_ic_btn_cancel;
    private EditText dialog_ic_et_name;
    private TextInputLayout dialog_ic_til_name;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.mind_map_fragment_layout, null);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        Bundle bundle = this.getArguments();
        int mapPosition = bundle.getInt("mapPosition", 0);

        mind_map_zoom_layout = (ZoomLayout) v.findViewById(R.id.mind_map_zoom_layout);

        mindMap = DBManager.getInstance().getMapByPosition(mapPosition);

        //mind_map_zoom_layout.zoomOnCenter(size.x, size.y);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        ideaList = DBManager.getInstance().getIdeas(getActivity(), mindMap.id, size);

        if(ideaList.size() > 0)
        {
            rootIdea = ideaList.get(0);

            checkAllIdeas(rootIdea);
        }
        else
        {
            rootIdea = new IdeaView(getActivity(), UUID.randomUUID()+"", mindMap.name, null,  mindMap.id, Constants.ACCENT_COLOR, 70f / Constants.MAIN_IDEA_SIZE);
            rootIdea.setX(size.x / 2);
            rootIdea.setY(size.y / 2);
        }

        boardView = new BoardView(getActivity(), this, mindMap, rootIdea);

        onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                Log.i(Constants.LOG_TAG, v.getX()+"");
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    clickable = true;
                }
                else if (event.getAction() == MotionEvent.ACTION_MOVE)
                {
                    moveAction(v, event);
                }

                if(event.getAction() == MotionEvent.ACTION_UP) {

                    if (clickable)
                    {
                        clickAction(v);
                    }

                    clickable = false;
                    movementFlags = 0;
                }

                return true;
            }
        };

        boardView.rootIdea.setOnTouchListener(onTouchListener);

        addIdeasOnView(rootIdea);

        mind_map_zoom_layout.addView(boardView);
    }

    private void checkAllIdeas(IdeaView ideaView)
    {
        for (int i = 0; i < ideaList.size(); i++)
        {
            if(ideaView.id.equals(ideaList.get(i).parentID))
            {
                ideaView.ideas.add((ideaList.get(i)));
                checkAllIdeas((ideaList.get(i)));
            }
        }
    }

    public void addIdeasOnView(IdeaView ideaView)
    {
        for (int i = 0; i < ideaView.ideas.size(); i++) {
            boardView.addView(ideaView.ideas.get(i));
            ideaView.ideas.get(i).setOnTouchListener(onTouchListener);
            addIdeasOnView(ideaView.ideas.get(i));
        }
    }

    private void moveAction(View v, MotionEvent event)
    {
        boardView.invalidate();
        movementFlags++;
        if(movementFlags > 5){
            clickable = false;
        }

        v.setX(event.getRawX() - v.getWidth() / 2);
        v.setY(event.getRawY() - v.getHeight() / 2);
    }

    private void clickAction(View v)
    {
        mapDialogBuilding(v);
    }

    private void addIdea(View v, String name)
    {
        IdeaView currentIdea = (IdeaView) v;

        IdeaView childIdea = new IdeaView(getActivity(), UUID.randomUUID()+"", name, currentIdea.id, mindMap.id, Constants.ACCENT_COLOR, 70f / Constants.CHILD_IDEA_SIZE);
        childIdea.ideaWidth = size.x/ Constants.CHILD_IDEA_SIZE;
        childIdea.ideaHeight = size.y / Constants.CHILD_IDEA_SIZE;
        currentIdea.ideas.add(childIdea);
        boardView.addView(childIdea);

        childIdea.setX(currentIdea.getX() - currentIdea.getWidth());
        childIdea.setY(currentIdea.getY() - currentIdea.getHeight());

        childIdea.setOnTouchListener(onTouchListener);
    }

    public void mapDialogBuilding(final View ideaView)
    {
        configIdeaDialog = new AlertDialog.Builder(getActivity()).create();

        View view = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.dialog_item_config_layout, null);

        configIdeaDialog.setView(view);

        dialog_ic_btn_submit = (Button) view.findViewById(R.id.dialog_ic_btn_submit);
        dialog_ic_btn_cancel = (Button) view.findViewById(R.id.dialog_ic_btn_cancel);

        dialog_ic_et_name = (EditText) view.findViewById(R.id.dialog_ic_et_name);
        dialog_ic_til_name = (TextInputLayout) view.findViewById(R.id.dialog_ic_til_name);
        dialog_ic_til_name.setHint(getString(R.string.name));

        dialog_ic_btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIdea(ideaView, dialog_ic_et_name.getText().toString());
                configIdeaDialog.dismiss();
            }
        });

        dialog_ic_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configIdeaDialog.dismiss();
            }
        });

        configIdeaDialog.show();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        DBManager.getInstance().addIdeaView(boardView.rootIdea, mindMap.id, size);
        boardView.saveIdeasInDB(boardView.rootIdea);
    }
}