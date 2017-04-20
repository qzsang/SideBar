package com.example.sidebar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;

public class MainActivity extends AppCompatActivity {

    PinnedSectionListView  listView;
    SideBar sideBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sideBar = (SideBar) findViewById(R.id.side_bar);
        listView = (PinnedSectionListView) findViewById(R.id.pslv_list);
        listView.setShadowVisible(false);
        List<DataBean> dataBeanList = new ArrayList<>();
        for (int i = 0; i < 250;i++) {
            DataBean dataBean = new DataBean("SideBar" + i,i % 10 == 0 ? 1 : 0);
            dataBeanList.add(dataBean);
        }

        ListAdapter listAdapter = new ListAdapter(this,dataBeanList);
        listView.setAdapter(listAdapter);

        List<String> list = new ArrayList<>();
        for (int i = 0;i < 20; i ++) {
            list.add(i +"");
        }
        sideBar.setLetters(list);
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                Log.e("onTouchingLetterChanged", s + "");
            }
        });
    }
}
