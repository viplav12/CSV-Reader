package com.rail.csvrenderer.csv.localCsv;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.rail.csvrenderer.R;
import com.rail.csvrenderer.csv.Reader;
import com.rail.csvrenderer.csv.ItemArrayAdapter;

import java.io.InputStream;
import java.util.List;

public class LocalActivity extends AppCompatActivity {
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);

        ListView listView = findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.issues);
        Reader csvFile = new Reader(inputStream);
        List<String[]> userList = csvFile.read();

        for (String[] userData : userList) {
            itemArrayAdapter.add(userData);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
