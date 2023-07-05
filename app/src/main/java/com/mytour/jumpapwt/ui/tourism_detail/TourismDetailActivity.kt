package com.mytour.jumpapwt.ui.tourism_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mytour.jumpapwt.R
import com.mytour.jumpapwt.data.FavoriteStateHelper
import com.mytour.jumpapwt.data.item.TourismItem
import com.mytour.jumpapwt.databinding.ActivityTourismDetailBinding
import com.mytour.jumpapwt.ui.maps.MapsActivity
import com.mytour.jumpapwt.ui.view_model.FavoriteViewModel

class TourismDetailActivity : AppCompatActivity() {
    private lateinit var bindingActivity: ActivityTourismDetailBinding
    private lateinit var favoriteStateHelper: FavoriteStateHelper

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var isFavorited = false
    private var favoriteStateChanged = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tourismDetailData = intent.getSerializableExtra(TOURISM_DETAIL) as TourismItem

        supportActionBar?.title = tourismDetailData.tourismName

        bindingActivity = ActivityTourismDetailBinding.inflate(layoutInflater)
        setContentView(bindingActivity.root)


        favoriteStateHelper = FavoriteStateHelper(this)

        isFavorited = favoriteStateHelper.isFavorite(tourismDetailData.tourismName)
        updateFavoriteIcon()

        bindingActivity.let {
            Glide.with(this)
                .load(tourismDetailData.tourismPhoto)
                .into(it.ivImageHolder)
            it.tvNameDetail.text = tourismDetailData.tourismName
            it.tvRatingDetail.text = tourismDetailData.tourismRating
            it.tvDescDetail.text = tourismDetailData.tourismDescription
            it.tvPhoneDetail.text = tourismDetailData.tourismCall
            it.tvOpenDetail.text = tourismDetailData.tourismOpenHour
            it.tvAddressDetail.text = tourismDetailData.tourismAddress
        }

        bindingActivity.btnToLocation.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java).apply {
                putExtra(TOURISM_LAT, tourismDetailData.tourismLat)
                putExtra(TOURISM_LON, tourismDetailData.tourismLon)
                putExtra(TOURISM_NAME, tourismDetailData.tourismName)
            }
            startActivity(intent)
        }

        bindingActivity.fabAddFavorite.setOnClickListener {
            if (isFavorited) {
                isFavorited = false
                favoriteStateHelper.setFavorite(tourismDetailData.tourismName, false)
                favoriteViewModel.deleteFavoriteTourism(tourismDetailData)
                Toast.makeText(this, "Objek Wisata Telah Dihapus Dari Favorit", Toast.LENGTH_SHORT)
                    .show()
            } else {
                isFavorited = true
                favoriteStateHelper.setFavorite(tourismDetailData.tourismName, true)
                favoriteViewModel.addFavoriteTourism(tourismDetailData)
                Toast.makeText(
                    this,
                    "Objek Wisata Telah Ditambahkan Ke Favorit",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            updateFavoriteIcon()
            favoriteStateChanged = true

        }
    }

    override fun onPause() {
        super.onPause()

        if (favoriteStateChanged) {
            val intent = Intent()
            intent.putExtra(FAVORITE_STATUS_CHANGED, isFavorited)
            setResult(Activity.RESULT_OK, intent)
        }
    }

    override fun onResume() {
        super.onResume()
        favoriteStateChanged = false
    }

    private fun updateFavoriteIcon() {
        if (isFavorited) {
            bindingActivity.fabAddFavorite.setImageResource(R.drawable.ic_favorite_filled)
        } else {
            bindingActivity.fabAddFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }


    companion object {
        const val FAVORITE_STATUS_CHANGED = "favorite_status_changed"
        const val TOURISM_DETAIL = "tourism_detail"
        const val TOURISM_LAT = "tourismLat"
        const val TOURISM_LON = "tourismLon"
        const val TOURISM_NAME = "tourismName"
    }

}