package mindmap.selestar.com.mindmap.controllers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import java.util.LinkedList;

/**
 * Created by ASTER-NOTUS on 22.11.2015.
 */
public class FragmentController
{
    private static FragmentController instance;
    private FragmentTransaction fTrans;
    private Activity activity;

    public LinkedList<Fragment> backStack;

    private FragmentController()
    {

    }

    public static FragmentController getInstance()
    {
        if(instance == null)
        {
            instance = new FragmentController();
        }
        return instance;
    }

    public void init(Activity activity)
    {
        this.activity = activity;
        fTrans = activity.getFragmentManager().beginTransaction();
        backStack = new LinkedList<Fragment>();
    }

    public void add(int container, Fragment fragment)
    {
        fTrans = activity.getFragmentManager().beginTransaction();
        fTrans.add(container, fragment);
        fTrans.commit();
    }

    public void replace(int container, Fragment current, Fragment next, boolean addToBackStack)
    {
        if(addToBackStack)
        {
            backStack.add(current);
        }
        fTrans = activity.getFragmentManager().beginTransaction();
        fTrans.replace(container, next);
        fTrans.commit();
    }

    public void replace(int container, Fragment fragment)
    {

        fTrans = activity.getFragmentManager().beginTransaction();
        fTrans.replace(container, fragment);
        fTrans.commit();
    }

    public void remove(Fragment fragment)
    {
        fTrans = activity.getFragmentManager().beginTransaction();
        fTrans.remove(fragment);
        fTrans.commit();
    }


}
