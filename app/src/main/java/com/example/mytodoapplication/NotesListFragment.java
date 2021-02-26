package com.example.mytodoapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;

public class NotesListFragment extends Fragment {

    private RecyclerView notesRecyclerView;
    private NotesAdapter notesAdapter;
    private int adapterPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notes_list, container, false);
        notesRecyclerView = view.findViewById(R.id.notes_recyclerView);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_notes_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.new_note):
                Note note = new Note();
                NotesModel.get(getActivity()).addNote(note);
                Intent intent = NotesListActivity.newIntent(getActivity(), note.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class NotesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView dateTextView;
        private ImageView imageViewSolved;
        private Note note;

        public NotesHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            titleTextView = itemView.findViewById(R.id.note_title);
            descriptionTextView = itemView.findViewById(R.id.note_description);
            dateTextView = itemView.findViewById(R.id.note_date);
            imageViewSolved = itemView.findViewById(R.id.note_solved);
        }

        public void bind(Note note) {
            this.note = note;
            titleTextView.setText("Название: " + note.getTitle());
            descriptionTextView.setText("Детали: " + note.getDescription());
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
            String format = dateFormat.format(note.getDate());
            dateTextView.setText("Дата создания: " + format);

            imageViewSolved.setVisibility(note.isDone() ? View.VISIBLE : View.GONE);

        }

        @Override
        public void onClick(View v) {
            adapterPosition = getAdapterPosition();
            Intent intent = DetailActivity.newIntent(getActivity(), note.getId());
            startActivity(intent);
        }
    }

    private class NotesAdapter extends RecyclerView.Adapter<NotesHolder> {
        private List<Note> notes;

        public NotesAdapter(List<Note> notes) {
            this.notes = notes;

        }

        @NonNull
        @Override
        public NotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_notes, parent, false);
            return new NotesHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull NotesHolder holder, int position) {
            Note note = notes.get(position);
            holder.bind(note);

        }

        @Override
        public int getItemCount() {
            return notes.size();
        }

    }

    private void updateUI() {
        NotesModel notesModel = NotesModel.get(getActivity());
        List<Note> notesList = notesModel.getNotes();

        if (notesAdapter == null) {
            notesAdapter = new NotesAdapter(notesList);
            notesRecyclerView.setAdapter(notesAdapter);
        } else {
            notesAdapter.notifyItemChanged(adapterPosition);
        }
    }
}
