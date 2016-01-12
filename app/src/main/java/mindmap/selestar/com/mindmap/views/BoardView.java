package mindmap.selestar.com.mindmap.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import mindmap.selestar.com.mindmap.Constants;
import mindmap.selestar.com.mindmap.data.DBManager;
import mindmap.selestar.com.mindmap.fragments.MindMapFragment;
import mindmap.selestar.com.mindmap.models.MindMap;

/**
 * Created by ASTER-NOTUS on 06.01.2016.
 */
public class BoardView extends RelativeLayout
{
    public IdeaView rootIdea;

    private Point size;

    private Paint paint;

    private MindMap mindMap;

    private Activity activity;

    public BoardView(Context context, MindMapFragment mindMapFragment, MindMap mindMap, IdeaView rootIdea)
    {
        super(context);

        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        this.mindMap = mindMap;

        activity = (Activity) context;

        this.rootIdea = rootIdea;

        paint = new Paint();

        Display display = activity.getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        addView(rootIdea);

        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        canvas.drawARGB(255, 178, 223, 219);

        paint.setColor(Constants.ACCENT_COLOR);
        paint.setStrokeWidth(10);

        rootIdea.ideaWidth = size.x / Constants.MAIN_IDEA_SIZE;
        rootIdea.ideaHeight = size.y / Constants.MAIN_IDEA_SIZE;

        Log.i(Constants.LOG_TAG, rootIdea.getTextScaleX()+"");

        rootIdea.setText(mindMap.name);

        bypassIdeas(canvas, rootIdea);
    }

    private void drawConnection(Canvas canvas, IdeaView current, IdeaView next)
    {
        canvas.drawLine(current.getX() + current.getWidth() / 2, current.getY() + current.getHeight() / 2, next.getX() + next.getWidth() / 2, next.getY() + next.getHeight() / 2, paint);
    }

    private void bypassIdeas(Canvas canvas, IdeaView ideaView)
    {
        for (int i = 0; i < ideaView.ideas.size(); i++)
        {
            drawConnection(canvas, ideaView, ideaView.ideas.get(i));
            bypassIdeas(canvas, ideaView.ideas.get(i));
        }
    }

    public void saveIdeasInDB(IdeaView ideaView)
    {
        for (int i = 0; i < ideaView.ideas.size(); i++)
        {
            DBManager.getInstance().addIdeaView(ideaView.ideas.get(i), mindMap.id, size);
            saveIdeasInDB(ideaView.ideas.get(i));
        }
    }
}
