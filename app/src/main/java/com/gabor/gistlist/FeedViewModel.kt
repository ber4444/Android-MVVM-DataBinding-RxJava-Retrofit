package com.gabor.gistlist

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gabor.gistlist.models.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers.io
import javax.inject.Inject


/**
 * A view model that exposes streams of data to the view
 * and also saves state that survives configuration changes.
 * Created by gabor on 12/3/17.
 */
@HiltViewModel
class FeedViewModel @Inject constructor(
    private val repo: Repository
): ViewModel() {

    private val _state = MutableLiveData<List<Item>>()
    @JvmField
    val state: LiveData<List<Item>> = _state
    private val disposables = CompositeDisposable()

    init {
        loadFeed()
    }
    // if we wanted to, could save the list itself here too
    fun loadFeed() {
        disposables.add(repo.getData()
            .subscribeOn(io())
            .observeOn(io())
            .subscribe(
                { if (it.isSuccessful) it.body()?.let { list ->
                    _state.postValue(list
                        .filter { l -> l.owner != null }
                        .map { i -> Item(i) }
                        .sortedBy { l -> l.size }) } },
                { Log.e("Network error: ", it.message.orEmpty()); }
            ))
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    @JvmField
    val imagesVisible = ObservableField(true)
    fun toggleImageVisibility() {
        imagesVisible.set(false == imagesVisible.get())
    }
}