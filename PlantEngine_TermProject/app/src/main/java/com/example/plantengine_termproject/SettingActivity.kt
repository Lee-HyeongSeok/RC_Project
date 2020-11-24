package com.example.plantengine_termproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        var listView = findViewById<ListView>(R.id.listView)
        listView.adapter = MyAdapter(this)
    }

    private class MyAdapter(context: Context) : BaseAdapter(){
        private val mContext: Context
        private var SettingValue = arrayOf<String>("프로필 정보", "연동된식물 리스트", "플랜트엔진 메뉴얼", "라이센스 정보", "개인정보 취급방침", "이용약관", "로그아웃")
        private var InfoValue = arrayOf<String>("프로필 정보를 확인합니다", "앱과 연동된 식물을 보여줍니다", "앱의 사용 설명서를 보여줍니다",
        "앱의 라이센스 정보를 보여줍니다", "개인정보 취급방침을 보여줍니다", "이용약관을 보여줍니다", "로그아웃을 합니다")

        init {
            mContext = context
        }
        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
            val layoutInflater = LayoutInflater.from(mContext)
            val settingMain = layoutInflater.inflate(R.layout.activity_listviewitem, viewGroup, false)

            val nameTextView = settingMain.findViewById<TextView>(R.id.name_textview)
            nameTextView.text = SettingValue.get(position)
            val nameTextView2 = settingMain.findViewById<TextView>(R.id.name_textview2)
            nameTextView2.text = InfoValue.get(position)
            return settingMain
        }

        override fun getItem(position: Int): Any {
            val selectItem = SettingValue.get(position)
            return selectItem
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return SettingValue.size
        }
    }
}