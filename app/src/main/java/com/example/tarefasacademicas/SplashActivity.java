package com.example.tarefasacademicas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tarefasacademicas.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ic_video_logo);
        binding.splashVideo.setVideoURI(uri);

        binding.splashVideo.setOnCompletionListener(mp -> {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        });
        binding.splashVideo.start();
    }
}