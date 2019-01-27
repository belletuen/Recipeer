package com.example.recipeer;


import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;

public class MainDatabase{

    EditText recipesBox;
    EditText ingredientsBox;

    public void addStudent(View view) {
        DBHandler dbHandler = new DBHandler(null, null, null, 1);
        String recipes = recipesBox.getText().toString();
        String name = ingredientsBox.getText().toString();
        DataModel datamodel = new DataModel(recipes, name);
        dbHandler.addHandler(datamodel);
        recipesBox.setText("");
        ingredientsBox.setText("");
    }
}