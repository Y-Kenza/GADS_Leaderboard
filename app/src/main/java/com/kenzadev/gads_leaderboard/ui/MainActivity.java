package com.kenzadev.gads_leaderboard.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.kenzadev.gads_leaderboard.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitFormActivity();
            }
        });

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        TabAdapter tabAdapter = new TabAdapter(this, getSupportFragmentManager());

        viewPager.setAdapter(tabAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void submitFormActivity() {
        Intent submitForm = new Intent(this, SubmitFormActivity.class);
        startActivity(submitForm);
    }
}


