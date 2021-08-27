package com.example.secondapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.secondapp.database.UserBaseHelper;
import com.example.secondapp.database.UserDbSchema;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.UUID;

public class Users {
    private ArrayList<User> userList;
    private SQLiteDatabase database;
    private Context context;
    public Users(Context context){
        this.context = context.getApplicationContext();
        this.database = new UserBaseHelper(this.context).getWritableDatabase();
    }

    public void addUser(User user){
        try {
            URL url = new URL("http://a98598l8.beget.tech/public_html/handlerAddUser.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }

/*
        ContentValues values = getContentValues(user);
        database.insert(UserDbSchema.UserTable.NAME, null, values);
*/
    }

    public void updateUser(User user){
        // Реализуем изменение данных
        ContentValues values = getContentValues(user);
        String stringUuid = user.getUuid().toString();
        database.update(UserDbSchema.UserTable.NAME,
                values,
                UserDbSchema.Cols.UUID+"=?",
                new String[]{stringUuid});
    }
    public void deleteUser(UUID uuid){
        // Отправляем запрос на удаление пользователя по его UUID
        String stringUuid = uuid.toString();
        database.delete(UserDbSchema.UserTable.NAME,
                UserDbSchema.Cols.UUID+"=?",
                new String[]{stringUuid});
    }

    private static ContentValues getContentValues(User user){
        ContentValues values = new ContentValues();
        values.put(UserDbSchema.Cols.UUID, user.getUuid().toString());
        values.put(UserDbSchema.Cols.USERNAME, user.getUserName());
        values.put(UserDbSchema.Cols.USERLASTNAME, user.getUserLastName());
        values.put(UserDbSchema.Cols.PHONE, user.getPhone());
        return values;
    }

    private UserCursorWrapper queryUsers(){
        Cursor cursor = database.query(UserDbSchema.UserTable.NAME,null,null,null,null,null,null);
        return new UserCursorWrapper(cursor);
    }

    public ArrayList<User> getUserList(){
        this.userList = new ArrayList<>();
        UserCursorWrapper cursorWrapper = queryUsers();
        try {
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                User user = cursorWrapper.getUser();
                userList.add(user);
                cursorWrapper.moveToNext();
            }

        }finally {
            cursorWrapper.close();
        }
        /*for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Пользователь "+i);
            user.setUserLastName("Фамилия "+i);
            userList.add(user);
        }*/
        return this.userList;
    }
}
