package com.example.mytodoapplication;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesModel {

    private static NotesModel notesModel;

    private List<Note> notesList;

    public static NotesModel get(Context context) {
        if (notesModel == null) {
            notesModel = new NotesModel(context);
        }
        return notesModel;
    }

    private NotesModel(Context context) {
        notesList = new ArrayList<>();
//        Код для заполнения 100 элементов. Уже не нужен!!!
//        for (int i = 0; i < 100; i++) {
//            Note note = new Note();
//            note.setTitle("Заметка №" + i);
//            notesList.add(note);
//        }
    }

    public List<Note> getNotes() {
        return notesList;
    }

    public Note getNote(UUID id) {
        for (Note note : notesList) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    public void addNote(Note note) {
        notesList.add(note);
    }

    public List<Note> getNote() {
        return notesList;
    }
}
