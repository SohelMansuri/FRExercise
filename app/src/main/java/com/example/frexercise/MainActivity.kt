package com.example.frexercise

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.frexercise.adapters.CustomListAdapter
import com.example.frexercise.databinding.ActivityMainBinding
import com.example.frexercise.models.DataItem
import com.example.frexercise.models.ListItem
import com.example.frexercise.utils.NetworkUtils
import com.example.frexercise.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple activity that displays a list of data inside
 * a recyclerview after fetching and retrieving the data
 * using a view model.
 *
 * A successful retrieval is displayed as data in the recycler view.
 * An error case will be shown otherwise.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var customListAdapter: CustomListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)

        initializeItemsNeededForViews()

        observeViewModelData()
        refreshHiringData(false)
    }

    private fun initializeItemsNeededForViews() {
        customListAdapter = CustomListAdapter(arrayListOf())

        mainActivityBinding.mainActivityRecyclerView.apply {
            adapter = customListAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL)
            )
        }

        mainActivityBinding.mainActivitySwipeRefreshLayout.setOnRefreshListener {
            refreshHiringData(true)
        }
    }

    private fun refreshHiringData(isRefresh: Boolean) {
        if(NetworkUtils.isNetworkAvailable(this)) {
            mainActivityViewModel.fetchHiringData(isRefresh)
        } else {
            handleManualRefreshUI(false)
            Toast.makeText(this, "It looks like you are not connected to the internet.  Please connect and try again with swipe to refresh.", Toast.LENGTH_LONG).show()
        }
    }

    private fun observeViewModelData() {
        mainActivityViewModel.isLoading().observe(this, {
            handleProgressBarUI(it)
        })

        mainActivityViewModel.getListOfItems().observe(this, {
            handleNewListOfItems(it)
        })

        mainActivityViewModel.wasFetchingDataSuccessful().observe(this, {
            handleSuccessFailureOfFetchUI(it)
        })

        mainActivityViewModel.isManualRefreshing().observe(this, {
            handleManualRefreshUI(it)
        })
    }

    private fun handleNewListOfItems(listOfItems: ArrayList<DataItem>) {
        customListAdapter.replaceData(listOfItems)
    }

    private fun handleSuccessFailureOfFetchUI(success: Boolean) {
        mainActivityBinding.mainActivityRecyclerView.visibility = if(success) View.VISIBLE else View.GONE
        mainActivityBinding.mainActivityErrorTextView.visibility = if(success) View.GONE else View.VISIBLE
    }

    private fun handleManualRefreshUI(isManualRefresh: Boolean) {
        mainActivityBinding.mainActivitySwipeRefreshLayout.isRefreshing = isManualRefresh
    }

    private fun handleProgressBarUI(show: Boolean) {
        mainActivityBinding.mainActivityProgressBar.visibility = if(show) View.VISIBLE else View.GONE
    }
}