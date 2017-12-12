package com.gabor.GistList;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by kayvan on 7/22/16.
 */
public class BindingAdapters {

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

    @BindingAdapter("imageVisibility")
    public static void setImageVisibility(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
