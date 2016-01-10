package mindmap.selestar.com.mindmap.activities;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.controllers.FragmentController;
import mindmap.selestar.com.mindmap.fragments.EntranceFragment;

/**
 * Created by ASTER-NOTUS on 22.11.2015.
 */
public class MainActivity extends Activity
{
    private ImageView iv_blackbrain, iv_whitebrain;

    public static Context context;

    private float brainTransparency = 1f;

    private Timer timer;

    private Animation anim;

    private EntranceFragment entranceFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        context = this;

        iv_blackbrain = (ImageView) findViewById(R.id.iv_blackbrain);
        iv_whitebrain = (ImageView) findViewById(R.id.iv_whitebrain);

        entranceFragment = new EntranceFragment();

        FragmentController.getInstance().init(this);
        FragmentController.getInstance().add(R.id.main_fl_container, entranceFragment);

        splashScreen();
    }

    private void splashScreen() {
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
                        brainTransparency = brainTransparency - 0.01f;
                        iv_blackbrain.setAlpha(brainTransparency);

                        if(brainTransparency<0f)
                        {
                            iv_whitebrain.startAnimation(anim);
                            timer.cancel();
                        }
                    }
                });
            }
        },0,50);

        anim = AnimationUtils.loadAnimation(this, R.anim.moving);
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
    }

    @Override
    public void onBackPressed()
    {
        // Custom BackStack realization
        LinkedList<Fragment> backStack = FragmentController.getInstance().backStack;
        if(backStack.size() !=0)
        {
            FragmentController.getInstance().replace(R.id.main_fl_container, backStack.get(backStack.size()-1));
            backStack.remove(backStack.size()-1);
        }
        else
        {
            super.onBackPressed();
        }
    }
}
