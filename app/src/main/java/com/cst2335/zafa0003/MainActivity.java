package com.cst2335.zafa0003;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    Button btn;
    private SharedPreferences sharedPref;
    private String currentInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);
        Button btn = (Button) findViewById(R.id.mybutton);
        EditText emailText = (EditText) findViewById(R.id.email); //[1]
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);//[1]
        currentInput = sharedPref.getString("email_val", "");
        emailText.setText(currentInput);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = emailText.getText().toString();
                onPause();
                Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class); //[1]
                goToProfile.putExtra("email", currentInput);
                MainActivity.this.startActivity(goToProfile);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPref.edit(); //[1]
        editor.putString("email_val", currentInput);
        editor.apply();

    }
}
//References: [1] “SharedPreferences.editor &nbsp;: &nbsp; android developers,” Android Developers.
// [Online]. Available: https://developer.android.com/reference/android/content/SharedPreferences.Editor.
// [Accessed: 17-Jun-2022].