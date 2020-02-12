package com.rail.csvrenderer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rail.csvrenderer.csv.browseCsv.CsvActivity;
import com.rail.csvrenderer.csv.ItemArrayAdapter;
import com.rail.csvrenderer.csv.localCsv.LocalActivity;


public class MainActivity extends AppCompatActivity {

    public static final int REQUESTCODE = 1;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_browseCSV = findViewById(R.id.browsecsv);
        btn_browseCSV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCsvActivity();
            }
        });

        Button openLocalCsv = findViewById(R.id.btn_openLocal);
        openLocalCsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLocalCsvActivity();
            }
        });
    }

    private void openCsvActivity() {
        Intent intent = new Intent(this, CsvActivity.class);
        startActivity(intent);
    }

    private void openLocalCsvActivity() {
        Intent intent = new Intent(this, LocalActivity.class);
        startActivity(intent);
    }
}
