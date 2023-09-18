package com.cst2335.zafa0003;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;


public class ProfileActivity extends AppCompatActivity {
    ImageButton btn;
    Button newButton;
    public static final String TAG = "PROFILE_ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG, "In function: " + "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btn = (ImageButton) findViewById(R.id.button);
        EditText emailText = (EditText) findViewById(R.id.email);

        Intent fromMain = getIntent(); //[1]
        emailText.setText(fromMain.getStringExtra("email"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }

            private void dispatchTakePictureIntent() { //code copied from lab 3.
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) { //[2]
                    myPictureTakerLauncher.launch(takePictureIntent); //[2]
                }
            }
        });

        newButton = (Button) findViewById(R.id.chatBtn); //lab3...
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToProfile = new Intent(ProfileActivity.this, ChatRoomActivity.class); //[1]
                ProfileActivity.this.startActivity(goToProfile);
            }
        });


    }

    ActivityResultLauncher<Intent> myPictureTakerLauncher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() { //[1]
        @Override
        public void onActivityResult(ActivityResult result) {
            Log.e(TAG, "In function: " + "onActivityResult");
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Bitmap imgbitmap = (Bitmap) data.getExtras().get("data"); //[3]
                btn.setImageBitmap(imgbitmap);
            } else if (result.getResultCode() == Activity.RESULT_CANCELED)
                Log.i("MYTAGGG", "User refused to capture a picture.");
        }
    });

    @Override
    protected void onStart() { //[1]
        super.onStart();
        Log.e(TAG, "In function: " + "onStart");
    }
    @Override
    protected void onResume() { //[1]
        super.onResume();
        Log.e(TAG, "In function: " + "onResume");
    }
    @Override
    protected void onPause() { //[1]
        super.onPause();
        Log.e(TAG, "In function: " + "onPause");
    }

    @Override
    protected void onStop() { //[1]
        super.onStop();
        Log.e(TAG, "In function: " + "onStop");
    }

    @Override
    protected void onDestroy() { //[1]
        super.onDestroy();
        Log.e(TAG, "In function: " + "onDestroy");
    }





}
//[1] https://github.com/gourteacher/Exercises_S22
//[2] picture, T., Chan, J., Chan, J. and Deshmukh, H., 2022. Trying to get my app to take a picture.
// [online] Stack Overflow. Available
// at: <https://stackoverflow.com/questions/46251811/trying-to-get-my-app-to-take-a-picture>
// [Accessed 27 June 2022].
//[3]  android, d., 2022. data.getExtras().get("data")
// result of low resolution image in android. [online] Stack Overflow. Available
// at:
// <https://stackoverflow.com/questions/6001918/data-getextras-getdata-result-of-low-resolution-image-in-android>
// [Accessed 27 June 2022].