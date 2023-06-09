package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.prefs.Preferences;

public class  settings extends AppCompatActivity {
    ImageView bacBtn;
    SwitchCompat switchCompatBtn;
    ImageButton logoutBtn;
    ImageView language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        bacBtn = findViewById(R.id.bac);
        switchCompatBtn = findViewById(R.id.swithem);
        logoutBtn = findViewById(R.id.outlog);
        language= findViewById(R.id.langArrw);
        bacBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settings.this, MainActivity.class);
                startActivity(intent);
            }
        });
        switchCompatBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }

            }
        });
       logoutBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(settings.this,LoginActivity.class);
               startActivity(intent);
               finish();
           }
       });

       language.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               PopupMenu popupMenu = new PopupMenu(settings.this,view);
               popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
               popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem item) {
                       switch (item.getItemId()) {
                           case R.id.menu_item_1:
                               // Handle menu item 1 click
                               return true;
                           case R.id.menu_item_2:
                               // Handle menu item 2 click
                               return true;
                           // Add more cases for other menu items
                           default:
                               return false;
                       }
                   }
               });

               // Show the popup menu
               popupMenu.show();
           }
       });


    }


    }




