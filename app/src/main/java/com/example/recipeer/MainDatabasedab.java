package com.example.recipeer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainDatabasedab extends AppCompatActivity {

    EditText recipesBox;
    EditText ingredientsBox;
    Button btnadd;
    Button btnload;
    TextView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_databasedab);

    }

    public void loadStudents(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);
        lst.setText(dbHandler.loadHandler());
        recipesBox.setText("");
        ingredientsBox.setText("");
    }

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
