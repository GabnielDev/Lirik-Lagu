package com.example.liriklagu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liriklagu.data.Data
import com.example.liriklagu.network.ApiClient
import kotlinx.coroutines.launch

class LyricViewModel: ViewModel() {

    private val loading = MutableLiveData<Boolean>()

    fun getLyric(id: String): LiveData<Data?> {
        val lyric = MutableLiveData<Data?>()
        loading.value = true
        viewModelScope.launch {
            try {
                val data = ApiClient.getClient().getLyric(id)
                if (data.isSuccessful) {
                    lyric.value = data.body()?.data
                } else {

                }
                loading.value = false
            } catch (t: Throwable) {

            }
            loading.value = false
        }
        return lyric
    }

    fun getLoading(): LiveData<Boolean> = loading

}