package mindmap.selestar.com.mindmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ASTER-NOTUS on 26.10.2015.
 */
public class EntranceActivity extends Activity
{

    private Button entrance_btn_enter, entrance_btn_register;
    private EditText entrance_et_name, entrance_et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);

        entrance_et_name = (EditText) findViewById(R.id.entrance_et_name);
        entrance_et_password = (EditText) findViewById(R.id.entrance_et_password);

        entrance_btn_enter = (Button) findViewById(R.id.entrance_btn_enter);
        entrance_btn_register = (Button) findViewById(R.id.entrance_btn_register);

        entrance_btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getInstance().init(EntranceActivity.this, entrance_et_name.getText().toString());
                String name = PreferenceManager.getInstance().getString(Constants.NAME_KEY);
                String password = PreferenceManager.getInstance().getString(Constants.PASSWORD_KEY);
                if(entrance_et_name.getText().toString().equals(name)
                        && entrance_et_password.getText().toString().equals(password))
                {
                    EntranceActivity.this.startActivity(new Intent(EntranceActivity.this, ProfileActivity.class));
                }
                else
                {
                    Toast.makeText(EntranceActivity.this,
                            "Sorry, but name/password combination is invalid!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        entrance_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getInstance().init(EntranceActivity.this, entrance_et_name.getText().toString());
                PreferenceManager.getInstance().addString(Constants.NAME_KEY, entrance_et_name.getText().toString());
                PreferenceManager.getInstance().addString(Constants.PASSWORD_KEY, entrance_et_password.getText().toString());

                entrance_et_name.setText("");
                entrance_et_password.setText("");

                Toast.makeText(EntranceActivity.this,
                        "Your name and password were saved!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


}
