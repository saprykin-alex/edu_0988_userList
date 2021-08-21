package com.example.secondapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    ArrayList<String> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 100; i++) {
            userList.add("Пользователь "+i);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
    private class UserHolder extends RecyclerView.ViewHolder{
        TextView itemTextView;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
        }
        public void bind(String userName){
            itemTextView.setText(userName);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<String> users;

        public UserAdapter(ArrayList<String> users) {
            this.users = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            String user = users.get(position);
            userHolder.bind(user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
