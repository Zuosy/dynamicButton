package com.zuosy.uilayouttest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button submit;
    private EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("输入新的名称");
        submit = (Button) findViewById(R.id.submit);
        name = (EditText) findViewById(R.id.name);
    }

    public void submitOnClickListener(View view) {
        Intent intent = new Intent();
        String name = this.name.getText().toString();
        intent.putExtra("name", name);
        setResult(RESULT_OK, intent);
        finish();
    }
}
