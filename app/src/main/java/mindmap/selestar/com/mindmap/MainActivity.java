package mindmap.selestar.com.mindmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

    private Button btn_enter, btn_about, btn_exit;
    private RelativeLayout rl_main, rl_login;

    private Animation dissapearing;

    private Intent intent;

    @Override
    public void onBackPressed() {
        if(rl_main.getVisibility() == View.VISIBLE)
        {
            super.onBackPressed();
        }
        else
        {
            rl_main.setVisibility(View.VISIBLE);
            rl_login.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl_main = (RelativeLayout) findViewById(R.id.rl_main);
        rl_login = (RelativeLayout) findViewById(R.id.rl_login);

        btn_enter = (Button) findViewById(R.id.btn_enter);
        btn_about = (Button) findViewById(R.id.btn_about);
        btn_exit = (Button) findViewById(R.id.btn_exit);


        dissapearing = AnimationUtils.loadAnimation(this, R.anim.dissapearing);
        dissapearing.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rl_main.setVisibility(View.INVISIBLE);
                rl_login.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rl_main.startAnimation(dissapearing);
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.finish();
            }
        });
    }


}
