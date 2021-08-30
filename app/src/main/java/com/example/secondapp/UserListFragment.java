package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class UserListFragment extends Fragment {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    Button addUserBtn;
    ArrayList<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Пользователь "+i);
            user.setUserLastName("Фамилия "+i);
            userList.add(user);
        }*/
        addUserBtn = findViewById(R.id.addUserBtn);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(intent);
            }
        });
    }
    private void recyclerViewInit(){
        Users users = new Users(MainActivity.this);
        userList = users.getUserList();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
    }

    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTextView;
        User user;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }
        public void bind(String userName, User user){
            this.user = user;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        ArrayList<User> users;

        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            User user = users.get(position);
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            userHolder.bind(userString, user);
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }
}
