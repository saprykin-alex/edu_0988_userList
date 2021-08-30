package com.example.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class UserPagerActivity extends AppCompatActivity {
    private ViewPager viewPager;
    Users users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pager);
        viewPager = findViewById(R.id.userViewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        users = new Users(UserPagerActivity.this);
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                User user = users.getUserList().get(position);
                Fragment fragment = new UserInfoFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                fragment.setArguments(bundle);
                return fragment;
            }

            @Override
            public int getCount() {
                return 0;
            }

    });
    }
}