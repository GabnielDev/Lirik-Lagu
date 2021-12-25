package com.example.liriklagu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.ViewModelProvider
import com.example.liriklagu.databinding.ActivityLyricBinding
import com.example.liriklagu.viewmodel.LyricViewModel

class LyricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLyricBinding
    private lateinit var lyricViewModel: LyricViewModel

    lateinit var id: String

    private var loading: Boolean = false

    companion object {
        const val ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lyric)

        binding = ActivityLyricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setViewModel()
        getData()
    }

    private fun getData() {
        getLoading()
        showLyric()
    }

    private fun getLoading() {
        lyricViewModel.getLoading().observe(this, {
            loading = it
            if (loading) binding.progressCircular.visibility = VISIBLE
            else binding.progressCircular.visibility = GONE
        })
    }

    private fun showLyric() {
        id = intent.getStringExtra("id").toString()
        lyricViewModel.getLyric(id).observe(this, {
            binding.txtTitle.text = it?.songTitle
            binding.txtArtis.text = it?.artist
            binding.txtLirik.text = it?.songLyrics
            Log.e("lyricData", "$it")
        })
    }

    private fun setViewModel() {
        lyricViewModel = ViewModelProvider(this).get(LyricViewModel::class.java)
    }
}