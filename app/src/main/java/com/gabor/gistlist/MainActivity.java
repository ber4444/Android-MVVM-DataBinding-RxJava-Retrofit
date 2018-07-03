package com.gabor.gistlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.gabor.gistlist.models.ItemViewModel;

import rx.subscriptions.CompositeSubscription;

/**
 * A view that dispatches the user's actions to the view model.
 */
public class MainActivity extends AppCompatActivity {

    private final CompositeSubscription subscriptions = new CompositeSubscription();
    private ListViewAdapter adapter;
    private final FeedViewModel viewModel = new FeedViewModel();
    static final String STATE_IMAGES_VISIBILITY = "imagesVisibility";
    private MenuItem button;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView listView = findViewById(R.id.listview);
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListViewAdapter(viewModel.imagesVisible);
        listView.setAdapter(adapter);

        subscriptions.add(viewModel.loadFeed().subscribe(this::onResponse, this::onFailure));
    }

    private void onResponse(ItemViewModel viewModel) {
        adapter.add(viewModel);
    }

    private void onFailure(Throwable t) {
        Log.e("Network error: ", t.getMessage());
    }

    private void changeButtonTitle() {
        button.setTitle(Boolean.FALSE.equals(viewModel.imagesVisible.get()) ? R.string.show_images : R.string.hide_images);
    }

    @Override public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_IMAGES_VISIBILITY, Boolean.TRUE.equals(viewModel.imagesVisible.get()));
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewModel.imagesVisible.set(savedInstanceState.getBoolean(STATE_IMAGES_VISIBILITY));
    }

    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        changeButtonTitle();
        return true;
    }

    @Override protected void onDestroy() {
        subscriptions.unsubscribe(); // note that this should be done in onDetachedFromWindow
                                     // whenever we have an Android View as an MVVM view
        super.onDestroy();
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
