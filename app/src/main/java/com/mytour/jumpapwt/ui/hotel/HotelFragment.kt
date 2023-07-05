package com.mytour.jumpapwt.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytour.jumpapwt.databinding.FragmentHotelBinding
import com.mytour.jumpapwt.ui.adapter.TourismListAdapter
import com.mytour.jumpapwt.ui.view_model.FetchViewModel

class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding

    private lateinit var rvAdapter: TourismListAdapter
    private val fetchViewModel: FetchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHotelBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvAdapter = TourismListAdapter()
        binding?.rvTourism?.layoutManager = LinearLayoutManager(activity)
        binding?.rvTourism?.adapter = rvAdapter

        fetchViewModel.dataInList.observe(viewLifecycleOwner) { lists ->
            rvAdapter.submitList(lists)
        }
        fetchViewModel.fetchTourismData("lodgingData")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}