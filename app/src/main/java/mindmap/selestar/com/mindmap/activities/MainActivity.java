package mindmap.selestar.com.mindmap.activities;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import java.util.LinkedList;

import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.controllers.FragmentController;

/**
 * Created by ASTER-NOTUS on 22.11.2015.
 */
public class MainActivity extends Activity
{
    private EntranceFragment entranceFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entranceFragment = new EntranceFragment();
        FragmentController.getInstance().init(this);
        FragmentController.getInstance().add(R.id.main_fl_container, entranceFragment);
    }

    @Override
    public void onBackPressed()
    {
        LinkedList<Fragment> backStack = FragmentController.getInstance().backStack;
        if(backStack.size() !=0) {
            FragmentController.getInstance().replace(R.id.main_fl_container, backStack.get(backStack.size()-1));
            backStack.remove(backStack.size()-1);
        }
        else
        {
            super.onBackPressed();
        }
    }
}
