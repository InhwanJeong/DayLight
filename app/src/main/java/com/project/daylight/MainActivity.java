package com.project.daylight;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        pa.addItem(lmf, "settings");
        pa.addItem(cmf, "logo");
        pa.addItem(rmf, "calender");

        pager.setAdapter(pa);
        pager.setCurrentItem(1);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("");
        tabs.getTabAt(0).setIcon(R.drawable.ic_settings);
        tabs.getTabAt(1).setText("Day-Light");
        tabs.getTabAt(2).setText("");
        tabs.getTabAt(2).setIcon(R.drawable.ic_calender);
        createNotificationChannel();
        setMainNotification();
    }

    private void setMainNotification(){
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "DayLight")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("기념일")
                .setContentText("D+12345")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_NO_CLEAR;    //알림이 사라지지 않게 하기
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, notification);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("DayLight", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // 프레그먼트 커스텀 어댑터
    class PagerAdapter extends FragmentStatePagerAdapter{
        //프래그먼트가 들어갈 어레이 리스트
        ArrayList<Fragment> page = new ArrayList<Fragment>();
        ArrayList<String> title = new ArrayList<>();
        public PagerAdapter(FragmentManager fm){
            super(fm);
        }
        public void addItem(Fragment _item, String _title){
            page.add(_item);
            title.add(_title);
        }
        @Override
        public Fragment getItem(int position) {
            return page.get(position);
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position){return title.get(position);}
        @Override
        public int getCount() {
            return page.size();
        }
    }


}