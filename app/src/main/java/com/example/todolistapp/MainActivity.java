package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ArrayAdapter<String> itemsAdapter;
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> addItem(view));

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(itemsAdapter);
        setUpListViewListener();
    }

    private void setUpListViewListener() {
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            addToast("Usunąłeś element z listy");
            items.remove(i);
            itemsAdapter.notifyDataSetChanged();
            return true;
        });
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.taskContent);
        String taskContent = input.getText().toString();

        if(taskContent.equals("")){
            addToast("Element nie może być pusty");
            return;
        }
        itemsAdapter.add(taskContent);
        input.setText("");
        addToast("Pomyslnie dodano element");

    }

    private void addToast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
}