package com.example.tewodros.laboratorio;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean isShowingAnswers = false;
    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flashcardDatabase = new FlashcardDatabase(getApplicationContext());
        allFlashcards = flashcardDatabase.getAllCards();

        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(0).getAnswer());
            ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(0).getWrongAnswer1());
            ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(0).getWrongAnswer2());
        }

        findViewById(R.id.flashcard_answer1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.my_original, null));
                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        findViewById(R.id.flashcard_answer2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.my_red_color, null));
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.my_original, null));
                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });

        findViewById(R.id.flashcard_answer3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.green, null));
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.my_original, null));
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.my_original, null));
            }
        });

        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
            }
        });

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;
                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if (currentCardDisplayedIndex > allFlashcards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }
                // set the question and answer TextViews with data from the database

                if (allFlashcards.size() != 0) {
                    ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                    ((TextView) findViewById(R.id.flashcard_answer1)).setText(allFlashcards.get(currentCardDisplayedIndex).getAnswer());
                    ((TextView) findViewById(R.id.flashcard_answer2)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer1());
                    ((TextView) findViewById(R.id.flashcard_answer3)).setText(allFlashcards.get(currentCardDisplayedIndex).getWrongAnswer2());
                }
//                ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.invisible);
//                findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
//                findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
//                findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);
//                //isShowingAnswers = false;
//                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.my_original, null));
//                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.my_original, null));
//                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.my_original, null));
            }
        });

        findViewById(R.id.resetBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.my_original, null));
                findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.my_original, null));
                findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.my_original, null));
            }
        });

        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                String value1 = ((TextView) findViewById(R.id.flashcard_question)).getText().toString();
                String value2 = ((TextView) findViewById(R.id.flashcard_answer1)).getText().toString();
                String value3 = ((TextView) findViewById(R.id.flashcard_answer2)).getText().toString();
                String value4 = ((TextView) findViewById(R.id.flashcard_answer3)).getText().toString();
                intent.putExtra("edit1", value1);
                intent.putExtra("edit2", value2);
                intent.putExtra("edit3", value3);
                intent.putExtra("edit4", value4);
                MainActivity.this.startActivityForResult(intent, 101);
            }
        });

//        findViewById(R.id.toggle_choices_visibility).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!isShowingAnswers) {
//                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.invisible);
//                    findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
//                    findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
//                    findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);
//                    isShowingAnswers = true;
//                }
//                else if (isShowingAnswers) {
//                    ((ImageView) findViewById(R.id.toggle_choices_visibility)).setImageResource(R.drawable.visible);
//                    findViewById(R.id.flashcard_answer1).setVisibility(View.INVISIBLE);
//                    findViewById(R.id.flashcard_answer2).setVisibility(View.INVISIBLE);
//                    findViewById(R.id.flashcard_answer3).setVisibility(View.INVISIBLE);
//                    isShowingAnswers = false;
//                }
//            }
//        });

//        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flashcardDatabase.deleteCard(((TextView) findViewById(R.id.flashcard_question)).getText().toString());
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {
            String question = data.getStringExtra("string1");
            String answer1 = data.getStringExtra("string2");
            String answer2 = data.getStringExtra("string3");
            String answer3 = data.getStringExtra("string4");
            ((TextView)findViewById(R.id.flashcard_question)).setText(question);
            ((TextView)findViewById(R.id.flashcard_answer1)).setText(answer1);
            ((TextView)findViewById(R.id.flashcard_answer2)).setText(answer2);
            ((TextView)findViewById(R.id.flashcard_answer3)).setText(answer3);
            flashcardDatabase.insertCard(new Flashcard(question, answer1, answer2, answer3));
            allFlashcards = flashcardDatabase.getAllCards();
            Snackbar.make(findViewById(R.id.flashcard_question), "Card successfully created!", Snackbar.LENGTH_SHORT).show();
        }
        if (resultCode == RESULT_OK && requestCode == 101) {
            String question = data.getStringExtra("string1");
            String answer1 = data.getStringExtra("string2");
            String answer2 = data.getStringExtra("string3");
            String answer3 = data.getStringExtra("string4");
            ((TextView)findViewById(R.id.flashcard_question)).setText(question);
            ((TextView)findViewById(R.id.flashcard_answer1)).setText(answer1);
            ((TextView)findViewById(R.id.flashcard_answer2)).setText(answer2);
            ((TextView)findViewById(R.id.flashcard_answer3)).setText(answer3);
            flashcardDatabase.updateCard(new Flashcard(question, answer1, answer2, answer3));
            allFlashcards = flashcardDatabase.getAllCards();
            Snackbar.make(findViewById(R.id.flashcard_question), "Card successfully edited!", Snackbar.LENGTH_SHORT).show();
        }

//        findViewById(R.id.flashcard_answer1).setBackgroundColor(getResources().getColor(R.color.my_original, null));
//        findViewById(R.id.flashcard_answer2).setBackgroundColor(getResources().getColor(R.color.my_original, null));
//        findViewById(R.id.flashcard_answer3).setBackgroundColor(getResources().getColor(R.color.my_original, null));
//        findViewById(R.id.flashcard_answer1).setVisibility(View.VISIBLE);
//        findViewById(R.id.flashcard_answer2).setVisibility(View.VISIBLE);
//        findViewById(R.id.flashcard_answer3).setVisibility(View.VISIBLE);

    }
}