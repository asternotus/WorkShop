package mindmap.selestar.com.mindmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button btn_enter, btn_about, btn_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_enter = (Button) findViewById(R.id.btn_enter);
        btn_about = (Button) findViewById(R.id.btn_about);
        btn_exit = (Button) findViewById(R.id.btn_exit);


        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MainActivity.this.startActivity(new Intent(MainActivity.this, EntranceActivity.class));
            }
        });

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.this.startActivity(new Intent(MainActivity.this, AboutActivity.class));
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
