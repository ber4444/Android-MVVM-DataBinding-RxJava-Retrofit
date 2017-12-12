package com.gabor.GistList;

import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gabor.GistList.databinding.ItemBinding;
import com.gabor.GistList.models.ItemViewModel;

import java.util.ArrayList;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private final ArrayList<ItemViewModel> items = new ArrayList<>();
    private final ObservableField<Boolean> imagesVisible;

    ListViewAdapter(ObservableField<Boolean> imagesVisible) {
        this.imagesVisible = imagesVisible;
    }

    void add(ItemViewModel item) {
        items.add(item);
        this.notifyItemInserted(items.size() - 1);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setImagesVisible(imagesVisible);
        return new ViewHolder(binding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemBinding binding;
        ViewHolder (ItemBinding binding) {
            this(binding.getRoot());
            this.binding = binding;
        }
        ViewHolder(View view) {
            super(view);
        }
        void bind(@NonNull ItemViewModel item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
