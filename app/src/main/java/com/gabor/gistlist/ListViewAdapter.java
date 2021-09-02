package com.gabor.gistlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.recyclerview.widget.RecyclerView;

import com.gabor.gistlist.databinding.ItemBinding;
import com.gabor.gistlist.models.Item;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {

    private List<Item> items;
    private final ObservableField<Boolean> imagesVisible;

    ListViewAdapter(ObservableField<Boolean> imagesVisible) {
        this.imagesVisible = imagesVisible;
    }

    void add(List<Item> list) {
        items = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public @NonNull ListViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setImagesVisible(imagesVisible);
        return new ViewHolder(binding);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        void bind(@NonNull Item item) {
            binding.setItem(item);
            binding.executePendingBindings();
        }
    }
}
