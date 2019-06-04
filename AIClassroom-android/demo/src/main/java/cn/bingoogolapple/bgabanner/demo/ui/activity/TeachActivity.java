package cn.bingoogolapple.bgabanner.demo.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import cn.bingoogolapple.bgabanner.demo.App;
import cn.bingoogolapple.bgabanner.demo.R;
import cn.bingoogolapple.bgabanner.demo.engine.Engine;

public class TeachActivity extends AppCompatActivity {
    private Engine mEngine;
    private Boolean flag = false;
    private TextView mTvMsg;

    private static final String TAG = "TeachActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setTitle("Teach");
        mEngine = App.getInstance().getEngine();


        initView();

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
                    new Thread(checkThread).start();
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
        Button check = (Button)findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(postThread).start();
            }
        });


    }






    private void initView(){
        mTvMsg = (TextView) findViewById(R.id.status);


    }


    private Thread postThread = new Thread(){
        public void run() {
//            Toast.makeText(getApplicationContext(), "开启线程", Toast.LENGTH_SHORT).show();
            HttpURLConnection conn=null;
            BufferedReader br=null;
            String encoding = "UTF-8";
            try {
                URL url=new URL("http://10.0.2.2:8000/post/");
                conn= (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(5000);
                conn.setDoOutput(true);//设置允许输出
                conn.setRequestMethod("POST");
//                conn.setRequestProperty("User-Agent", "Fiddler");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Charset", encoding);

                JSONObject ClientKey = new JSONObject();
                ClientKey.put("a", "1");
                ClientKey.put("b", "2");

                setContent("123456");
                /*封装Person数组*/
                JSONObject params = new JSONObject();
                params.put("Data", ClientKey);
                /*把JSON数据转换成String类型使用输出流向服务器写*/
                String content = String.valueOf(params);


                OutputStream os = conn.getOutputStream();
                os.write(content.getBytes());
                os.close();
                setContent("正在检测人脸");
                SystemClock.sleep(3000);
                setContent("将视频分割成帧\n共提取18帧\n正在识别请稍等……");
                /*服务器返回的响应码*/
                int code = conn.getResponseCode();
                if(code == 200)
                {
//                    setContent("数据提交成功");
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                    String retData = null;
                    String responseData = "";
                    while((retData = in.readLine()) != null)
                    {
                        responseData += retData + "\n\n";
                    }
//                    JSONObject jsonObject = new JSONObject(responseData);
//                    JSONObject succObject = jsonObject.getJSONObject("regsucc");
                    //System.out.println(result);
//                    String success = succObject.getString("number");
                    setContent(responseData);
                    in.close();
                }
                else
                {
                    System.out.println("数据提交失败");
                }

//                InputStream in=conn.getInputStream();
//                br=new BufferedReader(new InputStreamReader(in));
//
//                StringBuilder sb=new StringBuilder();
//                String s;
//                while((s = br.readLine())!=null){
//                    sb.append(s);
//                }
//                setContent(sb.toString());
//                Log.d("123","---"+sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("haha",e.getMessage());
            }finally {
                if (conn!=null){
                    conn.disconnect();
                }
                if (br!=null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    };





    private Thread checkThread = new Thread(){
        public void run() {
//            Toast.makeText(getApplicationContext(), "开启线程", Toast.LENGTH_SHORT).show();
            HttpURLConnection conn=null;
            BufferedReader br=null;
            String encoding = "UTF-8";
            try {
                URL url=new URL("http://10.0.2.2:8000/check/");
                conn= (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(5000);
                conn.setDoOutput(true);//设置允许输出
                conn.setRequestMethod("POST");
//                conn.setRequestProperty("User-Agent", "Fiddler");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Charset", encoding);

                JSONObject ClientKey = new JSONObject();
                ClientKey.put("a", "1");
                ClientKey.put("b", "2");

                setContent("123456");
                /*封装Person数组*/
                JSONObject params = new JSONObject();
                params.put("Data", ClientKey);
                /*把JSON数据转换成String类型使用输出流向服务器写*/
                String content = String.valueOf(params);


                OutputStream os = conn.getOutputStream();
                os.write(content.getBytes());
                os.close();
                setContent("正在点到");
                SystemClock.sleep(3000);
                setContent("正在识别人脸\n请稍等……");
                /*服务器返回的响应码*/
                int code = conn.getResponseCode();
                if(code == 200)
                {
//                    setContent("数据提交成功");
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), encoding));
                    String retData = null;
                    String responseData = "";
                    while((retData = in.readLine()) != null)
                    {
                        responseData += retData + '\n';
                    }
//                    JSONObject jsonObject = new JSONObject(responseData);
//                    JSONObject succObject = jsonObject.getJSONObject("regsucc");
                    //System.out.println(result);
//                    String success = succObject.getString("number");
                    setContent(responseData);
                    in.close();
                }
                else
                {
                    System.out.println("数据提交失败");
                }

//                InputStream in=conn.getInputStream();
//                br=new BufferedReader(new InputStreamReader(in));
//
//                StringBuilder sb=new StringBuilder();
//                String s;
//                while((s = br.readLine())!=null){
//                    sb.append(s);
//                }
//                setContent(sb.toString());
//                Log.d("123","---"+sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("haha",e.getMessage());
            }finally {
                if (conn!=null){
                    conn.disconnect();
                }
                if (br!=null){
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
        }
    };















    public void setContent(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTvMsg.setText(s);
                Log.d("haha",s);
            }
        });
    }
}
