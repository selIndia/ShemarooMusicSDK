package com.shemaroo.shemaroomusicsdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shemaroo.shemaroomusicsdk.utils.ShemarooMusicSDKInstance;
import com.shemaroo.shemaroomusicsdk.widget.ShemarooMusicButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public ShemarooMusicButton btnLaunchRadio;
    public Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLaunchRadio = findViewById(R.id.btnLaunchRadio);
        btnStop = findViewById(R.id.btnStop);

        initRadio();
        btnStop.setOnClickListener(this);
    }

    private void initRadio() {
        ShemarooMusicSDKInstance.setAppLicationContext(getApplicationContext());
        ShemarooMusicSDKInstance.setCurrActivity(MainActivity.this);

        ShemarooMusicSDKInstance.getImage(btnLaunchRadio);
    }

    @Override
    public void onClick(View view) {
        ShemarooMusicSDKInstance.stopPlayer();
    }
}