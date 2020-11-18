package com.example.rc_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String myJSON = null;

    private static final String TAG_RESULTS="result";
    private static final String TAG_NAME= "name";
    private static final String TAG_ID = "id";
    private static final String TAG_PASS = "pass";

    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;
    HashMap<String, String> persons = new HashMap<String, String>();

    ListView list;
    String url ="http://192.168.80.232/PHP_connection.php";

    Button submit_btn;
    EditText input_id;
    EditText input_pass;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         submit_btn = (Button)findViewById(R.id.submit_Btn);
         input_id = (EditText)findViewById(R.id.Edit_id);
         input_pass = (EditText)findViewById(R.id.Edit_pass);
         tv = (TextView)findViewById(R.id.log_text);
         tv.setVisibility(View.GONE);

        personList = new ArrayList<HashMap<String, String>>();
        getData(url); // MySQL에서 데이터를 가져옴

        submit_btn.setOnClickListener(buttonHandler);
    }

    View.OnClickListener buttonHandler = new View.OnClickListener(){
        public void onClick(View v){
            Login();
            String id = input_id.getText().toString();
            String pass = input_pass.getText().toString();
            Toast.makeText(getApplicationContext(), id + "+" + pass, Toast.LENGTH_SHORT).show();

            for(Map.Entry<String, String> et1 : personList.get(0).entrySet()){
                String key = et1.getKey(); // 아이디
                String value = et1.getValue(); // 패스워드
                if(id.equals(key) && pass.equals(value)){
                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();

                    input_id.setVisibility(View.GONE);
                    input_pass.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);

                    submit_btn.setText("로그아웃");

                }
            }

        }
    };
    /*
    protected void showList(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0; i<peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                String name = c.getString(TAG_NAME);
                String id = c.getString(TAG_ID);
                String pass = c.getString(TAG_PASS);

                HashMap<String, String> persons = new HashMap<String, String>();
                persons.put(TAG_NAME, name);
                persons.put(TAG_ID, id);
                persons.put(TAG_PASS, pass);

                personList.add(persons);
            }
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.list_item,
                    new String[]{TAG_NAME, TAG_ID, TAG_PASS},
                    new int[]{R.id.name, R.id.id, R.id.pass}
            );
            list.setAdapter(adapter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

     */
    public void Login(){
        try{
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0; i<peoples.length(); i++){
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String pass = c.getString(TAG_PASS);

                // onCreate() 외부에 선언
                // HashMap<String, String> persons = new HashMap<String, String>();
                // 사용자 아이디를 키, 비밀번호를 값으로 저장
                persons.put(id, pass);

                // onCreate() 외부에 선언
                // ArrayList<HashMap<String, String>> personList;
                personList.add(persons);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String>{
            protected void onPreExecute(){

            }
            protected String doInBackground(String...params){
                StringBuilder sb = new StringBuilder();

                try{
                    String uri = params[0];
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();

                    if(con != null){
                        con.setConnectTimeout(10000);

                        if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                            Log.i("###", "|bggggggggggggggggggggg");
                            String json;

                            while((json = bufferedReader.readLine()) != null){
                                sb.append(json+"\n");
                            }
                            bufferedReader.close();
                        }
                        con.disconnect();
                    }

                }
                catch(Exception e){
                    Log.i("###", "buffer exception");
                    e.printStackTrace();
                    return null;
                }
                return sb.toString().trim();
            }
            protected void onPostExecute(String result){
                super.onPostExecute(result);
                Log.i("###", "|" + result);
                myJSON = result;
                //showList();
                Login();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}