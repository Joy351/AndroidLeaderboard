package com.zikozee;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.zikozee.learning.LearningFragment;
import com.zikozee.skill.SkillFragment;

public class LeaderBoardActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar mToolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(1);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

//        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_leaderboard);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        submit = findViewById(R.id.submit);
        submit.setOnClickListener(this);
        //Toolbar
        mToolbar = findViewById(R.id.customToolBar);
        setSupportActionBar(mToolbar);

        tabLayout = findViewById(R.id.tabBar);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // Add Fragments Here
        viewPagerAdapter.AddFragment(new LearningFragment(), "Learning Leaders");
        viewPagerAdapter.AddFragment(new SkillFragment(), "Skill IQ Leaders");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.submit){
            Intent intent = new Intent(LeaderBoardActivity.this, SubmissionActivity.class);
            startActivity(intent);
        }
    }
}