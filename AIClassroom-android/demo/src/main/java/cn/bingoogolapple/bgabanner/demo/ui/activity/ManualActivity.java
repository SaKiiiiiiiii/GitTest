package cn.bingoogolapple.bgabanner.demo.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.suke.widget.SwitchButton;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import android.view.WindowManager;
import android.view.*;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.demo.App;
import cn.bingoogolapple.bgabanner.demo.R;
import cn.bingoogolapple.bgabanner.demo.engine.Engine;


/**
 手动控制
 */
public class ManualActivity extends AppCompatActivity {

    private Engine mEngine;

    private static final String TAG = "ManualActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setTitle("Manual");
        mEngine = App.getInstance().getEngine();

        SwitchButton lightButton = (SwitchButton) findViewById(R.id.light_button);
        SwitchButton windowButton = (SwitchButton) findViewById(R.id.window_button);
        ImageButton airButton = (ImageButton) findViewById(R.id.airButton);
        Button in = (Button)findViewById(R.id.increaseButton);
        Button de = (Button)findViewById(R.id.decreaseButton);
        lightButton.setChecked(false);
        windowButton.setChecked(false);
//        switchButton.isChecked();
//        switchButton.toggle();     //switch state
//        switchButton.toggle(false);//switch without animation
//        switchButton.setShadowEffect(true);//disable shadow effect
//        switchButton.setEnabled(false);//disable button
//        switchButton.setEnableEffect(false);//disable the switch animation



        //监听灯按钮
        lightButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "开灯", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "关灯", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //监听窗户按钮
        windowButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                //TODO do your job
                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "开窗", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "关窗", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //监听空调开关
        airButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在当前方法onClick中监听
                TextView temperature = (TextView)findViewById(R.id.temperature);
                TextView c = (TextView)findViewById(R.id.C);
                Button de = (Button) findViewById(R.id.decreaseButton);
                Button in = (Button) findViewById(R.id.increaseButton);
                if (temperature.getVisibility()==View.VISIBLE) {
                    Toast.makeText(getApplicationContext(), "空调已关闭", Toast.LENGTH_SHORT).show();
                    temperature.setVisibility(View.INVISIBLE);
                    c.setVisibility(View.INVISIBLE);
                    de.setVisibility(View.INVISIBLE);
                    in.setVisibility(View.INVISIBLE);
                }
                else {
                    Toast.makeText(getApplicationContext(), "空调已开启", Toast.LENGTH_SHORT).show();
                    temperature.setVisibility(View.VISIBLE);
                    c.setVisibility(View.VISIBLE);
                    de.setVisibility(View.VISIBLE);
                    in.setVisibility(View.VISIBLE);
                }



            }
        });




        //空调升温
        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在当前方法onClick中监听
                TextView temperature = (TextView)findViewById(R.id.temperature);
                if (temperature.getVisibility()==View.VISIBLE) {
                    int t = Integer.parseInt(temperature.getText().toString());
//                    Toast.makeText(getApplicationContext(), "升温", Toast.LENGTH_SHORT).show();
                    if(t<28) {
                        t = t + 1;
                        temperature.setText(String.valueOf(t));
                    }
                }



            }
        });

        //空调降温
        de.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在当前方法onClick中监听
                TextView temperature = (TextView)findViewById(R.id.temperature);
                if (temperature.getVisibility()==View.VISIBLE) {
                    int t = Integer.parseInt(temperature.getText().toString());
//                    Toast.makeText(getApplicationContext(), "降温", Toast.LENGTH_SHORT).show();
                    if(t>16) {
                        t = t - 1;
                        temperature.setText(String.valueOf(t));
                    }
                }



            }
        });






    }






}
