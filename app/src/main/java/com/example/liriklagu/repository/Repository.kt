package com.example.liriklagu.repository

import com.example.liriklagu.di.AppModule
import javax.inject.Inject

class Repository @Inject constructor() {

    suspend fun getHot() = AppModule.getClient().getHot()

    suspend fun getSearch(q: String) = AppModule.getClient().getSearch(q)

    suspend fun getLyric(id: String) = AppModule.getClient().getLyric(id)

}