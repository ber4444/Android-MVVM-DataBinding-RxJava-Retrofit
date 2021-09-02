package com.gabor.gistlist;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabor.gistlist.models.Item;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * A view that dispatches the user's actions to the view model.
 */
@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ListViewAdapter adapter;
    private FeedViewModel viewModel;
    private MenuItem button;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(FeedViewModel.class);

        RecyclerView listView = findViewById(R.id.listview);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListViewAdapter(viewModel.imagesVisible);
        listView.setAdapter(adapter);

        final Observer<List<Item>> observer = list -> {
            adapter.add(list);
        };
        viewModel.state.observe(this, observer);
    }

    private void changeButtonTitle() {
        button.setTitle(Boolean.FALSE.equals(viewModel.imagesVisible.get()) ? R.string.show_images : R.string.hide_images);
    }

    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        changeButtonTitle();
        return true;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        button = menu.findItem(R.id.change_image_visibility);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_image_visibility) {
            viewModel.toggleImageVisibility();
            changeButtonTitle();
        }
        return super.onOptionsItemSelected(item);
    }
}
