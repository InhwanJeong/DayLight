package com.example.daylight;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intent intent = new Intent(MainActivity.this, ListDemoActivity.class);
        //startActivity(intent);

        ViewPager pager = findViewById(R.id.viewpager);
        // 미리 캐싱 - 준비 해놓을 프레그먼트 개수
        pager.setOffscreenPageLimit(3);

        // 프래그먼트 매니저 생성과 동시에 어댑터에 할당 즉, 매니저=어댑터
        PagerAdapter pa = new PagerAdapter(getSupportFragmentManager());
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        Center_main_Fragment cmf = new Center_main_Fragment();
        Right_main_Fragment rmf = new Right_main_Fragment();
        Left_main_Fragment lmf = new Left_main_Fragment();
        pa.addItem(lmf);
        pa.addItem(cmf);
        pa.addItem(rmf);

        pager.setAdapter(pa);
        pager.setCurrentItem(1);
        tabs.setupWithViewPager(pager);


    }
    // 프레그먼트 커스텀 어댑터
    class PagerAdapter extends FragmentStatePagerAdapter{
        //프래그먼트가 들어갈 어레이 리스트
        ArrayList<Fragment> page = new ArrayList<Fragment>();
        public PagerAdapter(FragmentManager fm){
            super(fm);
        }
        public void addItem(Fragment _item){
            page.add(_item);
        }
        @Override
        public Fragment getItem(int position) {
            return page.get(position);
        }
        @Override
        public int getCount() {
            return page.size();
        }
    }


}
