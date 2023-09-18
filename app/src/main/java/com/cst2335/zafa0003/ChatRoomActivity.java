package com.cst2335.zafa0003;

import static android.view.View.TEXT_ALIGNMENT_VIEW_END;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ChatRoomActivity extends AppCompatActivity {
    private static final String TAG = "ChatRoomActivity";

    ArrayList<Message> ls;
    ListView results;
    Button receiveButton;
    Button sendButton;
    EditText editText;
    CustomAdapter myAdapter;
    RecyclerView rView;
    List<Message> chatList = new ArrayList<Message>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


        results = (ListView) findViewById(R.id.ListV);
        editText = (EditText) findViewById(R.id.textMessage);
        receiveButton = findViewById(R.id.receiveButton);
        sendButton = findViewById(R.id.sendButton);

        ls = new ArrayList<Message>();


        myAdapter = new CustomAdapter(getApplicationContext(), R.layout.receive_row); //[1]
        results.setAdapter(myAdapter);


        editText = (EditText) findViewById(R.id.textMessage);
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) { //[1]
                myAdapter.add(new Message(editText.getText().toString(), false));
                editText.setText("");
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                myAdapter.add(new Message(editText.getText().toString(), true));
                editText.setText("");
            }
        });

        results.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL); //[1]
        results.setAdapter(myAdapter);

        myAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() { //[1]
                super.onChanged();
                results.setSelection(myAdapter.getCount() - 1);
            }
        });

        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //[2]
                Message whatWasClicked = myAdapter.getItem(position);

                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoomActivity.this ); //[3]

                builder.setTitle("Question:")
                        .setMessage("Do you want to delete this:" + whatWasClicked.getMessage() +
                                "The selected row is:" + position + "The database id is:" + id  ) //[3]
                        .setNegativeButton("Negative", (dialog, click1)->{ })
                        .setPositiveButton("Positive", (dialog, click2)->{ //[3]
                            //actually delete something:
                            myAdapter.removeItem(position);
                            myAdapter.notifyDataSetChanged(); //[3]
                        }).create().show();
            }
        });
    }
}
//[1]android, S., 2022. Simple chat application using listview in android - trinity tuts. [online] TrinityTuts.
// Available at: <https://trinitytuts.com/simple-chat-application-using-listview-in-android/>
// [Accessed 27 June 2022].

//[2]ListView, s., 2022. setOnItemClickListener on custom ListView. [online] Stack Overflow.
// Available at: <https://stackoverflow.com/questions/4709870/setonitemclicklistener-on-custom-listview>
// [Accessed 27 June 2022].

//[3] https://github.com/gourteacher/Exercises_S22




