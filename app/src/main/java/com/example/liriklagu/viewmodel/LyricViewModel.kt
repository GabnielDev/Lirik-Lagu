package com.example.liriklagu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liriklagu.data.Data
import com.example.liriklagu.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LyricViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val loading = MutableLiveData<Boolean>()

    fun getLyric(id: String): LiveData<Data?> {
        val lyric = MutableLiveData<Data?>()
        loading.value = true
        viewModelScope.launch {
            try {
                repository.getLyric(id).let { response ->
                    val data = response.body()
                    lyric.value = data?.data
                    loading.value = false
                }
            } catch (t: Throwable) {

            }
        }
        return lyric
    }

    fun getLoading(): LiveData<Boolean> = loading

}