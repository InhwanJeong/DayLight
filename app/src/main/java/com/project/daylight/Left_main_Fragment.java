package com.project.daylight;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * created by inhwan Jeong on 4/12/19.
 */

public class Left_main_Fragment extends Fragment {
    TextView version_tv;
    TextView notification_tv;
    TextView donation_tv;
    TextView theme_tv;
    TextView bug_tv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.left_main, container,false);
        //activity_main.xml2이 인플레이트 되고 자바 소스와 연결됨

        version_tv = rootView.findViewById(R.id.version_tv);
        notification_tv = rootView.findViewById(R.id.notification_tv);
        donation_tv = rootView.findViewById(R.id.donation_tv);
        theme_tv = rootView.findViewById(R.id.theme_tv);
        bug_tv = rootView.findViewById(R.id.bug_tv);

        version_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup("Version", "Now version: -1.0.0");
            }
        });
        notification_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(rootView.getContext(), "설정해야함", Toast.LENGTH_SHORT).show();
            }
        });
        donation_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup("Thank you for a cup of coffee.", "우리은행 1002-352-639226");
            }
        });
        theme_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(rootView.getContext(), "설정해야함", Toast.LENGTH_SHORT).show();
            }
        });
        bug_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bugReport();
            }
        });

        return rootView;
    }

    protected void showPopup(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(title).setMessage(msg);

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }

    protected void bugReport(){
        String msg = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Bug report");
        final EditText et = new EditText(getContext());
        et.setPadding(10, 15, 15, 10);
        et.setHint(" Write Bug message here!");
        builder.setView(et);

        builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String msg = et.getText().toString();
                Toast.makeText(getContext(), "[Error]We don't have this kind of bug.\nmsg: " + msg, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();

        alertDialog.show();
    }
}
