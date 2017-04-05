package in.com.horizontalscrollview;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.preview_shop).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.preview_shop:
                start(ScrollingActivity.class);
                break;

        }
    }

    private void start(Class<? extends Activity> token) {
        Intent intent = new Intent(this, token);
        startActivity(intent);
    }
}
