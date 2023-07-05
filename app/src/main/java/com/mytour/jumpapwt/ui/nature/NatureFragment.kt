package com.mytour.jumpapwt.ui.nature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mytour.jumpapwt.databinding.FragmentNatureBinding
import com.mytour.jumpapwt.ui.adapter.TourismListAdapter
import com.mytour.jumpapwt.ui.view_model.FetchViewModel

class NatureFragment : Fragment() {
    private var _binding: FragmentNatureBinding? = null
    private val binding get() = _binding

    private lateinit var rvAdapter: TourismListAdapter
    private val fetchViewModel: FetchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNatureBinding.inflate(inflater, container, false)
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
        fetchViewModel.fetchTourismData("natureData")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}