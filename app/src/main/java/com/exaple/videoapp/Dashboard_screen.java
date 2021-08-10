package com.exaple.videoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Dashboard_screen extends AppCompatActivity {

    EditText secretCodeBox;
    Button joinButton;
    Button shareButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);

        secretCodeBox = findViewById(R.id.codeBox);
        joinButton = findViewById(R.id.joinButton);
        shareButton = findViewById(R.id.shareButton);

        URL serverURL;
        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defauloptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .build();

            JitsiMeet.setDefaultConferenceOptions(defauloptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sharetext = secretCodeBox.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT ,"Share this code with your friends");
                sendIntent.setType("text/plain");
                sendIntent.putExtra(Intent.EXTRA_TEXT , sharetext+"                              " +
                        "                        " +"\n"+

                        "Hello this is my secret code for our meeting , join meeting with this code " +
                        "for our secret video call chat :))");
                startActivity(sendIntent);
            }
        });



        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                      .setRoom(secretCodeBox.getText().toString())
                      .setWelcomePageEnabled(false)
                      .build();

                JitsiMeetActivity.launch(Dashboard_screen.this , options);
            }
        });
    }
}