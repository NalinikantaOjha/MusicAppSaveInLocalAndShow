package com.masai.ituneapisaveindb.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.masai.ituneapisaveindb.local.Dao
import com.masai.ituneapisaveindb.local.MusicEntity
import com.masai.ituneapisaveindb.remote.Response
import com.masai.ituneapisaveindb.remote.api.ApiService

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DataRepository (private val userApi: ApiService,private val dao: Dao) {
    private val userLiveData = MutableLiveData<Response>()

    val user: LiveData<Response>
        get() = userLiveData

    suspend fun getData(actor: String) {

        val result = userApi.getData(actor)
        if (result?.body() != null) {
            userLiveData.postValue(result.body())
            Log.d("getdata", "response")
        }
    }
    fun insertDataInDb(itunesDbTable: MusicEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAllDataFromDB()
            dao.addDataFromAPI(itunesDbTable)
        }
    }



    fun getDataFromDb():LiveData<List<MusicEntity>>{
        return dao.getResponseFromDb()
    }
    fun deleteDataFromDb(){
        dao.deleteAllDataFromDB()
    }

}