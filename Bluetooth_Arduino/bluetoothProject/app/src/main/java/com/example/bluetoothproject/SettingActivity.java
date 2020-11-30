package com.example.bluetoothproject;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        list = (ListView)findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(getApplicationContext());
        list.setAdapter(adapter);



        //ListView listView = (ListView) this.findViewById(R.id.listView);
        //listView.setAdapter((ListAdapter) (new SettingActivity.MyAdapter((Context) this)));
    }

    private static final class MyAdapter extends BaseAdapter {
        Context mContext = null;
        private String[] SettingValue = {"프로필 정보", "연동된식물 리스트", "플랜트엔진 메뉴얼", "라이센스 정보", "개인정보 취급방침", "이용약관", "로그아웃"};
        //private String[] InfoValue = {"프로필 정보를 확인합니다", "앱과 연동된 식물을 보여줍니다", "앱의 사용 설명서를 보여줍니다", "앱의 라이센스 정보를 보여줍니다", "개인정보 취급방침을 보여줍니다", "이용약관을 보여줍니다", "로그아웃을 합니다"};


        public View getView(int position, View view, ViewGroup viewGroup) {

            TextView setView = new TextView(mContext);
            setView.setText(SettingValue[position]);
            return setView;
        }

        public Object getItem(int position) {
            String selectItem = SettingValue[position];
            return selectItem;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getCount() {
            return SettingValue.length;
        }

        public MyAdapter(Context context) {
             mContext = context;
        }
    }
}

