package com.cst2335.zafa0003;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class CustomAdapter extends ArrayAdapter<Message> {

    private TextView chat;
    private List<Message> chatList = new ArrayList<Message>();
    private Context context;

    @Override
    public void add(Message object) {
        chatList.add(object);
        super.add(object);
    }

    public CustomAdapter(Context context, int textViewResourceId) { //[4]
        super(context, textViewResourceId);
        this.context = context;
    }

    public int getCount() {
        return this.chatList.size();
    } //[4]

    public Message getItem(int index) {
        return this.chatList.get(index);
    } //[4]

    public void removeItem(int index) {
        chatList.remove(index);
        this.notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent) { //[3]
        Message chatMsgObj = getItem(position);
        View Row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE); //[3]
        if (chatMsgObj.send) {
            Row = inflater.inflate(R.layout.send_row, parent, false);
        } else {
            Row = inflater.inflate(R.layout.receive_row, parent, false); //[3]
        }
        chat = (TextView) Row.findViewById(R.id.msg);
        chat.setText(chatMsgObj.message); //[3]
        return Row;
    }

    @Override
    public long getItemId(int position) {
        return position;
    } //[4]
}
//[4] Android Developers. 2022. ArrayAdapter  |  Android Developers. [online]
// Available at: <https://developer.android.com/reference/android/widget/ArrayAdapter>
// [Accessed 27 June 2022].

