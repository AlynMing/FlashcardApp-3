package com.example.tewodros.laboratorio;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Flashcard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlashcardDao flashcardDao();
}
