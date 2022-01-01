package com.example.liriklagu.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import com.example.liriklagu.R
import com.example.liriklagu.databinding.ActivityLyricBinding
import com.example.liriklagu.viewmodel.LyricViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LyricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLyricBinding
    private val lyricViewModel: LyricViewModel by viewModels()

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

}