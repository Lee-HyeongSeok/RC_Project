package com.example.bluetoothproject;

/*
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private ListView list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        list = (ListView)findViewById(R.id.listView);

        List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);



//        ListView listView = (ListView) this.findViewById(R.id.listView);
//        Intrinsics.checkExpressionValueIsNotNull(listView, "listView");
//        listView.setAdapter((ListAdapter) (new SettingActivity.MyAdapter((Context) this)));
    }

    private static final class MyAdapter extends BaseAdapter {
        private final Context mContext;
        private String[] SettingValue;
        private String[] InfoValue;
        @NotNull
        public View getView(int position, @org.jetbrains.annotations.Nullable View view, @org.jetbrains.annotations.Nullable ViewGroup viewGroup) {
            LayoutInflater layoutInflater = LayoutInflater.from(this.mContext);
            View settingMain = layoutInflater.inflate(R.layout.activity_setting, viewGroup, false);

            TextView nameTextView = settingMain.findViewById(R.id.name_textview);
            nameTextView.setText(SettingValue[position]);

            TextView nameTextView2 = settingMain.findViewById(R.id.name_textview2);
            nameTextView2.setText(InfoValue[position]);
            return settingMain;
        }

        public Object getItem(int position) {
            String selectItem = this.SettingValue[position];
            return selectItem;
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getCount() {
            return this.SettingValue.length;
        }

        public MyAdapter(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.SettingValue = new String[]{"프로필 정보", "연동된식물 리스트", "플랜트엔진 메뉴얼", "라이센스 정보", "개인정보 취급방침", "이용약관", "로그아웃"};
            this.InfoValue = new String[]{"프로필 정보를 확인합니다", "앱과 연동된 식물을 보여줍니다", "앱의 사용 설명서를 보여줍니다", "앱의 라이센스 정보를 보여줍니다", "개인정보 취급방침을 보여줍니다", "이용약관을 보여줍니다", "로그아웃을 합니다"};
            this.mContext = context;
        }
    }
}

 */