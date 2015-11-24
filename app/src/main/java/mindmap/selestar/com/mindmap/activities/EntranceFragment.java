package mindmap.selestar.com.mindmap.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import mindmap.selestar.com.mindmap.Constants;
import mindmap.selestar.com.mindmap.R;
import mindmap.selestar.com.mindmap.background.ImageTask;
import mindmap.selestar.com.mindmap.controllers.FragmentController;
import mindmap.selestar.com.mindmap.controllers.PreferenceManager;

/**
 * Created by ASTER-NOTUS on 26.10.2015.
 */
public class EntranceFragment extends Fragment
{
    private Button entrance_btn_enter, entrance_btn_register;
    private EditText entrance_et_name, entrance_et_password;
    private TextInputLayout entrance_til_name, entrance_til_password;
    private ImageView iv_blackbrain, iv_whitebrain;

    private ImageTask imageTask;

    private MapListFragment mapListFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_entrance, null);

        mapListFragment = new MapListFragment();

        iv_blackbrain = (ImageView) v.findViewById(R.id.iv_blackbrain);
        iv_whitebrain = (ImageView) v.findViewById(R.id.iv_whitebrain);

        imageTask = new ImageTask(getActivity(), iv_blackbrain, iv_whitebrain);
        imageTask.execute();

        entrance_til_name = (TextInputLayout) v.findViewById(R.id.entrance_til_name);
        entrance_til_password = (TextInputLayout) v.findViewById(R.id.entrance_til_password);

        entrance_et_name = (EditText) v.findViewById(R.id.entrance_et_name);
        entrance_et_password = (EditText) v.findViewById(R.id.entrance_et_password);

        entrance_til_name.setHint(getString(R.string.hint_name));
        entrance_til_password.setHint(getString(R.string.hint_password));

        entrance_btn_enter = (Button) v.findViewById(R.id.entrance_btn_enter);
        entrance_btn_register = (Button) v.findViewById(R.id.entrance_btn_register);

        entrance_btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getInstance().init(getActivity(), entrance_et_name.getText().toString());
                String name = PreferenceManager.getInstance().getString(Constants.NAME_KEY);
                String password = PreferenceManager.getInstance().getString(Constants.PASSWORD_KEY);
                if(entrance_et_name.getText().toString().equals(name)
                        && entrance_et_password.getText().toString().equals(password))
                {
                    FragmentController.getInstance().replace(R.id.main_fl_container, EntranceFragment.this, mapListFragment, true);
                }
                else
                {
                    Toast.makeText(getActivity(),
                            R.string.invalid_combination,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        entrance_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getInstance().init(getActivity(), entrance_et_name.getText().toString());
                PreferenceManager.getInstance().addString(Constants.NAME_KEY, entrance_et_name.getText().toString());
                PreferenceManager.getInstance().addString(Constants.PASSWORD_KEY, entrance_et_password.getText().toString());

                entrance_et_name.setText("");
                entrance_et_password.setText("");

                Toast.makeText(getActivity(),
                        R.string.name_and_password_saved,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }




}
