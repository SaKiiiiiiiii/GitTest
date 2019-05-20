package cn.bingoogolapple.bgabanner.demo.ui.activity;
import android.view.WindowManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import cn.bingoogolapple.bgabanner.demo.App;
import cn.bingoogolapple.bgabanner.demo.R;
import cn.bingoogolapple.bgabanner.demo.engine.Engine;



public class MainActivity extends AppCompatActivity {


    private Engine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setTitle("智慧教室");
        mEngine = App.getInstance().getEngine();

    }


    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_main_auto:
                startActivity(new Intent(this, AutoActivity.class));
                break;
            case R.id.tv_main_manual:
                startActivity(new Intent(this, ManualActivity.class));
                break;

            default:
                break;
        }
    }
}
