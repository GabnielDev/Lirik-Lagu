package com.example.liriklagu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liriklagu.adapter.HotAdapter
import com.example.liriklagu.data.DataItem
import com.example.liriklagu.databinding.ActivityMainBinding
import com.example.liriklagu.viewmodel.MainViewModel
import java.lang.Exception

class MainActivity : AppCompatActivity(), HotAdapter.OnItemClickCallback {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var hotAdapter: HotAdapter

    private var loading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()
        setViewModel()
        getData()


    }

    private fun getData() {
        getLoading()
        showData()
        searchSong()
    }

    private fun setAdapter() {
        hotAdapter = HotAdapter(ArrayList(), this)
        binding.rvHot.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = hotAdapter
        }
    }

    private fun setViewModel() {
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun getLoading() {
        mainViewModel.getLoading().observe(this, {
            loading = it
            if (loading) binding.progressCircular.visibility = VISIBLE
            else binding.progressCircular.visibility = GONE
        })
    }



    private fun showData() {
        mainViewModel.getHot().observe(this, {
            it?.let { it1 -> hotAdapter.setData(it1) }
            Log.e("hotData", "$it")
        })
    }

    private fun searchSong() {
        binding.edtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                try {
                    mainViewModel.getSearch(p0.toString()).observe(this@MainActivity, {
                        hotAdapter.setData(it!!)
                        Log.e("searchData", "$it")
                    })
                } catch (e: Exception) {
                    e.message
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                return false
            }

        })
    }

    override fun onItemClick(data: DataItem?) {
        startActivity(
            Intent(this, LyricActivity::class.java)
                .putExtra(LyricActivity.ID, data?.songId)
        )
    }


}