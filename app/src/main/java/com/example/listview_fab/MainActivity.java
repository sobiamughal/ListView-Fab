package com.example.listview_fab;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnClickListener {
    private static final String TAG = "MainActivity";
    String name;
    FloatingActionButton fab;
    ArrayList<LvItem> arrayList = new ArrayList<>();
    private RecyclerView mrecyclerview;
    private CustomAdapter customAdapter;
    private int mAmount = 0;
    private UserTabihDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mrecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(),arrayList, this);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getApplication()));
        mrecyclerview.setAdapter(customAdapter);

        db = new UserTabihDBHelper(this);
        arrayList.addAll(db.getAllNotes());

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.fabitem);

                final EditText etname = dialog.findViewById(R.id.etname);

                Button btnsave = dialog.findViewById(R.id.btnsave);
                btnsave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        name = etname.getText().toString();

                        LvItem lvItem = new LvItem();
                        lvItem.setName(name);
                        arrayList.add(lvItem);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public void onItemClick(int poistion) {
        Log.d(TAG, "onItemClick: "+poistion);

        Intent intent = new Intent(getApplicationContext(),GalleryActivity.class);
        intent.putExtra("name",arrayList.get(poistion));
        startActivity(intent);
    }
}

