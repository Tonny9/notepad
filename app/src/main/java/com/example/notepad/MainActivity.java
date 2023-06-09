package com.example.notepad;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.SearchView;
import android.widget.Toast;

import com.example.notepad.Adapter.NoteAdapter;
import com.example.notepad.model.Note;
import com.example.notepad.repository.DatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NoteAdapter.OnClickListener{
    FloatingActionButton addNoteBtn;
     ArrayList<Note> notes = new ArrayList<>();
     ArrayList<Note> searchList;
     ImageButton settingBtn;
    public RecyclerView noteRecycleView;
    EditText searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNoteBtn = findViewById(R.id.add_note_Btn);
        settingBtn = findViewById(R.id.settings);
        noteRecycleView = findViewById(R.id.noteRecycleView);
        searchView = findViewById(R.id.searchView);

        getNotes();

        NoteAdapter adapter = new NoteAdapter(this, notes);

        adapter.setOnClickListener(this);

        noteRecycleView.setHasFixedSize(true);
        noteRecycleView.setLayoutManager(new LinearLayoutManager(this));
        noteRecycleView.setAdapter(adapter);



        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,settings.class);
                startActivity(intent);
            }
        });
        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NoteDetailsActivity.class);
               startActivity(intent);
            }
        });

       searchView.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            adapter.cancelTimer();
           }

           @Override
           public void afterTextChanged(Editable s) {
            if (notes.size() != 0){
                adapter.searchNotes(s.toString());
            }
           }
       });





    }




    public void onClick(int position, Note note) {
        Toast.makeText(this, "Position: "+position+" id: "+note.getId(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,UpdateNoteActivity.class);
        intent.putExtra("id",note.getId());
        intent.putExtra("title",note.getTitle());
        intent.putExtra("body",note.getBody());
        startActivity(intent);

    }

    private void getNotes() {
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        notes = db.getAllNotes();
    }
    @Override
    public void onClick(View view) {

    }

    }


