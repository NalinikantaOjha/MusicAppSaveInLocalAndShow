package com.masai.ituneapisaveindb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.masai.ituneapisaveindb.local.MusicEntity
import com.masai.ituneapisaveindb.remote.Response
import com.masai.ituneapisaveindb.repository.DataRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    fun CreateTransation(string: String) {
      //  Log.d("nalini", int.toString())
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.getData(string)
        }
    }

    val user: LiveData<Response>
        get() = dataRepository.user
    fun insertDataInDb(itunesDbTable: MusicEntity) {
        dataRepository.insertDataInDb(itunesDbTable)
    }

    fun deleteDataFromDb() {
        dataRepository.deleteDataFromDb()
    }

    fun getDataFromDb() {
        dataRepository.getDataFromDb()
    }
    fun getData():LiveData<List<MusicEntity>>{
        return dataRepository.getDataFromDb()
    }
}
