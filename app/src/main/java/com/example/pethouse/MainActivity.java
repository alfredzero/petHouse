package com.example.pethouse;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    SwipeRefreshLayout swipeIndicator;
    ListView myList;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        addFloatBTN();
        super.onCreate(savedInstanceState);

        swipeIndicator = (SwipeRefreshLayout) findViewById(R.id.swipeIndicator);
        myList = (ListView) findViewById(R.id.myList);

        String[] planets = getResources().getStringArray(R.array.planets);
        myList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planets));

        swipeIndicator.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
    }
    public void refreshContent(){
        String[] planets = getResources().getStringArray(R.array.planets);
        myList.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, planets));
        swipeIndicator.setRefreshing(false);
        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true ;
    }

    public void addFloatBTN(){
        FloatingActionButton myFloat = (FloatingActionButton) findViewById(R.id.floating_button);
        myFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, getResources().getString(R.string.message), Snackbar.LENGTH_LONG)
                        .setAction(getResources().getString(R.string.action_text), new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                Log.i("SNACK BAR", "Click the snackbar");
                            }
                        }).show();
        }
        });
    }
}