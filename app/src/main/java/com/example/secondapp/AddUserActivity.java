package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {
    EditText editTextUserName;
    EditText editTextUserLastName;
    EditText editTextPhone;
    Button formAddUserBtn;
    User user;
    boolean addUser = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        user = (User) getIntent().getSerializableExtra("user");
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserLastName = findViewById(R.id.editTextUserLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        formAddUserBtn = findViewById(R.id.formAddUserBtn);
        if(user!=null){         //Редактирование существующего пользователя
            editTextUserName.setText(user.getUserName());
            editTextUserLastName.setText(user.getUserLastName());
            editTextPhone.setText(user.getPhone());
            addUser = false;
        }else{                  //Создание нового пользователя
            user = new User();
        }


        formAddUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUserName(editTextUserName.getText().toString());
                user.setUserLastName(editTextUserLastName.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                Users users = new Users(AddUserActivity.this);
                if(addUser) users.addUser(user);
                else users.updateUser(user);

                onBackPressed();
            }
        });

    }
}