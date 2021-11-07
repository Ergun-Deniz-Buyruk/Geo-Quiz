package com.ergundenizbuyruk.geo_quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.ergundenizbuyruk.geo_quiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.quizText.setText("sdafsadfsd");
            }
        });

    }


}