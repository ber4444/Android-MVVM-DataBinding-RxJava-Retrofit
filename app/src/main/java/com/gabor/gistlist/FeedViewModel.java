package com.gabor.gistlist;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.gabor.gistlist.models.Item;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A view model that exposes streams of data to the view
 * and also saves state that survives configuration changes.
 * Created by gabor on 12/3/17.
 */

@SuppressWarnings("WeakerAccess")
public class FeedViewModel extends ViewModel {

    final ObservableField<Boolean> imagesVisible = new ObservableField<>(true);
    // if we wanted to, could save the list itself here too

    Observable<Item> loadFeed() {
        return RetrofitSingleton.getInstance().provideClient()
                .getGistsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Observable::from)
                .filter(gist -> gist.owner != null)
                .map(Item::new);
    }

    void toggleImageVisibility() {
        imagesVisible.set(Boolean.FALSE.equals(imagesVisible.get()));
    }
}
