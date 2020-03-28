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

        ((TextView) findViewById(R.id.edit_question)).setText(s1);
        ((TextView) findViewById(R.id.edit_answer)).setText(s2);
        ((TextView) findViewById(R.id.edit_wronganswer1)).setText(s3);
        ((TextView) findViewById(R.id.edit_wronganswer2)).setText(s4);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = ((EditText) findViewById(R.id.edit_question)).getText().toString();
                String answer = ((EditText) findViewById(R.id.edit_answer)).getText().toString();
                String wronganswer1 = ((EditText) findViewById(R.id.edit_wronganswer1)).getText().toString();
                String wronganswer2 = ((EditText) findViewById(R.id.edit_wronganswer2)).getText().toString();

                Intent data = new Intent();
                data.putExtra("string1", question);
                data.putExtra("string2", answer);
                data.putExtra("string3", wronganswer1);
                data.putExtra("string4", wronganswer2);
                setResult(RESULT_OK, data);
                if (question.isEmpty() || answer.isEmpty() || wronganswer1.isEmpty() || wronganswer2.isEmpty()) {
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