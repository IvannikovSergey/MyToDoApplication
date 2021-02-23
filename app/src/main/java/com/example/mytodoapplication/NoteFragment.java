package com.example.mytodoapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.DateFormat;
import java.util.UUID;

public class NoteFragment extends Fragment {

    private Note note;
    private EditText editTextnote;
    private Button setDateButton;
    private SwitchMaterial switchIsDone;

    public static final String ARG_NOTE_ID = "note_id";

    public static NoteFragment newInstance(UUID noteId) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_NOTE_ID, noteId);

        NoteFragment fragment = new NoteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//        note = new Note(); /////////
        UUID noteId = (UUID) getArguments().getSerializable(ARG_NOTE_ID);
        note = NotesModel.get(getActivity()).getNote(noteId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note, container, false);

        editTextnote = view.findViewById(R.id.crime_title);
        editTextnote.setText(note.getTitle());   // Выводит номер соответствующей заметки для редактирования в названии

        editTextnote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                note.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setDateButton = view.findViewById(R.id.set_date);
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
        String format = dateFormat.format(note.getDate());
        setDateButton.setText(format);
        setDateButton.setEnabled(false);
        switchIsDone = view.findViewById(R.id.switchIsDone);
//        switchIsDone.setChecked(note.isDone(true));
        switchIsDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                note.isDone(isChecked);
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_note, menu);
    }
}
