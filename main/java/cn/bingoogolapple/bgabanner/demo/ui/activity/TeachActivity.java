package cn.bingoogolapple.bgabanner.demo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import cn.bingoogolapple.bgabanner.demo.App;
import cn.bingoogolapple.bgabanner.demo.R;
import cn.bingoogolapple.bgabanner.demo.engine.Engine;

public class TeachActivity extends AppCompatActivity {
    private Engine mEngine;
    private Boolean flag = false;
    private static final String TAG = "TeachActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setTitle("Teach");
        mEngine = App.getInstance().getEngine();




        SwitchButton cameraButton = (SwitchButton) findViewById(R.id.camera_button);
        SwitchButton projectorButton = (SwitchButton) findViewById(R.id.projector_button);
        cameraButton.setChecked(false);
        projectorButton.setChecked(false);
        //监听摄像头按钮
        cameraButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "开启摄像头", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "关闭摄像头", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //监听投影仪按钮
        projectorButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "开启投影仪", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "关闭投影仪", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button rest = (Button)findViewById(R.id.rest);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button rest = (Button)findViewById(R.id.rest);
                if(!flag) {
                    flag = true;
                    rest.setText("继续上课");
                } else {
                    flag = false;
                    rest.setText("下课休息");
                }
            }
        });

    }
}
