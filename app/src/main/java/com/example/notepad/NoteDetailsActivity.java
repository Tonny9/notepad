package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notepad.model.Note;
import com.example.notepad.repository.DatabaseHelper;

public class NoteDetailsActivity extends AppCompatActivity {

    EditText titleEditTxt, contentEditTxt;
    ImageView saveBtn, shareBtn,albumBtn;
    ImageButton backBtn;
    Note note;
    public int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        titleEditTxt = findViewById(R.id.title);
        contentEditTxt = findViewById(R.id.editTextTextMultiLine2);
        saveBtn = findViewById(R.id.save_btn);
        shareBtn = findViewById(R.id.share_btn);
        backBtn = findViewById(R.id.back);
        albumBtn = findViewById(R.id.alb);





        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareText ("Title: " + titleEditTxt.getText().toString() + " \nBody: " + contentEditTxt.getText().toString());


            }

            private void shareText(String textToShare) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");

                shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }

        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditTxt.getText().toString();
                String content = contentEditTxt.getText().toString();

                if (!content.equals("") && !title.equals("")) {
                    DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                    db.createNote(new Note(title, content));


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        albumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              openGallery();;
            }

        });
    }
    private static final int REQUEST_GALLERY = 1;
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_GALLERY);
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK && data !=null){
            Uri selectedImageUri = data.getData();
            albumBtn.setImageURI(selectedImageUri);
        }
    }

}