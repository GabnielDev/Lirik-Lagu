package com.example.liriklagu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liriklagu.data.DataItem
import com.example.liriklagu.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val loading = MutableLiveData<Boolean>()
    private val status = MutableLiveData<Int>()
    private val message = MutableLiveData<String>()

    fun getHot(): LiveData<ArrayList<DataItem?>?> {
        val hot = MutableLiveData<ArrayList<DataItem?>?>()
        loading.value = true
        viewModelScope.launch {
            repository.getHot().let { response ->
                try {
                    val data = response.body()
                    hot.value = data?.data
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
        }
        return hot
    }

    fun getSearch(q: String): LiveData<ArrayList<DataItem?>?> {
        val searchSong = MutableLiveData<ArrayList<DataItem?>?>()
        loading.value = true
        viewModelScope.launch {
            repository.getSearch(q).let { response ->
                try {
                    val data = response.body()
                    searchSong.value = data?.data
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
        }
        return searchSong
    }


    fun getLoading(): LiveData<Boolean> = loading
    fun getStatus(): LiveData<Int> = status
    fun getMessage(): LiveData<String> = message

}