package com.example.tewodros.laboratorio;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        String s1 = getIntent().getStringExtra("edit1");
        String s2 = getIntent().getStringExtra("edit2");
        String s3 = getIntent().getStringExtra("edit3");
        String s4 = getIntent().getStringExtra("edit4");

        ((TextView) findViewById(R.id.new_question)).setText(s1);
        ((TextView) findViewById(R.id.new_answer1)).setText(s2);
        ((TextView) findViewById(R.id.new_answer2)).setText(s3);
        ((TextView) findViewById(R.id.new_answer3)).setText(s4);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String q1 = ((EditText) findViewById(R.id.new_question)).getText().toString();
                String a1 = ((EditText) findViewById(R.id.new_answer1)).getText().toString();
                String a2 = ((EditText) findViewById(R.id.new_answer2)).getText().toString();
                String a3 = ((EditText) findViewById(R.id.new_answer3)).getText().toString();
                Intent data = new Intent();
                data.putExtra("string1", q1);
                data.putExtra("string2", a1);
                data.putExtra("string3", a2);
                data.putExtra("string4", a3);
                setResult(RESULT_OK, data);
                if (q1.isEmpty() || a1.isEmpty() || a2.isEmpty() || a3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Must enter all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    finish();
                }
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }
}