package com.masai.ituneapisaveindb.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.masai.ituneapisaveindb.R
import com.masai.ituneapisaveindb.local.Dao
import com.masai.ituneapisaveindb.local.MusicEntity
import com.masai.ituneapisaveindb.local.Musicdatabase
import com.masai.ituneapisaveindb.remote.Result
import com.masai.ituneapisaveindb.remote.api.ApiService
import com.masai.ituneapisaveindb.remote.api.Network
import com.masai.ituneapisaveindb.repository.DataRepository
import com.masai.ituneapisaveindb.ui.adapter.Adapter
import com.masai.ituneapisaveindb.ui.adapter.OnClick
import com.masai.ituneapisaveindb.viewmodel.MainViewModel
import com.masai.ituneapisaveindb.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),OnClick {
    lateinit var adapter2: Adapter
    lateinit var viewModel2: MainViewModel
    lateinit var repository: DataRepository
    private var List = mutableListOf<Result>()
    private var miusicList= mutableListOf<MusicEntity>()
    lateinit var dao: Dao

    var page: Int = 1
    var manager: LinearLayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = LinearLayoutManager(this)
        dao=Musicdatabase.getMusicDatabase(this).getMusic()

      val userApi = Network.getInstance().create(ApiService::class.java)
        repository = DataRepository(userApi,dao)
        val wishlistFactory = ViewModelFactory(repository)
        viewModel2 = ViewModelProviders.of(this, wishlistFactory).get(MainViewModel::class.java)
       etEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                etEditText.clearFocus()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadApi(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        setRecycle()
        viewModel2.getData().observe(this, Observer {
miusicList.clear()
            miusicList.addAll(it)
           adapter2.notifyDataSetChanged()
        })
    }
    private fun insertDataToDb(resultModels: List<Result>) {
        viewModel2.deleteDataFromDb()
        resultModels.forEach {
            val itunesDb = MusicEntity(it.artistName, it.artworkUrl100)
            viewModel2.insertDataInDb(itunesDb)
        }
    }
    fun loadApi(query:String){
        viewModel2.CreateTransation(query)
        viewModel2.user.observe(this, Observer {
            List.clear()
            if (it!=null){
                CoroutineScope(Dispatchers.IO).launch {
                    insertDataToDb(it.results)
                }
            }

            List.addAll(it.results as MutableList<Result>)
     // setRecycle()
        })
    }
    fun setRecycle() {
        adapter2 = Adapter(miusicList, this, this)
        recycleAll.adapter = adapter2
        recycleAll.layoutManager = GridLayoutManager(this, 2)
    }

    override fun setOnClick(result: Result) {
    }
}