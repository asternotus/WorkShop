package mindmap.selestar.com.mindmap.background;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.concurrent.TimeUnit;

import mindmap.selestar.com.mindmap.R;

/**
 * Created by ASTER-NOTUS on 04.11.2015.
 */
public class ImageTask extends AsyncTask<Void, Float, Void>
{
    private ImageView iv_blackbrain, iv_whitebrain;
    private Activity activity;
    private Float brainTransparency = 1f;
    private Animation anim;

    public ImageTask(Activity activity, ImageView iv_blackbrain, ImageView iv_whitebrain)
    {

        this.iv_blackbrain = iv_blackbrain;
        this.iv_whitebrain = iv_whitebrain;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        iv_whitebrain.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... params) {

        while(brainTransparency>0f)
        {
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            brainTransparency = brainTransparency - 0.01f;
            publishProgress(brainTransparency);
        }

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Float... values)
    {
        super.onProgressUpdate(values);
        iv_blackbrain.setAlpha(values[0]);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        anim = AnimationUtils.loadAnimation(activity, R.anim.moving);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_whitebrain.setAlpha(0f);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_whitebrain.startAnimation(anim);
    }
}
