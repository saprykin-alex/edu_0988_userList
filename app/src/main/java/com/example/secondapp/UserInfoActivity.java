package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    private TextView userNameTextView;
    private TextView phoneTextView;
    private User user;
    private Button editUserDataBtn;
    private Button deleteUserBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        userNameTextView = findViewById(R.id.userNameTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        user = (User) getIntent().getSerializableExtra("user");
        userNameTextView.setText(user.getUserName()+"\n"+user.getUserLastName());
        phoneTextView.setText(user.getPhone());
        editUserDataBtn = findViewById(R.id.editUserDataBtn);
        deleteUserBtn = findViewById(R.id.deleteUserBtn);
        editUserDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Открываем активность для редактирования пользователя
                // На активность нужно передать данные пользователя (Используйте сериализацию)
                // На активности реализуем возможность изменить данные пользователя
                Intent intent=new Intent(UserInfoActivity.this, AddUserActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Реализовать удаление пользователя
                Users users = new Users(UserInfoActivity.this);
                users.deleteUser(user.getUuid());
                // Возврат на главный экран
                onBackPressed();
            }
        });
    }
}