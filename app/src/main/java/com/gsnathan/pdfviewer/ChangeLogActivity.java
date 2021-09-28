package com.gsnathan.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ChangeLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_log);
        updateActionBar();

        List<ChangeLog> changeLogs = new ArrayList<>();
        changeLogs.add(new ChangeLog("Bottom Bar", "Removed FAB and replaced it with bottom bar", R.drawable.star_icon));
        changeLogs.add(new ChangeLog("Bug Fixes", "Fixed crashes on Android Q", R.drawable.thumbs_icon));

        RecyclerView recyclerView = findViewById(R.id.listView);
        ChangeLogAdapter adapter = new ChangeLogAdapter(changeLogs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(false);
    }

    public void onContinueButtonClicked(View view) {
        onBackPressed();
    }

    private void updateActionBar() {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}