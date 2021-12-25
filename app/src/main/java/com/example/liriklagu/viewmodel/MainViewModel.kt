package com.example.liriklagu.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liriklagu.data.DataItem
import com.example.liriklagu.network.ApiClient
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val status = MutableLiveData<Int>()
    private val message = MutableLiveData<String>()

    fun getHot(): LiveData<ArrayList<DataItem?>?> {
        val hot = MutableLiveData<ArrayList<DataItem?>?>()
        loading.value = true
        viewModelScope.launch {
            try {
                val data = ApiClient.getClient().getHot()
                if (data.isSuccessful) {
                    hot.value = data.body()?.data
                } else {
                    status.value = data.code()
                }
                loading.value = false
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> message.value = t.message.toString()
                    is HttpException -> message.value = t.message().toString()
                    else -> message.value = "Unknow Error"
                }
                loading.value = false
            }
        }
        return hot
    }

    fun getSearch(q: String): LiveData<ArrayList<DataItem?>?> {
        val searchSong = MutableLiveData<ArrayList<DataItem?>?>()
        loading.value = true
        viewModelScope.launch {
            try {
                val data = ApiClient.getClient().getSearch(q)
                if (data.isSuccessful) {
                    searchSong.value = data.body()?.data
                } else {
                    Log.e(TAG, "GAGAL")
                }
                loading.value = false
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> message.value = t.message.toString()
                    is HttpException -> message.value = t.message().toString()
                    else -> message.value = "Unknow Error"
                }
            }
            loading.value = false
        }
        return searchSong
    }

    fun getLoading(): LiveData<Boolean> = loading
    fun getStatus(): LiveData<Int> = status
    fun getMessage(): LiveData<String> = message

}