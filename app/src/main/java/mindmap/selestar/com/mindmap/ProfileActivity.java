package mindmap.selestar.com.mindmap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class ProfileActivity extends ActionBarActivity {

    private TextView profile_tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_tv_username = (TextView) findViewById(R.id.profile_tv_username);
        profile_tv_username.setText(PreferenceManager.getInstance().getString(Constants.NAME_KEY));
    }
}
