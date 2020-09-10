package com.joshbillionz.gadsleaderboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.joshbillionz.gadsleaderboard.adapters.ViewPagerAdapter;
import com.joshbillionz.gadsleaderboard.fragments.HoursFragment;
import com.joshbillionz.gadsleaderboard.fragments.SkillIQFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);

        adapter.addFragment(HoursFragment.newInstance());
        adapter.addFragment(SkillIQFragment.newInstance());

        viewPager.setAdapter(adapter);


        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Learning Leaders");
                        break;
                    case 1:
                        tab.setText("Skill IQ Leaders");
                        break;
                }
            }
        }).attach();

        MaterialButton submitLink = findViewById(R.id.submit);
        submitLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SubmitActivity.class);
                startActivity(intent);
            }
        });

    }
}