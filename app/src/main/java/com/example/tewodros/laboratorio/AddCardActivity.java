package com.example.tewodros.laboratorio;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Question = ((EditText) findViewById(R.id.Question)).getText().toString();
                String Answer = ((EditText) findViewById(R.id.Answer)).getText().toString();
                Intent data = new Intent();
                data.putExtra("string1", Question);
                data.putExtra("string2", Answer);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
