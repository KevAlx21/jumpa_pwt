package com.mytour.jumpapwt.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytour.jumpapwt.data.item.TourismItem
import com.mytour.jumpapwt.data.repository.FetchRepository

class FetchViewModel : ViewModel() {
    private val fetchRepository = FetchRepository()

    private val _dataInList = MutableLiveData<List<TourismItem>>()
    val dataInList: LiveData<List<TourismItem>> get() = _dataInList

    fun fetchTourismData(collectionNames: String) {
        fetchRepository.fetchTourismData(collectionNames) { list ->
            _dataInList.value = list
        }
    }
}