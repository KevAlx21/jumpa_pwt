package com.mytour.jumpapwt.data.repository

import android.util.Log
import com.google.firebase.database.*
import com.mytour.jumpapwt.data.item.TourismItem

class FetchRepository {
    private val fbReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun fetchTourismData(collectionsName : String, callback: (List<TourismItem>) -> Unit){
        fbReference.child(collectionsName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val listData = mutableListOf<TourismItem>()
                for (snapshots in dataSnapshot.children) {
                    val dataItem = snapshots.getValue(TourismItem::class.java)
                    dataItem?.let { listData.add(it) }
                }
                callback(listData)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Couldn't get data: ${error.message}")
            }
        })
    }

    companion object {
        const val TAG = "FetchRepository"
    }
}