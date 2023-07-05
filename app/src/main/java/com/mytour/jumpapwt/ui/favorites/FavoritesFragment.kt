package com.mytour.jumpapwt.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytour.jumpapwt.databinding.FragmentFavoriteBinding
import com.mytour.jumpapwt.ui.adapter.TourismListAdapter
import com.mytour.jumpapwt.ui.view_model.FavoriteViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private lateinit var rvAdapter: TourismListAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = TourismListAdapter()
        binding?.rvTourism?.layoutManager = LinearLayoutManager(activity)
        binding?.rvTourism?.adapter = rvAdapter

        favoriteViewModel.dataInList.observe(viewLifecycleOwner) { lists ->
            rvAdapter.submitList(lists)
        }

        favoriteViewModel.fetchFavoriteTourism()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}