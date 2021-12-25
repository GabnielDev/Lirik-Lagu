package com.example.liriklagu.network

import com.example.liriklagu.data.Lyric
import com.example.liriklagu.utils.Constants.HOT
import com.example.liriklagu.utils.Constants.LYRIC
import com.example.liriklagu.utils.Constants.SEARCH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET(HOT)
    suspend fun getHot(
    ): Response<com.example.liriklagu.data.Response>

    @GET(LYRIC)
    suspend fun getLyric(
        @Path("id") id: String
    ): Response<Lyric>

    @GET(SEARCH)
    suspend fun getSearch(
        @Query("q") q: String
    ): Response<com.example.liriklagu.data.Response>

}