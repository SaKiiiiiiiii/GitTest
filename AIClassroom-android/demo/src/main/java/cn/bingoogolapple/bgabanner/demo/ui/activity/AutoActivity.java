package cn.bingoogolapple.bgabanner.demo.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import com.suke.widget.SwitchButton;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
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
public class AutoActivity extends AppCompatActivity {

    private Engine mEngine;


    private static final String TAG = "AutoActivity";

    private Boolean b11_flag = false;
    private Boolean b12_flag = false;
    private Boolean b13_flag = false;
    private Boolean b21_flag = false;
    private Boolean b22_flag = false;
    private Boolean b23_flag = false;
    private Boolean b31_flag = false;
    private Boolean b32_flag = false;
    private Boolean b33_flag = false;
    private int temp;
    private int light;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setTitle("Auto");
        mEngine = App.getInstance().getEngine();
//
//
//
        Button b11 = (Button) findViewById(R.id.b11);
        b11.setOnClickListener(new View.OnClickListener() {
            Button b11 = (Button) findViewById(R.id.b11);
            @Override
            public void onClick(View view) {
                if (!b11_flag) {
                    b11_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b11.setBackgroundColor(Color.BLACK);
                } else {
                    b11_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b11.setBackgroundColor(Color.WHITE);
                }
            }
        });


        Button b12 = (Button) findViewById(R.id.b12);
        b12.setOnClickListener(new View.OnClickListener() {
            Button b12 = (Button) findViewById(R.id.b12);
            @Override
            public void onClick(View view) {
                if (!b12_flag) {
                    b12_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b12.setBackgroundColor(Color.BLACK);
                } else {
                    b12_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b12.setBackgroundColor(Color.WHITE);
                }
            }
        });

        Button b13 = (Button) findViewById(R.id.b13);
        b13.setOnClickListener(new View.OnClickListener() {
            Button b13 = (Button) findViewById(R.id.b13);
            @Override
            public void onClick(View view) {
                if (!b13_flag) {
                    b13_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b13.setBackgroundColor(Color.BLACK);
                } else {
                    b13_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b13.setBackgroundColor(Color.WHITE);
                }
            }
        });

        Button b21 = (Button) findViewById(R.id.b21);
        b21.setOnClickListener(new View.OnClickListener() {
            Button b21 = (Button) findViewById(R.id.b21);
            @Override
            public void onClick(View view) {
                if (!b21_flag) {
                    b21_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b21.setBackgroundColor(Color.BLACK);
                } else {
                    b21_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b21.setBackgroundColor(Color.WHITE);
                }
            }
        });

        Button b22 = (Button) findViewById(R.id.b22);
        b22.setOnClickListener(new View.OnClickListener() {
            Button b22 = (Button) findViewById(R.id.b22);
            @Override
            public void onClick(View view) {
                if (!b22_flag) {
                    b22_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b22.setBackgroundColor(Color.BLACK);
                } else {
                    b22_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b22.setBackgroundColor(Color.WHITE);
                }
            }
        });

        Button b23 = (Button) findViewById(R.id.b23);
        b23.setOnClickListener(new View.OnClickListener() {
            Button b23 = (Button) findViewById(R.id.b23);
            @Override
            public void onClick(View view) {
                if (!b23_flag) {
                    b23_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b23.setBackgroundColor(Color.BLACK);
                } else {
                    b23_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b23.setBackgroundColor(Color.WHITE);
                }
            }
        });

        Button b31 = (Button) findViewById(R.id.b31);
        b31.setOnClickListener(new View.OnClickListener() {
            Button b31 = (Button) findViewById(R.id.b31);
            @Override
            public void onClick(View view) {
                if (!b31_flag) {
                    b31_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b31.setBackgroundColor(Color.BLACK);
                } else {
                    b31_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b31.setBackgroundColor(Color.WHITE);
                }
            }
        });

        Button b32 = (Button) findViewById(R.id.b32);
        b32.setOnClickListener(new View.OnClickListener() {
            Button b32 = (Button) findViewById(R.id.b32);
            @Override
            public void onClick(View view) {
                if (!b32_flag) {
                    b32_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b32.setBackgroundColor(Color.BLACK);
                } else {
                    b32_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b32.setBackgroundColor(Color.WHITE);
                }
            }
        });

        Button b33 = (Button) findViewById(R.id.b33);
        b33.setOnClickListener(new View.OnClickListener() {
            Button b33 = (Button) findViewById(R.id.b33);
            @Override
            public void onClick(View view) {
                if (!b33_flag) {
                    b33_flag = true;
                    //设置是否被激活状态，true表示被激活
                    b33.setBackgroundColor(Color.BLACK);
                } else {
                    b33_flag = false;
                    //设置是否被激活状态，false表示未激活
                    b33.setBackgroundColor(Color.WHITE);
                }
            }
        });




        SeekBar light_bar = (SeekBar) findViewById(R.id.light_bar);
        light_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView light_text = (TextView) findViewById(R.id.light_text);
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                light_text.setText(progress + "%");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar temp_bar = (SeekBar) findViewById(R.id.temp_bar);
        temp_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            TextView temp_text = (TextView) findViewById(R.id.temp_text);
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int temp = progress/2 - 10;
                temp_text.setText(temp + "℃");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });










        Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView light_text = (TextView) findViewById(R.id.light_text);
                TextView temp_text = (TextView) findViewById(R.id.temp_text);
                String light = light_text.getText().toString();
                String temp = temp_text.getText().toString();
                System.out.println("光照强度设定：" + light);
                System.out.println("室内温度设定：" + temp);


                Boolean[] stu = returnStu();
                System.out.println("学生分布情况：");
                for(int i=0;i<9;i++)
                    System.out.println("第" + (i+1) + "片位置是否有人："+stu[i]);

                Toast.makeText(getApplicationContext(), "已提交，具体见控制台", Toast.LENGTH_SHORT).show();
            }
        });



        Button st = (Button) findViewById(R.id.start_teaching);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity(new Intent(AutoActivity.this, TeachActivity.class));
            }
        });






    }

    public Boolean[] returnStu(){
        Boolean[] stus = new Boolean[9];
        stus[0] = b11_flag;
        stus[1] = b12_flag;
        stus[2] = b13_flag;
        stus[3] = b21_flag;
        stus[4] = b22_flag;
        stus[5] = b23_flag;
        stus[6] = b31_flag;
        stus[7] = b32_flag;
        stus[8] = b33_flag;
        return stus;
    }



}
