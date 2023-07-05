package com.mytour.jumpapwt.data.repository

import android.util.Log
import com.google.firebase.database.*
import com.mytour.jumpapwt.data.item.TourismItem

class FavoriteRepository {
    private val fbReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("favoritesData")

    fun fetchFavoriteTourism(callback: (List<TourismItem>) -> Unit){
        fbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listData = mutableListOf<TourismItem>()
                for (snapshots in dataSnapshot.children) {
                    val dataItem = snapshots.getValue(TourismItem::class.java)
                    dataItem?.let { listData.add(it) }
                }
                callback(listData)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(FetchRepository.TAG, "Couldn't get data: ${error.message}")
            }
        })
    }

    fun addFavoriteTourism(tourismItem: TourismItem) {
        fbReference.child(tourismItem.tourismName).setValue(tourismItem)
    }

    fun deleteFavoriteTourism(tourismItem: TourismItem) {
        fbReference.child(tourismItem.tourismName).removeValue()
    }
}