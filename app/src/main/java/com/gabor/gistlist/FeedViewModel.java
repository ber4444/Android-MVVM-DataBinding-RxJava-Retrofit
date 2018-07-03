package com.gabor.gistlist;

import android.databinding.ObservableField;

import com.gabor.gistlist.models.ItemViewModel;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A view model that exposes streams of data to the view... but has no knowledge of the view.
 * Created by gabor on 12/3/17.
 */

class FeedViewModel {

    final ObservableField<Boolean> imagesVisible = new ObservableField<>(true);

    Observable<ItemViewModel> loadFeed() {
        return RetrofitSingleton.getInstance().provideClient()
                .getGistsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::from)
                .filter(gist -> gist.owner != null)
                .map(ItemViewModel::new);
    }

    void toggleImageVisibility() {
        imagesVisible.set(!imagesVisible.get());
    }
}
