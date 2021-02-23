package com.example.mytodoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.UUID;

public class DetailActivity extends CommonFragmentClass {

    private static final String EXTRA_NOTE_ID = "com.example.mytodoapplication.note_id";

    public static Intent newIntent(Context context, UUID note_id) {

        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_NOTE_ID, note_id);
        return intent;
    }

    @Override
    protected Fragment createNewFragment() {
        UUID noteId = (UUID) getIntent().getSerializableExtra(EXTRA_NOTE_ID);
        return NoteFragment.newInstance(noteId);
    }

    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month + 1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string +
                "/" + day_string +
                "/" + year_string);

        Toast.makeText(this, getString(R.string.date_picker_title) + dateMessage,
                Toast.LENGTH_SHORT).show();
    }
}