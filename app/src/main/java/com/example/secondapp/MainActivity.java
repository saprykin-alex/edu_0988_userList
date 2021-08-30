package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = new UserListFragment();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment, "main_fragment").commit();

    }

    @Override
    public void onBackPressed(){
        Fragment currentFragment = fragmentManager.findFragmentByTag("main_fragment");
        if(currentFragment!= null && currentFragment.isVisible()){
            super.onBackPressed();
        }else {
            Fragment fragment = new UserListFragment();
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, "main_fragment");
        }
    }

    public static void changeFragment(View view, User user){
        //Получаем хостинговую активность
        FragmentActivity activity = (FragmentActivity) view.getContext();
        //Создаём фрагмент менеджер
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        //Создаём фрагмент
        Fragment fragment = new UserInfoFragment();
        //Создаём Bundle(это как коллекция)
        Bundle bundle = new Bundle();
        //Записываем пользователя в bundle
        bundle.putSerializable("user", user);
        //Добавить bundle к фрагменту
        fragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment).commit();

    }

}
