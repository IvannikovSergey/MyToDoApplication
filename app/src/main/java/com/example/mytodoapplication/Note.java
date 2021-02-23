package com.example.mytodoapplication;

import java.util.Date;
import java.util.UUID;

public class Note {

    private UUID id;
    private String title;
    private Date date;
    private boolean isDone;

    public Note() {
        id = UUID.randomUUID();
        date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone(boolean isChecked) {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
