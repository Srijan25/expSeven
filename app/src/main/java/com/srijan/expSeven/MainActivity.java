package com.srijan.expSeven;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.srijan.expSeven.ui.notes.NotesListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new NotesListFragment(), NotesListFragment.TAG)
                    .commitNow();
        }
    }

    @OnClick(R.id.fab)
    void onFabClick() {
        NotesListFragment fragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(NotesListFragment.TAG);
        if (fragment != null) {
            fragment.showNoteDialog(false, null, -1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete_all) {
            NotesListFragment fragment = (NotesListFragment) getSupportFragmentManager().findFragmentByTag(NotesListFragment.TAG);
            if (fragment != null) {
                fragment.deleteAllNotes();
            }
            return true;
        }

        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}