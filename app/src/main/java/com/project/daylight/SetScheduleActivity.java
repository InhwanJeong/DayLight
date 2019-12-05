package com.project.daylight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;

public class SetScheduleActivity extends AppCompatActivity {
    int num = 1;

    // 입력받은 에디트 텍스트뷰 값 저장하는 메소드
    void setSchedule(String str, EditText tv)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        myRef = database.getReference("user/schedule/event"+ num + "/" + str);
        myRef.setValue(tv.getText() + "");
    }

    // DatePicker 로 고른 날짜 저장하는 메소드 ( 오버라이딩 )
    void setSchedule(String str, DatePicker datePicker)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        myRef = database.getReference("user/schedule/event"+ num + "/" + str);
        myRef.setValue(datePicker.getYear() + "" + (datePicker.getMonth()+1) + "" + datePicker.getDayOfMonth());
    }

    // TimePicker 로 고른 날짜 저장하는 메소드 ( 오버라이딩 )
    void setSchedule(String str, TimePicker timePicker)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef;
        myRef = database.getReference("user/schedule/event"+ num + "/" + str);
        myRef.setValue(timePicker.getHour() + "" + timePicker.getMinute());
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str;
            EditText title, category, gift, location, memo;
            DatePicker datePicker;
            TimePicker timePicker;

            title = (EditText) findViewById(R.id.title);
            category = (EditText) findViewById(R.id.category);
            gift = (EditText) findViewById(R.id.gift);
            location = (EditText) findViewById(R.id.location);
            memo = (EditText) findViewById(R.id.memo);
            datePicker = (DatePicker)findViewById(R.id.datePicker);
            timePicker = (TimePicker)findViewById(R.id.timePicker);

            setSchedule("title", title);
            setSchedule("category", category);
            setSchedule("gift", gift);
            setSchedule("location", location);
            setSchedule("memo", memo);
            setSchedule("date", datePicker);
            setSchedule("time", timePicker);

            //str = title.getText() + " / " + category.getText() + " / " + gift.getText() + " / " +
            //        location.getText() + " / " + memo.getText();

            //makeText(SetScheduleActivity.this, str, LENGTH_SHORT).show();
            //Toast.makeText(SetScheduleActivity.this, datePicker.getYear() + " 년 " +
            //        (datePicker.getMonth()+1) + " 월 " + datePicker.getDayOfMonth() + " 일 입니다.", LENGTH_SHORT).show();
            //makeText(SetScheduleActivity.this, timePicker.getHour() + " 시 " + timePicker.getMinute() + " 분 ", LENGTH_SHORT).show();
            //myRef.setValue(str);

            num++;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_schedule_main);
        setCustomActionbar();

        Button add_btn;

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(listener);
    }

    private void setCustomActionbar() {
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
        actionBar.setCustomView(mCustomView);

        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomView, params);

        textColor(findViewById(R.id.datePicker), Color.WHITE);
        textColor(findViewById(R.id.timePicker), Color.WHITE);

    }

    void numberPickerTextColor(NumberPicker $v, int $c) {
        for (int i = 0, j = $v.getChildCount(); i < j; i++) {
            View t0 = $v.getChildAt(i);
            if (t0 instanceof EditText) {
                try {
                    Field t1 = $v.getClass().getDeclaredField("mSelectorWheelPaint");
                    t1.setAccessible(true);
                    ((Paint) t1.get($v)).setColor($c);
                    ((EditText) t0).setTextColor($c);
                    $v.invalidate();
                } catch (Exception e) {
                }
            }
        }
    }


    void textColor(View $v, int $color) {
        if ($v instanceof NumberPicker) numberPickerTextColor((NumberPicker) $v, $color);
        else if ($v instanceof TextView) {
            ((TextView) $v).setTextColor($color);
            $v.invalidate();
        } else if ($v instanceof ViewGroup) {
            ViewGroup t0 = (android.view.ViewGroup) $v;
            for (int i = 0, j = t0.getChildCount(); i < j; i++)
                textColor( t0.getChildAt(i), $color );
        }
    }
}