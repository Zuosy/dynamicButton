package com.zuosy.uilayouttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
    private String TAG = "ShowActivity";
    private LinearLayout layout;
    private String buttonName = "button";
    private ArrayList<Button> buttons = new ArrayList<>();

    private LinearLayout buttonLayout;
    private ArrayList<LinearLayout> layouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        layout = (LinearLayout)findViewById(R.id.lineLayout);

        // 生成水平滚动条
        HorizontalScrollView scrollView = new HorizontalScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        scrollView.setScrollBarSize(50);

        // 生成button的布局
        buttonLayout = new LinearLayout(this);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        layout.addView(scrollView);
        scrollView.addView(buttonLayout);
    }

    public void addButton(View view) {

        Button btn = new Button(this);
        btn.setText(buttonName);
        btn.setTextSize(30);
        btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        buttons.add(btn);

        Log.d(TAG, String.format("button[%d], layout[%d]",
                buttons.size(), layouts.size()));
        if (buttons.size() > layouts.size() * 5) {
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));

            layouts.add(layout);
            buttonLayout.addView(layout);
        }

        layouts.get(layouts.size() - 1).addView(btn);
//        buttonLayout.addView(btn);
    }

    public void removeButton(View view) {
        if (buttons.isEmpty()) {
            Toast.makeText(this, "no button to remove", Toast.LENGTH_LONG).show();
        } else {
            int index = buttons.size() - 1;
            layouts.get(layouts.size() - 1).removeView(buttons.remove(index));
            if ((buttons.size() + 5) <=
                (layouts.size() * 5)) {
                layouts.remove(layouts.size() - 1);
            }
        }
    }

    public void renameButton(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, 0XFFFF);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != 0XFFFF) {
            Toast.makeText(this, "ERROR REQUESTCODE", Toast.LENGTH_LONG).show();
            return;
        }
        if (resultCode != RESULT_OK) {
            Toast.makeText(this, "RESULT ERROR", Toast.LENGTH_LONG).show();
            return ;
        }
        if (data == null) {
            Toast.makeText(this, "null Intent", Toast.LENGTH_LONG).show();
            return;
        }
        buttonName = data.getStringExtra("name");
        for (Button btn : buttons) {
            btn.setText(buttonName);
        }
    }
}
