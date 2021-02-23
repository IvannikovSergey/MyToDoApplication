package com.example.mytodoapplication;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import java.util.UUID;

public class NotesListActivity extends CommonFragmentClass {

    private static final String NOTE_ID = "com.example.mytodoapplication.note_id";

    public static Intent newIntent(Context context, UUID note_id) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(NOTE_ID, note_id);
        return intent;
    }
    @Override
    protected Fragment createNewFragment() {
        return new NotesListFragment();
    }
}
