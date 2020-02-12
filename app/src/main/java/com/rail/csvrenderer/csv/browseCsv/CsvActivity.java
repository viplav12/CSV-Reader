package com.rail.csvrenderer.csv.browseCsv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rail.csvrenderer.R;
import com.rail.csvrenderer.csv.Reader;
import com.rail.csvrenderer.csv.ItemArrayAdapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class CsvActivity extends AppCompatActivity {

    public static final int REQUESTCODE = 1;
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_layout);
        if (savedInstanceState == null) {
            browseCSVFile();
        }

    }


    private void browseCSVFile() {
        Intent fileintent = new Intent(Intent.ACTION_GET_CONTENT);
        fileintent.addCategory(Intent.CATEGORY_OPENABLE);
        fileintent.setType("text/csv");
        startActivityForResult(fileintent, REQUESTCODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Intent intentParent = getIntent();
//        setResult(RESULT_OK, intentParent);
        // Check which request we're responding to
        if (requestCode == REQUESTCODE && resultCode != RESULT_CANCELED) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The Intent's data Uri identifies which contact was selected.

                Uri uri = null;
                uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    displayCSV(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void displayCSV(InputStream inputStream) {
        ListView listView = findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        Reader csvFile = new Reader(inputStream);
        List<String[]> userList = csvFile.read();

        for (String[] userData : userList) {
            itemArrayAdapter.add(userData);
        }
    }
}
