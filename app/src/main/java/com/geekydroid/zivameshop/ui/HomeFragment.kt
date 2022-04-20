package com.geekydroid.zivameshop.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.geekydroid.zivameshop.R
import com.geekydroid.zivameshop.adapters.ProductsAdapter
import com.geekydroid.zivameshop.entity.Product
import com.geekydroid.zivameshop.utils.AddToCartListener
import com.geekydroid.zivameshop.utils.Resource
import com.geekydroid.zivameshop.viewmodel.HomeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "HomeFragment"

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), AddToCartListener {

    private lateinit var fragmentView: View
    private val viewModel: HomeFragmentViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductsAdapter
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentView = view
        setUI(fragmentView)
        setHasOptionsMenu(true)

        viewModel.getProducts().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    response.data?.let {
                        adapter.submitList(it.products)
                        hideProgressBar()
                    }
                }
                is Resource.Error -> Log.d(TAG, "onViewCreated: Error ${response.message}")
            }
        }

    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }


    private fun setUI(fragmentView: View) {
        recyclerView = fragmentView.findViewById(R.id.recycler_view)
        progressBar = fragmentView.findViewById(R.id.progress_bar)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        adapter = ProductsAdapter(this)
        recyclerView.adapter = adapter

    }

    override fun addToCart(product: Product) {
        viewModel.insertOrder(product)
        showToast(product.name + " added to cart!")
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cart) {
            navigateToCartScreen()
        }
        return true
    }

    private fun navigateToCartScreen() {

        val action = HomeFragmentDirections.actionHomeFragmentToCartFragment()
        findNavController().navigate(action)
    }
}