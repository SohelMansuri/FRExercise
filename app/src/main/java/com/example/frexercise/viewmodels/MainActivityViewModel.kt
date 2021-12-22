package com.example.frexercise.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.frexercise.models.ListItem
import com.example.frexercise.networking.HiringService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * A view model designed to provide emissions for views
 * (activities and fragments to subscribe to) in order to observe
 * communication requests and responses with the server
 * using the hiring service (constructor injection for Hilt & testing ease).
 */
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    val hiringService: HiringService
) : ViewModel() {

    private var loading: MutableLiveData<Boolean> = MutableLiveData(false)
    private var successFetchingData: MutableLiveData<Boolean> = MutableLiveData(true)
    private var listItems: MutableLiveData<ArrayList<ListItem>> = MutableLiveData(arrayListOf())

    fun isLoading(): LiveData<Boolean> {
        return loading
    }

    fun getListOfItems(): LiveData<ArrayList<ListItem>> {
        return listItems
    }

    fun wasFetchingDataSuccessful(): LiveData<Boolean> {
        return successFetchingData
    }

    fun fetchHiringData() {
        loading.value = true
        hiringService.getHiringData().enqueue(object : Callback<List<ListItem>> {
            override fun onResponse(
                call: Call<List<ListItem>>,
                response: Response<List<ListItem>>
            ) {
                loading.value = false
                successFetchingData.value = true
                val listOfItemsFetched = arrayListOf<ListItem>()
                response.body()?.let { fetchedList ->
                    val sortedData = ListItem.filterListItems(fetchedList).sorted()
                    listOfItemsFetched.addAll(sortedData)
                }
                listItems.value = listOfItemsFetched
            }

            override fun onFailure(call: Call<List<ListItem>>, t: Throwable) {
                loading.value = false
                successFetchingData.value = false
            }
        })
    }
}