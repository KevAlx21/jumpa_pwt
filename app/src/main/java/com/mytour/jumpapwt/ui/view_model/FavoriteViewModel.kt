package com.mytour.jumpapwt.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mytour.jumpapwt.data.item.TourismItem
import com.mytour.jumpapwt.data.repository.FavoriteRepository

class FavoriteViewModel : ViewModel() {
    private val fetchRepository = FavoriteRepository()

    private val _dataInList = MutableLiveData<List<TourismItem>>()
    val dataInList: LiveData<List<TourismItem>> get() = _dataInList

    fun fetchFavoriteTourism() {
        fetchRepository.fetchFavoriteTourism { list ->
            _dataInList.value = list
        }
    }

    fun addFavoriteTourism(tourismItem: TourismItem) {
        fetchRepository.addFavoriteTourism(tourismItem)
    }

    fun deleteFavoriteTourism(tourismItem: TourismItem) {
        fetchRepository.deleteFavoriteTourism(tourismItem)
    }
}