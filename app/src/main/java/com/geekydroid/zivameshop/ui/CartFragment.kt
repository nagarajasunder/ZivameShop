package com.geekydroid.zivameshop.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geekydroid.zivameshop.R
import com.geekydroid.zivameshop.adapters.CartAdapter
import com.geekydroid.zivameshop.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var fragmentView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CartAdapter
    private val ViewModel: CartViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentView = view
        setUI()

        ViewModel.getOrders().observe(viewLifecycleOwner) { result ->
            result?.let {
                adapter.submitList(it)
            }

        }


    }

    private fun setUI() {

        recyclerView = fragmentView.findViewById(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = CartAdapter();
        recyclerView.adapter = adapter

    }

}