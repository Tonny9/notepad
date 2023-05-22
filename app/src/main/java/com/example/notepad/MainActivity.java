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
import android.view.View;
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
    //SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNoteBtn = findViewById(R.id.add_note_Btn);
        settingBtn = findViewById(R.id.settings);
        noteRecycleView = findViewById(R.id.noteRecycleView);
       // searchView = findViewById(R.id.searchView);

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

        /*searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();

                if (int i=0;i <notes.size(); i++) {
                performSearch(query);
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return true;
            }


        });
        private void performSearch(String query) {
        }*/



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


