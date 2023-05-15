package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class NoteDetailsActivity extends AppCompatActivity {

    EditText titleEditTxt,contentEditTxt;
    ImageView saveBtn,shareBtn;
    ImageButton backBtn;
    Notes notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        titleEditTxt = findViewById(R.id.title);
       contentEditTxt = findViewById(R.id.editTextTextMultiLine2);
         saveBtn = findViewById(R.id.save_btn);
         shareBtn = findViewById(R.id.share_btn);
         backBtn = findViewById(R.id.back);
         backBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(NoteDetailsActivity.this,MainActivity.class);
                 startActivity(intent);
             }
         });
         shareBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String textToShare = "Title: "+titleEditTxt.getText().toString()+" \nBody: "+contentEditTxt.getText().toString();
                 shareText(textToShare);

             }
         });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditTxt.getText().toString();
                String content = contentEditTxt.getText().toString();

                if(content.isEmpty()){
                    Toast.makeText(NoteDetailsActivity.this, "please add content!", Toast.LENGTH_SHORT).show();
                    return;
                }
                   notes = new Notes();

                notes.setTitle(title);
                notes.setDescription(content);

                Intent intent = new Intent();
                intent.putExtra("note", notes);
                setResult(Activity.RESULT_OK,intent);
                finish();

            }
        });
    }
               private void shareText(String text){

                   Intent shareIntent = new Intent(Intent.ACTION_SEND);
                   shareIntent.setType("text/plain");
                   shareIntent.putExtra(Intent.EXTRA_TEXT, text);
                   startActivity(Intent.createChooser(shareIntent, "Share via"));

               }
    }


