package com.example.notepad.Adapter;



import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notepad.MainActivity;
import com.example.notepad.R;
import com.example.notepad.model.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder>{

    ArrayList<Note> notes;
    Context ctx;
    private OnClickListener onClickListener;
    private Timer timer;
    ArrayList<Note> notesSource;
    public NoteAdapter(MainActivity ctx, ArrayList<Note> notes) {
        this.ctx = ctx;
        this.notes = notes;
        notesSource = notes;
    }



    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View listItem = inflater.inflate(R.layout.list_item,parent,false);
        return new NoteViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tvNoteTitle.setText(notes.get(position).getTitle());
        holder.tvNoteBody.setText(notes.get(position).getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClick(position,note);
            }
        });

        int color_code = getRandomColor();
        holder.notes_container.setBackgroundColor(holder.itemView.getResources().getColor(color_code,null));

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Note model);
    }
    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<>();

        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void searchNotes(final String searchKeyword){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               if (searchKeyword.trim().isEmpty()){
                   notes = notesSource;
               }else {
                   ArrayList<Note> temp = new ArrayList<>();
                   for (Note note : notesSource){
                       if (note.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())
                       ||note.getBody().toLowerCase().contains(searchKeyword.toLowerCase())){
                           temp.add(note);
                       }
                   }
                   notes = temp;
               }
               new Handler(Looper.getMainLooper()).post(new Runnable() {
                   @Override
                   public void run() {
                       notifyDataSetChanged();
                   }
               });
            }
        }, 500);

        }
        public void cancelTimer(){
        if (timer!= null){
            timer.cancel();
        }
        }
}

class NoteViewHolder extends RecyclerView.ViewHolder{

    RelativeLayout notes_container;
    public TextView tvNoteTitle,tvNoteBody;
    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);

        notes_container = itemView.findViewById(R.id.notes_container);
        this.tvNoteTitle = itemView.findViewById(R.id.tvNoteTitle);
        this.tvNoteBody = itemView.findViewById(R.id.tvNoteBody);
    }

    }
