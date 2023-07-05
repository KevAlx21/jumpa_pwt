package com.mytour.jumpapwt.ui.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mytour.jumpapwt.data.item.TourismItem
import com.mytour.jumpapwt.databinding.TourismListItemBinding
import com.mytour.jumpapwt.ui.tourism_detail.TourismDetailActivity

class TourismListAdapter :
    ListAdapter<TourismItem, TourismListAdapter.TourismViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TourismViewHolder {
        val tourismBinding =
            TourismListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TourismViewHolder(tourismBinding)
    }

    override fun onBindViewHolder(holder: TourismViewHolder, position: Int) {
        val groceriesListItem = getItem(position)
        holder.bind(groceriesListItem)
    }

    inner class TourismViewHolder(private val binding: TourismListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tourismItem: TourismItem) {
            Glide.with(itemView.context)
                .load(tourismItem.tourismPhoto)
                .into(binding.ivTourismPhoto)
            binding.tvTourismName.text = tourismItem.tourismName
            binding.tvTourismRating.text = tourismItem.tourismRating

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, TourismDetailActivity::class.java)
                intent.putExtra(TourismDetailActivity.TOURISM_DETAIL, tourismItem)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    itemView.context as Activity,
                    Pair.create(binding.ivTourismPhoto, "tourism_photo"),
                    Pair.create(binding.tvTourismName, "tourism_name"),
                    Pair.create(binding.tvTourismRating, "tourism_rating")
                )
                itemView.context.startActivity(intent, options.toBundle())
            }
        }

    }

    companion object {

        val DIFF_CALLBACK: DiffUtil.ItemCallback<TourismItem> =
            object : DiffUtil.ItemCallback<TourismItem>() {
                override fun areItemsTheSame(
                    oldItem: TourismItem,
                    newItem: TourismItem
                ): Boolean {
                    return oldItem.tourismName == newItem.tourismName
                }

                override fun areContentsTheSame(
                    oldItem: TourismItem,
                    newItem: TourismItem
                ): Boolean {
                    return oldItem == newItem
                }
            }

    }
}