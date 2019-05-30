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

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        Button sb = (Button)findViewById(R.id.submitM);
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

        //提交
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(postThread).start();


            }
        });




    }






    private Thread postThread = new Thread() {
        public void run() {
            //在当前方法onClick中监听
            TextView temperature = (TextView)findViewById(R.id.temperature);
            SwitchButton lightButton = (SwitchButton) findViewById(R.id.light_button);
            SwitchButton windowButton = (SwitchButton) findViewById(R.id.window_button);
            ImageButton airButton = (ImageButton) findViewById(R.id.airButton);
            String light;
            String window;
            String aircon;
            int temp;


            if(lightButton.isChecked())
                light = "On";
            else
                light = "Off";

            if(windowButton.isChecked())
                window = "Open";
            else
                window = "Close";



            if (temperature.getVisibility()==View.VISIBLE) {
                aircon = "On";
                temp = Integer.parseInt(temperature.getText().toString());
//                    Toast.makeText(getApplicationContext(), "降温", Toast.LENGTH_SHORT).show();

            }
            else {
                aircon = "Off";
                temp = 0;
            }


            HttpURLConnection conn = null;
            BufferedReader br = null;
            String encoding = "UTF-8";
            try {
                URL url = new URL("http://10.0.2.2:8000/state/");
                conn = (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(500);
                conn.setDoOutput(true);//设置允许输出
                conn.setRequestMethod("POST");
//                conn.setRequestProperty("User-Agent", "Fiddler");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Charset", encoding);

                JSONObject ClientKey = new JSONObject();
                ClientKey.put("light", light);
                ClientKey.put("window", window);
                ClientKey.put("aircon", aircon);
                ClientKey.put("temp", temp);







                /*封装Person数组*/
                JSONObject params = new JSONObject();
                params.put("Data", ClientKey);
                /*把JSON数据转换成String类型使用输出流向服务器写*/
                String content = String.valueOf(params);
                System.out.println(content);

                OutputStream os = conn.getOutputStream();
                os.write(content.getBytes());
                os.close();

                /*服务器返回的响应码*/
                int code = conn.getResponseCode();
                if (code == 200) {
                    System.out.println("数据提交成功");
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                    String retData = null;
                    String responseData = "";
                    while ((retData = in.readLine()) != null) {
                        responseData += retData;
                    }
//                    JSONObject jsonObject = new JSONObject(responseData);
//                    JSONObject succObject = jsonObject.getJSONObject("regsucc");
                    //System.out.println(result);
//                    String success = succObject.getString("number");

                    in.close();
                } else {
                    System.out.println("数据提交失败");
                }

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("haha", e.getMessage());
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    };










}
